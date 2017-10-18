/*
 * Copyright (C) 2017 Tignis, Inc.
 */

package com.tignis.interview.hotdog;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tignis.interview.hotdog.Competition;
import com.tignis.interview.hotdog.Event;

import java.io.IOException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HotDogTest {
    private static final ObjectMapper mapper = new ObjectMapper();
    
    protected void runTestCase(TestCase testCase, Competition competition) throws IOException {
        List<Event> expectedEvents = mapper.readValue(
                HotDogTest.class.getResource(testCase.results),
                new TypeReference<List<Event>>() {
                });
        String expectedWinner = Collections.max(expectedEvents, new Comparator<Event>() {
            public int compare(Event event1, Event event2) {
                return Double.compare(event1.totalHotDogsEaten, event2.totalHotDogsEaten);
            }
        }).name;

        String rowFormat = "%10s%10s%20s%20s%10s%10s%10s\n";
        System.out.format(rowFormat,
                "Time", "Expected", "Competitor", "Expected", "Hot Dogs", "Expected", "Result");

        boolean allOk = true;
        int i = 0;
        for (Event event : competition.run()) {
            Event expectedEvent = i < expectedEvents.size() ? expectedEvents.get(i) : null;
            String result = expectedEvent != null && event.equals(expectedEvent) ? "OK" : "FAIL";

            if (result.equals("FAIL")) {
                allOk = false;
            }
            System.out.format(rowFormat,
                    String.format("%.3f", event.elapsedTime),
                    expectedEvent != null ? String.format("%.3f", expectedEvent.elapsedTime) : "n/a",
                    event.name,
                    expectedEvent != null ? expectedEvent.name : "n/a",
                    String.format("%.3f", event.totalHotDogsEaten),
                    expectedEvent != null ? String.format("%.3f", expectedEvent.totalHotDogsEaten) : "n/a",
                    result);
            i++;
        }

        while (i < expectedEvents.size()) {
            Event expectedEvent = expectedEvents.get(i);

            allOk = false;
            System.out.format(rowFormat,
                    "n/a",
                    String.format("%.3f", expectedEvent.elapsedTime),
                    "n/a",
                    expectedEvent.name,
                    "n/a",
                    String.format("%.3f", expectedEvent.totalHotDogsEaten),
                    "FAIL");
            i++;
        }

        assertTrue(allOk);
        assertEquals(competition.winner(), expectedWinner);
    }
   
    
}

