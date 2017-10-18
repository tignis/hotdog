package com.tignis.interview.hotdog;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.tignis.interview.hotdog.Competition;
import com.tignis.interview.hotdog.MyCompetition;

public class HotDogTestTest {

	@Test
	public void test() throws IOException {
		HotDogTest h = new HotDogTest();
		Competition c = new MyCompetition(TestCase.testCases.get(0).competitors, TestCase.testCases.get(0).duration);
		h.runTestCase(TestCase.testCases.get(0), c);
	}
}