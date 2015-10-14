package com.culturedear.counterpoint;

/**
 * Created by jamesweaver on 10/13/15.
 */
public class Lyric {
  public String text;

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
