#
# Copyright (C) 2017 Tignis, Inc.
#

from .basic import testcases as basic_testcases
testcases = basic_testcases

try:
    from .advanced import testcases as advanced_testcases
    testcases += advanced_testcases
except ImportError:
    # Advanced test cases are not provided.
    pass
