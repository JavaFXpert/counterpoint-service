package com.culturedear.counterpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

/**
 * Created by jamesweaver on 10/22/15.
 */
public class RulePenalties implements Serializable {
  static int infinity = 1000000;
  static int bad = 100;
  static int realBad = 200;

  // ---------- Prohibitions ----------
  // Parallel fifths are not allowed.
  private int parallelFifthPenalty = infinity;

  // Parallel unisons are not allowed.
  private int parallelUnisonPenalty = infinity;

  // In two part counterpoint the final chord must be a unison or an octave; it can include the third or fifth when there are more than two parts.
  private int endOnPerfectPenalty = infinity;

  // The next to last chord must have a leading tone.
  private int noLeadingTonePenalty = infinity;

  // Dissonances must be handled correctly (this covers a host of rules).
  private int dissonancePenalty = infinity;

  // Voices must stay within the mode of the cantus firmus.
  private int outOfModePenalty = infinity;

  // ??? Some melodic intervals are not allowed.
  private int badMelodyPenalty = infinity;

  // A melody must stay within the range of a twelfth.
  private int overTwelfthPenalty = infinity;

  // Cadences must be correct (this covers a host of rules).
  private int badCadencePenalty = infinity;

  // TODO: Create description for this penalty
  private int sixFiveChordPenalty = infinity;

  // Direct motion to an octave or fifth over three chords where the intervening interval is not at least a fourth is not allowed.
  //TODO: Identify which variable this is

  // A dissonance as a passing tone must fill in a third.
  private int dissonanceNotFillingThirdPenalty = infinity;

  // A nota combiata must be resolved correctly.
  private int notaCambiataPenalty = infinity;

  // A ligature must be resolved correctly.
  private int unresolvedLigaturePenalty = infinity;

  // There are certain situations where a ligature is not allowed.
  private int noTimeForaLigaturePenalty = infinity;

  // Augmented intervals are not allowed.
  private int augmentedIntervalPenalty = infinity;

  // Doubled leading tones are not allowed.
  private int doubledLeadingTonePenalty = infinity;

  // The leading tone must be resolved correctly.
  private int unresolvedLeadingTonePenalty = infinity;

  // TODO: Create description for this penalty
  private int directPerfectOnDownbeatPenalty = infinity;

  // TODO: Create description for this penalty
  private int crossBelowBassPenalty = infinity;

  // TODO: Create description for this penalty
  private int crossAboveCantusPenalty = infinity;


  // ---------- Very bad infractions ----------
  // Avoid direct motion to a perfect fifth.
  private int directToFifthPenalty = realBad;

  // Avoid direct motion to an octave.
  private int directToOctavePenalty = realBad;

  // Voices should not move outside certain ranges.
  private int outOfRangePenalty = realBad;

  // Avoid a unison on the down beat.
  // TODO: Determine whether this is "very bad" per Bill's doc or "bad" per Bill's code
  private int unisonDownbeatPenalty = realBad;


  // ---------- Bad infractions ----------
  // Avoid unisons in two part counterpoint.
  private int unisonPenalty = bad;

  // Try not to repeat a note on the upbeat.
  private int repetitionOnUpbeatPenalty = bad;

  // A melody should stay within the range of an octave.
  private int overOctavePenalty = bad;

  // Eighth notes should not skip.
  private int eighthJumpPenalty = bad;

  // There are various rules concerning the rhythmic values of tied notes.
  //TODO: Identify which variable this is

  // A six-five chord must be prepared correctly.
  private int unpreparedSixFivePenalty = bad;

  // A six-five chord must be resolved correctly.
  private int unresolvedSixFivePenalty = bad;

  // Avoid direct motion to a tritone.
  private int directToTritonePenalty = bad;


  // ---------- Other rules ----------
  // Avoid a sixth followed by motion in the same direction
  private int sixthFollowedBySameDirectionPenalty = 34;

  // Strive for triads
  private int notTriadPenalty = 34;

  // TODO: Create description for this penalty
  private int noMotionAgainstOctavePenalty = 34;

  // In fourth species, avoid passages without ligatures
  private int notaLigaturePenalty = 21;

  // Avoid direct motion to a perfect consonance in the inner voices
  private int innerVoicesInDirectToPerfectPenalty = 21;

  // TODO: Create description for this penalty
  private int unisonUpbeatPenalty = 21;

  // Avoid tritones near the cadence in lydian mode
  private int lydianCadentialTritonePenalty = 13;

  // Avoid a leap to the cadence
  private int leapAtCadencePenalty = 13;

  // TODO: Create description for this penalty
  private int halfUntiedPenalty = 13;

  // Avoid moving from a tenth to an octave
  private int tenthToOctavePenalty = 8;

  // Avoid a skip to an octave
  private int skipTo8vePenalty = 8;

  // Avoid a sixth preceded by motion in the same direction
  private int sixthPrecededBySameDirectionPenalty = 8;

  // Avoid a tritone outlined by a melody
  private int melodicTritonePenalty = 8;

  // Avoid skips in all voices at once
  private int allVoicesSkipPenalty = 8;

  // Avoid a fifth or octave followed by motion in the same direction
  //TODO: Determine whether this variable covers the "octave" case as well
  private int fifthFollowedBySameDirectionPenalty = 8;

  // TODO: Create description for this penalty
  private int lesserLigaturePenalty = 8;

  // Avoid a repeated pattern of four notes
  private int fourRepeatedNotesPenalty = 7;

  // Avoid notes on the outer edges of the ranges
  private int extremeRangePenalty = 5;

  // Avoid leaps of an octave
  private int octaveLeapPenalty = 5;

  // Avoid doubling the third
  private int thirdDoubledPenalty = 5;

  // Avoid doubling the sixth
  private int doubledSixthPenalty = 5;

  // Try not to skip from a unison
  private int skipFromUnisonPenalty = 4;

  // Avoid a repeated pattern of three notes
  private int threeRepeatedNotesPenalty = 4;

  // Avoid a fifth or octave preceded by motion in the same direction
  //TODO: Determine whether this variable covers the "octave" case as well
  private int fifthPrecededBySameDirectionPenalty = 3;

  // Avoid any skip followed by motion in the same direction
  private int skipFollowedBySameDirectionPenalty = 3;

  // Avoid two skips that do not fill in a triad
  private int twoSkipsNotInTriadPenalty = 3;

  // Do not repeat a note on the down beat
  // TODO: Determine whether this is the correct variable
  private int downBeatUnisonPenalty = 3;

  // Avoid a repeated note on the fourth beat
  private int unisonOnBeat4Penalty = 3;

  // TODO: Create description for this penalty
  private int threeSkipsPenalty = 3;

  // Avoid a skip followed by a skip in the opposite direction
  //TODO: Identify which variable this is

  // Avoid direct motion to a tritone in the inner voices
  private int innerVoicesInDirectToTritonePenalty = 13;

  // Avoid doubling the fifth
  private int doubledFifthPenalty = 3;

  // Avoid doubling the bass twice
  private int tripledBassPenalty = 3;

  // Avoid a repeated pattern of two notes
  private int twoRepeatedNotesPenalty = 2;

  // Avoid perfect consonances
  private int perfectConsonancePenalty = 2;

  // Avoid leaps of a sixth
  private int sixthLeapPenalty = 2;

  // Avoid a tritone between voices
  private int verticalTritonePenalty = 2;

  // Avoid a skip followed by a skip
  private int twoSkipsPenalty = 1;

  // Avoid direct motion
  private int directMotionPenalty = 1;

  // Avoid compound intervals
  private int compoundPenalty = 1;

  // Avoid any skip preceded by motion in the same direction
  private int skipPrecededBySameDirectionPenalty = 1;

  // Avoid upper neighbor notes
  private int upperNeighborPenalty = 1;

  // Avoid lower neighbor notes
  private int lowerNeighborPenalty = 1;

  // Strive for variety in a melody (this covers several rules which try to avoid a melody made up entirely of skips,
  // constant repetition of one pitch, constant use of the same interval and so on)
  private int melodicBoredomPenalty = 1;

  // Avoid skips to a down beat
  private int skipToDownBeatPenalty = 1;

  // Strive for contrary motion
  private int notContraryToOthersPenalty = 1;

  // Try to keep the upper voices from wandering far apart
  int upperVoicesTooFarApartPenalty = 1;

  // Avoid repeated voice crossing [penalty dependent on the number of crossings]
  //TODO: Identify which variable (if any) this is


  public RulePenalties() {
  }

  public RulePenalties(int parallelFifthPenalty, int noLeadingTonePenalty) {
    this.parallelFifthPenalty = parallelFifthPenalty;

    this.noLeadingTonePenalty = noLeadingTonePenalty;
  }

  public int getParallelFifthPenalty() {
    return parallelFifthPenalty;
  }

  public void setParallelFifthPenalty(int parallelFifthPenalty) {
    this.parallelFifthPenalty = parallelFifthPenalty;
  }

  public int getParallelUnisonPenalty() {
    return parallelUnisonPenalty;
  }

  public void setParallelUnisonPenalty(int parallelUnisonPenalty) {
    this.parallelUnisonPenalty = parallelUnisonPenalty;
  }

  public int getNoLeadingTonePenalty() {
    return noLeadingTonePenalty;
  }

  public void setNoLeadingTonePenalty(int noLeadingTonePenalty) {
    this.noLeadingTonePenalty = noLeadingTonePenalty;
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
