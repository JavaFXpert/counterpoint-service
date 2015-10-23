package com.culturedear.counterpoint;

/**
 * Created by jamesweaver on 9/25/15.
 */
public class MeasureAttributes {
  public Clef clef;

  public MeasureAttributes(Clef clef) {
    this.clef = clef;
  }

  public Clef getClef() {
    return clef;
  }

  public void setClef(Clef clef) {
    this.clef = clef;
  }
}
