/*
 * Copyright (C) 2017 Tignis, Inc.
 */

package com.tignis.interview.hotdog;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class HotDogTest {
    private static final ObjectMapper mapper = new ObjectMapper();
    protected final TestCase testCase;
    protected final Competition competition;

    /**
     * Iterator over all test cases. Advanced test cases that test e.g. corner cases and boundary conditions are not
     * provided.
     *
     * @return An Iterable over all TestCases.
     */
    protected static List<TestCase> getAllTestCases() {
        List<TestCase> allTestCases = new ArrayList<TestCase>(new BasicTestCases().getTestCases());
        try {
            TestCases advancedTestCases = TestCases.class.cast(
                    Class.forName("com.tignis.interview.hotdog.AdvancedTestCases").newInstance());
            allTestCases.addAll(advancedTestCases.getTestCases());
        } catch (ClassNotFoundException e) {
            // Advanced test cases are not provided.
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return allTestCases;
    }

    /**
     * Run a test case.
     *
     * @param testCase    {@link TestCase} to run.
     * @param competition {@link Competition} implementation.
     * @throws IOException If the expected results file is corrupt (unexpected).
     */
    protected void runTestCase(TestCase testCase, Competition competition) throws IOException {
        Event[] expectedEvents = null;
        String expectedWinner = null;

        if (testCase.results != null) {
            TestCaseResults results = mapper.readValue(HotDogTest.class.getResource(testCase.results),
                    TestCaseResults.class);
            expectedEvents = results.events;
            expectedWinner = results.winner;
        }

        System.out.println(competition.getClass().getSimpleName() + " " + testCase.name);
        System.out.format("\n");

        String rowFormat = "%10s%10s%20s%20s%10s%10s%10s\n";
        System.out.format(rowFormat,
                "Time", "Expected", "Competitor", "Expected", "Hot Dogs", "Expected", "Result");

        boolean allOk = true;
        int i = 0;
        for (Event event : competition.run()) {
            Event expectedEvent = null;
            String result = "n/a";

            if (expectedEvents != null) {
                expectedEvent = i < expectedEvents.length ? expectedEvents[i] : null;
                result = expectedEvent != null && event.equals(expectedEvent) ? "OK" : "FAIL";
            }

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

        if (expectedEvents != null) {
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
        }

        System.out.format("\n");

        String winner = competition.winner();
        String winnerFormat = "%40s%20s%30s\n";
        System.out.format(winnerFormat, "Winner", "Expected", "Result");
        System.out.format(winnerFormat,
                winner,
                expectedWinner != null ? expectedWinner : "n/a",
                expectedWinner != null ? winner.equals(expectedWinner) ? "OK" : "FAIL" : "n/a");
        System.out.format("\n");

        if (expectedWinner != null) {
            assertTrue(allOk);
            assertEquals(winner, expectedWinner);
        }
    }

    @Parameterized.Parameters(name = "{0}({1})")
    public static Collection<Object[]> data() {
        List<Object[]> parameters = new ArrayList<Object[]>();
        for (TestCase testCase : getAllTestCases()) {
            parameters.add(new Object[]{
                    "Competition",                                           // Name of the class to test.
                    testCase,                                                // Test case.
                    new Competition(testCase.competitors, testCase.duration) // Class instance.
            });
        }
        return parameters;
    }

    /**
     * The Parameterized runner (https://github.com/junit-team/junit4/wiki/parameterized-tests) constructs an instance
     * of this class for every TestCase/Competition combination.
     *
     * @param className Name of the class to test.
     * @param testCase Test case.
     * @param competition Class instance.
     * @throws IOException If there is an error reading/parsing expected test results (unexpected).
     */
    public HotDogTest(String className, TestCase testCase, Competition competition) throws IOException {
        this.testCase = testCase;
        this.competition = competition;
    }

    /**
     * Test function. Run the specified TestCase/Competition combination.
     *
     * @throws IOException If there is an error reading/parsing expected test result (unexpected).
     */
    @Test
    public void testCompetition() throws IOException {
        try {
            runTestCase(testCase, competition);
        } catch (NotImplementedException e) {
            System.out.println("TODO: Implement the Competition class.");
        }
    }
}

