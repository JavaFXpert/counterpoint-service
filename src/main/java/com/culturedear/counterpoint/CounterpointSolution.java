package com.culturedear.counterpoint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jamesweaver on 9/25/15.
 */
public class CounterpointSolution {
  // Default beats per measure
  private static int beatsPerMeasure = 4;

  // Default beats per measure.
  // TODO: Utilize this
  private static int beatType = 4;

  // Onset units per measure
  // TODO: Calculate as a function of 8 (eighth notes), beatsPerMeasure and beatType
  private static int onsetUnitsPerMeasure = 8;

  // First dimension is voice #, second is array of notes
  private List<List<Note>> notesAllVoices = new ArrayList<List<Note>>();

  public void addVoice(List<Note> notes) {
    notesAllVoices.add(notes);
  }

  public ScorePartwise toScorePartwise() {
    Measure curMeasure = new Measure(" ");
    ScorePartwise scorePartwise = new ScorePartwise();
    if (notesAllVoices.size() > 0) {
      scorePartwise.getScoreParts().add(new ScorePart("Cantus firmus", "P1"));
      for (int voiceIdxA = 1; voiceIdxA < notesAllVoices.size(); voiceIdxA++) {
        scorePartwise.getScoreParts().add(new ScorePart("Melody " + voiceIdxA, "P" + (voiceIdxA + 1)));
      }

      for (int voiceIdxB = 0; voiceIdxB < notesAllVoices.size(); voiceIdxB++) {
        Part part = new Part("P" + (voiceIdxB + 1));
        scorePartwise.getParts().add(part);

        List<Note> notesForPart = notesAllVoices.get(voiceIdxB);
        // Iteratate over the notes in this part and insert Measure elements
        Iterator<Note> noteIterator = notesForPart.iterator();

        while (noteIterator.hasNext()) {
          Note note = noteIterator.next();
          int onset = note.getOnset();
          if (onset % onsetUnitsPerMeasure == 0) {
            curMeasure = new Measure("" + (onset / onsetUnitsPerMeasure + 1));
            part.getMeasures().add(curMeasure);
          }
          curMeasure.getNotes().add(note);
        }
      }
    }
    return scorePartwise;
  }

  @Override
  public String toString() {
    return "CounterpointSolution{" +
        "notesAllVoices=" + notesAllVoices +
        '}';
  }
}
