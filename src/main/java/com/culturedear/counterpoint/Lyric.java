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
