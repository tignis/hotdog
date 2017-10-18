package com.tignis.interview.hotdog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.tignis.interview.hotdog.Competition;
import com.tignis.interview.hotdog.Event;
import com.tignis.interview.hotdog.MyCompetition;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class YourTest extends HotDogTest {
    @Test
    public void testYourCompetitionClass() throws IOException {
    		
        for (TestCase testCase : TestCase.testCases) {
            /* TODO: Run each test case against a new instance of your competition class. */
	        	Competition c = new MyCompetition(TestCase.testCases.get(0).competitors, TestCase.testCases.get(0).duration);
	    		c.run();
	    		for (int i = 0; i < c.events.size(); i++) {
	    			Event e = c.events.get(i);
	    			System.out.println("Event(elapsed_time: " + e.elapsedTime + ", " +
	    								"name = " + e.name + ", " + "total_hot_dogs_eaten = " + e.totalHotDogsEaten + ")");
	    		}
	    		assertEquals("Joey Chestnut", c.winner());
        }
        
    }
    
}
