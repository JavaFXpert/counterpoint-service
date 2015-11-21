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
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author James Weaver
 */
public class Measure {
    @JacksonXmlProperty(localName = "number", isAttribute = true)
    private String measure;

    @JacksonXmlProperty(localName = "attributes")
    private MeasureAttributes measureAttributes;

    public Measure(String measure) {
        this.measure = measure;
    }

    public MeasureAttributes getMeasureAttributes() {
        return measureAttributes;
    }

    public void setMeasureAttributes(MeasureAttributes measureAttributes) {
        this.measureAttributes = measureAttributes;
    }

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("note")
    private List<Note> notes = new ArrayList<>();

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

}
