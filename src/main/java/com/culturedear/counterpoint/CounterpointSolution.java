/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.culturedear.counterpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jamesweaver on 9/25/15.
 */
@Component
public class CounterpointSolution {

    private Log log = LogFactory.getLog(getClass());

    // Default beats per measure
    private static int beatsPerMeasure = 4;

    // Default beats per measure.
    // TODO: Utilize this
    private static int beatType = 4;

    // Onset units per measure
    // TODO: Calculate as a function of 8 (eighth notes), beatsPerMeasure and beatType
    private static int onsetUnitsPerMeasure = 8;

    @Autowired
    public CounterpointSolution(CounterpointProperties counterpointProperties) {
        this.counterpointProperties = counterpointProperties;
    }

    // First dimension is voice #, second is array of notes
    private List<List<Note>> notesAllVoices = new ArrayList<>();

    public void addVoice(List<Note> notes) {
        notesAllVoices.add(notes);
    }

    public ScorePartwise toScorePartwise() {
        try {
            return this.doScorePartwise();
        } catch (NullPointerException e) {
            return null;
        }
    }

    private ScorePartwise doScorePartwise() {
        Measure curMeasure = new Measure(" ");
        ScorePartwise scorePartwise = new ScorePartwise();
        if (notesAllVoices.size() > 0) {
            scorePartwise.getScoreParts().add(new ScorePart("Cantus firmus", "P1"));
            for (int voiceIdxA = 1; voiceIdxA < notesAllVoices.size(); voiceIdxA++) {
                scorePartwise.getScoreParts().add(new ScorePart("Melody " + (voiceIdxA + 1), "P" + (voiceIdxA + 1)));
            }

            for (int voiceIdxB = 0; voiceIdxB < notesAllVoices.size(); voiceIdxB++) {
                Part part = new Part("P" + (voiceIdxB + 1));
                scorePartwise.getParts().add(part);

                List<Note> notesForPart = notesAllVoices.get(voiceIdxB);

                // Iteratate over the notes in this part and insert Measure elements

                for (Note note : notesForPart) {
                    int onset = note.getOnset();
                    if (onset % onsetUnitsPerMeasure == 0) {
                        curMeasure = new Measure("" + (onset / onsetUnitsPerMeasure + 1));
                        part.getMeasures().add(curMeasure);
                    }
                    curMeasure.getNotes().add(note);
                }
            }
        }

        // Iterate over the measures for all parts, identifying the first chord in each measure
        boolean keepGoing = true;
        int measureNum = 0;
        while (keepGoing) {
            log.debug("Analyzing chord for measure " + measureNum);
            Note topMeasureFirstNote = null;
            List<Note> chordNotes = new ArrayList<>();
            List<Part> parts = scorePartwise.getParts();
            for (Part part : parts) {
                List<Measure> measures = part.getMeasures();
                if (measureNum < measures.size()) {
                    Measure measure = measures.get(measureNum);
                    List<Note> notes = measure.getNotes();
                    if (notes.size() > 0) {
                        Note firstNoteInMeasure = notes.get(0);
                        if (measureNum == 0 && firstNoteInMeasure.getPitch().octave <= 3) {
                            // First note in measure is below middle C, so use bass clef
                            Clef clef = new Clef("F", 4);
                            MeasureAttributes measureAttributes = new MeasureAttributes(clef);
                            measure.setMeasureAttributes(measureAttributes);
                        }
                        if (topMeasureFirstNote == null) {
                            topMeasureFirstNote = firstNoteInMeasure;
                        }
                        chordNotes.add(firstNoteInMeasure);
                    } else {
                        // Unexpected, as all measures should have at least one note
                        keepGoing = false;
                    }
                } else {
                    // We've run out of measures in at least one part
                    keepGoing = false;
                }
            }
            if (topMeasureFirstNote != null) {
                Collections.sort(chordNotes);

                String notesString = chordNotes.stream()
                        .map(note -> note.getPitch().toString())
                        .collect(Collectors.joining(" "));

                // Call the Chord Analyzer service
                RestTemplate restTemplate = new RestTemplate();
                Lyric lyric = null;
                try {

                    ClientMusicChord clientMusicChord =
                            restTemplate.getForObject(this.counterpointProperties.getAnalyzerServiceEndpoint(notesString),
                                    ClientMusicChord.class);

                    String chordTypeStr = clientMusicChord.getChordType();

                    String chordNotationStr = clientMusicChord.getRoot();
                    if (chordTypeStr.equalsIgnoreCase("pow")) {
                        chordNotationStr += "5"; // This is a power chord (triad with no 3)
                    } else {
                        chordNotationStr += " " + chordTypeStr;
                    }

                    // Notate as a slash chord if appropriate
                    if (clientMusicChord.getInversion() != 0) {
                        chordNotationStr += "/" + clientMusicChord.getBassNote();
                    }

                    lyric = new Lyric(chordNotationStr);
                } catch (Exception e) {
                    log.info("Caught exception when analyzing chord " + e);
                    //lyric = new Lyric("???");
                }
                topMeasureFirstNote.setLyric(lyric);
            }
            measureNum++;
        }

        return scorePartwise;
    }

    private final CounterpointProperties counterpointProperties;

    @Override

    public String toString() {
        return "CounterpointSolution{" +
                "notesAllVoices=" + notesAllVoices +
                '}';
    }
}
