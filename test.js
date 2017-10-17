/*
 * Copyright (C) 2017 Tignis, Inc.
 *
 * Run this file with node.js, e.g.
 *
 * node test.js
 */

'use strict';

let Event = require('./competition').Event;
let testcases = require('./testcases').testcases;

function runTestCases(CompetitionClass) {
    let allOk = true;

    for (let testcase of testcases) {
        let fs = require('fs');
        let results = JSON.parse(fs.readFileSync(testcase.results));
        let expectedEvents = results['events'].map(function (event) {
            return new Event(event.elapsedTime, event.name, event.totalHotDogsEaten);
        });

	console.log(testcase.results);

	let competition = new CompetitionClass(testcase.competitors, testcase.duration);
        let i = 0;
        for (let event of competition.run()) {
            let expectedEvent = i < expectedEvents.length ? expectedEvents[i] : null;
	    let result = 'OK';
            if (expectedEvent === null || !event.equals(expectedEvent)) {
                allOk = false;
		result = 'FAIL';
	    }
	    console.log(event, expectedEvent, result);
            i++;
        }
        for (; i < expectedEvents.length; i++) {
            let expectedEvent = expectedEvents[i];
            allOk = false;
            console.log(null, expectedEvent, 'FAIL');
        }

        let winner = competition.winner();
        let expectedWinner = results['winner'];
        if (winner !== expectedWinner) {
            allOk = false;
            console.log(winner, expectedWinner, 'FAIL');
        }
    }

    if (allOk) {
        console.log('OK');
    } else {
        throw 'FAIL';
    }
}

module.exports = {
    runTestCases: runTestCases
};

console.log('TODO: Run tests against your Competition class.')
/*
 * YourCompetitionClass = require('./competition').YourCompetitionClass;
 * runTestCases(YourCompetitionClass);
 */
