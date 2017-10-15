#
# Copyright (C) 2017 Tignis, Inc.
#

"""
Implement the Competition class.
"""
from decimal import Decimal


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
    winner = "No One"
    
    def __init__(self, competitors, duration):
        """
        Initialize the competition.

        :param competitors: A dictionary of {competitor name: hot dog function}.
        :param duration: Duration in seconds of the competition.
        """
        
        self.competitors = competitors
        self.duration = duration
        if duration <= 0:
            raise InvalidDurationError()
        if len(competitors.keys()) > 2:
            raise NotImplementedForMoreThanTwoCompetitorsError()

    def run(self):
        """
        Run a simulation of the competition.
		
        :return: List of (or iterator over) Events.
        """

        contestants = self.competitors.keys()
        all_events = []
        contestantNum = 0
        most_hd_ate = 0
        for i in contestants:
            events_arr = []
            cur_time = 0
            hd_func = self.competitors[i]    
            n = 0
            while(cur_time + hd_func(n) < self.duration):
                cur_event = Event(cur_time + hd_func(n), i, n + 1).rounded()
                events_arr.append(cur_event)
                cur_time += hd_func(n)
                n += 1
            partial_hd = (self.duration - cur_time) * (1.0 / (cur_time - (cur_time - hd_func(n))))
            if(n + partial_hd > most_hd_ate):    # update the winner if this contestant ate the most hot dogs
                most_hd_ate = n + partial_hd
                global winner
                winner = i
            events_arr.append(Event(self.duration, i, n + partial_hd).rounded())
            events_arr.append(Event(self.duration + 1,'Not A Contestant',0))   # to test when our sorting is done
            all_events.append([])       # create an another column to store this contestant's events data
            all_events[contestantNum] = events_arr
            contestantNum += 1        
        sorted_events = []
        while (not all_events[0][0].__eq__(all_events[1][0])):  # finishes when we reach the flagged event for both contestants
            if(all_events[0][0].__lt__(all_events[1][0])):
                sorted_events.append(all_events[0].pop(0))
            else:
                sorted_events.append(all_events[1].pop(0))	
        return sorted_events	

    def winner(self):
        """
        Get the winner of the competition.

        :return: Name of winner.
        """
        return winner


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
