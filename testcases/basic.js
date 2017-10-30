/*
 * Copyright (C) 2017 Tignis, Inc.
 */

'use strict';

let testcases = [
  {
    competitors: {
      'Joey Chestnut': function(totalHotDogsEaten) {
        return Math.exp(0.0344 * totalHotDogsEaten) + 4;
      },
      'Carmen Cincotti': function(totalHotDogsEaten) {
        return totalHotDogsEaten * 0.120 + 6;
      },
    },
    duration: 600,
    results: 'testcases/2017.json',
    name: '2017',
  },
  // Consider adding additional test cases to test corner cases:
  //
  // 1. Who is the winner in the event of a tie?
  // 2. Which Event should be emitted first if multiple Events occur at the same time?
  //
  // To add a test case but not verify it, specify null for the results file.
];

module.exports = {
  testcases: testcases,
};
