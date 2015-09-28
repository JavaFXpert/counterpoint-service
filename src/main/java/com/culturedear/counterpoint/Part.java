package com.culturedear.counterpoint;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamesweaver on 9/26/15.
 * TODO: Investigate how to prevent namespaces like zdef1193312741:id= from appearing
 */
public class Part {
  @JacksonXmlProperty(localName="id", isAttribute=true)
  private String id;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JsonProperty("measure")
  private List<Measure> measures = new ArrayList<>();

  public Part(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<Measure> getMeasures() {
    return measures;
  }

  public void setMeasures(List<Measure> measures) {
    this.measures = measures;
  }
}
