/*
 * Copyright (C) 2017 Tignis, Inc.
 */

package com.tignis.interview.hotdog;

/**
 * A HotDogFunction models how long it will take to eat the next hot dog, as a function of how many hot dogs have been
 * eaten already.
 */

public interface HotDogFunction {
    double nextHotDogDuration(int totalHotDogsEaten);
}
