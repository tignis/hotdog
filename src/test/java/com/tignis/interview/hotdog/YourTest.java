package com.tignis.interview.hotdog;

import org.apache.commons.collections.IteratorUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class YourTest extends HotDogTest {
    @Test
    public void testYourCompetitionClass() throws IOException {
        for (TestCase testCase : TestCase.testCases) {
            System.out.println("This is a new case.");
            Competition competition = new CompetitionEntity(testCase.competitors, testCase.duration);

            Iterable<Event> iter = competition.run();
            for (Event event : iter) {
                System.out.println("Event(elapsed_time="+event.elapsedTime+", name=\""+event.name+"\", total_hot_dogs_eaten="+event.totalHotDogsEaten+")");
            }

            List<Event> list = IteratorUtils.toList(iter.iterator());
            String expectedWinner = list.get(list.size()-1).name;

            String actualWinner = competition.winner();

            assertEquals(expectedWinner, actualWinner);
            System.out.println("The winner of this testcase is "+actualWinner+"!");
        }
    }
}
