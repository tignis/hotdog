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
            /* TODO: Run each test case against a new instance of your competition class. */
            Comp comp = new Comp(testCase.competitors,testCase.duration);
            runTestCase(testCase,comp );
        }
    }
}
