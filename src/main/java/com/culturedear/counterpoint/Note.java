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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * TODO: Add comments that explain usage of both durations (quarters and eights), and onset.
 *
 * @author James Weaver
 */
@JsonPropertyOrder({"pitch", "duration", "type", "lyric"})
public class Note implements Comparable {
    // Onset in eighth notes from beginning of part
    @JsonIgnore
    private int onset;

    // Note number, expressed as semitones above low C
    private int noteNum;

    // Duration in eighth notes
    private int durationEights;

    // Lyric to hold chord annotations
    private Lyric lyric;

    public Note(int onset, int noteNum, int durationEights) {
        this.onset = onset;
        this.noteNum = noteNum;
        this.durationEights = durationEights;
    }

    // Private so that onset isn't output in XML stream
    // TODO: Investigate if an annotation exists to do the same thing
    public int getOnset() {
        return onset;
    }

    public void setOnset(int onset) {
        this.onset = onset;
    }

    // Private so that noteNum isn't output in XML stream
    private int getNoteNum() {
        return noteNum;
    }

    public void setNoteNum(int noteNum) {
        this.noteNum = noteNum;
    }

    /**
     * The presence of this method causes pitch elements to be created and written to XML stream
     * TODO: Consider making the values sensitive to key
     *
     * @return
     */
    public Pitch getPitch() {
        int octaveNum = noteNum / 12 - 1;
        int pitchClassNum = noteNum % 12;
        String step = "";
        int alter = 0;
        switch (pitchClassNum) {
            case 0:
                step = "C";
                break;
            case 1:
                step = "C";
                alter = 1;
                break;
            case 2:
                step = "D";
                break;
            case 3:
                step = "E";
                alter = -1;
                break;
            case 4:
                step = "E";
                break;
            case 5:
                step = "F";
                break;
            case 6:
                step = "F";
                alter = 1;
                break;
            case 7:
                step = "G";
                break;
            case 8:
                step = "A";
                alter = -1;
                break;
            case 9:
                step = "A";
                break;
            case 10:
                step = "B";
                alter = -1;
                break;
            case 11:
                step = "B";
                break;
        }

        return new Pitch(step, alter, octaveNum);
    }


    private int getDurationEights() {
        return durationEights;
    }

    public void setDurationEights(int duration) {
        this.durationEights = durationEights;
    }

    /**
     * The presence of this method causes duration elements to be created and written to XML stream
     */
    public int getDuration() {
        return durationEights / 2;
    }

    /**
     * The presence of this method causes type elements to be created and written to XML stream
     */
    public String getType() {
        String noteType = "UNKNOWN";
        switch (durationEights) {
            case 8:
                noteType = "whole";
                break;
            case 4:
                noteType = "half";
                break;
            case 2:
                noteType = "quarter";
                break;
            case 1:
                noteType = "eighth";
                break;
        }
        return noteType;
    }

    public Lyric getLyric() {
        return lyric;
    }

    public void setLyric(Lyric lyric) {
        this.lyric = lyric;
    }

    @Override
    public String toString() {
        return "Note{" +
                "onset=" + onset +
                ", noteNum=" + noteNum +
                ", durationEights=" + durationEights +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        int retVal = 0;
        if (getNoteNum() > ((Note) o).getNoteNum()) {
            retVal = 1;
        } else if (getNoteNum() < ((Note) o).getNoteNum()) {
            retVal = -1;
        }
        return retVal;
    }
}
