"""Main solution file for F1 Kart Racing."""

import heapq


def compare(a, b):
    """
    Return True if a is smaller than b, or False, otherwise.

    Arguments:
        a (Fraction): The first fraction.
        b (Fraction): The second fraction.

    Return:
        bool: A boolean that is True if a < b, or False, otherwise.
    """
    return a.num * b.den < a.den * b.num


class Fraction(object):
    """Class for fractions."""

    def __init__(self, num, den):
        """Construct a new fraction."""
        self.num = num
        self.den = den

    def __lt__(self, other):
        """Necessary for heapq to work automagically."""
        return compare(self, other)

    def __str__(self):
        """Return textual representation of the fraction."""
        return str(self.num) + "/" + str(self.den)


def losetime(p_s, v_s, p_f, v_f, L):
    """
    Return when (p_f, v_f) is going to take over (p_s, v_s).

    Return the time when the kart (p_s, v_s) is going to take over the kart
    (p_f, v_f) on a track of length L.

    Arguments:
        p_s (int): The starting position of the slower kart.
        v_s (int): The top velocity of the slower kart.
        p_f (int): The starting position of the faster kart.
        v_f (int): The top velocity of the faster kart.
        L (int): The length of the track.

    Preconditions:
        (1) 0 <= p_s, p_f < L;
        (2) v_s < v_f
        (3) p_f != p_s

    Return:
        Fraction: The time when the faster kart is going to take over the
            slower one.
    """
    if p_s > p_f:
        return Fraction(p_s - p_f, v_f - v_s)
    elif p_s < p_f:
        return Fraction((L - p_f) + p_s, v_f - v_s)
    else:
        raise Exception("There should not be two cars with the same speed.")


def remove(i, ahead, behind):
    """
    Update the (ahead, behind) data structure by removing competitor i.

    Note that this method does not return anything.

    Arguments:
        i (int): The id of the competitor.
        ahead (dict): A dictionary where the competitor ahead of competitor j
            is given by ahead[j].
        behind (dict): A dictionary where the competitor behind competitor j
            is given by behind[j].

    Preconditions:
        (1) i is present in both dictionaries.
        (2) for every competitor i present in either ahead or behind, we have
            i = ahead[behind[i]], as well as i = behind[ahead[i]]

    Return: Nothing.
    """
    assert i in behind
    assert i in ahead
    behind_me = behind[i]
    ahead_of_me = ahead[i]
    del behind[i]
    del ahead[i]
    ahead[behind_me] = ahead_of_me
    behind[ahead_of_me] = behind_me


def rank(N, L, velocity, position):
    """
    Return the rank-of-0 (as defined in the problem statement).

    Arguments:
        N (int): The number of competitors.
        L (int): The length of the track.
        velocity (list[int]): The top velocities of all competitors, where the
            top velocity of competitor i (0 <= i < N) is given by velocity[i].
        position (list[int]): The starting positions of all competitors, where
            the starting position of competitor i (0 <= i < N) is given by
            position[i].

    Preconditions:
        (1) len(velocity) = len(position) = N
        (2) all elements of velocity are distinct, and non-negative
        (3) all elements of position are distinct, and non-negative

    Return: The rank of competitor 0, which is a number between 1 and N,
        inclusive.
    """
    # Step 1: sort competitors by position, so that we can build ahead-behind.
    # This takes O(N log N) time.
    karts = sorted([(position[i], i) for i in range(N)])

    # Step 2: build the ahead-behind datastructure. This takes O(n) time.
    ahead, behind = {}, {}
    for i in range(N):
        ahead[karts[i][1]] = karts[(i + 1) % N][1]
        behind[karts[i][1]] = karts[(i - 1 + N) % N][1]

    event_q = []  # Create an empty min-heap.

    def add_event(i, j):
        """Add the event kart i takes over kart j to the heap."""
        assert velocity[i] > velocity[j]
        v_f, p_f = velocity[i], position[i]
        v_s, p_s = velocity[j], position[j]
        heapq.heappush(event_q, (losetime(p_s, v_s, p_f, v_f, L), i, j))

    # Step 3: initialize the priority queue with initial events.
    # Takes O(N log N).
    for i in range(N):
        if velocity[karts[i][1]] > velocity[karts[(i + 1) % N][1]]:
            add_event(karts[i][1], karts[(i + 1) % N][1])

    eliminated = set()  # We maintain a set with all eliminated competitors.
    my_finish_time = Fraction(2 * L, 1)
    # Step 4: do the simulation. Takes O(N log N)
    while len(event_q) > 0:
        time, fast, slow = heapq.heappop(event_q)
        if (fast in eliminated) or (slow in eliminated):
            # This event is no longer valid, because at least one of the
            # competitors is already eliminated. It's safe to ignore the event.
            continue
        if slow == 0:
            my_finish_time = time  # Record our finishing time.
        if compare(my_finish_time, time):
            # We are out of the game, and everybody still in is going to be
            # eliminated at time strictly larger than ours.
            break
        eliminated.add(slow)  # We eliminate slow from the competition.
        ahead_of_slow = ahead[slow]  # We need who is in front of slow.
        remove(slow, ahead, behind)  # We remove slow from the competition.
        if velocity[fast] > velocity[ahead_of_slow]:
            # We add the event of fast overtaking ahead_of_slow to the heap.
            add_event(fast, ahead_of_slow)

    # Step 5: return the rank of 0. Takes O(1)
    return 1 if 0 not in eliminated else (N - len(eliminated)) + 1
