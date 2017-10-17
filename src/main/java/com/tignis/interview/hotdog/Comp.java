/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tignis.interview.hotdog;

import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author Robert Roppo
 */
public class Comp extends Competition {
    
    //global variables
    double duration;
    Map<String, HotDogFunction> competitors;
    ArrayList<Event> events = new ArrayList<Event>();
    
    //constructor
    Comp(Map<String, HotDogFunction> competitors, double duration) {
        super(competitors,duration);
        this.competitors = competitors;
        this.duration = duration;
    }
    
    /**
     *Method calculates hot dogs eaten per competitor and adds them to ArrayList.
     * 
     * 
     * @return List of hot dogs eaten
     */
    public ArrayList<Event> run() {
        //for each competitor, calc hot dogs eaten and add to events
        for (Map.Entry<String, HotDogFunction> entry : competitors.entrySet()) {
            //calculate hotdogs eaten and add to list
            double elapsedTime = 0.0;
            int hotDogs = 0;
            while(elapsedTime < duration){
                elapsedTime += entry.getValue().nextHotDogDuration(hotDogs);
                hotDogs++;
                events.add(new Event(elapsedTime,entry.getKey(),hotDogs).rounded());
            }
            
            //remove last entry to list in place to place last hotdog
            events.remove(events.size() - 1);
            hotDogs -= 1;
            
            //calc last hot dog and add to list
            events.add(calcLastHotDog(elapsedTime,entry.getValue().nextHotDogDuration(hotDogs),duration,entry.getKey(),hotDogs));
        }
        
        //sort list by elapsed time
        Collections.sort(events);        
        
        return events;
        
    }
    
    /**
     * Finds end of sorted list, which is the winner.
     *
     * @return name of the winner
     */
    public String winner(){
        //last value is winner
        return events.get(events.size()-1).name;
    }
    
    /**
     *Method to calculate last hot dog based on elapsed time and
     * duration of next hot dog.
     * 
     * @return event with last hot dog
     */
    public Event calcLastHotDog(double elapsedTime, double nextDogTime,double duration,String name, double totalHotDogs){
        double lastDogTime = elapsedTime - nextDogTime;
        double lastDog = (duration - lastDogTime) / (elapsedTime - lastDogTime);
        totalHotDogs += lastDog;
        Event event = new Event(duration,name,totalHotDogs);
        return event.rounded();
    }
    
}
