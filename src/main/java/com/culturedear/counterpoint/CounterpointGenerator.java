/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.culturedear.counterpoint;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jamesweaver
 */
@Component
public class CounterpointGenerator {
  private CounterpointSolution counterpointSolution;

  void arrBlt(int array[],int destIdx, int sourceOffset, int numToCopy) {
    int i; 
    for (i = 0; i < numToCopy; i++) {
      array[i] = array[i + sourceOffset];
    }
  }  
  
  static int unison = 0;
  static int minorSecond = 1;
  static int majorSecond = 2;
  static int minorThird = 3;
  static int majorThird = 4;
  static int fourth = 5;
  static int tritone = 6;
  static int fifth = 7;
  static int minorSixth = 8;
  static int majorSixth = 9;
  static int minorSeventh = 10;
  static int majorSeventh = 11;
  static int octave = 12;
    
  boolean perfectConsonance[] = {true, false, false, false, false, false, false, 
                                 true, false, false, false, false, true};
  boolean imperfectConsonance[] = {false, false, false, true, true, false, false, false,
                                   true, true, false, false, false};
  boolean dissonance[] = {false, true, true, false, false, true, true, false, 
                          false, false, true, true, false};

  static int ionian = 0; //was 6
  static int dorian = 1; //was 2
  static int phrygian = 2; //was 3
  static int lydian = 3; //was 4
  static int mixolydian = 4; //was 5
  static int aeolian = 5; //was 1
  static int locrian = 6; //was 7

  boolean _ionian[] =     {true, false, true, false, true, true, false, 
                           true, false, true, false, true};
  boolean _dorian[] =     {true, false, true, true, false, true, false, 
                           true, false, true, true, false};
  boolean _phrygian[] =   {true, true, false, true, false, true, false,
                           true, true, false, true, false};
  boolean _lydian[] =     {true, false, true, false, true, false, true, 
                           true, false, true, false, true};
  boolean _mixolydian[] = {true, false, true, false, true, true, false, 
                           true, false, true, true, false};
  boolean _aeolian[] =    {true, false, true, true, false, true, false,
                           true, false, false, true, false};
  boolean _locrian[] =    {true, true, false, true, false, true, true,
                           false, true, false, true, false};
    
  /**
   * 
   * @param pitch
   * @param mode
   * @return 
   */
  boolean inMode(int pitch, int mode) {
    int pit;
    if (pitch > 11)
      pit = pitch % 12;
    else pit=pitch;
    switch (mode) {
      case 0:     return(_ionian[pit]); //ionian
      case 1:     return(_dorian[pit]); //dorian
      case 2:     return(_phrygian[pit]); //phrygian
      case 3:     return(_lydian[pit]); //lydian
      case 4:     return(_mixolydian[pit]); //mixolydian
      case 5:     return(_aeolian[pit]); //aeolian
      case 6:     return(_locrian[pit]); //locrian
    }
    return(false);
  }

  // NOTE: C version was int
  boolean badMelodyInterval[] = {false,false,false,false,false,false,true,false,false,true,true,true,false};
  
  /**
   * NOTE: C version returned int
   * @param intv
   * @return 
   */
  boolean badMelody(int intv) {
    return( ((Math.abs(intv) > octave) || badMelodyInterval[Math.abs(intv)]) || (intv == -minorSixth));
  }
  
  /**
   * NOTE: C version returned int
   * @param interval
   * @return 
   */
  boolean aSkip(int interval) {
    return(Math.abs(interval) > majorSecond);
  }
    
  /**
   * NOTE: C version returned int
   * @param interval
   * @return 
   */
  boolean aStep(int interval) {
    return((Math.abs(interval) == minorSecond) || (Math.abs(interval) == majorSecond));
  }
    
  /**
   * NOTE: C version returned int
   * @param interval
   * @return 
   */
  boolean aThird(int interval) {
    return((interval == minorThird) || (interval == majorThird));
  }
    
  /**
   * NOTE: C version returned int
   * @param interval
   * @return 
   */
  boolean aSeventh(int interval) {
    return((interval == minorSeventh) || (interval == majorSeventh));
  }
    
  /**
   * NOTE: C version returned int
   * @param interval
   * @return 
   */
  boolean anOctave(int interval) {
    return((interval != unison) && ((Math.abs(interval) % 12) == 0));
  }
    
  /**
   * NOTE: C version returned int
   * @param interval
   * @return 
   */
  boolean aTenth(int interval) {
    return((Math.abs(interval) > 14) && (aThird((Math.abs(interval) % 12))));
  }
    
  static int directMotion = 1;
  static int contraryMotion = 2;
  static int obliqueMotion = 3;
  static int noMotion = 4;

  /**
   * 
   * @param pitch1
   * @param pitch2
   * @param pitch3
   * @param pitch4
   * @return 
   */
  int motionType(int pitch1, int pitch2, int pitch3, int pitch4) {
    if ((pitch1 == pitch2) || (pitch3 == pitch4)) {
      if ((pitch1 == pitch2) && (pitch3 == pitch4)) {
	    return(noMotion);
      }
      else {
        return(obliqueMotion);
      }
    }
    else {
      if ((pitch2 - pitch1) * (pitch4 - pitch3) > 0) {
	    return(directMotion);
      }
      else {
        return(contraryMotion);
      }
    }
  }
  
  /**
   * NOTE: C version returned int
   * @param pitch1
   * @param pitch2
   * @param pitch3
   * @param pitch4
   * @return 
   */
  boolean directMotionToPerfectConsonance(int pitch1, int pitch2, int pitch3, int pitch4) {
    return (perfectConsonance[Math.abs(pitch4 - pitch2) % 12] && 
           (motionType(pitch1, pitch2, pitch3, pitch4) == directMotion));
  }
  
  /**
   * NOTE: C version returned int
   * @param pitch1
   * @param pitch2
   * @param pitch3
   * @return 
   */
  boolean consecutiveSkipsInSameDirection(int pitch1, int pitch2, int pitch3) {
    return( (((pitch1 > pitch2) && (pitch2 > pitch3)) ||
           ((pitch1 < pitch2) && (pitch2 < pitch3))) &&
	       (aSkip(pitch2 - pitch1) && aSkip(pitch3 - pitch2)));
  }

  static int highestSemitone = 72;
  static int lowestSemitone = 24;
  
  /**
   * NOTE: C version returned int
   * @param pitch
   * @return 
   */
  boolean outOfRange(int pitch) {
    return ((pitch > highestSemitone) || (pitch < lowestSemitone));
  }
  
  /**
   * NOTE: C version returned int
   * @param pitch
   * @return 
   */
  boolean extremeRange(int pitch) {
    return (pitch > (highestSemitone - 3) || pitch < (lowestSemitone + 3));
  }
  
  static int mostNotes = 128;
  static int mostVoices = 6;
  
  int basePitch, mode, totalTime;
  
  int ctrpt[][] = new int [mostNotes][mostVoices];
  int onset[][] = new int [mostNotes][mostVoices];
  int dur[][] = new int [mostNotes][mostVoices];
  int totalNotes[] = new int [mostVoices];
  
  /**
   * 
   * @param n
   * @param v
   * @return 
   */
  int us(int n, int v) {
    return (ctrpt[n][v]);
  }
  
  /**
   * NOTE: C version returned int
   * @param n
   * @param v
   * @return 
   */
  boolean lastNote(int n, int v) {
    return (n == totalNotes[v]);
  }

  /**
   * NOTE: C version returned int
   * @param n
   * @param v
   * @return 
   */
  boolean firstNote(int n, int v) {
    return (n == 1);
  }
  
  /**
   * NOTE: C version returned int
   * @param n
   * @param v
   * @return 
   */
  boolean nextToLastNote(int n, int v) {
    return (n == (totalNotes[v]-1));
  }
  
  /**
   * 
   * @param cn
   * @param cp
   * @param v
   * @return 
   */
  int totalRange(int cn, int cp, int v) {
    int minp, maxp, i, pit;
    minp = cp;
    maxp = cp;
    for (i = 1; i < cn; i++) {
      pit=us(i,v);
      minp = Math.min(minp, pit);
      maxp = Math.max(maxp, pit);
    }
    return(maxp - minp);
  }
  
  /**
   * 
   * @param n
   * @param v
   * @return 
   */
  int cantus(int n, int v) {
    return (ctrpt[((onset[n][v]) >> 3) + 1][0]);
  }
  
  /**
   * 
   * @param n
   * @param p
   * @param v 
   */
  void setUs(int n, int p, int v) {
    ctrpt[n][v] = p;
  }
  
  /**
   * 
   * @param time
   * @param vNum
   * @return 
   */
  int vIndex(int time, int vNum) {
    int i;
    for (i = 1; i < totalNotes[vNum]; i++) {
      if ((onset[i][vNum] <= time) && 
          ((onset[i][vNum] + dur[i][vNum]) > time)) {
        return(i);
      }
    }
    return(i);
  }
  
  /**
   * 
   * @param cn
   * @param v
   * @param v1
   * @return 
   */
  int other(int cn, int v, int v1) {
    return (ctrpt[vIndex(onset[cn][v],v1)][v1]);
  }
  
  /**
   * 
   * @param cn
   * @param v
   * @return 
   */
  int bass(int cn, int v) {
    int j, lowestPitch;
    lowestPitch = cantus(cn,v);
    for (j = 1; j < v; j++) {
      lowestPitch = Math.min(lowestPitch, other(cn, v, j));
    }
    return(lowestPitch);
  }

  static int wholeNote = 8;
  static int halfNote = 4;
  static int dottedHalfNote = 6;
  static int quarterNote = 2;
  static int dottedQuarterNote = 3;
  static int eighthNote = 1;
  
  /**
   * 
   * @param n
   * @return 
   */
  int beat8(int n) {
    return (n % 8);
  }
  
  /**
   * NOTE: C version returned int
   * @param n
   * @param v
   * @return 
   */
  boolean downBeat(int n, int v) {
    return( beat8(onset[n][v]) == 0);
  }

  /**
   * NOTE: C version returned int
   * @param n
   * @param v
   * @return 
   */
  boolean upBeat(int n, int v) {
    return(!(downBeat(n,v)));
  }
  
  /**
   * 
   * @param cn
   * @param cp
   * @param v
   * @return 
   */
  int pitchRepeats(int cn, int cp, int v) {
    int i,k;
    i = 0;
    for (k = 1; k < cn; k++) {
      if (us(k,v) == cp) {
        i++;
      }
    }
    return(i);
  }
  
  static int one = 0;
  static int two = 2;
  static int three = 3;
  static int four = 4;
  static int five = 5;
  static int six = 6;
  static int eight = 8;

  /**
   * 
   * @param melInt
   * @return 
   */
  int size(int melInt) {
    int actInt, intTyp = 0;
    actInt = Math.abs(melInt);
    switch (actInt) {
      case 0: intTyp = one; break; //unison
      case 1: case 2: intTyp = two; break; //minorSecond majorSecond
      case 3: case 4: intTyp = three; break; //minorThird majorThird
      case 5: intTyp = four; break; //fourth
      case 7: intTyp = five; break; //fifth
      case 8: intTyp = six; break; //minorSixth
      case 12: intTyp = eight; break; //octave
    }
    if (melInt > 0) {
      return (intTyp);
    }
    else {
      return(-intTyp);
    }
  }
  
  /**
   * NOTE: C version returned int
   * @param cn
   * @param cp
   * @param v
   * @return 
   */
  boolean tooMuchOfInterval(int cn, int cp, int v) {
    int seventeen = 17;
    int ints[] = new int[seventeen];
    int i, k, minL;
    for (i = 0 ;i < seventeen; i++) {
      ints[i] = 0;
    }
    for (i = 2; i < cn; i++) {
      k = (size(ctrpt[i][v] - ctrpt[i-1][v]) + 8);
      ints[k]++;
    }
    k = (size(cp - ctrpt[cn-1][v]) + 8);
    minL = 0;
    for (i = 1; i < seventeen; i++) {
      if ((i != k) && (ints[i] > ints[minL])) {
        minL = i;
      }
    }
    return (ints[k] > (ints[minL] + 6));
  }
  
  /**
   * NOTE: C version returned int
   * @param interval
   * @param cn
   * @param cp
   * @param v
   * @param species
   * @return 
   */
  boolean aDissonance(int interval, int cn, int cp, int v, int species) {
    int melInt;
    if ((species == 1) || (dur[cn][v] == wholeNote)) {
      return (dissonance[interval]);
    }
    else {
      if (species == 2) {
        if (downBeat(cn, v) || (!(aStep(cp - us(cn - 1, v))))) {
          return (dissonance[interval]);
        }
        else {
          return(false);
        }
      }
      else {
        if (species == 3) {
          if ((beat8(onset[cn][v]) == 0) || 
              (firstNote(cn, v) || lastNote(cn, v))) {
            return (dissonance[interval]);
          }
          melInt = (cp - us(cn - 1, v));
          if (!(aStep(melInt))) {
            return(dissonance[interval]);
          }
          /* 0 cannot be dissonant (downbeat)
           * 1 can be if passing either way, but must be approached by step.
           * 2 can be if passing 2 to 4 (both latter cons)
           * 3 can be if passing but must be approached and left by step
           */
          return(false);
        }
        else {
          if (species == 4) {
            if (upBeat(cn, v) || (firstNote(cn, v) || lastNote(cn, v))) {
              return (dissonance[interval]);
            }
            melInt = (cp - us(cn-1, v));
            if (melInt != 0) {
              return(dissonance[interval]);
            }
            return(false);
            // i.e. unison to downbeat is ok, but needs check later
          }
          else {
            if (species == 5) {
              if (beat8(onset[cn][v]) == 0) {
                if (cp == us(cn - 1, v)) {
                  return(false);
                }
                else {
                  return (dissonance[interval]);
                }
              }
              else {
                if (!(aStep(cp - us(cn - 1, v)))) {
                  return (dissonance[interval]);
                }
                return(false);
              }
            }
          }
        }
      }
    }
    return(false);
  }
  
  /**
   * NOTE: C version returned int
   * @param pitch
   * @param cn
   * @param v
   * @return 
   */
  boolean doubled(int pitch, int cn, int v) {
    int vNum;
    for (vNum = 0; vNum < v; vNum++) {
      if ((other(cn, v, vNum) % 12) == pitch) {
        return(true);
      }
    }
    return(false);
  }

  static int infinity = 1000000;
  static int bad = 100;
  static int realBad = 200;

  static int unisonPenalty = bad;
  static int directToFifthPenalty = realBad;
  static int directToOctavePenalty = realBad;
  static int parallelFifthPenalty = infinity;
  static int parallelUnisonPenalty = infinity;
  static int endOnPerfectPenalty = infinity;
  static int noLeadingTonePenalty = infinity;
  static int dissonancePenalty = infinity;
  static int outOfRangePenalty = realBad;
  static int outOfModePenalty = infinity;
  static int twoSkipsPenalty = 1;
  static int directMotionPenalty = 1;
  static int perfectConsonancePenalty = 2;
  static int compoundPenalty = 1;
  static int tenthToOctavePenalty = 8;
  static int skipTo8vePenalty = 8;
  static int skipFromUnisonPenalty = 4;
  static int skipPrecededBySameDirectionPenalty = 1;
  static int fifthPrecededBySameDirectionPenalty = 3;
  static int sixthPrecededBySameDirectionPenalty = 8;
  static int skipFollowedBySameDirectionPenalty = 3;
  static int fifthFollowedBySameDirectionPenalty = 8;
  static int sixthFollowedBySameDirectionPenalty = 34;
  static int twoSkipsNotInTriadPenalty = 3;
  static int badMelodyPenalty = infinity;
  static int extremeRangePenalty = 5;
  static int lydianCadentialTritonePenalty = 13;
  static int upperNeighborPenalty = 1;
  static int lowerNeighborPenalty = 1;
  static int overTwelfthPenalty = infinity;
  static int overOctavePenalty = bad;
  static int sixthLeapPenalty = 2;
  static int octaveLeapPenalty = 5;
  static int badCadencePenalty = infinity;
  static int directPerfectOnDownbeatPenalty = infinity;
  static int repetitionOnUpbeatPenalty = bad;
  static int dissonanceNotFillingThirdPenalty = infinity;
  static int unisonDownbeatPenalty = 3;
  static int twoRepeatedNotesPenalty = 2;
  static int threeRepeatedNotesPenalty = 4;
  static int fourRepeatedNotesPenalty = 7;
  static int leapAtCadencePenalty = 13;
  static int notaCambiataPenalty = infinity;
  static int notBestCadencePenalty = 8;
  static int unisonOnBeat4Penalty = 3;
  static int notaLigaturePenalty = 21;
  static int lesserLigaturePenalty = 8;
  static int unresolvedLigaturePenalty = infinity;
  static int noTimeForaLigaturePenalty = infinity;
  static int eighthJumpPenalty = bad;
  static int halfUntiedPenalty = 13;
  static int unisonUpbeatPenalty = 21;
  static int melodicBoredomPenalty = 1;
  static int skipToDownBeatPenalty = 1;
  static int threeSkipsPenalty = 3;
  static int downBeatUnisonPenalty = bad;
  static int verticalTritonePenalty = 2;
  static int melodicTritonePenalty = 8;
  static int ascendingSixthPenalty = 1;
  static int repeatedPitchPenalty = 1;
  static int notContraryToOthersPenalty = 1;
  static int notTriadPenalty = 34;
  static int innerVoicesInDirectToPerfectPenalty = 21;
  static int innerVoicesInDirectToTritonePenalty = 13;
  static int sixFiveChordPenalty = infinity;
  static int unpreparedSixFivePenalty = bad;
  static int unresolvedSixFivePenalty = bad;
  static int augmentedIntervalPenalty = infinity;
  static int thirdDoubledPenalty = 5;
  static int doubledLeadingTonePenalty = infinity;
  static int doubledSixthPenalty = 5;
  static int doubledFifthPenalty = 3;
  static int tripledBassPenalty = 3;
  static int upperVoicesTooFarApartPenalty = 1;
  static int unresolvedLeadingTonePenalty = infinity;
  static int allVoicesSkipPenalty = 8;
  static int directToTritonePenalty = bad;
  static int crossBelowBassPenalty = infinity;
  
  static int crossAboveCantusPenalty = infinity;
  static int noMotionAgainstOctavePenalty = 34;
  
  /**
   * 
   * @param cn
   * @param cp
   * @param v
   * @param other0
   * @param other1
   * @param other2
   * @param numParts
   * @param species
   * @param melInt
   * @param interval
   * @param actInt
   * @param lastIntClass
   * @param pitch
   * @param lastMelInt
   * @param curLim
   * @return 
   */
  int specialSpeciesCheck(int cn, int cp, int v, int other0, int other1, 
                          int other2, int numParts, int species, int melInt,
                          int interval, int actInt, int lastIntClass, 
                          int pitch, int lastMelInt, int curLim) {
    int val, i, lastDisInt = 0;
    boolean above;
    if (species == 1) {
      return(0);
    }	/* no special rules for 1st species */
    val = 0;
    if (species == 2) {
      if ((nextToLastNote(cn, v)) && ((pitch == 11) || (pitch == 10))) {
        if ((mode != phrygian) || (interval >= 0)) {
          if (lastIntClass != fifth) {
            val += badCadencePenalty;
          }
        }
        else {
          if (lastIntClass != minorSixth) {
            val += badCadencePenalty;
          }
        }
      }
    }
    else {
      if (species == 4) {
        if ((downBeat(cn, v)) && (melInt != unison)) {
          val += notaLigaturePenalty;
        }
        if ((upBeat(cn, v)) && (dissonance[lastIntClass])) {
          if ((melInt != (-minorSecond)) && (melInt != (-majorSecond))) {
            val += unresolvedLigaturePenalty;
          }
          if ((actInt == unison) && ((interval < 0) || 
              (((Math.abs(us(cn - 2, v) - other2)) % 12) == unison))) {
            val += noTimeForaLigaturePenalty;
          }
          if ((actInt == fifth) || (actInt == tritone)) {
            val += noTimeForaLigaturePenalty;
          }
        }
      }
      else {
        above = (interval >= 0);

        /* added check to stop optimizer from changing 4th beat passing tones into repeated notes+skip */
        if (((beat8(onset[cn][v]) == 6) || (beat8(onset[cn][v]) == 7)) && 
            (cp == us(cn - 1, v))) {
          val += unisonOnBeat4Penalty;
        }

        /* skip to down beat seems not so great */
        if (beat8(onset[cn][v]) == 0) {
          if (aSkip(melInt)) {
            val += skipToDownBeatPenalty;
          }
          if ((cn > 2) && ((actInt == unison) || (actInt == fifth))) {
            if (species == 5) {
              i = (cn - 1);
              while ((i > 0) && ((beat8(onset[i][v])) != 0)) {
                i--;
              }
            }
            else {
              i = (cn - 4);
            }
            if (((Math.abs(us(i, v) - bass(i, v))) % 12) == actInt) {
              val += downBeatUnisonPenalty;
            }
          }
        }

        /* check for cambiata not resolved correctly (on 4th beat) */
        if ((beat8(onset[cn][v]) == 6) && 
            ((aThird(Math.abs(lastMelInt))) &&
            ((dissonance[(Math.abs(us(cn - 2, v) - other2)) % 12]) &&
            ((melInt < 0) || ((Math.abs(melInt) != majorSecond) && 
            (Math.abs(melInt) != minorSecond)))))) {
          val += notaCambiataPenalty;
        }
        if (val >= curLim) {
          return (val);
        }

        if ((species == 3) && ((cn > 1) && (dissonance[lastIntClass]))) {
          switch (beat8(onset[cn][v])) {
            case 0: case 6:
              if ((!(aStep(melInt))) || ((!(aStep(lastMelInt))) || 
                  ((melInt * lastMelInt) <0 ))) {
                val += dissonancePenalty;
              }
              break;
            case 2:
              val += dissonancePenalty;
              break;
            case 4:
              if ((!(aStep(lastMelInt))) || ((Math.abs(melInt) > majorThird) ||
                  ((melInt == 0) || ((lastMelInt * melInt) < 0)))) {
                val += dissonancePenalty;
              }
              else {
                if (!(aStep(melInt))) {
                  if (above) {
                    if (!(aSeventh(lastIntClass))) {
                      val += dissonancePenalty;
                    }
                  }
                  else {
                    if (lastIntClass != fourth) {
                      val += dissonancePenalty;
                    }
                  }
                } 
              }
              break;
          }
        }

        if (species == 5) {
          if ((cn > 1) && ((beat8(onset[cn][v]) == 0) && 
              ((cp != us(cn-1, v)) && (dur[cn][v] <= dur[cn-1][v])))) {
            val += lesserLigaturePenalty;
          }
          if ((cn > 3) && ((dur[cn][v] == halfNote) && 
              ((beat8(onset[cn][v]) == 4) &&
              ((dur[cn-1][v] == quarterNote) &&
              (dur[cn - 2][v] == quarterNote))))) {
            val += halfUntiedPenalty;
          }
          if ((dur[cn][v] == eighthNote) && ((downBeat(cn, v)) && 
              (dissonance[actInt]))) {
            val += dissonancePenalty;
          }
          if (val >= curLim) {
            return(val);
          }
          if (cn > 1) {
            lastDisInt = ((Math.abs(us(cn - 1, v) - other1)) % 12);
          }
          if ((cn > 1) && (dissonance[lastDisInt])) {
            switch (beat8(onset[cn - 1][v])) {
              case 6: case 4:
                if (!((lastDisInt == fourth) && ((melInt == unison) &&
                    (((other0 - other1) == unison) && 
                    (beat8(onset[cn][v]) == 0))))) {
                  if ((!(aStep(melInt))) || ((!(aStep(lastMelInt))) || 
                      (((melInt * lastMelInt) < 0) || 
                      ((dur[cn - 1][v] == eighthNote) ||
                      ((dur[cn - 1][v] == quarterNote) && 
                      (dur[cn - 2][v] == halfNote)))))) {
                    val += dissonancePenalty;
                  }
                }
                break;
              case 1: case 3: case 5: case 7:
                if ((!(aStep(melInt))) || ((!(aStep(lastMelInt))) || 
                    ((melInt * lastMelInt) < 0))) {
                  val += dissonancePenalty;
                }
                break;
              case 0:
                if ((dur[cn - 2][v] == eighthNote) || 
                    (dur[cn - 2][v] < dur[cn - 1][v])) {
                  val += noTimeForaLigaturePenalty;
                }
                if ((melInt != (-minorSecond)) && 
                    (melInt != (-majorSecond))) {
                  val += unresolvedLigaturePenalty;
                }
                if ((actInt == fourth) || (actInt == tritone)) {
                  val += noTimeForaLigaturePenalty;
                }
                if ((actInt == fifth) && (interval < 0)) {
                  val += noTimeForaLigaturePenalty;
                }
                if ((actInt == 0) && 
                    (((Math.abs(us(cn - 2, v) - other2)) % 12) == 0)) {
                  val += noTimeForaLigaturePenalty;
                }
                if (lastMelInt != unison) {
                  val += dissonancePenalty;
                }
                break;
              case 2:
                if ((!(aStep(lastMelInt))) || 
                    ((Math.abs(melInt) > majorThird) ||
                    ((melInt == 0) || ((dur[cn - 1][v] == eighthNote) || 
                    ((lastMelInt * melInt) < 0))))) {
                  val += dissonancePenalty;
                }
                else {
                  if (!(aStep(melInt))) {
                    if (above) {   
                      if (!(aSeventh(lastIntClass))) {
                        val += dissonancePenalty;
                      }
                    }
                    else {
                      if (lastIntClass != fourth) {
                        val += dissonancePenalty;
                      }
                    }
                  }
                }
                break;
            }
          }
          if ((cn > 1) && ((dur[cn - 1][v] == eighthNote) &&
              (!(aStep(melInt))))) {
            val += eighthJumpPenalty;
          }
          if ((cn > 1) && ((dur[cn - 1][v] == halfNote) && 
              ((beat8(onset[cn][v]) == 4) && (melInt == unison)))) {
            val += unisonUpbeatPenalty;
          }
        }
      }
    }
    return(val);
  }

  static int INTERVALS_WITH_BASS_SIZE = 8;
  int intervalsWithBass[] = new int[INTERVALS_WITH_BASS_SIZE];
  /* 0 = octave, 2 = step, 3 = third, 4 = fourth, 
     5 = fifth, 6 = sixth, 7 = seventh */

  /**
   * 
   * @param n 
   */
  void addInterval(int n) {
    int actInt = 0;
    switch (n % 12) {
      case 0: actInt = 0; break;
      case 1: case 2: actInt = 2; break;
      case 3: case 4: actInt = 3; break;
      case 5: case 6: actInt = 4; break;
      case 7: actInt = 5; break;
      case 8: case 9: actInt = 6; break;
      case 10: case 11: actInt = 7; break;
    }
    intervalsWithBass[actInt]++;
  }
  
  /**
   * 
   * @param cn
   * @param cp
   * @param v
   * @param numParts
   * @param species
   * @param curLim
   * @return 
   */
  int otherVoiceCheck(int cn, int cp, int v, int numParts, int species, 
                      int curLim) {
    int val, k, curBass, other0, other1, int0, int1, actPitch, intBass,
        lastCp, i, ourLastInt = 0;
    boolean allSkip = false;
    if (v == 1) {
      return(0);
    }	/* two part or bass voice, so nothing to check */
    for (i = 0; i < INTERVALS_WITH_BASS_SIZE; i++) {
      intervalsWithBass[i] = 0;
    }
    val = 0;
    curBass = bass(cn, v);
    if (cp <= curBass) {
      val += crossBelowBassPenalty;
    }
    intBass = ((cp - curBass) % 12);
    if ((intBass == majorThird) && (!(inMode(curBass, mode)))) {
      val += augmentedIntervalPenalty;
    }
    actPitch = (cp % 12);
  
    if ((val >= curLim) || ((v == numParts) && (dissonance[intBass]))) {
      return(val);
    }
    /* logic here is that only the last part can be non-1st species
       and may therefore have various dissonances that don't want to be
       calculated as chord tones */
    lastCp = us(cn - 1, v);
    allSkip = aSkip(cp - lastCp);
    addInterval(intBass);
    for (k = 0; k < v; k++) {
      other0 = other(cn, v, k);
      other1 = other(cn-1, v, k);
      if (!(aSkip(other0 - other1))) {
        allSkip = false;
      }
      addInterval(other0 - curBass);	/* add up tones in chord */
      /* avoid unison with other voice */
      if ((!(lastNote(cn, v))) && (other0 == cp)) {
        val += unisonPenalty;
      }

      /* keep upper voices closer together than lower */
      if ((other0 != curBass) && 
          ((Math.abs(cp - other0)) >= (octave + fifth))) {
        val += upperVoicesTooFarApartPenalty;
      }

      /* check for direct motion to perfect consonance between these two voices */
      int0 = ((Math.abs(other0 - cp)) % 12);
      int1 = ((Math.abs(other1 - lastCp)) % 12);
      if (int1 == int0) {
        if (int0 == unison) {
          val += parallelUnisonPenalty;
        }
	    else if (int0 == fifth) {
          val += parallelFifthPenalty;
        }
	  }
      if ((cn > 2) && ((int0 == unison) && 
          (((Math.abs(us(cn - 2, v) - other(cn - 2, v, k))) % 12) == unison))) {
        val += parallelUnisonPenalty;
      }

      if (val >= curLim) {
        return(val);
      }

      /* penalize tritones between voices */
      if (int0 == tritone) {
        val += verticalTritonePenalty;
      }

      if (species == 5) {
        if ((dissonance[int1]) && (int1 != fourth)) {
          ourLastInt = ((lastCp - bass(cn - 1, v)) % 12);
          if (ourLastInt != unison)	{
            /* if unison, 6-6 somewhere else? */
            if (ourLastInt == fifth) {
              if ((aSkip(cp - lastCp)) || (cp >= lastCp)) {
                val += unresolvedSixFivePenalty;
              }
		    }
            else {
		      if ((aSkip(other0 - other1)) || (other0 >= other1)) {
                val += unresolvedSixFivePenalty;
              }
		    }
		  }
	    }
        if ((dissonance[int0]) && ((int0 != fourth) && 
            (intBass != unison))) {
          if ((intBass == fifth && ((cp - lastCp) != unison)) ||
		      ((intBass != fifth) && ((other0 - other1) != unison))) {
		    val += unpreparedSixFivePenalty;
          }
	    }
	  }

      /* penalize direct motion to perfect consonance except at the cadence */
      if ((!(lastNote(cn ,v))) && 
          (directMotionToPerfectConsonance(lastCp, cp, other1, other0))) {
	    val += innerVoicesInDirectToPerfectPenalty;
      }

      /* if we have an unraised leading tone it is possible that some other
       * voice has the raised form thereof (since the voices can move at very
       * different paces, one voice's next to last note may be long before
       * another's) 
       */
      if ((actPitch == 10) &&	 	        /* if 11 we've aready checked */
	      ((other0 % 12) == 11)) {		/* They have the raised form */
        val += doubledLeadingTonePenalty;
      }

      /* similarly for motion to a tritone */
      if ((motionType(lastCp, cp, other1, other0) == directMotion) && 
          (int0 == tritone)) {
        val += innerVoicesInDirectToTritonePenalty;
      }

      /* look for a common diminished fourth (when a raised leading tone is in 
       * the bass, a "major third" above it is actually a diminished fourth.
       * Similarly, an augmented fifth can be formed in other cases 
       */
      if ((actPitch == 3) && ((other0 % 12) == 11)) {
        val += augmentedIntervalPenalty;
      }

      /* try to encourage voices not to move in parallel too much */
      if (motionType(lastCp, cp, other1, other0) != contraryMotion) {
        val += notContraryToOthersPenalty;
      }
    }

    /* check for doubled third */
    if (intervalsWithBass[3] > 1) {
      val += thirdDoubledPenalty;
    }

    /* check for doubled sixth */
    if ((intervalsWithBass[3] == 0) && (intervalsWithBass[6] > 1)) {
      val += doubledSixthPenalty;
    }
  
    /* check for too many voices at octaves */
    if (intervalsWithBass[0] > 2) {
      val += tripledBassPenalty;
    }

    /* check for doubled fifth */
    if (intervalsWithBass[5] > 1) {
      val += doubledFifthPenalty;
    }

    /* check that chord contains at least one third or sixth */
    if ((v == numParts) && ((!(lastNote(cn, v))) && 
        ((intervalsWithBass[3] == 0) && (intervalsWithBass[6] == 0)))) {
      val += notTriadPenalty;
    }
  
    /* discourage all voices from skipping at once */
    if ((v == numParts) && allSkip) {
      val += allVoicesSkipPenalty;
    }
  
    /* except in 5th species, disallow 6-5 chords altogether */
    if ((intervalsWithBass[5] > 0) && ((intervalsWithBass[6] > 0) && 
        (species != 5))) {
      val += sixFiveChordPenalty;
    }
    return(val);
  }
  
  int check(int cn, int cp, int v, int numParts, int species, int curLim) {
    int val, k, interval, intClass, pitch, lastIntClass = 0, melInt, 
        lastMelInt = 0, other0, other1, other2 = 0;
    int cross, lastPitch, totalJump, 
        lastCp, lastCp2, lastCp3, lastCp4;
    boolean weHaveARealLeadingTone, sameDir = false;
    if (v == 1) {
      other0 = cantus(cn, v);
      other1 = cantus(cn - 1, v);
      if (cn > 2) {
        other2 = cantus(cn - 2, v);
      }
    }
    else {
      other0 = bass(cn,v);
      other1 = bass(cn - 1, v);
      if (cn > 2) {
        other2 = bass(cn - 2, v);
      }
    }
    val = 0;
    lastCp = us(cn - 1, v);
    lastCp2 = 0; 
    lastCp3 = 0; 
    lastCp4 = 0;
    interval = (cp - other0);
    intClass = (Math.abs(interval)) % 12;
    melInt=(cp - lastCp);
    pitch = (cp % 12);

    /* melody must stay in range */
    if (outOfRange(cp + basePitch)) {
      val += outOfRangePenalty;
    }

    /* extremes of range are also bad (to be avoided) */
    if (extremeRange(cp + basePitch)) {
      val += extremeRangePenalty;
    }

    /* two part with ctrpt below cantus -- keep it below */
    if ((numParts == 1) && ((us(1, v) < cantus(1, v)) && 
        (interval > unison))) {
      val += crossAboveCantusPenalty;
    }

    /* Chromatically altered notes are accepted only at the cadence.  Other alterations (such as ficta) will be handled later) */
    if (!(nextToLastNote(cn, v))) {
      if (species != 2) {
        if (!(inMode(pitch, mode))) {
          val += outOfModePenalty;
        }
	  }
      else {
	    if ((cn != totalNotes[v] - 2) || ((mode != aeolian) || 
            ((cp <= other0) || (intClass != fifth)))) {
          if (!(inMode(pitch, mode))) {
            val += outOfModePenalty;
          }
	    }
	  }
    }
    else {
      weHaveARealLeadingTone = ((pitch == 11) || ((pitch == 10) && 
                                (mode == phrygian)));
      if (weHaveARealLeadingTone) {
	    if (doubled(pitch, cn, v)) {
          val += doubledLeadingTonePenalty;
        }
	  }
      else {
	    if (pitch == 10) {
          val += badCadencePenalty;
        }
        else {
	      if (!(inMode(pitch, mode))) {
            val += outOfModePenalty;
          }
          else {
		    if (v == numParts) {
		      if ((!(doubled(11, cn, v))) && (!(doubled(10, cn, v)))) {
                val += noLeadingTonePenalty;
              }
		    }
		  }
	    }
	  }
    }
    if (val >= curLim) {
      return(val);
    }
    if (cn > 2) {
      lastCp2 = us(cn - 2, v);
      if ( cn > 3) {
	    lastCp3 = us(cn - 3, v);
	    if (cn > 4) {
          lastCp4 = us(cn - 4, v);
        }
	  }
      lastMelInt = (lastCp - lastCp2);
      sameDir = ((melInt * lastMelInt) >= 0);
    }
    if (cn > 1) {
      lastIntClass = ((Math.abs(lastCp - other1)) % 12);
    }
    if (aDissonance(intClass, cn, cp, v, species)) {
      val += dissonancePenalty;
    }
    if (val >= curLim) {
      return(val);
    }
    val += specialSpeciesCheck(cn, cp, v, other0, other1, other2,
                               numParts, species, melInt, interval,
                               intClass, lastIntClass, pitch, lastMelInt,
                               curLim);
    if (v > 1) {
      val += otherVoiceCheck(cn, cp, v, numParts, species, curLim);
    }
    if (firstNote(cn, v)) {
      return(val);
    }
    /* no further rules apply to first note */
    if (val >= curLim) {
      return(val);
    }

    /* direct motion to perfect consonances considered harmful */
    if ((!(lastNote(cn, v))) || (numParts == 1)) {
      if (directMotionToPerfectConsonance(lastCp, cp, other1, other0)) {
        if (intClass == unison) {
          val += directToOctavePenalty;
        }  
        else {
          val += directToFifthPenalty;
        }
      }
    }

    /* check for more blatant examples of the same error */
    if ((intClass == fifth) && (lastIntClass == fifth)) {
      val += parallelFifthPenalty;
    }
    if ((intClass == unison) && (lastIntClass == unison)) {
      val += parallelUnisonPenalty;
    }
    if (val >= curLim) {
      return(val);
    }

    if ((cn > 1) && ((species == 1) && ((numParts == 1) && 
        ((intClass == lastIntClass) && (melInt == unison))))) {
      val += noMotionAgainstOctavePenalty;
    }

    /* certain melodic intervals are disallowed */
    if (badMelody(melInt)) {
      val += badMelodyPenalty;
    }
    if (val >= curLim) {
      return(val);
    }

    /* must end on unison or octave in two parts, 
       fifth and major third allowed in 3 and 4 part writing */
    if ((lastNote(cn, v)) && (intClass != unison)) {
      if ((numParts == 1) || (interval < 0)) {
        val += endOnPerfectPenalty;
      }
      else {
        if ((intClass != fifth) && (intClass != majorThird)) {
          val += endOnPerfectPenalty;
        }
	  }
    }

    /* penalize direct motion any kind (contrary motion is better) */
    if (motionType(lastCp, cp, other1, other0) == directMotion) {
      val += directMotionPenalty;
      if (intClass == tritone) {
        val += directToFifthPenalty;
      }
    }

    /* penalize compound intervals (close position is favored) */
    if ((Math.abs(interval)) > octave) {
      val += compoundPenalty;
    }

    /* penalize consecutive skips in the same direction */
    if ((cn > 2) && (consecutiveSkipsInSameDirection(lastCp2, lastCp, cp))) {
      val += twoSkipsPenalty;
      totalJump = Math.abs(cp - lastCp2);

      /* do not let these skips traverse more than an octave, nor a seventh */
      if ((totalJump > majorSixth) && (totalJump < octave)) {
        val += twoSkipsNotInTriadPenalty;
      }
    }

    /* penalize a skip to an octave */
    if ((intClass == unison) && ((aSkip(melInt)) || (aSkip(other0 - other1)))) {
      val += skipTo8vePenalty;
    }

    /* do not skip from a unison (not a very important rule) */
    if ((other1 == lastCp) && (aSkip(melInt))) {
      val += skipFromUnisonPenalty;
    }

    /* penalize skips followed or preceded by motion in same direction */
    if ((cn > 2) && ((aSkip(melInt)) && sameDir)) {
      /* especially penalize fifths, sixths, and octaves of this sort */
      if ((Math.abs(melInt)) < fifth) {
        val += skipPrecededBySameDirectionPenalty;
      }
      else {
        if (((Math.abs(melInt)) == fifth) || ((Math.abs(melInt)) == octave)) { 
	      val += fifthPrecededBySameDirectionPenalty;
        }
	    else {
          val += sixthPrecededBySameDirectionPenalty;
        }
	  }
    }
    if ((cn > 2) && ((aSkip(lastMelInt)) && sameDir)) {
      if ((Math.abs(lastMelInt)) < fifth) {
        val += skipFollowedBySameDirectionPenalty;
      }
      else {
        if (((Math.abs(lastMelInt)) == fifth) || 
            ((Math.abs(lastMelInt)) == octave)) {
          val += fifthFollowedBySameDirectionPenalty;
        }
        else {
          val += sixthFollowedBySameDirectionPenalty;
        }
	  }
    }

    /* too many skips in a row -- favor a mix of steps and skips */
    if ((cn > 4) && ((aSkip(melInt)) && ((aSkip(lastMelInt)) && 
        (aSkip(lastCp2 - lastCp3))))) {
      val += melodicBoredomPenalty;
    }

    /* avoid tritones melodically */
    if ((cn > 4) && (((Math.abs(cp - lastCp2)) == tritone) || 
        (((Math.abs(cp - lastCp3)) == tritone) || 
         ((Math.abs(cp - lastCp4)) == tritone)))) {
      val += melodicTritonePenalty;
    }

    /* do not allow movement from a tenth to an octave by contrary motion */
    if ((species != 5) && (numParts == 1)) {
      if (aTenth(other1 - lastCp) && (anOctave(interval))) {
        val += tenthToOctavePenalty;
      }
    }

    /* more range checks -- did we go over an octave recently */
    if ((cn > 2) && ((Math.abs(cp - lastCp2)) > octave)) {
      val += overOctavePenalty;
    }

    /* same for a twelfth */
    if (((cn > 30) || (species != 5)) && 
        (totalRange(cn, cp, v) > (octave + fifth))) {
      val += overTwelfthPenalty;
    }
    if (val >= curLim) {
      return(val);
    }

    /* slightly penalize repeated notes */
    if ((cn > 3) && ((cp == lastCp2) && (lastCp == lastCp3))) {
      val += twoRepeatedNotesPenalty;
    }
    if ((cn > 5) && ((cp == lastCp3) && ((lastCp == lastCp4) && 
        (lastCp2 == us(cn - 5, v))))) {
      val += threeRepeatedNotesPenalty;
    }
    if ((cn > 6) && ((cp == lastCp4) && ((lastCp == us(cn - 5, v)) &&
        (lastCp2 == us(cn - 6, v))))) {
      val += (threeRepeatedNotesPenalty - 1);
    }
    if ((cn > 7) && ((cp == lastCp4) && ((lastCp == us(cn - 5, v)) &&
        ((lastCp2 == us(cn - 6, v)) && (lastCp3 == us(cn - 7, v)))))) {
      val += fourRepeatedNotesPenalty;
    }
    if ((cn > 8) && ((cp == us(cn - 5, v)) && ((lastCp == us(cn - 6, v)) &&
        ((lastCp2 == us(cn - 7, v)) && (lastCp3 == us(cn - 8, v)))))) {
      val += fourRepeatedNotesPenalty;
    }
    if (lastNote(cn, v)) {
      lastPitch = (lastCp % 12);
      if (((lastPitch == 11) || ((lastPitch == 10) && 
          (mode == phrygian))) && (pitch != 0)) {
        val += unresolvedLeadingTonePenalty;
      }
    }
    if (val >= curLim) {
      return(val);
    }

    /* an imperfect consonance is better than a perfect consonance */
    if (perfectConsonance[intClass]) {
      val += perfectConsonancePenalty;
    }

    /* no unisons allowed within counterpoint unless more than 2 parts */
    if ((numParts == 1) && (interval == unison)) {
      val += unisonPenalty;
    }
    if (val >= curLim) {
      return(val);
    }

    /* seek variety by avoiding pitch repetitions */
    val += (pitchRepeats(cn, cp, v)>>1);

    /* penalize octave leaps a little */
    if (anOctave(melInt)) {
      val += octaveLeapPenalty;
    }

    /* similarly for minor sixth leaps */
    if (melInt == minorSixth) {
      val += sixthLeapPenalty;
    }

    /* penalize upper neighbor notes slightly (also lower neighbors) */
    if ((cn > 2) && ((melInt < 0) && ((aStep(melInt)) && (cp == lastCp2)))) {
      val += upperNeighborPenalty;
    }
    
    if ((cn > 2) && ((melInt > 0) && ((aStep(melInt)) && (cp == lastCp2)))) { 
      val += lowerNeighborPenalty;
    }
    
    /* do not allow normal leading tone to precede raised leading tone */
    /* also check here for augmented fifths and diminished fourths */
    if ((!(inMode(pitch, mode))) && ((melInt == minorSecond) || 
        ((melInt == minorSixth) || (melInt == (-majorThird))))) {
      val += outOfModePenalty;
    }   

    /* slightly frown upon leap back in the opposite direction */
    if ((cn > 2) && ((aSkip(melInt)) && ((aSkip(lastMelInt)) && 
        (!(sameDir))))) {
      val += (Math.max(0,((Math.abs(melInt) + Math.abs(lastMelInt)) - 8)));
      if ((cn > 3) && (aSkip(lastCp2 - lastCp3))) {
        val += threeSkipsPenalty;
      }
    }

    /* try to approach cadential passages by step */
    if ((numParts == 1) && ((cn >= (totalNotes[v] - 4)) && 
        ((Math.abs(melInt)) > 4))) {
      val += leapAtCadencePenalty;
    }

    /* check for entangled voices */
    cross = 0;
    if (numParts == 1) {
      for (k = 4; k <= cn; k++) {
        if ((us(k, v) - cantus(k, v)) * (us(k-1, v) - cantus(k - 1, v)) < 0) {
          cross++;
        }
	  }
    }
    if (cross > 0) {
      val += (Math.max(0, ((cross - 2) * 3)));
    }
  
    /* don't repeat note on upbeat */
    if (upBeat(cn, v) && (melInt == unison)) {
      val += repetitionOnUpbeatPenalty;
    }
 
    /* avoid tritones near Lydian cadence */
    if ((mode == lydian) && ((cn > (totalNotes[v] - 4)) && (pitch == 6))) {
      val += lydianCadentialTritonePenalty;
    }

    /* various miscellaneous checks.  More elaborate dissonance resolution and cadential formula checks will be given under "Species definition" */
    if ((species != 1) && (downBeat(cn, v))) {
      if (species < 4) {
	    if ((melInt == unison) && (!(lastNote(cn, v)))) {
          val += unisonDownbeatPenalty;
        }
	    /* check for dissonance that doesn't fill a third as a passing tone */
	    if ((dissonance[lastIntClass]) && 
            ((!(aStep(melInt))) || (!(sameDir)))) {
          val += dissonanceNotFillingThirdPenalty;
        }
	  }

      /* check for Direct 8ve or 5 where the intervening interval is less than a fourth */
      if ((directMotionToPerfectConsonance(lastCp2, cp, other2, other0)) && 
          ((Math.abs(lastMelInt)) < fourth)) {
	    val += directPerfectOnDownbeatPenalty;
      }
    }

    /* check for tritone with cantus or bass */
    if (intClass == tritone) {
      val += verticalTritonePenalty;
    }

    /* check for melodic interval variety */
    if ((cn > 10) && (tooMuchOfInterval(cn, cp, v))) {
      val += melodicBoredomPenalty;
    }

    return(val);
  }

  int bestFit[][] = new int[mostNotes][mostVoices];
  /* next-to-best fits (for testing) */
  int bestFit1[][] = new int[mostNotes][mostVoices];
  int bestFit2[][] = new int[mostNotes][mostVoices];
  int fits[] = new int[3];
  int bestFitPenalty, maxPenalty, branches;
  boolean allDone;
  float penaltyRatio;
  
  int numFields = 16;
  int field = mostVoices + 1;
  int endF = (field * numFields);
  
  /**
   * 
   * @param indx
   * @param sp
   * @return 
   */
  int saveIndx(int indx, int[] sp) {
    int i;
    /* if INDX is less than current NUMFIELD-th worst,
     * find its position in SP, insert space for its
     * data, and return a pointer to the block.  The
     * blocks are stored "backwards" for (SAIL's) ARRBLT 
     */
    i = endF;
    while ((i >= 0) && (sp[i] <= indx)) {
      i -= field;
    }
    if (i > 0) { /* 0 is the end of the list.  If i>0 then we insert INDX */
      arrBlt(sp, 0, field, i);
      sp[i] = indx;
      /* SP[i]=penalty for block starting at I.  SP[i-1]=index into
       * melodic interval array for voice 1, SP[i-2] for voice 2 and
       * so on.  The searcher starts at SP[EndF] and works backwards
       * through the stored continuations as it searches for a satisfactory
       * overall solution 
       */
    }
    return(i);
  }

  /**
   * 
   * @param currentPenalty
   * @param penalty
   * @param v1
   * @param species 
   */
  void saveResults(int currentPenalty, int penalty, int v1, int species) {
    int i, lastPitch, v, cn, k, pitch;
    boolean done;
    for (v = 1; v <= v1; v++) {
      /* check all voices for raised leading tone */
      cn = totalNotes[v];
      lastPitch = (us(cn - 1, v) % 12);	/* must be raised if any are */
      if (!(inMode(lastPitch, mode))) {    /* it is a raised leading tone */
        k = 2;
        while (true) {                  /* exit via break */
          /* look backwards through voice's notes */
          if (k >= (cn - 1)) {
            break;  /* ran off start!! */
          }	                
          pitch = (us(cn - k, v) % 12);	                /* current pitch */
          if (((pitch < 8) && (pitch != 0)) || /* not 6-7-1 scale degree anymore */
              (aSkip(us(cn - k + 1, v) - us(cn - k, v)))) { /* skip breaks drive to cadence */
            break;
          }
          pitch = Math.abs(us(cn - k, v) - us(cn - k - 1, v));  /* interval with raised leading tone */
          if ((pitch == fourth) || ((pitch == fifth) || ((pitch == unison) || 
              (pitch == octave)))) {
            break;
          }
          /* don't create illegal melody */
          done = false;
          i = 0;
          while (i <= v1) {            /* do others have unraised form? */
            if ((i != v) && (((other(cn - k, v, i)) % 12) == 11)) {
              done = true;
              break;
            }
            i++;
          }
          if (done) {
            break;
          }
          if (((us(cn - 1, v) - us(cn - k, v)) == minorThird) || 
              ((us(cn - 1, v) - us(cn - k, v)) == minorSecond)) {
            setUs(cn - k, us(cn - k, v) + 1, v);            /* raise it and maybe 6th degree too */
          }
          k++;
        }
      }
    }
    bestFitPenalty = currentPenalty + penalty;
    maxPenalty = Math.min((int)(bestFitPenalty * penaltyRatio), maxPenalty);
    /*  AllDone=1; */
    fits[2] = fits[1]; 
    fits[1] = fits[0]; 
    fits[0] = bestFitPenalty;
    for (v = 1; v <= v1; v++) {
      for (i = 1; i <= totalNotes[v]; i++) {
        bestFit2[i][v] = bestFit1[i][v];       
        bestFit1[i][v] = bestFit[i][v];        
        bestFit[i][v] = ctrpt[i][v] + basePitch; 
      }
    }
    
    System.out.println();
    System.out.println("[" + bestFitPenalty + "]");

    counterpointSolution = new CounterpointSolution();

    List<Note> cantusFirmusVoiceNotes = new ArrayList<Note>(cantusFirmus.length);
    for (int cfIdx = 0; cfIdx < cantusFirmus.length; cfIdx++) {
      System.out.print(onset[cfIdx + 1][0] + ":" + (ctrpt[cfIdx + 1][0] + basePitch) + ":" + dur[cfIdx + 1][0] + " ");
      Note note = new Note(onset[cfIdx + 1][0], ctrpt[cfIdx + 1][0] + basePitch, dur[cfIdx + 1][0]);
      cantusFirmusVoiceNotes.add(note);
    }
    System.out.println();
    counterpointSolution.addVoice(cantusFirmusVoiceNotes);

    for (v = 1; v <= v1; v++) {
      List<Note> computedVoiceNotes = new ArrayList<Note>(totalNotes[v]);
      for (i = 1; i <= totalNotes[v]; i++) {
	      //System.out.print(bestFit[i][v] + " ");
        System.out.print(onset[i][v] + ":" + bestFit[i][v] + ":" + dur[i][v] + " ");
        Note note = new Note(onset[i][v], bestFit[i][v], dur[i][v]);
        computedVoiceNotes.add(note);
	    }
      System.out.println();
      counterpointSolution.addVoice(computedVoiceNotes);
    }

    /*
    for (v = 1; v <= v1; v++) {
      for (i = 1; i <= totalNotes[v]; i++) {
        System.out.print(ctrpt[i][v] + " ");
      }
      System.out.println();
    }
    */

  }  
  
  int indx[] = {0,1,-1,2,-2,3,-3,0,4,-4,5,7,-5,8,12,-7,-12};

  /**
   * 
   * @param curPen
   * @param curVoice
   * @param numParts
   * @param species
   * @param lim
   * @param pens
   * @param is
   * @param curNotes
   * @return 
   */
  int look(int curPen, int curVoice, int numParts, int species, int lim, 
           int[] pens, int[] is, int[] curNotes) {
    int penalty, pit, i, x, tmp1, newLim;
    newLim = lim;
    for (is[curVoice] = 1; is[curVoice] <= 16; is[curVoice]++) {
      pit =indx[is[curVoice]] + ctrpt[curNotes[curVoice] - 1][curVoice];
      if (curVoice == numParts) {
        tmp1 = species;
      } 
      else {
        tmp1 = 1;
      }
      penalty = curPen + check(curNotes[curVoice], pit, curVoice, numParts, 
                tmp1, newLim);
      setUs(curNotes[curVoice], pit, curVoice);
      if (penalty < newLim) {
        if (curVoice < numParts) {
	      i = (curVoice + 1);
	      while (i <= numParts) {
		    if (curNotes[i] != 0) {
              break;
            }
		    i++;
		  }
          if (i <= numParts) {	/* there is another voice needing a note */
            newLim = look(penalty, i, numParts, species, newLim, 
                          pens, is, curNotes);
		  }
	    }
        else {
	      x = saveIndx(penalty, pens);
	      if (x > 0) {
		    for (i = 1; i <= numParts; i++) {
              pens[x-i] = is[i];
            }
		  }
	      else newLim = Math.min(newLim, penalty);
	    }
	  }
    }
    return(newLim);
  }
  
  void bestFitFirst(int curTime, int currentPenalty, int numParts, int species, 
                    int brLim) {
    int i, j, curMin, lim, choiceIndex, nextTime, ourTime;
    int[] pens, is, curNotes;
    if ((allDone) || (currentPenalty > maxPenalty)) {
      return;
    }

    branches++;
    //pens = new int[1 + (field * numFields)];
    pens = new int[1 + (field * numFields) + field]; //JLW TODO: Trying this
    is = new int[1 + numParts];
    curNotes = new int[1 + mostVoices];

    choiceIndex = endF;
    allDone = false;
    for (i = 0; i <= (field * numFields); i++) {
      pens[i] = infinity;
    }
    for (i = 0; i <= numParts; i++) {
      is[i] = 0;
    }
    for (i = 0; i <= mostVoices; i++) {
      curNotes[i] = 0;
    }

    if (branches == brLim) {
      maxPenalty = (int)(maxPenalty * penaltyRatio);
      branches =0;
    }

    curMin = infinity;
    lim = bestFitPenalty - currentPenalty;
    nextTime = infinity;
    for (i = 0; i <= numParts; i++) {
      ourTime = onset[vIndex(curTime , i) + 1][i];
      if (ourTime != 0) {
        nextTime = Math.min(nextTime, ourTime);
      }
    }
    for (i = 1; i <= numParts; i++) {
      j = vIndex(nextTime,i);
      if (onset[j][i] == nextTime) {
        curNotes[i] = j;
      }
    }
    i=1;
    while (i <= numParts) {
      if (curNotes[i] != 0) {
        break;
      }
      i++;
    }
    lim = look(0, i, numParts, species, lim, pens, is, curNotes);

    curMin = pens[choiceIndex];
    if (curMin < infinity) {
      allDone = false;
      while (!(allDone)) {
        if (curTime < totalTime) {
          if ((curMin + currentPenalty) >= maxPenalty) {
            break;
          }
        }
        else {
          if ((curMin + currentPenalty) >= bestFitPenalty) {
            break;
          }
        }

        for (i = 1; i <= numParts; i++) {
          if (curNotes[i] != 0) {
            setUs(curNotes[i], indx[pens[choiceIndex - i]] + us(curNotes[i] - 1, i),i);
          }
        }
        if (nextTime < totalTime) {
          bestFitFirst(nextTime, currentPenalty + curMin, numParts, 
                       species, brLim);
        }
        else {
          saveResults(currentPenalty, curMin, numParts, species);
        }

        choiceIndex = choiceIndex - field;
        if (choiceIndex <= 0) {
          break;
        }
        curMin = pens[choiceIndex];
        if (curMin == infinity) {
          break;
        }
        if (curTime == 0) {
          maxPenalty = (int)(bestFitPenalty * penaltyRatio);
        }
      }
    }

    //free(curNotes);
    //free(is);
    //free(pens);
  }
  
  int rhyPat[][] = new int[11][9];
  int rhyNotes[] = new int[11];
  
  /**
   * 
   */
  public void fillRhyPat() {
    rhyPat[0][1] = wholeNote;
    rhyNotes[0] = 1;
    
    rhyPat[1][0] = 0; 
    rhyPat[1][1] = halfNote; 
    rhyPat[1][2] = halfNote;
    rhyNotes[1] = 2;
    
    rhyPat[2][0] = 0; 
    rhyPat[2][1] = halfNote; 
    rhyPat[2][2] = quarterNote; 
    rhyPat[2][3] = quarterNote; 
    rhyNotes[2] = 3;
    
    rhyPat[3][0] = 0;
    rhyPat[3][1] = quarterNote; 
    rhyPat[3][2] = quarterNote; 
    rhyPat[3][3] = quarterNote; 
    rhyPat[3][4] = quarterNote; 
    rhyNotes[3] = 4;
    
    rhyPat[4][0] = 0; 
    rhyPat[4][1] = quarterNote; 
    rhyPat[4][2] = quarterNote; 
    rhyPat[4][3] = halfNote; 
    rhyNotes[4] = 3;
    
    rhyPat[5][0] = 0; 
    rhyPat[5][1] = quarterNote; 
    rhyPat[5][2] = eighthNote; 
    rhyPat[5][3] = eighthNote; 
    rhyPat[5][4] = halfNote;
    rhyNotes[5] = 4;
    
    rhyPat[6][0] = 0; 
    rhyPat[6][1] = quarterNote; 
    rhyPat[6][2] = eighthNote; 
    rhyPat[6][3] = eighthNote; 
    rhyPat[6][4] = quarterNote; 
    rhyPat[6][5] = quarterNote;
    rhyNotes[6] = 5;
    
    rhyPat[7][0] = 0; 
    rhyPat[7][1] = halfNote; 
    rhyPat[7][2] = quarterNote; 
    rhyPat[7][3] = eighthNote; 
    rhyPat[7][4] = eighthNote;
    rhyNotes[7] = 4;
    
    rhyPat[8][0] = 0; 
    rhyPat[8][1] = quarterNote; 
    rhyPat[8][2] = eighthNote; 
    rhyPat[8][3] = eighthNote; 
    rhyPat[8][4] = quarterNote; 
    rhyPat[8][5] = eighthNote; 
    rhyPat[8][6] = eighthNote; 
    rhyNotes[8]=6;
    
    rhyPat[9][0] = 0; 
    rhyPat[9][1] = quarterNote; 
    rhyPat[9][2] = quarterNote; 
    rhyPat[9][3] = quarterNote; 
    rhyPat[9][4] = eighthNote; 
    rhyPat[9][5] =eighthNote; 
    rhyNotes[9]=5;
    rhyPat[10][1] = wholeNote;
    rhyNotes[10]=1;
  }
  
  static long randx = 1;
  static float inverse_rscl = (float)0.000061035156;
  
  /**
   * 
   * @param n 
   */
  void usedRhy(int n) {
    rhyPat[n][0] = rhyPat[n][0] + 1;
  }
  
  /**
   * 
   * @param n
   * @return 
   */
  int curRhy(int n) {
    return(rhyPat[n][0]);
  }
  
  /**
   * 
   */
  void cleanRhy() {
    int i; 
    for (i = 1; i < 10; i++) {
      rhyPat[i][0] = 0;
    }
  }
  
  int goodRhy() {
    int i;
    i = (int)(Math.random() * 10.0);
    if (curRhy(i) > curRhy(Math.max(1, (i-1)))) {
      return(Math.max(1, (i-1)));
    }
    if (curRhy(i) <= curRhy(Math.min(9, (i + 1)))) {
      return(Math.min(9, (i + 1)));
    }
    return(i);
  }

  /**
   * 
   * @param ourMode
   * @param startPitches
   * @param cantusFirmusLength
   * @param species 
   */
  public CounterpointSolution anySpecies(int ourMode, int[] startPitches,
                  int cantusFirmusLength, int species) {
    int curV = startPitches.length;
    int i, j, k, m, v, oldSpecies, currentMode, brLim;
    for (i = 0; i < mostNotes; i++) {
      for (j = 1; j < mostVoices; j++) {
        bestFit[i][j] = 0;
        ctrpt[i][j] = 0;
      }
    }
    penaltyRatio = (float)(1.0 - (species * curV * 0.01));
    brLim = (50 * (6 - curV) * (6 - species));
    currentMode = ourMode;
    mode = ourMode;
    totalTime = ((cantusFirmusLength - 1) * 8);
    totalNotes[0] = cantusFirmusLength;
    basePitch = ((ctrpt[cantusFirmusLength][0]) % 12);
    bestFitPenalty = infinity;
    maxPenalty = infinity;
    allDone = false;
    branches = 0;

    for (i = 1; i <= cantusFirmusLength; i++) {
      ctrpt[i][0] -= basePitch;
      dur[i][0] = wholeNote;
      onset[i][0] = ((i - 1) * 8);
    }
    oldSpecies = species;
    for (v = 1; v <= curV; v++) {
      if (v != curV) {
        species = 1;
      }
      else {
        species = oldSpecies;
      }
      if (species == 1) {
        totalNotes[v] = cantusFirmusLength;
        for (i = 1; i < cantusFirmusLength; i++) {
          dur[i][v] = wholeNote;
        }
      }
      else if (species == 2) {
        totalNotes[v] = (cantusFirmusLength * 2) - 1;
        for (i = 1; i < totalNotes[v]; i++) {
          dur[i][v] = halfNote;
        }
      }
      else if (species == 3) {
        totalNotes[v] = (cantusFirmusLength * 4) - 3;
        for (i = 1; i < totalNotes[v]; i++) {
          dur[i][v] = quarterNote;
        }
      } 
      else if (species == 4) {
        totalNotes[v] = (cantusFirmusLength * 2) - 1;
        for (i = 1; i < totalNotes[v]; i++) {
          dur[i][v] = halfNote;
        }
      }
      else {
        cleanRhy();
        m = 0;
        for (i = 1; i < cantusFirmusLength; i++) {
          j = goodRhy();
          usedRhy(j);
          for (k = 1; k <= (rhyNotes[j]); k++) {
            dur[k+m][v] = rhyPat[j][k];
          }
          m += rhyNotes[j];
        }
        totalNotes[v] = (m + 1);
      }
      dur[totalNotes[v]][v] = wholeNote;
      onset[1][v] = 0;
      for (k = 2; k <= totalNotes[v]; k++) {
        onset[k][v] = (onset[k-1][v] + dur[k-1][v]);
      }
      ctrpt[1][v] = (startPitches[v-1] - basePitch);
    }
    if (curV == 1) {
      maxPenalty = (2 * realBad);
    } 
    else {
      maxPenalty = infinity;
    }
    bestFitFirst(0, 0, curV, species, brLim);
    return counterpointSolution;
  }

  /*
  void fillCantus(int c0, int c1, int c2, int c3, int c4, int c5, int c6, 
                  int c7, int c8, int c9, int c10, int c11, int c12, 
                  int c13, int c14) {
    ctrpt[1][0] = c0; 
    ctrpt[2][0] = c1; 
    ctrpt[3][0] = c2; 
    ctrpt[4][0] = c3; 
    ctrpt[5][0] = c4; 
    ctrpt[6][0] = c5; 
    ctrpt[7][0] = c6;
    ctrpt[8][0] = c7; 
    ctrpt[9][0] = c8; 
    ctrpt[10][0] = c9; 
    ctrpt[11][0] = c10; 
    ctrpt[12][0] = c11; 
    ctrpt[13][0] = c12;
    ctrpt[14][0] = c13; 
    ctrpt[15][0] = c14;
  }
  */
  
  private int[] cantusFirmus;
  void setCantusFirmus(int[] cantus) {
    cantusFirmus = new int[cantus.length];
    for (int i = 0; i < cantus.length && i < mostNotes; i++) {
      ctrpt[i + 1][0] = cantus[i];
    }
  }  
  
  /*****************************************
   * @param args the command line arguments
   */
  /*
  public static void main(String[] args) {
    CounterpointGenerator cg = new CounterpointGenerator();
    cg.fillRhyPat();
    int[] cf = {50, 53, 52, 50, 55, 53, 57, 55, 53, 52, 50};
    cg.setCantusFirmus(cf);
    //cg.fillCantus(50, 53, 52, 50, 55, 53, 57, 55, 53, 52, 50, 0, 0, 0, 0);
    int[] vbs = {38,57,62};
    cg.anySpecies(dorian, vbs, cf.length, 2);

    //System.out.println("cg.counterpointSolution.toScorePartwise(): " + cg.counterpointSolution.toScorePartwise());
    ScorePartwise scorePartwise = cg.counterpointSolution.toScorePartwise();
  }
  */
}
