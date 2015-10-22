package com.culturedear.counterpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

public class CounterpointModel implements Serializable {

  private int[] mainMelody;
  private int[] partsInitialNotes;
  private int counterpointSpecies;
  private int scaleMode;
  private RulePenalties rulePenalties;

  public CounterpointModel() {
  }

  public CounterpointModel(int[] mainMelody, int[] partsInitialNotes, int counterpointSpecies, int scaleMode, RulePenalties rulePenalties) {
    this.mainMelody = mainMelody;
    this.partsInitialNotes = partsInitialNotes;
    this.counterpointSpecies = counterpointSpecies;
    this.scaleMode = scaleMode;
    this.rulePenalties = rulePenalties;
  }

  public int[] getMainMelody() {
        return mainMelody;
    }

  public void setMainMelody(int[] mainMelody) {
      this.mainMelody = mainMelody;
  }

  public int[] getPartsInitialNotes() {
      return partsInitialNotes;
  }

  public void setPartsInitialNotes(int[] partsInitialNotes) {
      this.partsInitialNotes = partsInitialNotes;
  }

  public int getCounterpointSpecies() {
      return counterpointSpecies;
  }

  public void setCounterpointSpecies(int counterpointSpecies) {
      this.counterpointSpecies = counterpointSpecies;
  }

  public int getScaleMode() {
      return scaleMode;
  }

  public void setScaleMode(int scaleMode) {
      this.scaleMode = scaleMode;
  }

  public RulePenalties getRulePenalties() {
    return rulePenalties;
  }

  public void setRulePenalties(RulePenalties rulePenalties) {
    this.rulePenalties = rulePenalties;
  }

  @Override
  public String toString() {
      Gson gson = new GsonBuilder()
              .setPrettyPrinting()
              .serializeNulls()
              .create();
      return gson.toJson(this);
  }
}
