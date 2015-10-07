// Noteflight Client Library 2.0.0
// Copyright (C) Noteflight LLC.  All rights reserved.
// Unauthorized copying of this material by any medium is strictly prohibited.
//

var NFClient = {};

(function() {
  'use strict';

  var VERSION = "2.0.0";
  
  /*
   * NFClient.init(callback)
   *
   * Call NFClient.init() to initialize the API.  The passed-in function will be
   * invoked as soon as the API is initialized. The version number of the API
   * will be passed to the callback after initialization.
   *
   */
  NFClient.init = function(callback) {
    // Currently this is only a hook point for dynamic loading of other libraries,
    // should they be required in later versions.
    
    if (callback) {
      callback({
        version: VERSION
      });
    }
  }

  /*
   * var view = new NFClient.ScoreView(elementId, scoreId, options);
   *    (or)
   * var view = new NFClient.ScoreView(iFrameElement);
   *
   * An NFClient.ScoreView object creates or wraps embedded instances of the Noteflight score viewer,
   * which are implemented as <iframe> elements in the page with a prescribed URL.  The constructor
   * call can be called in two ways: 1) with a string element ID , or 2) with an existing iframe
   * DOM element. In the first cases, accompanying scoreId and options arguments are also provided to the constructor
   * that govern the generation of the <iframe>.
   *
   * Upon establishment of API communication with the frame, an "editorReady" event will ultimately be
   * dispatched from the embedded document.
   */
  NFClient.ScoreView = function(arg0) {
    var PATH_PREFIX = "/embed/";
    
    var self = this;
    
    var iframe, urlPrefix, id;

    self.getSubstringIndex = function(str, substring, n) {
      var times = 0, index = null;
      while (times < n && index !== -1) {
          index = str.indexOf(substring, index+1);
          times++;
      }

      return index;
    }
    
    if (typeof(arg0) == "string") {
      // We've been given the ID of an element in the DOM, which will be replaced by
      // a brand spankin' new iframe element configured as per the options.
      
      id = arg0;
      var scoreId = arguments[1];
      var options = arguments[2];

      // Create the iframe element hosting this ScoreView
      var el = document.getElementById(id);
      var iframe = document.createElement('iframe');

      // Determine its origin
      var protocol = options.protocol || 'http';
      var host = options.host || 'www.noteflight.com';
      urlPrefix = protocol + '://' + host;

      var staticViewerLocation = options.staticViewerLocation;

      // Pass through viewer parameters as query arguments in the iframe URL
      var params = '';
      var componentName = '';

      // It iterates the object and creates a string with the following structure:
      // property1=value & property2.subproperty1=value & property2.subproperty2=value ...
      self.iterateObject = function(obj, componentName) {
        for (var property in obj) {
          if (obj.hasOwnProperty(property)) {
            if (typeof obj[property] == "object") {
              self.iterateObject(obj[property], componentName + property + '.');
            } else {
              if (params) {
                params += '&';
              }
              
              params += encodeURIComponent(componentName + property) + '=' + encodeURIComponent(obj[property]);
            }
          }
        }
      }

      // It creates the params string
      if (options.viewParams) {
        self.iterateObject(options.viewParams, '');
      }

      var url = "";
      if (staticViewerLocation) {
        url = staticViewerLocation;
      } else {
        url = urlPrefix + PATH_PREFIX + encodeURIComponent(scoreId);
      }

      if (params) {
        url += '?' + params;
      }

      // Configure the iframe
      iframe.setAttribute('id', id);
      iframe.setAttribute('src', url);
      iframe.setAttribute('width', options.width || 800);
      iframe.setAttribute('height', options.height || 600);
      iframe.style.border = '1px solid #f0f0f0';

      // Now jam it into the DOM and we're done!
      el.parentNode.insertBefore(iframe, el);
      el.parentNode.removeChild(el);
    }
    else {
      // We have been provided with an already-existing iframe, so just figure out its
      // origin and connect to it.
      
      iframe = arg0;

      id = iframe.getAttribute('id');
      var src = iframe.getAttribute('src')
      urlPrefix = src.substring(0, self.getSubstringIndex(src, "/", 3));
    }
    
    var initInterval;     // handle to interval timer for initialization
    var invocationId = 0; // callback result ID counter
    var invocationPromises = {};    // table of promises awaiting notification
    var dispatchers = {}; // table of event handler arrays by event Id
    
    // Set up the listener for postMessage() activity in the iframe
    self.handleMessage = function(e) {
      // If we get an OK-looking event from the origin and iframe we expect, then go ahead and handle it.
      if (e.origin == urlPrefix && e.source == iframe.contentWindow && typeof(e.data) == 'string') {
        var data = {};
        try {
            data = JSON.parse(e.data);
        } catch (err) {
        }

        if (data.kind == "initialized") {
          // Yay, we're initialized! Stop trying to do that any more.
          if (initInterval) {
            clearInterval(initInterval);
            initInterval = null;
          }
        }
        else if (data.kind == "invokeResult") {
          // Look for an invocation callback that is expecting this return value.
          if (invocationPromises[data.invocationId]) {
            invocationPromises[data.invocationId].success(data.result);
            delete invocationPromises[data.invocationId];
          }
        }
        else if (data.kind == "noteflightEvent") {
          // Stamp this event with the associated ID and target of this client, if we have one.
          if (data.event) {
            data.event.embedId = id;
            data.event.target = self;
          
            // If this event is "editorReady", pick up the method names to create local stubs
            if (data.event.type == "editorReady" && data.event.methodNames) {
              for (var i = 0; i < data.event.methodNames.length; i++) {
                self.createMethodStub(data.event.methodNames[i]);
              }
            }
            
            // Forward an API event to any event handler that cares about it.
            self.dispatchEvent(data.event.type, data.event);
            self.dispatchEvent('any', data.event);
          }
        }
      }
    };
    
    // Send a message to our iframe
    self.dispatchMessage = function(data) {
      if (typeof(JSON) !== 'undefined') {
        iframe.contentWindow.postMessage(JSON.stringify(data), urlPrefix);
      }
    };

    if (window.addEventListener) {
      window.addEventListener('message', self.handleMessage);
    }
    else if (window.attachEvent) {
      window.attachEvent('onmessage', self.handleMessage);
    }

    // Set up a repeating call that will initialize the viewer eventually
    if (typeof(JSON) !== 'undefined') {
      initInterval = setInterval(function() {
        self.dispatchMessage({
          kind: 'initializeViewer'
        });
      }, 100);
    }
    else {
      throw new Error('Noteflight API requires JSON');
    }

    // Invoke an API client method on the viewer, where methodName is the name of the method
    // and args is an array of arguments. A Promise object is returned that can accept done(callback)
    // calls.
    //
    self.applyMethod = function(methodName, args) {
      var message = {
        kind: 'invokeMethod',
        methodName: methodName,
        args: args
      };
      
      var promise = {
        doneCallbacks: [],
        done: function(callback) {
          this.doneCallbacks.push(callback);
          return this;
        },
        success: function(result) {
          for (var i = 0; i < this.doneCallbacks.length; i++) {
            this.doneCallbacks[i](result);
          }
        }
      };
      
      var iid = ++invocationId;
      invocationPromises[iid] = promise;
      message.invocationId = iid;
      
      self.dispatchMessage(message);
      
      return promise;
    }
    
    // Create a stub method on this ScoreView object that can be invoked as a proxy
    // for the invocation of the corresponding embedded document method, returning a promise.
    self.createMethodStub = function(methodName) {
      self[methodName] = function() {
        return self.applyMethod(methodName, Array.prototype.slice.call(arguments, 0));
      };
    }

    // Add an event handler. The special eventType value "any" will receive callbacks
    // on all events.
    self.addEventListener = function(eventType, handler) {
      if (!dispatchers[eventType]) {
        dispatchers[eventType] = [];
      }
      dispatchers[eventType].push(handler);
    }
    
    // Remove an event handler.
    self.removeEventListener = function(eventType, handler) {
      if (dispatchers[eventType]) {
        for (var i = 0; i < dispatchers[eventType].length; i++) {
          if (dispatchers[eventType][i] === handler) {
            dispatchers[eventType].splice(i, 1);
            return true;
          }
        }
      }
      return false;
    }
    
    // Dispatch an event to a specific event type.
    self.dispatchEvent = function(eventType, event) {
      if (dispatchers[eventType]) {
        for (var i = 0; i < dispatchers[eventType].length; i++) {
          dispatchers[eventType][i](event);
        }
      }
    }
  };
}());
