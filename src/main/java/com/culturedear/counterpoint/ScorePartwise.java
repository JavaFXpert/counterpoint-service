/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author jamesweaver
 */
@JsonRootName("score-partwise")
public class ScorePartwise implements Serializable {

  @JacksonXmlProperty(localName="version", isAttribute=true)
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
