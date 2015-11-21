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

/**
 * TODO: Investigate way to not have "alter" written to XML stream if it has a value of 0
 *
 * @author James Weaver
 */
public class Pitch {
    public String step;

    public int alter;

    public int octave;

    public Pitch(String step, int alter, int octave) {
        this.step = step;
        this.alter = alter;
        this.octave = octave;
    }

    @Override
    public String toString() {
        String accidental = "";
        if (alter < 0) {
            accidental = "b";
        } else if (alter > 0) {
            accidental = "#";
        }
        return step + accidental + octave;
    }
}
