package com.tignis.interview.hotdog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

@RunWith(JUnit4.class)
public class YourTest extends HotDogTest {
    @Test
    public void testYourCompetitionClass() throws IOException {
        for (TestCase testCase : TestCase.testCases) {
            runTestCase(testCase, new CompetitionImpl(testCase.competitors, testCase.duration));
        }
    }
}
