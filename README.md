# Introduction

Hot dog eating is an exciting, competitive sport in America. In a
regulation hot dog eating competition, two athletes eat as many hot
dogs as they can in 10 minutes. We would like to simulate the 2017
contest between the legendary Joey Chestnut and Carmen
Cincotti. Although Joey Chestnut won for the 10th time, some experts
believe Carmen Cincotti had greater stamina and could have won if the
competition had lasted longer.

# Model

Our data scientists have modeled Joey Chestnut's and Carmen Cincotti's
hot dog eating abilities with equations that predict how long it will
take to eat the next hot dog, as a function of how many hot dogs have
been eaten already. For example, Joey Chestnut's equation is:

e<sup>0.0344n</sup> + 4

Where ``n`` is the number of hot dogs eaten already. If we plug in ``n
= 0``, we see that Joey can eat his first hot dog in 5 seconds.

# Simulation

The data scientists would like you to simulate their models under
various conditions. For each model, or test case, they would like to
know when each competitor ate a whole hot dog, how many hot dogs were
eaten by each competitor, and who ate the most hot dogs.

# Requirements

- A Python 3 environment if running the Python simulation.
- A Java 8 environment if running the Java simulation.
- A Node.js 8 environment if running the JavaScript simulation.

# Tasks

- Checkout the code:

    ```
    git clone https://github.com/Tignis/hotdog.git
    ```

  Do not fork the repository. Because the repository is public, if you
  fork it, any changes that you push to your fork will also become
  public.

- Ensure that your Python and/or Java and/or Node.js environments are
  suitable for compiling and executing the code:

    ```
    python test.py
    ```

    or

    ```
    mvn test
    ```

    or

    ```
    node test.js
    ```

- Implement the ``Competition`` class, located in ``competition.py``
  or ``Competition.java`` or ``competition.js``.

- The ``run()`` function should return a list of (or iterator over)
  ``Event`` objects, one for each time that a competitor has eaten a
  whole hot dog, and one for each competitor at the end (representing
  their final results).

    ```
    [
        Event(elapsed_time=5.000, name="Joey Chestnut", total_hot_dogs_eaten=1.000),
        Event(elapsed_time=6.000, name="Carmen Cincotti", total_hot_dogs_eaten=1.000),
        ...
        Event(elapsed_time=600.000, name="Joey Chestnut, ...),
        Event(elapsed_time=600.000, name="Carmen Cincotti, ...),    
    ]
    ```

- The list of (or iterator over) ``Event`` objects should be sorted by
  ``elapsed_time`` (in Python) or ``elapsedTime`` (in Java and
  JavaScript). If two ``Event`` objects have the same
  ``elapsed_time``, they should be further sorted by ``name``.

- When reporting ``elapsed_time`` and ``total_hot_dogs_eaten`` in
  Event objects, fields should be rounded to 3 decimal places of
  precision (the newspapers do not have space). Be careful not to let
  limited precision affect the order of Events reported, or determine
  the winner of a competition. Hot dog eating competitions can be
  extremely close contests.

- The ``winner()`` function should return the name of the winner of
  the competition, e.g. ``"Joey Chestnut"``. If the two competitors
  ate exactly the same number of hot dogs, the winner is the
  competitor whose ``name`` comes first lexicographically.

- At the end of the competition, each competitor probably only ate
  part of their last hot dog. For the purposes of calculating how much
  of the last hot dog was eaten, you may assume that hot dogs are
  eaten at a linear rate. For example, if it takes Joey 5 seconds to
  eat a hot dog, he eats it a rate of 0.2 hot dogs per second.

- Test your class by running:

    ```
    python test.py
    ```

    or

    ```
    mvn test
    ```

    or

    ```
    node test.js
    ```

  You should not need to edit the ``test.py``, ``HotDogTest.java``, or
  ``test.js`` files, unless you are curious. However, you may want to
  define additional test cases in ``basic.py``,
  ``BasicTestCases.java``, or ``basic.js`` to test corner cases and
  boundary conditions.

- Commit your changes. Include any special instructions that might be
  necessary for running the code (for example, how to install any
  additional dependencies), in the commit message. Reply to the e-mail
  you received asking you to complete this exercise, and attach your
  patch file to the message.

    ```
    git commit -am "Implemented the Competition class."
    git format-patch origin/master
    ```

  Again, do not fork the repository or submit a pull request from your
  fork. Because the repository is public, if you fork it, any changes
  that you push to your fork will also become public.
