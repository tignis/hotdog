package com.tignis.interview.hotdog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class extends {@link Competition}. This generates events when a competitor eats a hotdog. 
 * It also generates an event when the competition ends and also decides the winner based on the 
 * total no. of hotdogs eaten. 
 * 
 * @author Chaitra
 */
public class CompetitionImpl extends Competition {
    private final Map<String, HotDogFunction> competitors;
    private final double duration;
    private String winner;

    public CompetitionImpl(Map<String, HotDogFunction> competitors, double duration) {
        super(competitors, duration);
        this.competitors = competitors;
        this.duration = duration;
    }

    @Override
    public Iterable<Event> run() {
        Set<Event> events = new TreeSet<Event>();
        double maxHotDogsEaten = 0;
        for (Map.Entry<String, HotDogFunction> entry : this.competitors.entrySet()) {
            double prevTime = 0.0;
            double elapsedTime = 0.0;
            int n = 0;
            // generate events for hotdogs eaten until the end of duration
            while ((elapsedTime += entry.getValue().nextHotDogDuration(n)) < this.duration) {
                n++;
                Event e = new Event(elapsedTime, entry.getKey(), n);
                events.add(e);
                prevTime = elapsedTime;
            }
            // pro-rate the last hotdog eaten based on the remaining time since the last hotdog was eaten
            double timeRemaining = this.duration - prevTime;
            double timeToEatNext = entry.getValue().nextHotDogDuration(n);
            double lastHotDogEaten = (timeRemaining / timeToEatNext);
            double totalHotDogsEaten = n + lastHotDogEaten;
            Event e = new Event(this.duration, entry.getKey(), totalHotDogsEaten);
            events.add(e);
            
            // keep track of the winner here
            if (totalHotDogsEaten > maxHotDogsEaten) {
                maxHotDogsEaten = totalHotDogsEaten;
                this.winner = entry.getKey();
            }
        }
        // events elapsedtime should be rounded to 3 decimal places
        List<Event> roundedEvents = new ArrayList<Event>();
        for(Event e : events) {
            roundedEvents.add(e.rounded());
        }
        return roundedEvents;
    }

    @Override
    public String winner() {
        return this.winner;
    }
}
