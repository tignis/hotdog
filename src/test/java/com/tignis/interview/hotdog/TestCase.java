/*
 * Copyright (C) 2017 Tignis, Inc.
 */

package com.tignis.interview.hotdog;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCase {
    public static List<TestCase> testCases = Arrays.asList(
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
            }}, 600, "/2017.json")
    );
    public Map<String, HotDogFunction> competitors;
    public double duration;
    public String results;

    TestCase(Map<String, HotDogFunction> competitors, double duration, String results) {
        this.competitors = competitors;
        this.duration = duration;
        this.results = results;
    }
}
