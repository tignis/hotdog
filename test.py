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
from unittest import expectedFailure

import testcases
from competition import Event, Competition


class HotDogTest(unittest.TestCase):
    """
    Test runner for Competition class. Actual test methods are dynamically defined below for every test case in
    testcases.testcases.
    """

    @staticmethod
    def define_test(testcase, competition_class, expect_failure=False):
        """
        Dynamically define a test_* method for the given (testcase, competition_class) combination.

        :param testcase: Test case.
        :param competition_class: Competition class.
        :param expect_failure: Whether failure should be expected or not.
        :return: Test method.
        """
        def test(self):
            try:
                self.run_test(testcase, competition_class)
            except NotImplementedError:
                print("TODO: Implement or extend the Competition class.")

        return test if not expect_failure else expectedFailure(test)

    @classmethod
    def define_tests(cls, competition_class, only_testcases=None, except_testcases=None, expect_failure=False):
        """
        Dynamically define test_* methods for each testcase.

        :param competition_class: Class object (should be a subclass of Competition).
        :param only_testcases: If specified, only these testcases.
        :param except_testcases: If specified, except these testcases.
        :param expect_failure: Whether the testcase is expected to fail.
        """
        which_testcases = [
            testcase for testcase in testcases.testcases
            if ((only_testcases is None or testcase['name'] in only_testcases) and
                (except_testcases is None or testcase['name'] not in except_testcases))
        ]
        for testcase in which_testcases:
            setattr(cls, 'test_' + competition_class.__name__ + '_' + testcase['name'],
                    cls.define_test(testcase, competition_class, expect_failure))

    def run_test(self, testcase, competition_class):
        """
        Test the given Competition class.

        :param testcase: Test case dictionary.
        :param competition_class: Competition class to test.
        """
        # Instantiate the Competition class.
        competition = competition_class(competitors=testcase['competitors'],
                                        duration=testcase['duration'])
        # Run it.
        events = list(competition.run())
        # Determine the winner.
        winner = competition.winner()

        if testcase['results'] is not None:
            # Open the expected results file (e.g. 2017.json).
            with open(os.path.join(os.path.dirname(testcases.__file__), testcase['results'])) as f:
                results = json.load(f)
                expected_events = [
                    Event(elapsed_time=event['elapsedTime'],
                          name=event['name'],
                          total_hot_dogs_eaten=event['totalHotDogsEaten'])
                    for event in results['events']
                ]
                expected_winner = results['winner']
        else:
            expected_events = []
            expected_winner = None

        # Print header.
        print(competition_class.__name__, testcase['name'])
        print()

        # Print each Event.
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
                'OK' if event == expected_event else
                'n/a' if expected_event is missing else
                'FAIL'))
        print()

        # Print the winner.
        row = '{:>40}{:>20}{:>30}'
        print(row.format('Winner', 'Expected', 'Result'))
        print(row.format(winner,
                         expected_winner if expected_winner is not None else 'n/a',
                         'OK' if winner == expected_winner else
                         'n/a' if expected_winner is None else
                         'FAIL'))
        print()

        if expected_winner is not None:
            self.assertEqual(events, expected_events)
            self.assertEqual(winner, expected_winner)


# Dynamically define test methods for every test case in testcases.testcases.
HotDogTest.define_tests(Competition)


if __name__ == '__main__':
    unittest.main()
