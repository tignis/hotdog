#
# Copyright (C) 2017 Tignis, Inc.
#

"""
Implement the Competition class.
"""
from decimal import Decimal
from operator import attrgetter

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
        self.competitors = competitors
        self.duration = duration
        self.events = []
        self.competition_winner = 'Error: Competition has no events.'

    def run(self):
        """
        Run a simulation of the competition.

        :return: List of (or iterator over) Events.
        """

        events = []

        for name_key in self.competitors:
            time_cursor = 0.000
            hotdog_cursor = 0.000
            while time_cursor <= self.duration:
                time_cursor += self.competitors[name_key](hotdog_cursor)
                hotdog_cursor += 1.000

                # append Event for all whole hotdogs eaten within the competition duration
                if time_cursor <= self.duration:
                    events.append(Event(elapsed_time=time_cursor,
                                        name=name_key,
                                        total_hot_dogs_eaten=hotdog_cursor).rounded())

            # Add last Event for fractional hotdog eaten as time expired
            fractional_hotdog = (hotdog_cursor-1)+(self.duration - events[-1].elapsed_time)/(time_cursor - events[-1].elapsed_time)
            events.append(Event(elapsed_time=self.duration,
                                name=name_key,
                                total_hot_dogs_eaten=fractional_hotdog).rounded())

        # store the sorted events as the instance events
        self.events = sorted(events, key=attrgetter('elapsed_time', 'name'))

        # set the instance competition winner
        self.competition_winner = self.winner()

        # return the list of Events sorted by 'elapsed_time' then by 'name'
        return self.events


    def winner(self):
        """
        Get the winner of the competition.

        :return: Name of winner.
        """
        # the winner will be the name of the 0th element of the events list sorted by total hot dogs eaten
        return sorted(self.events, key=attrgetter('total_hot_dogs_eaten'))[0].name


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
