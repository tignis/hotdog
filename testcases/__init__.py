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
    },
# {
#         'competitors': {
#             'Joey Chestnut': lambda n: math.exp(0.0344 * n) + 4,
#             'Carmen Cincotti': lambda n: n * 0.120 + 6,
#         },
#         'duration': 1200,
#         'results': '2017.json',
#     },
]
