/*
 * Copyright (C) 2017 Tignis, Inc.
 */

package com.tignis.interview.hotdog;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * An Event should be generated whenever a hot dog is eaten by a competitor, and at the end of the competition for
 * each competitor.
 */

public class Event implements Comparable<Event> {
    public double elapsedTime;
    public String name;
    public double totalHotDogsEaten;

    Event() {
    }

    Event(double elapsedTime, String name, double totalHotDogsEaten) {
        this.elapsedTime = elapsedTime;
        this.name = name;
        this.totalHotDogsEaten = totalHotDogsEaten;
    }

    /**
     * Whether two Events can be considered to be identical.
     *
     * @param other The other Event.
     * @return True if the Events are identical.
     */
    public boolean equals(Event other) {
        return elapsedTime == other.elapsedTime &&
                name.equals(other.name) &&
                totalHotDogsEaten == other.totalHotDogsEaten;
    }

    /**
     * If two Events occur at the same time, sort them alphabetically by competitor name.
     *
     * @param other The other Event.
     * @return <0,0,>0 If this Event should come before, at the same time, or after the other Event.
     */
    public int compareTo(Event other) {
        if (elapsedTime == other.elapsedTime) {
            return name.compareTo(other.name);
        } else {
            return Double.compare(elapsedTime, other.elapsedTime);
        }
    }

    /**
     * Round double value to the specified number of decimal points.
     *
     * @param value     Double value.
     * @param precision Number of decimal points.
     * @return Rounded double value.
     */
    private static double roundValue(double value, int precision) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(precision, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Round numeric fields to the specified number of decimal points.
     *
     * @return New Event with numeric fields rounded to 3 decimal points.
     */
    public Event rounded(int precision) {
        return new Event(roundValue(elapsedTime, precision), name, roundValue(totalHotDogsEaten, precision));
    }

    /**
     * Round numeric fields to the specified number of decimal points.
     *
     * @return New Event with numeric fields rounded to 3 decimal points.
     */
    public Event rounded() {
        return rounded(3);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Elapsed time = ");
        sb.append(elapsedTime);
        sb.append(", name = ");
        sb.append(name);
        sb.append(", total hotdogs eaten = ");
        sb.append(totalHotDogsEaten);
        sb.append("]");
        return sb.toString();
    }
}
