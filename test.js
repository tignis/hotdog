/*
 * Copyright (C) 2017 Tignis, Inc.
 *
 * Run this file with node.js, e.g.
 *
 * node test.js
 */

'use strict';

let Event = require('./competition').Event;
let testcases = require('./testcases/basic').testcases;
try {
  testcases = testcases.concat(require('./testcases/advanced').testcases);
} catch (err) {
  // Advanced test cases are not provided.
}

/**
 * Run all test cases against the given Competition class.
 *
 * @param {function} CompetitionClass Competition class to test.
 */
function runTestCases(CompetitionClass) {
  let allOk = true;

  for (let testcase of testcases) {
    let expectedEvents = null;
    let expectedWinner = null;

    if (testcase.results !== null) {
      let fs = require('fs');
      let results = JSON.parse(fs.readFileSync(testcase.results));
      expectedEvents = results['events'].map(function(event) {
        return new Event(event.elapsedTime, event.name, event.totalHotDogsEaten);
      });
      expectedWinner = results['winner'];
    }

    console.log(testcase.name);

    let competition = new CompetitionClass(testcase.competitors, testcase.duration);
    let i = 0;
    for (let event of competition.run()) {
      if (expectedEvents !== null) {
        let expectedEvent = i < expectedEvents.length
            ? expectedEvents[i]
            : null;
        let result = 'OK';
        if (expectedEvent === null || !event.equals(expectedEvent)) {
          allOk = false;
          result = 'FAIL';
        }
        console.log(event, expectedEvent, result);
        i++;
      } else {
        console.log(event, 'n/a', 'n/a');
      }
    }

    if (expectedEvents !== null) {
      for (; i < expectedEvents.length; i++) {
        let expectedEvent = expectedEvents[i];
        allOk = false;
        console.log('n/a', expectedEvent, 'FAIL');
      }
    }

    let winner = competition.winner();
    if (expectedWinner !== null) {
      let result = 'OK';
      if (winner !== expectedWinner) {
        allOk = false;
        result = 'FAIL';
      }
      console.log(winner, expectedWinner, result);
    } else {
      console.log(winner, 'n/a', 'n/a');
    }
  }

  if (allOk) {
    console.log('OK');
  } else {
    throw new Error('FAIL');
  }
}

module.exports = {
  runTestCases: runTestCases,
};

let NotImplementedError = require('./competition').NotImplementedError;
let Competition = require('./competition').Competition;
try {
  runTestCases(Competition);
} catch (err) {
  if (err instanceof NotImplementedError) {
    console.log('TODO: Implement the Competition class.');
  } else {
    throw err;
  }
}

