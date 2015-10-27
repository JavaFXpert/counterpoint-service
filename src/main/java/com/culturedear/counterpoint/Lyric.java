/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.culturedear.counterpoint;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by jamesweaver on 10/13/15.
 */
public class Lyric {
  public String text;

  @JacksonXmlProperty(localName="placement", isAttribute=true)
  public String placement = "above";

  public Lyric(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return "Lyric{" +
        "text='" + text + '\'' +
        '}';
  }
}
