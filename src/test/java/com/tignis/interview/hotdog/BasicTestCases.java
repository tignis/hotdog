/*
 * Copyright (C) 2017 Tignis, Inc.
 */

package com.tignis.interview.hotdog;

import java.util.*;

public class BasicTestCases implements TestCases {
    private static final List<TestCase> testCases = Arrays.asList(
            new TestCase(new HashMap<String, HotDogFunction>() {{
                put("Joey Chestnut", new HotDogFunction() {
                    public double nextHotDogDuration(int totalHotDogsEaten) {
                        return Math.exp(0.0344 * totalHotDogsEaten) + 4;
                    }
                });
                put("Carmen Cincotti", new HotDogFunction() {
                    public double nextHotDogDuration(int totalHotDogsEaten) {
                        return totalHotDogsEaten * 0.120 + 6;
                    }
                });
            }}, 600, "/2017.json", "2017")
            // Consider adding additional test cases to test corner cases:
            //
            // 1. Who is the winner in the event of a tie?
            // 2. Which Event should be emitted first if multiple Events occur at the same time?
            //
            // To add a test case but not verify it, specify null for the results file.
    );

    public List<TestCase> getTestCases() {
        return testCases;
    }
}
