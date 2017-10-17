/*
 * Copyright (C) 2017 Tignis, Inc.
 */

package com.tignis.interview.hotdog;

import java.io.IOException;
import java.util.Map;

/**
 * Implement the Competition class.
 */

public abstract class Competition {
    public Map<String, HotDogFunction> competitors;
    public double duration;

    Competition(Map<String, HotDogFunction> competitors, double duration) {
        this.competitors = competitors;
        this.duration = duration;
    }

    public abstract Iterable<Event> run() throws IOException;

    public abstract String winner() throws IOException;
}
