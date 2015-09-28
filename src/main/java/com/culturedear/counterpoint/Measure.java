package com.culturedear.counterpoint;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamesweaver on 9/26/15.
 */
public class Measure {
  @JacksonXmlProperty(localName="number", isAttribute=true)
  private String measure;

  public Measure(String measure) {
    this.measure = measure;
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
