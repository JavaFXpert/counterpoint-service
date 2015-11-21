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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author James Weaver
 */
@JsonRootName("score-partwise")
public class ScorePartwise implements Serializable {

    @JacksonXmlProperty(localName = "version", isAttribute = true)
    private final String version = "3.0";

    @JacksonXmlElementWrapper(localName = "part-list")
    @JsonProperty("score-part")
    private List<ScorePart> scoreParts = new ArrayList<>();

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("part")
    private List<Part> parts = new ArrayList<>();

    public String getVersion() {
        return version;
    }

    public List<ScorePart> getScoreParts() {
        return scoreParts;
    }

    public void setScoreParts(List<ScorePart> scoreParts) {
        this.scoreParts = scoreParts;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
