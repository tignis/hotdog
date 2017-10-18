/*
 * Copyright (C) 2017 Tignis, Inc.
 */

package com.tignis.interview.hotdog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Implement the Competition class.
 */

public abstract class Competition {
	protected Map<String, HotDogFunction> competitors;
	protected double duration;
	public List<Event> events;

    Competition(Map<String, HotDogFunction> competitors, double duration) {
	    	this.competitors = competitors;
	    	this.duration = duration;
    }

    public abstract Iterable<Event> run();

    public abstract String winner();
}
