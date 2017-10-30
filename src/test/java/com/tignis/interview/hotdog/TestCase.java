/*
 * Copyright (C) 2017 Tignis, Inc.
 */

package com.tignis.interview.hotdog;

import java.util.*;

public class TestCase {
    public final Map<String, HotDogFunction> competitors;
    public final double duration;
    public final String results;
    public final String name;

    public TestCase(Map<String, HotDogFunction> competitors, double duration, String results, String name) {
        this.competitors = competitors;
        this.duration = duration;
        this.results = results;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}