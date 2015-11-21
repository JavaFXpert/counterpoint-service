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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author James Weaver
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientMusicChord {
    private String root;
    private String chordType;
    private String bassNote;
    private int inversion;
    private boolean isMajor;
    private boolean isMinor;
    private String name;

    public ClientMusicChord() {
    }

    public String getRoot() {
        return root;
    }

    public String getChordType() {
        return chordType;
    }

    public String getBassNote() {
        return bassNote;
    }

    public int getInversion() {
        return inversion;
    }

    public boolean isMajor() {
        return isMajor;
    }

    public boolean isMinor() {
        return isMinor;
    }

    public String getName() {
        return name;
    }
}
