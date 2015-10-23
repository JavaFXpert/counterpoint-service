package com.culturedear.counterpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

/**
 * Created by jamesweaver on 10/22/15.
 */
public class RulePenalties implements Serializable {
  public static int INFINITE = 1000000;
  public static int VERY_BAD = 200;
  public static int BAD = 100;

  // ---------- Prohibitions ----------
  // Parallel fifths are not allowed.
  private int parallelFifthPenalty = INFINITE;

  // Parallel unisons are not allowed.
  private int parallelUnisonPenalty = INFINITE;

  // In two part counterpoint the final chord must be a unison or an octave; it can include the third or fifth when there are more than two parts.
  private int endOnPerfectPenalty = INFINITE;

  // The next to last chord must have a leading tone.
  private int noLeadingTonePenalty = INFINITE;

  // Dissonances must be handled correctly (this covers a host of rules).
  private int dissonancePenalty = INFINITE;

  // Voices must stay within the mode of the cantus firmus.
  private int outOfModePenalty = INFINITE;

  // Some melodic intervals are not allowed.
  private int badMelodyPenalty = INFINITE;

  // A melody must stay within the range of a twelfth.
  private int overTwelfthPenalty = INFINITE;

  // Cadences must be correct (this covers a host of rules).
  private int badCadencePenalty = INFINITE;

  // TODO: Create description for this penalty
  private int sixFiveChordPenalty = INFINITE;

  // Direct motion to an octave or fifth over three chords where the intervening interval is not at least a fourth is not allowed.
  //TODO: Identify which variable this is

  // A dissonance as a passing tone must fill in a third.
  private int dissonanceNotFillingThirdPenalty = INFINITE;

  // A nota combiata must be resolved correctly.
  private int notaCambiataPenalty = INFINITE;

  // A ligature must be resolved correctly.
  private int unresolvedLigaturePenalty = INFINITE;

  // There are certain situations where a ligature is not allowed.
  private int noTimeForaLigaturePenalty = INFINITE;

  // Augmented intervals are not allowed.
  private int augmentedIntervalPenalty = INFINITE;

  // Doubled leading tones are not allowed.
  private int doubledLeadingTonePenalty = INFINITE;

  // The leading tone must be resolved correctly.
  private int unresolvedLeadingTonePenalty = INFINITE;

  // TODO: Create description for this penalty
  private int directPerfectOnDownbeatPenalty = INFINITE;

  // TODO: Create description for this penalty
  private int crossBelowBassPenalty = INFINITE;

  // TODO: Create description for this penalty
  private int crossAboveCantusPenalty = INFINITE;


  // ---------- Very bad infractions ----------
  // Avoid direct motion to a perfect fifth.
  private int directToFifthPenalty = VERY_BAD;

  // Avoid direct motion to an octave.
  private int directToOctavePenalty = VERY_BAD;

  // Voices should not move outside certain ranges.
  private int outOfRangePenalty = VERY_BAD;

  // Avoid a unison on the down beat.
  // TODO: Determine whether this is "very bad" per Bill's doc or "bad" per Bill's code
  private int downBeatUnisonPenalty = VERY_BAD;


  // ---------- Bad infractions ----------
  // Avoid unisons in two part counterpoint.
  private int unisonPenalty = BAD;

  // Try not to repeat a note on the upbeat.
  private int repetitionOnUpbeatPenalty = BAD;

  // A melody should stay within the range of an octave.
  private int overOctavePenalty = BAD;

  // Eighth notes should not skip.
  private int eighthJumpPenalty = BAD;

  // There are various rules concerning the rhythmic values of tied notes.
  //TODO: Identify which variable this is

  // A six-five chord must be prepared correctly.
  private int unpreparedSixFivePenalty = BAD;

  // A six-five chord must be resolved correctly.
  private int unresolvedSixFivePenalty = BAD;

  // Avoid direct motion to a tritone.
  private int directToTritonePenalty = BAD;


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
  private int unisonDownbeatPenalty = 3;

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
  private int upperVoicesTooFarApartPenalty = 1;

  // Avoid repeated voice crossing [penalty dependent on the number of crossings]
  //TODO: Identify which variable (if any) this is

  public RulePenalties() {
  }

  public RulePenalties(int parallelFifthPenalty, int parallelUnisonPenalty, int endOnPerfectPenalty,
                       int noLeadingTonePenalty, int dissonancePenalty, int outOfModePenalty, int badMelodyPenalty,
                       int overTwelfthPenalty, int badCadencePenalty, int sixFiveChordPenalty,
                       int dissonanceNotFillingThirdPenalty, int notaCambiataPenalty, int unresolvedLigaturePenalty,
                       int noTimeForaLigaturePenalty, int augmentedIntervalPenalty, int doubledLeadingTonePenalty,
                       int unresolvedLeadingTonePenalty, int directPerfectOnDownbeatPenalty, int crossBelowBassPenalty,
                       int crossAboveCantusPenalty, int directToFifthPenalty, int directToOctavePenalty,
                       int outOfRangePenalty, int downBeatUnisonPenalty, int unisonPenalty,
                       int repetitionOnUpbeatPenalty, int overOctavePenalty, int eighthJumpPenalty,
                       int unpreparedSixFivePenalty, int unresolvedSixFivePenalty, int directToTritonePenalty,
                       int sixthFollowedBySameDirectionPenalty, int notTriadPenalty, int noMotionAgainstOctavePenalty,
                       int notaLigaturePenalty, int innerVoicesInDirectToPerfectPenalty, int unisonUpbeatPenalty,
                       int lydianCadentialTritonePenalty, int leapAtCadencePenalty, int halfUntiedPenalty,
                       int tenthToOctavePenalty, int skipTo8vePenalty, int sixthPrecededBySameDirectionPenalty,
                       int melodicTritonePenalty, int allVoicesSkipPenalty, int fifthFollowedBySameDirectionPenalty,
                       int lesserLigaturePenalty, int fourRepeatedNotesPenalty, int extremeRangePenalty,
                       int octaveLeapPenalty, int thirdDoubledPenalty, int doubledSixthPenalty,
                       int skipFromUnisonPenalty, int threeRepeatedNotesPenalty,
                       int fifthPrecededBySameDirectionPenalty, int skipFollowedBySameDirectionPenalty,
                       int twoSkipsNotInTriadPenalty, int unisonDownbeatPenalty, int unisonOnBeat4Penalty,
                       int threeSkipsPenalty, int innerVoicesInDirectToTritonePenalty, int doubledFifthPenalty,
                       int tripledBassPenalty, int twoRepeatedNotesPenalty, int perfectConsonancePenalty,
                       int sixthLeapPenalty, int verticalTritonePenalty, int twoSkipsPenalty, int directMotionPenalty,
                       int compoundPenalty, int skipPrecededBySameDirectionPenalty, int upperNeighborPenalty,
                       int lowerNeighborPenalty, int melodicBoredomPenalty, int skipToDownBeatPenalty,
                       int notContraryToOthersPenalty, int upperVoicesTooFarApartPenalty) {
    this.parallelFifthPenalty = parallelFifthPenalty;
    this.parallelUnisonPenalty = parallelUnisonPenalty;
    this.endOnPerfectPenalty = endOnPerfectPenalty;
    this.noLeadingTonePenalty = noLeadingTonePenalty;
    this.dissonancePenalty = dissonancePenalty;
    this.outOfModePenalty = outOfModePenalty;
    this.badMelodyPenalty = badMelodyPenalty;
    this.overTwelfthPenalty = overTwelfthPenalty;
    this.badCadencePenalty = badCadencePenalty;
    this.sixFiveChordPenalty = sixFiveChordPenalty;
    this.dissonanceNotFillingThirdPenalty = dissonanceNotFillingThirdPenalty;
    this.notaCambiataPenalty = notaCambiataPenalty;
    this.unresolvedLigaturePenalty = unresolvedLigaturePenalty;
    this.noTimeForaLigaturePenalty = noTimeForaLigaturePenalty;
    this.augmentedIntervalPenalty = augmentedIntervalPenalty;
    this.doubledLeadingTonePenalty = doubledLeadingTonePenalty;
    this.unresolvedLeadingTonePenalty = unresolvedLeadingTonePenalty;
    this.directPerfectOnDownbeatPenalty = directPerfectOnDownbeatPenalty;
    this.crossBelowBassPenalty = crossBelowBassPenalty;
    this.crossAboveCantusPenalty = crossAboveCantusPenalty;
    this.directToFifthPenalty = directToFifthPenalty;
    this.directToOctavePenalty = directToOctavePenalty;
    this.outOfRangePenalty = outOfRangePenalty;
    this.downBeatUnisonPenalty = downBeatUnisonPenalty;
    this.unisonPenalty = unisonPenalty;
    this.repetitionOnUpbeatPenalty = repetitionOnUpbeatPenalty;
    this.overOctavePenalty = overOctavePenalty;
    this.eighthJumpPenalty = eighthJumpPenalty;
    this.unpreparedSixFivePenalty = unpreparedSixFivePenalty;
    this.unresolvedSixFivePenalty = unresolvedSixFivePenalty;
    this.directToTritonePenalty = directToTritonePenalty;
    this.sixthFollowedBySameDirectionPenalty = sixthFollowedBySameDirectionPenalty;
    this.notTriadPenalty = notTriadPenalty;
    this.noMotionAgainstOctavePenalty = noMotionAgainstOctavePenalty;
    this.notaLigaturePenalty = notaLigaturePenalty;
    this.innerVoicesInDirectToPerfectPenalty = innerVoicesInDirectToPerfectPenalty;
    this.unisonUpbeatPenalty = unisonUpbeatPenalty;
    this.lydianCadentialTritonePenalty = lydianCadentialTritonePenalty;
    this.leapAtCadencePenalty = leapAtCadencePenalty;
    this.halfUntiedPenalty = halfUntiedPenalty;
    this.tenthToOctavePenalty = tenthToOctavePenalty;
    this.skipTo8vePenalty = skipTo8vePenalty;
    this.sixthPrecededBySameDirectionPenalty = sixthPrecededBySameDirectionPenalty;
    this.melodicTritonePenalty = melodicTritonePenalty;
    this.allVoicesSkipPenalty = allVoicesSkipPenalty;
    this.fifthFollowedBySameDirectionPenalty = fifthFollowedBySameDirectionPenalty;
    this.lesserLigaturePenalty = lesserLigaturePenalty;
    this.fourRepeatedNotesPenalty = fourRepeatedNotesPenalty;
    this.extremeRangePenalty = extremeRangePenalty;
    this.octaveLeapPenalty = octaveLeapPenalty;
    this.thirdDoubledPenalty = thirdDoubledPenalty;
    this.doubledSixthPenalty = doubledSixthPenalty;
    this.skipFromUnisonPenalty = skipFromUnisonPenalty;
    this.threeRepeatedNotesPenalty = threeRepeatedNotesPenalty;
    this.fifthPrecededBySameDirectionPenalty = fifthPrecededBySameDirectionPenalty;
    this.skipFollowedBySameDirectionPenalty = skipFollowedBySameDirectionPenalty;
    this.twoSkipsNotInTriadPenalty = twoSkipsNotInTriadPenalty;
    this.unisonDownbeatPenalty = unisonDownbeatPenalty;
    this.unisonOnBeat4Penalty = unisonOnBeat4Penalty;
    this.threeSkipsPenalty = threeSkipsPenalty;
    this.innerVoicesInDirectToTritonePenalty = innerVoicesInDirectToTritonePenalty;
    this.doubledFifthPenalty = doubledFifthPenalty;
    this.tripledBassPenalty = tripledBassPenalty;
    this.twoRepeatedNotesPenalty = twoRepeatedNotesPenalty;
    this.perfectConsonancePenalty = perfectConsonancePenalty;
    this.sixthLeapPenalty = sixthLeapPenalty;
    this.verticalTritonePenalty = verticalTritonePenalty;
    this.twoSkipsPenalty = twoSkipsPenalty;
    this.directMotionPenalty = directMotionPenalty;
    this.compoundPenalty = compoundPenalty;
    this.skipPrecededBySameDirectionPenalty = skipPrecededBySameDirectionPenalty;
    this.upperNeighborPenalty = upperNeighborPenalty;
    this.lowerNeighborPenalty = lowerNeighborPenalty;
    this.melodicBoredomPenalty = melodicBoredomPenalty;
    this.skipToDownBeatPenalty = skipToDownBeatPenalty;
    this.notContraryToOthersPenalty = notContraryToOthersPenalty;
    this.upperVoicesTooFarApartPenalty = upperVoicesTooFarApartPenalty;
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

  public int getEndOnPerfectPenalty() {
    return endOnPerfectPenalty;
  }

  public void setEndOnPerfectPenalty(int endOnPerfectPenalty) {
    this.endOnPerfectPenalty = endOnPerfectPenalty;
  }

  public int getNoLeadingTonePenalty() {
    return noLeadingTonePenalty;
  }

  public void setNoLeadingTonePenalty(int noLeadingTonePenalty) {
    this.noLeadingTonePenalty = noLeadingTonePenalty;
  }

  public int getDissonancePenalty() {
    return dissonancePenalty;
  }

  public void setDissonancePenalty(int dissonancePenalty) {
    this.dissonancePenalty = dissonancePenalty;
  }

  public int getOutOfModePenalty() {
    return outOfModePenalty;
  }

  public void setOutOfModePenalty(int outOfModePenalty) {
    this.outOfModePenalty = outOfModePenalty;
  }

  public int getBadMelodyPenalty() {
    return badMelodyPenalty;
  }

  public void setBadMelodyPenalty(int badMelodyPenalty) {
    this.badMelodyPenalty = badMelodyPenalty;
  }

  public int getOverTwelfthPenalty() {
    return overTwelfthPenalty;
  }

  public void setOverTwelfthPenalty(int overTwelfthPenalty) {
    this.overTwelfthPenalty = overTwelfthPenalty;
  }

  public int getBadCadencePenalty() {
    return badCadencePenalty;
  }

  public void setBadCadencePenalty(int badCadencePenalty) {
    this.badCadencePenalty = badCadencePenalty;
  }

  public int getSixFiveChordPenalty() {
    return sixFiveChordPenalty;
  }

  public void setSixFiveChordPenalty(int sixFiveChordPenalty) {
    this.sixFiveChordPenalty = sixFiveChordPenalty;
  }

  public int getDissonanceNotFillingThirdPenalty() {
    return dissonanceNotFillingThirdPenalty;
  }

  public void setDissonanceNotFillingThirdPenalty(int dissonanceNotFillingThirdPenalty) {
    this.dissonanceNotFillingThirdPenalty = dissonanceNotFillingThirdPenalty;
  }

  public int getNotaCambiataPenalty() {
    return notaCambiataPenalty;
  }

  public void setNotaCambiataPenalty(int notaCambiataPenalty) {
    this.notaCambiataPenalty = notaCambiataPenalty;
  }

  public int getUnresolvedLigaturePenalty() {
    return unresolvedLigaturePenalty;
  }

  public void setUnresolvedLigaturePenalty(int unresolvedLigaturePenalty) {
    this.unresolvedLigaturePenalty = unresolvedLigaturePenalty;
  }

  public int getNoTimeForaLigaturePenalty() {
    return noTimeForaLigaturePenalty;
  }

  public void setNoTimeForaLigaturePenalty(int noTimeForaLigaturePenalty) {
    this.noTimeForaLigaturePenalty = noTimeForaLigaturePenalty;
  }

  public int getAugmentedIntervalPenalty() {
    return augmentedIntervalPenalty;
  }

  public void setAugmentedIntervalPenalty(int augmentedIntervalPenalty) {
    this.augmentedIntervalPenalty = augmentedIntervalPenalty;
  }

  public int getDoubledLeadingTonePenalty() {
    return doubledLeadingTonePenalty;
  }

  public void setDoubledLeadingTonePenalty(int doubledLeadingTonePenalty) {
    this.doubledLeadingTonePenalty = doubledLeadingTonePenalty;
  }

  public int getUnresolvedLeadingTonePenalty() {
    return unresolvedLeadingTonePenalty;
  }

  public void setUnresolvedLeadingTonePenalty(int unresolvedLeadingTonePenalty) {
    this.unresolvedLeadingTonePenalty = unresolvedLeadingTonePenalty;
  }

  public int getDirectPerfectOnDownbeatPenalty() {
    return directPerfectOnDownbeatPenalty;
  }

  public void setDirectPerfectOnDownbeatPenalty(int directPerfectOnDownbeatPenalty) {
    this.directPerfectOnDownbeatPenalty = directPerfectOnDownbeatPenalty;
  }

  public int getCrossBelowBassPenalty() {
    return crossBelowBassPenalty;
  }

  public void setCrossBelowBassPenalty(int crossBelowBassPenalty) {
    this.crossBelowBassPenalty = crossBelowBassPenalty;
  }

  public int getCrossAboveCantusPenalty() {
    return crossAboveCantusPenalty;
  }

  public void setCrossAboveCantusPenalty(int crossAboveCantusPenalty) {
    this.crossAboveCantusPenalty = crossAboveCantusPenalty;
  }

  public int getDirectToFifthPenalty() {
    return directToFifthPenalty;
  }

  public void setDirectToFifthPenalty(int directToFifthPenalty) {
    this.directToFifthPenalty = directToFifthPenalty;
  }

  public int getDirectToOctavePenalty() {
    return directToOctavePenalty;
  }

  public void setDirectToOctavePenalty(int directToOctavePenalty) {
    this.directToOctavePenalty = directToOctavePenalty;
  }

  public int getOutOfRangePenalty() {
    return outOfRangePenalty;
  }

  public void setOutOfRangePenalty(int outOfRangePenalty) {
    this.outOfRangePenalty = outOfRangePenalty;
  }

  public int getDownBeatUnisonPenalty() {
    return downBeatUnisonPenalty;
  }

  public void setDownBeatUnisonPenalty(int downBeatUnisonPenalty) {
    this.downBeatUnisonPenalty = downBeatUnisonPenalty;
  }

  public int getUnisonPenalty() {
    return unisonPenalty;
  }

  public void setUnisonPenalty(int unisonPenalty) {
    this.unisonPenalty = unisonPenalty;
  }

  public int getRepetitionOnUpbeatPenalty() {
    return repetitionOnUpbeatPenalty;
  }

  public void setRepetitionOnUpbeatPenalty(int repetitionOnUpbeatPenalty) {
    this.repetitionOnUpbeatPenalty = repetitionOnUpbeatPenalty;
  }

  public int getOverOctavePenalty() {
    return overOctavePenalty;
  }

  public void setOverOctavePenalty(int overOctavePenalty) {
    this.overOctavePenalty = overOctavePenalty;
  }

  public int getEighthJumpPenalty() {
    return eighthJumpPenalty;
  }

  public void setEighthJumpPenalty(int eighthJumpPenalty) {
    this.eighthJumpPenalty = eighthJumpPenalty;
  }

  public int getUnpreparedSixFivePenalty() {
    return unpreparedSixFivePenalty;
  }

  public void setUnpreparedSixFivePenalty(int unpreparedSixFivePenalty) {
    this.unpreparedSixFivePenalty = unpreparedSixFivePenalty;
  }

  public int getUnresolvedSixFivePenalty() {
    return unresolvedSixFivePenalty;
  }

  public void setUnresolvedSixFivePenalty(int unresolvedSixFivePenalty) {
    this.unresolvedSixFivePenalty = unresolvedSixFivePenalty;
  }

  public int getDirectToTritonePenalty() {
    return directToTritonePenalty;
  }

  public void setDirectToTritonePenalty(int directToTritonePenalty) {
    this.directToTritonePenalty = directToTritonePenalty;
  }

  public int getSixthFollowedBySameDirectionPenalty() {
    return sixthFollowedBySameDirectionPenalty;
  }

  public void setSixthFollowedBySameDirectionPenalty(int sixthFollowedBySameDirectionPenalty) {
    this.sixthFollowedBySameDirectionPenalty = sixthFollowedBySameDirectionPenalty;
  }

  public int getNotTriadPenalty() {
    return notTriadPenalty;
  }

  public void setNotTriadPenalty(int notTriadPenalty) {
    this.notTriadPenalty = notTriadPenalty;
  }

  public int getNoMotionAgainstOctavePenalty() {
    return noMotionAgainstOctavePenalty;
  }

  public void setNoMotionAgainstOctavePenalty(int noMotionAgainstOctavePenalty) {
    this.noMotionAgainstOctavePenalty = noMotionAgainstOctavePenalty;
  }

  public int getNotaLigaturePenalty() {
    return notaLigaturePenalty;
  }

  public void setNotaLigaturePenalty(int notaLigaturePenalty) {
    this.notaLigaturePenalty = notaLigaturePenalty;
  }

  public int getInnerVoicesInDirectToPerfectPenalty() {
    return innerVoicesInDirectToPerfectPenalty;
  }

  public void setInnerVoicesInDirectToPerfectPenalty(int innerVoicesInDirectToPerfectPenalty) {
    this.innerVoicesInDirectToPerfectPenalty = innerVoicesInDirectToPerfectPenalty;
  }

  public int getUnisonUpbeatPenalty() {
    return unisonUpbeatPenalty;
  }

  public void setUnisonUpbeatPenalty(int unisonUpbeatPenalty) {
    this.unisonUpbeatPenalty = unisonUpbeatPenalty;
  }

  public int getLydianCadentialTritonePenalty() {
    return lydianCadentialTritonePenalty;
  }

  public void setLydianCadentialTritonePenalty(int lydianCadentialTritonePenalty) {
    this.lydianCadentialTritonePenalty = lydianCadentialTritonePenalty;
  }

  public int getLeapAtCadencePenalty() {
    return leapAtCadencePenalty;
  }

  public void setLeapAtCadencePenalty(int leapAtCadencePenalty) {
    this.leapAtCadencePenalty = leapAtCadencePenalty;
  }

  public int getHalfUntiedPenalty() {
    return halfUntiedPenalty;
  }

  public void setHalfUntiedPenalty(int halfUntiedPenalty) {
    this.halfUntiedPenalty = halfUntiedPenalty;
  }

  public int getTenthToOctavePenalty() {
    return tenthToOctavePenalty;
  }

  public void setTenthToOctavePenalty(int tenthToOctavePenalty) {
    this.tenthToOctavePenalty = tenthToOctavePenalty;
  }

  public int getSkipTo8vePenalty() {
    return skipTo8vePenalty;
  }

  public void setSkipTo8vePenalty(int skipTo8vePenalty) {
    this.skipTo8vePenalty = skipTo8vePenalty;
  }

  public int getSixthPrecededBySameDirectionPenalty() {
    return sixthPrecededBySameDirectionPenalty;
  }

  public void setSixthPrecededBySameDirectionPenalty(int sixthPrecededBySameDirectionPenalty) {
    this.sixthPrecededBySameDirectionPenalty = sixthPrecededBySameDirectionPenalty;
  }

  public int getMelodicTritonePenalty() {
    return melodicTritonePenalty;
  }

  public void setMelodicTritonePenalty(int melodicTritonePenalty) {
    this.melodicTritonePenalty = melodicTritonePenalty;
  }

  public int getAllVoicesSkipPenalty() {
    return allVoicesSkipPenalty;
  }

  public void setAllVoicesSkipPenalty(int allVoicesSkipPenalty) {
    this.allVoicesSkipPenalty = allVoicesSkipPenalty;
  }

  public int getFifthFollowedBySameDirectionPenalty() {
    return fifthFollowedBySameDirectionPenalty;
  }

  public void setFifthFollowedBySameDirectionPenalty(int fifthFollowedBySameDirectionPenalty) {
    this.fifthFollowedBySameDirectionPenalty = fifthFollowedBySameDirectionPenalty;
  }

  public int getLesserLigaturePenalty() {
    return lesserLigaturePenalty;
  }

  public void setLesserLigaturePenalty(int lesserLigaturePenalty) {
    this.lesserLigaturePenalty = lesserLigaturePenalty;
  }

  public int getFourRepeatedNotesPenalty() {
    return fourRepeatedNotesPenalty;
  }

  public void setFourRepeatedNotesPenalty(int fourRepeatedNotesPenalty) {
    this.fourRepeatedNotesPenalty = fourRepeatedNotesPenalty;
  }

  public int getExtremeRangePenalty() {
    return extremeRangePenalty;
  }

  public void setExtremeRangePenalty(int extremeRangePenalty) {
    this.extremeRangePenalty = extremeRangePenalty;
  }

  public int getOctaveLeapPenalty() {
    return octaveLeapPenalty;
  }

  public void setOctaveLeapPenalty(int octaveLeapPenalty) {
    this.octaveLeapPenalty = octaveLeapPenalty;
  }

  public int getThirdDoubledPenalty() {
    return thirdDoubledPenalty;
  }

  public void setThirdDoubledPenalty(int thirdDoubledPenalty) {
    this.thirdDoubledPenalty = thirdDoubledPenalty;
  }

  public int getDoubledSixthPenalty() {
    return doubledSixthPenalty;
  }

  public void setDoubledSixthPenalty(int doubledSixthPenalty) {
    this.doubledSixthPenalty = doubledSixthPenalty;
  }

  public int getSkipFromUnisonPenalty() {
    return skipFromUnisonPenalty;
  }

  public void setSkipFromUnisonPenalty(int skipFromUnisonPenalty) {
    this.skipFromUnisonPenalty = skipFromUnisonPenalty;
  }

  public int getThreeRepeatedNotesPenalty() {
    return threeRepeatedNotesPenalty;
  }

  public void setThreeRepeatedNotesPenalty(int threeRepeatedNotesPenalty) {
    this.threeRepeatedNotesPenalty = threeRepeatedNotesPenalty;
  }

  public int getFifthPrecededBySameDirectionPenalty() {
    return fifthPrecededBySameDirectionPenalty;
  }

  public void setFifthPrecededBySameDirectionPenalty(int fifthPrecededBySameDirectionPenalty) {
    this.fifthPrecededBySameDirectionPenalty = fifthPrecededBySameDirectionPenalty;
  }

  public int getSkipFollowedBySameDirectionPenalty() {
    return skipFollowedBySameDirectionPenalty;
  }

  public void setSkipFollowedBySameDirectionPenalty(int skipFollowedBySameDirectionPenalty) {
    this.skipFollowedBySameDirectionPenalty = skipFollowedBySameDirectionPenalty;
  }

  public int getTwoSkipsNotInTriadPenalty() {
    return twoSkipsNotInTriadPenalty;
  }

  public void setTwoSkipsNotInTriadPenalty(int twoSkipsNotInTriadPenalty) {
    this.twoSkipsNotInTriadPenalty = twoSkipsNotInTriadPenalty;
  }

  public int getUnisonDownbeatPenalty() {
    return unisonDownbeatPenalty;
  }

  public void setUnisonDownbeatPenalty(int unisonDownbeatPenalty) {
    this.unisonDownbeatPenalty = unisonDownbeatPenalty;
  }

  public int getUnisonOnBeat4Penalty() {
    return unisonOnBeat4Penalty;
  }

  public void setUnisonOnBeat4Penalty(int unisonOnBeat4Penalty) {
    this.unisonOnBeat4Penalty = unisonOnBeat4Penalty;
  }

  public int getThreeSkipsPenalty() {
    return threeSkipsPenalty;
  }

  public void setThreeSkipsPenalty(int threeSkipsPenalty) {
    this.threeSkipsPenalty = threeSkipsPenalty;
  }

  public int getInnerVoicesInDirectToTritonePenalty() {
    return innerVoicesInDirectToTritonePenalty;
  }

  public void setInnerVoicesInDirectToTritonePenalty(int innerVoicesInDirectToTritonePenalty) {
    this.innerVoicesInDirectToTritonePenalty = innerVoicesInDirectToTritonePenalty;
  }

  public int getDoubledFifthPenalty() {
    return doubledFifthPenalty;
  }

  public void setDoubledFifthPenalty(int doubledFifthPenalty) {
    this.doubledFifthPenalty = doubledFifthPenalty;
  }

  public int getTripledBassPenalty() {
    return tripledBassPenalty;
  }

  public void setTripledBassPenalty(int tripledBassPenalty) {
    this.tripledBassPenalty = tripledBassPenalty;
  }

  public int getTwoRepeatedNotesPenalty() {
    return twoRepeatedNotesPenalty;
  }

  public void setTwoRepeatedNotesPenalty(int twoRepeatedNotesPenalty) {
    this.twoRepeatedNotesPenalty = twoRepeatedNotesPenalty;
  }

  public int getPerfectConsonancePenalty() {
    return perfectConsonancePenalty;
  }

  public void setPerfectConsonancePenalty(int perfectConsonancePenalty) {
    this.perfectConsonancePenalty = perfectConsonancePenalty;
  }

  public int getSixthLeapPenalty() {
    return sixthLeapPenalty;
  }

  public void setSixthLeapPenalty(int sixthLeapPenalty) {
    this.sixthLeapPenalty = sixthLeapPenalty;
  }

  public int getVerticalTritonePenalty() {
    return verticalTritonePenalty;
  }

  public void setVerticalTritonePenalty(int verticalTritonePenalty) {
    this.verticalTritonePenalty = verticalTritonePenalty;
  }

  public int getTwoSkipsPenalty() {
    return twoSkipsPenalty;
  }

  public void setTwoSkipsPenalty(int twoSkipsPenalty) {
    this.twoSkipsPenalty = twoSkipsPenalty;
  }

  public int getDirectMotionPenalty() {
    return directMotionPenalty;
  }

  public void setDirectMotionPenalty(int directMotionPenalty) {
    this.directMotionPenalty = directMotionPenalty;
  }

  public int getCompoundPenalty() {
    return compoundPenalty;
  }

  public void setCompoundPenalty(int compoundPenalty) {
    this.compoundPenalty = compoundPenalty;
  }

  public int getSkipPrecededBySameDirectionPenalty() {
    return skipPrecededBySameDirectionPenalty;
  }

  public void setSkipPrecededBySameDirectionPenalty(int skipPrecededBySameDirectionPenalty) {
    this.skipPrecededBySameDirectionPenalty = skipPrecededBySameDirectionPenalty;
  }

  public int getUpperNeighborPenalty() {
    return upperNeighborPenalty;
  }

  public void setUpperNeighborPenalty(int upperNeighborPenalty) {
    this.upperNeighborPenalty = upperNeighborPenalty;
  }

  public int getLowerNeighborPenalty() {
    return lowerNeighborPenalty;
  }

  public void setLowerNeighborPenalty(int lowerNeighborPenalty) {
    this.lowerNeighborPenalty = lowerNeighborPenalty;
  }

  public int getMelodicBoredomPenalty() {
    return melodicBoredomPenalty;
  }

  public void setMelodicBoredomPenalty(int melodicBoredomPenalty) {
    this.melodicBoredomPenalty = melodicBoredomPenalty;
  }

  public int getSkipToDownBeatPenalty() {
    return skipToDownBeatPenalty;
  }

  public void setSkipToDownBeatPenalty(int skipToDownBeatPenalty) {
    this.skipToDownBeatPenalty = skipToDownBeatPenalty;
  }

  public int getNotContraryToOthersPenalty() {
    return notContraryToOthersPenalty;
  }

  public void setNotContraryToOthersPenalty(int notContraryToOthersPenalty) {
    this.notContraryToOthersPenalty = notContraryToOthersPenalty;
  }

  public int getUpperVoicesTooFarApartPenalty() {
    return upperVoicesTooFarApartPenalty;
  }

  public void setUpperVoicesTooFarApartPenalty(int upperVoicesTooFarApartPenalty) {
    this.upperVoicesTooFarApartPenalty = upperVoicesTooFarApartPenalty;
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
