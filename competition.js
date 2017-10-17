/*
 * Copyright (C) 2017 Tignis, Inc.
 */

'use strict';

/**
 * An Event should be generated whenever a hot dog is eaten by a competitor, and at the end of the
 * competition for each competitor.
 */
class Event {
    constructor(elapsedTime, name, totalHotDogsEaten) {
        this.elapsedTime = Number(elapsedTime);
        this.name = name;
        this.totalHotDogsEaten = Number(totalHotDogsEaten);
    }

    /**
     * Whether two Events can be considered to be identical.
     *
     * @param {Event} other The other Event.
     * @returns {boolean} True if the Events are identical.
     */
    equals(other) {
        return (
            this.elapsedTime === other.elapsedTime &&
            this.name === other.name &&
            this.totalHotDogsEaten === other.totalHotDogsEaten
        );
    }

    /**
     * Round numeric fields to 3 decimal points.
     *
     * @returns {Event} New Event with numeric fields rounded to 3 decimal points.
     */
    rounded() {
        return new Event(
            this.elapsedTime.toFixed(3),
            this.name,
            this.totalHotDogsEaten.toFixed(3)
        );
    }
}

/**
 * Implement or extend this class to simulate the competition.
 */
class YourCompetitionClass {
    /**
     * Initialize the competition.
     *
     * @param {Object} competitors A dictionary of {competitor name: hot dog function}.
     * @param {Number} duration Duration in seconds of the competition.
     */
    constructor(competitors, duration) {
    }

    /**
     * Run a simulation of the competition and return a list of (or iterator over) Events. Events should be sorted by
     * elapsedTime first, then name if they have the same elapsedTime. There should be an Event for every whole hot dog
     * that is eaten by a competitor, as well as an Event for each competitor at the end of the competition. See the
     * expected results file for each testcase.
     *
     * @returns {Array} List of (or iterator over) Events.
     */
    run() {
    }

    /**
     * Get the winner of the competition. If multiple competitors have eaten the same number of hot dogs, the
     * competitor whose name comes first lexically is the winner.
     *
     * @returns {String} Name of winner.
     */
    winner() {
    }
}

module.exports = {
    Event: Event
};
