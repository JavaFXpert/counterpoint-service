package com.culturedear.counterpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jamesweaver on 10/13/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientMusicChord {
  private String name;
  private String root;
  private int inversion;
  private boolean isMajor;
  private boolean isMinor;

  public ClientMusicChord() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRoot() {
    return root;
  }

  public void setRoot(String root) {
    this.root = root;
  }

  public int getInversion() {
    return inversion;
  }

  public void setInversion(int inversion) {
    this.inversion = inversion;
  }

  public boolean isMajor() {
    return isMajor;
  }

  public void setIsMajor(boolean isMajor) {
    this.isMajor = isMajor;
  }

  public boolean isMinor() {
    return isMinor;
  }

  public void setIsMinor(boolean isMinor) {
    this.isMinor = isMinor;
  }
}
