package com.culturedear.counterpoint;

/**
 * Created by jamesweaver on 9/25/15.
 * TODO: Investigate way to not have "alter" written to XML stream if it has a value of 0
 */
public class Pitch {
  public String step;

  public int alter;

  public int octave;

  public Pitch(String step, int alter, int octave) {
    this.step = step;
    this.alter = alter;
    this.octave = octave;
  }

  @Override
  public String toString() {
    return "Pitch {" +
        "step='" + step + '\'' +
        ", alter=" + alter +
        ", octave=" + octave +
        '}';
  }
}
