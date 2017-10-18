package com.tignis.interview.hotdog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class MyCompetition extends Competition {
	
	MyCompetition(Map<String, HotDogFunction> competitors, double duration) {
		super(competitors, duration);
		events = new ArrayList<Event>();
	}

	public Iterable<Event> run() {
		for (Map.Entry<String, HotDogFunction> entry : competitors.entrySet()) {
			int totalHotDogsEaten = 0;
			double elapsedTime = 0;
			String name = entry.getKey();
			double nextHotDog = entry.getValue().nextHotDogDuration(totalHotDogsEaten);
			while (duration - elapsedTime >= nextHotDog) {
				elapsedTime += nextHotDog;
				events.add(new Event(elapsedTime, name, ++totalHotDogsEaten).rounded());
				nextHotDog = entry.getValue().nextHotDogDuration(totalHotDogsEaten);
			}
			if (duration - elapsedTime > 0) {
				double remainHotDog = (duration - elapsedTime) / (double) nextHotDog + totalHotDogsEaten;
				events.add(new Event(duration, name, remainHotDog).rounded());
			}
		}
		Collections.sort(events);
		return events;
	};

	public String winner() {
		double max = Integer.MIN_VALUE;
		String winner = "";
		for (int i = events.size() - 1; i >= events.size() - competitors.size(); i--) {
			if (events.get(i).totalHotDogsEaten > max) {
				max = events.get(i).totalHotDogsEaten;
				winner = events.get(i).name;
			}
		}
		return winner;
	}
}
