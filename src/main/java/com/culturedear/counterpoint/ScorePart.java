package com.culturedear.counterpoint;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

/**
 * Created by jamesweaver on 9/26/15.
 */
public class ScorePart implements Serializable {
  @JacksonXmlProperty(localName="id", isAttribute=true)
  private String id;

  @JsonProperty("part-name")
  private String partName;

  public ScorePart(String partName, String id) {
    this.partName = partName;
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPartName() {
    return partName;
  }

  public void setPartName(String partName) {
    this.partName = partName;
  }
}
