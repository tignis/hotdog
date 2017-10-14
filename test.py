#
# Copyright (C) 2017 Tignis, Inc.
#

"""
Unit tests for various implementations of the Competition class.
"""

import json
import os
import unittest
from itertools import zip_longest

import testcases
from competition import Event


class HotDogTest(unittest.TestCase):
    def run_test(self, competition_class):
        """
        Test the given Competition class to the given precision.

        :param competition_class: Competition class to test.
        """
        for testcase in testcases.testcases:
            competition = competition_class(competitors=testcase['competitors'],
                                            duration=testcase['duration'])
            events = list(competition.run())

            with open(os.path.join(os.path.dirname(testcases.__file__), testcase['results'])) as f:
                expected_events = [
                    Event(elapsed_time=event['elapsedTime'],
                          name=event['name'],
                          total_hot_dogs_eaten=event['totalHotDogsEaten'])
                    for event in json.load(f)
                ]
            expected_winner = max(expected_events, key=lambda event: event.total_hot_dogs_eaten).name

            row = '{:>10}{:>10}{:>20}{:>20}{:>10}{:>10}{:>10}'
            print(row.format('Time', 'Expected', 'Competitor', 'Expected', 'Hot Dogs', 'Expected', 'Result'))
            missing = Event('n/a', 'n/a', 'n/a')
            for event, expected_event in zip_longest(events, expected_events, fillvalue=missing):
                print(row.format(
                    event.elapsed_time,
                    expected_event.elapsed_time,
                    event.name, expected_event.name,
                    event.total_hot_dogs_eaten,
                    expected_event.total_hot_dogs_eaten,
                    'OK' if event == expected_event else 'FAIL'))

            self.assertEqual(events, expected_events)
            self.assertEqual(competition.winner(), expected_winner)


class YourTest(HotDogTest):
    def test_your_competition_class(self):
        # TODO: Run tests against your competition class.
        # self.run_test(YourCompetitionClass)
        pass
