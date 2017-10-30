/*
 * Copyright (C) 2017 Tignis, Inc.
 */

'use strict';

/**
 * An Event should be generated whenever a hot dog is eaten by a competitor, and at the end of the competition for
 * each competitor.
 */
class Event {
  /**
   * Initialize Event.
   *
   * @param {Number} elapsedTime Elapsed time in seconds since start of competition.
   * @param {string} name Competitor name.
   * @param {Number} totalHotDogsEaten Total number of hot dogs eaten by competitor.
   */
  constructor(elapsedTime, name, totalHotDogsEaten) {
    this.elapsedTime = elapsedTime;
    this.name = name;
    this.totalHotDogsEaten = totalHotDogsEaten;
  }

  /**
   * Whether two Events can be considered to be identical.
   *
   * @param {Event} other The other Event.
   * @return {boolean} True if the Events are identical.
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
   * @return {Event} New Event with numeric fields rounded to 3 decimal points.
   */
  rounded() {
    return new Event(
        Number(this.elapsedTime.toFixed(3)),
        this.name,
        Number(this.totalHotDogsEaten.toFixed(3))
    );
  }
}

/**
 * Exception raised when the Competition class has not been implemented yet.
 */
class NotImplementedError extends Error {}

/**
 * TODO: Implement this class to simulate the competition.
 */
class Competition {
  /**
   * Initialize the competition.
   *
   * @param {Object} competitors A dictionary of {competitor name: hot dog function}.
   * @param {Number} duration Duration in seconds of the competition.
   */
  constructor(competitors, duration) {
    throw new NotImplementedError();
  }

  /**
   * Run a simulation of the competition and return a list of Events. Events should be sorted by elapsed_time
   * first, then name if they have the same elapsed_time. There should be an Event for every whole hot dog that is
   * eaten by a competitor, as well as an Event for each competitor at the end of the competition. See the
   * expected results file for each testcase.
   *
   * @return {Array} List of (or iterator over) Events.
   */
  run() {
    throw new NotImplementedError();
    return null;
  }

  /**
   * Get the winner of the competition. If multiple competitors have eaten the same number of hot dogs, the
   * competitor whose name comes first lexically is the winner.
   *
   * @return {string} Name of winner.
   */
  winner() {
    throw new NotImplementedError();
    return '';
  }
}

module.exports = {
  Event: Event,
  NotImplementedError: NotImplementedError,
  Competition: Competition,
};
