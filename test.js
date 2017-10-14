/*
 * Copyright (C) 2017 Tignis, Inc.
 */

"use strict";

let testcases = [
    {
        competitors: {
            /** @return {Number} */
            "Joey Chestnut": function (totalHotDogsEaten) {
                return Math.exp(0.0344 * totalHotDogsEaten) + 4;
            },
            /** @return {Number} */
            "Carmen Cincotti": function (totalHotDogsEaten) {
                return totalHotDogsEaten * 0.120 + 6;
            }
        },
        duration: 600,
        results: "testcases/2017.json"
    }
];

/*
 * TODO: Implement this function. It should return a list of Event
 * objects with the following properties, e.g.
 *
 * [
 *     {
 *         elapsedTime: {In seconds, to a precision of 3 decimal places},
 *         name: {Competitor name},
 *         totalHotDogsEaten: {In number of hot dogs, to a precision of 3 decimal places}
 *     },
 *     ...
 * ]
 *
 * Events should be sorted by elapsedTime first, then name if they
 * have the same elapsedTime. There should be an Event for every whole
 * hot dog that is eaten by a competitor, as well as an Event for each
 * competitor at the end of the competition. See the expected results
 * file for each testcase.
 */
function Competition(testcase) {
    return [];
}

/*
 * Do not change code below this line. Run this file with node.js, e.g.
 *
 * node test.js
 */

let allOk = true;

for (let testcase of testcases) {
    let fs = require("fs");
    let expectedEvents = JSON.parse(fs.readFileSync(testcase.results));
    let events = Competition(testcase);
    let i = 0;
    for (; i < events.length; i++) {
        let event = events[i];
        let expectedEvent = i < expectedEvents.length ? expectedEvents[i] : null;
        let result = (expectedEvent !== null &&
            event.elapsedTime === expectedEvent.elapsedTime &&
            event.name === expectedEvent.name &&
            event.totalHotDogsEaten === expectedEvent.totalHotDogsEaten);
        if (!result) {
            allOk = false;
            console.log(event, expectedEvent, "FAIL");
        }
    }
    for (; i < expectedEvents.length; i++) {
        let expectedEvent = expectedEvents[i];
        allOk = false;
        console.log(null, expectedEvent, "FAIL");
    }
}

if (allOk) {
    console.log("OK");
} else {
    throw "FAIL";
}
