#
# Copyright (C) 2017 Tignis, Inc.
#

"""
Implement the Competition class.
python version shares the similar idea with java version
"""
from decimal import Decimal
import operator
import testcases



def round_value(value, precision=Decimal('1.000')):
    """
    Round float value to specified precision.

    :param value: Float value.
    :param precision: Precision as a Decimal() object.
    :return: Rounded float value.
    """
    return float(Decimal(value).quantize(precision))


class Competition(object):
    """
    Implement or extend this class to simulate the competition.
    """


    def __init__(self, competitors, duration):
        """
        Initialize the competition.

        :param competitors: A dictionary of {competitor name: hot dog function}.
        :param duration: Duration in seconds of the competition.

        """
        self.eventList = []
        self.duration = duration
        self.competitors = competitors





    def run(self):
        """
        Run a simulation of the competition.

        :return: List of (or iterator over) Events.
        """
        JElapseTime = 0.000
        CElapseTime = 0.000
        JHotDogNum = 0.000
        CHotDogNum = 0.000




        while (JElapseTime < self.duration or CElapseTime < self.duration):
            if (JElapseTime < self.duration):
                JElapseTime += self.competitors['Joey Chestnut'](JHotDogNum)
                JHotDogNum +=1
                self.eventList.append(Event(JElapseTime, 'Joey Chestnut', JHotDogNum).rounded())
            if (CElapseTime < self.duration):
                CElapseTime += self.competitors['Carmen Cincotti'](CHotDogNum)
                CHotDogNum += 1
                self.eventList.append(Event(CElapseTime, 'Carmen Cincotti', CHotDogNum).rounded())



        eventListNew = sorted(self.eventList,key =  lambda x:(x.elapsed_time, ord(x.name[0])))
        eventListNew = [obj for obj in eventListNew if obj.elapsed_time <self.duration]

        JLastTime = JElapseTime - self.competitors['Joey Chestnut'](JHotDogNum-1)
        JPartialHotDog = (self.duration-JLastTime)/(JElapseTime-JLastTime)
        JFinalHotDog = round_value(JHotDogNum - 1 + JPartialHotDog)

        eventListNew.append(Event(self.duration,'Joey Chestnut', JFinalHotDog).rounded())

        CLastTime = CElapseTime - self.competitors['Carmen Cincotti'](CHotDogNum-1)
        CPartialHotDog = (self.duration - CLastTime) / (CElapseTime - CLastTime)
        CFinalHotDog = round_value(CHotDogNum - 1 + CPartialHotDog)

        eventListNew.append(Event(self.duration, 'Carmen Cincotti', CFinalHotDog).rounded())

        eventListNew = sorted(eventListNew, key=lambda x: (x.elapsed_time, ord(x.name[0])))

        self.eventList = eventListNew

        return self.eventList


    def winner(self):
        """
        Get the winner of the competition.

        :return: Name of winner.
        """
        if not self.eventList:
            self.run()
        length = len(self.eventList)
        return self.eventList[length-1].name



class Event(object):
    """
    An Event should be generated whenever a hot dog is eaten by a competitor, and at the end of the competition for
    each competitor.
    """

    def __init__(self, elapsed_time, name, total_hot_dogs_eaten):
        self.elapsed_time = elapsed_time
        self.name = name
        self.total_hot_dogs_eaten = total_hot_dogs_eaten

    def __repr__(self):
        return 'Event(elapsed_time=%s, name=%s, total_hot_dogs_eaten=%s)' % (
            repr(self.elapsed_time), repr(self.name), repr(self.total_hot_dogs_eaten))

    def __eq__(self, other):
        """
        Whether two Events can be considered to be identical.

        :param other: The other Event.
        :return: True if the Events are identical.
        """
        return (
            self.elapsed_time == other.elapsed_time and
            self.name == other.name and
            self.total_hot_dogs_eaten == other.total_hot_dogs_eaten
        )

    def __lt__(self, other):
        """
        If two Events occur at the same time, sort them alphabetically by competitor name.

        :param other: The other Event.
        :return: True if this Event should come before the other Event.
        """
        if self.elapsed_time == other.elapsed_time:
            return self.name < other.name
        else:
            return self.elapsed_time < other.elapsed_time

    def rounded(self, precision=Decimal('1.000')):
        """
        Round numeric fields to 3 decimal points.

        :return: New Event with numeric fields rounded to 3 decimal points.
        """
        return Event(round_value(self.elapsed_time, precision),
                     self.name,
                     round_value(self.total_hot_dogs_eaten, precision))


# if __name__ == "__main__":
#     for testcase in testcases.testcases:
#         competition = Competition(competitors=testcase['competitors'],
#                                         duration=testcase['duration'])
#         events = list(competition.run())
#         winner = competition.winner()