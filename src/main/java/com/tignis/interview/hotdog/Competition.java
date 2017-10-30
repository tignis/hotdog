/*
 * Copyright (C) 2017 Tignis, Inc.
 */

package com.tignis.interview.hotdog;

import java.util.Map;

public class Competition {
    /**
     * Initialize the competition.
     *
     * @param competitors A Map of {competitor name: hot dog function}.
     * @param duration Duration in seconds of the competition.
     */
    public Competition(Map<String, HotDogFunction> competitors, double duration) {
    }

    /**
     * Run a simulation of the competition and return a list of (or iterator over) Events. Events should be sorted by
     * elapsedTime first, then name if they have the same elapsedTime. There should be an Event for every whole hot dog
     * that is eaten by a competitor, as well as an Event for each competitor at the end of the competition. See the
     * expected results file for each testcase.
     *
     * @return List of (or iterator over) Events.
     */
    public Iterable<Event> run() {
        throw new NotImplementedException();
    }

    /**
     * Get the winner of the competition. If multiple competitors have eaten the same number of hot dogs, the
     * competitor whose name comes first lexically is the winner.
     *
     * @return Name of winner.
     */
    public String winner() {
        throw new NotImplementedException();
    }
}
