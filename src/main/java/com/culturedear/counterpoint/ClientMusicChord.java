package com.culturedear.counterpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jamesweaver on 10/13/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientMusicChord {
  private String root;
  private String chordType;
  private String bassNote;
  private int inversion;
  private boolean isMajor;
  private boolean isMinor;
  private String name;

  public ClientMusicChord() {
  }

  public String getRoot() {
    return root;
  }

  public String getChordType() {
    return chordType;
  }

  public String getBassNote() {
    return bassNote;
  }

  public int getInversion() {
    return inversion;
  }

  public boolean isMajor() {
    return isMajor;
  }

  public boolean isMinor() {
    return isMinor;
  }

  public String getName() {
    return name;
  }
}
