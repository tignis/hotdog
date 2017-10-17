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

module.exports = {
    testcases: testcases
};
