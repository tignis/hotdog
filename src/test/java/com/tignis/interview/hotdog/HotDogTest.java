/*
 * Copyright (C) 2017 Tignis, Inc.
 */

package com.tignis.interview.hotdog;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HotDogTest {
    private static final ObjectMapper mapper = new ObjectMapper();

    protected void runTestCase(TestCase testCase, Competition competition) throws IOException {
        TestCaseResults results = mapper.readValue(
                HotDogTest.class.getResource(testCase.results),
                TestCaseResults.class);
        Event[] expectedEvents = results.events;
        String expectedWinner = results.winner;

        System.out.println(testCase.results);

        String rowFormat = "%10s%10s%20s%20s%10s%10s%10s\n";
        System.out.format(rowFormat,
                "Time", "Expected", "Competitor", "Expected", "Hot Dogs", "Expected", "Result");

        boolean allOk = true;
        int i = 0;
        for (Event event : competition.run()) {
            Event expectedEvent = i < expectedEvents.length ? expectedEvents[i] : null;
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

        while (i < expectedEvents.length) {
            Event expectedEvent = expectedEvents[i];

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

