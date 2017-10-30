#
# Copyright (C) 2017 Tignis, Inc.
#

"""
Test cases for debugging.
"""
import math

testcases = [
    {
        'competitors': {
            'Joey Chestnut': lambda n: math.exp(0.0344 * n) + 4,
            'Carmen Cincotti': lambda n: n * 0.120 + 6,
        },
        'duration': 600,
        'results': '2017.json',
        'name': '2017',
    },

    # Consider adding additional test cases to test corner cases:
    #
    # 1. Who is the winner in the event of a tie?
    # 2. Which Event should be emitted first if multiple Events occur at the same time?
    #
    # To add a test case but not verify it, specify None for the results file.
]
