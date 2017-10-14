/*
 * Copyright (C) 2017 Tignis, Inc.
 */

package com.tignis.interview.hotdog;

import java.util.Map;

/**
 * Implement the Competition class.
 */

public abstract class Competition {
    Competition(Map<String, HotDogFunction> competitors, double duration) {
    }

    public abstract Iterable<Event> run();

    public abstract String winner();
}
