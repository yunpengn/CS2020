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
    # Implement this method for part (b).
    pass


class Fraction(object):
    """Class for fractions."""

    def __init__(self, num, den):
        """Construct a new fraction."""
        self.num = num
        self.den = den

    def __lt__(self, other):
        """Return True if self < other, or False otherwise."""
        return compare(self, other)

    def __str__(self):
        """Return textual representation of the fraction."""
        return str(self.num) + "/" + str(self.den)


def losetime(p_s, v_s, p_f, v_f, L):
    """
    Return the moment in time when (p_f, v_f) is going to take over (p_s, v_s).

    Arguments:
        p_s (int): The starting position of the slower kart.
        v_s (int): The velocity of the slower kart.
        p_f (int): The starting position of the faster kart.
        v_f (int): The velocity of the faster kart.
        L (int): The length of the track.

    Preconditions:
        (1) 0 <= p_s, p_f < L;
        (2) v_s < v_f
        (3) p_f != p_s

    Return:
        Fraction: The time when the faster kart (p_f, v_f) is going to take
            over the slower kart (p_s, v_s).
    """
    # Implement this method for part (c).
    pass


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
    # Implement this method for part (d).
    pass


def rank(N, L, velocity, position):
    """
    Compute the rank (as defined in the problem statement) of competitor 0.

    Arguments:
        N (int): The number of competitors.
        L (int): The length of the track.
        velocity (list[int]): The velocities of all competitors, where the
            velocity of competitor i (0 <= i < N) is given by velocity[i].
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
    # Implement this method for part (f).
    pass
