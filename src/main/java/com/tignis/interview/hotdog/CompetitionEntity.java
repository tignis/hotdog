package com.tignis.interview.hotdog;

import java.io.IOException;
import java.util.*;



/**
 * Created by cyk on 2017/10/14.
 */
public class CompetitionEntity extends Competition {

    public CompetitionEntity(Map<String, HotDogFunction> competitor, double duration) {
        super(competitor, duration);
    }
    List<Event> eventList = new ArrayList<Event>();

    public Iterable<Event> run() {
        double JelapseTime = 0.000;
        double CelapseTime = 0.000;
        double JhotDogNum = 0;
        double ChotDogNum = 0;
        HotDogFunction JFunction = competitors.get("Joey Chestnut");
        HotDogFunction CFunction = competitors.get("Carmen Cincotti");

        //main idea is use the given function to calculate the corresponding time of number of
        //hotdog, until the time is over the target elapsetime, and then based on the proportion
        //time to calculate the number of hot dog eaten at the target time.
        while(JelapseTime <= duration || CelapseTime <= duration) {
            if(JelapseTime<duration) {
                JelapseTime += JFunction.nextHotDogDuration((int)JhotDogNum);
                JhotDogNum++;
                Event JEvent = new Event(JelapseTime,"Joey Chestnut",JhotDogNum).rounded(3);
                eventList.add(JEvent);
            }
            if(CelapseTime<duration) {
                CelapseTime += CFunction.nextHotDogDuration((int)ChotDogNum);
                ChotDogNum++;
                Event CEvent = new Event(CelapseTime,"Carmen Cincotti",ChotDogNum).rounded(3);
                eventList.add(CEvent);
            }
        }

        //sort the list in order to remove the time over target time
        Collections.sort(eventList,new Comparator<Event>(){
            public int compare(Event e1, Event e2) {
                if (e1.elapsedTime == e2.elapsedTime) {
                    return e1.totalHotDogsEaten > e2.totalHotDogsEaten ? 1 : -1;
                }
                return e1.elapsedTime > e2.elapsedTime ? 1 : -1;
            }
        });

        //delete the hot dog whose elapsed time has excessed the duration time
        eventList.remove(eventList.size()-1);
        eventList.remove(eventList.size()-1);

        //get final hotdog eaten number
        double JLastTime = JelapseTime-JFunction.nextHotDogDuration((int)JhotDogNum-1);
        double JpartialHotDog = (duration-JLastTime)/(JelapseTime-JLastTime);
        double JFinalHotDog = JhotDogNum-1+JpartialHotDog;

        eventList.add(new Event(duration, "Joey Chestnut", JFinalHotDog).rounded(3));

        double CLastTime = CelapseTime-CFunction.nextHotDogDuration((int)ChotDogNum-1);
        double CpartialHotDog = (duration-CLastTime)/(CelapseTime-CLastTime);
        double CFinalHotDog = ChotDogNum-1+CpartialHotDog;

        eventList.add(new Event(duration, "Carmen Cincotti", CFinalHotDog).rounded(3));


        Collections.sort(eventList,new Comparator<Event>(){
            public int compare(Event e1, Event e2) {
                if (e1.elapsedTime == e2.elapsedTime) {
                    return e1.totalHotDogsEaten > e2.totalHotDogsEaten ? 1 : -1;
                }
                return e1.elapsedTime > e2.elapsedTime ? 1 : -1;
            }
        });


        return eventList;
    }

    public String winner() throws IOException {
        System.out.print(eventList.get(eventList.size()-1).name);
        return eventList.get(eventList.size()-1).name;

    }



}
