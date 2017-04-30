import unittest
from racing_template import Fraction, compare, losetime, remove, rank


def equal_fractions(a, b):
    """Returns True if the fractions a and b are equal."""
    if type(compare(a, b)) != bool:
        raise Exception("compare method is not implemented correctly!")
    if type(compare(b, a)) != bool:
        raise Exception("compare method is not implemented correctly!")
    return (not compare(a, b)) and (not compare(b, a))


class TestCompare(unittest.TestCase):
    def test_compare(self):
        self.assertTrue(compare(Fraction(7, 3), Fraction(10, 2)))
        self.assertFalse(compare(Fraction(10, 2), Fraction(7, 3)))
        self.assertFalse(compare(Fraction(1, 10), Fraction(1, 10)))
        self.assertFalse(compare(Fraction(2, 10), Fraction(1, 5)))
        self.assertTrue(compare(
            Fraction(10 ** 10, 10), Fraction(10 ** 10, 1)))


    def test_compare_no_division(self):
        actual = Fraction(10 ** 100, 3 ** 205)
        truncated = Fraction(15493259051847846, 10 ** 14)
        self.assertTrue(compare(actual, truncated),
                "Make sure you are not using division to compare fractions.")


class TestLosetime(unittest.TestCase):
    def test_losetime_slower_behind(self):
        self.assertTrue(equal_fractions(Fraction(25, 5),
                losetime(5, 2, 10, 7, 30)))


    def test_losetime_faster_behind(self):
        self.assertTrue(equal_fractions(Fraction(5, 5),
                losetime(10, 2, 5, 7, 30)))


class TestRemove(unittest.TestCase):
    def test_remove_only_two_karts(self):
        # We have the loop 10 --> 20 --> 10.
        ahead_1 = {10: 20, 20: 10}
        behind_1 = {20: 10, 10: 20}
        remove(10, ahead_1, behind_1)
        self.assertEqual(ahead_1, {20: 20})
        self.assertEqual(behind_1, {20: 20})


    def test_remove_four_karts(self):
        # We have the loop 4 --> 7 --> 2 --> 9 --> 4.
        ahead_2 = {4: 7, 7: 2, 2: 9, 9: 4}
        behind_2 = {4: 9, 9: 2, 2: 7, 7: 4}
        remove(7, ahead_2, behind_2)
        self.assertEqual(ahead_2, {4: 2, 2: 9, 9: 4})
        self.assertEqual(behind_2, {4: 9, 9: 2, 2: 4})


class TestKartRacing(unittest.TestCase):
    def test_rank(self):
        # Consider that there are 4 competitors, starting from respectively
        # from positions 10, 5, 13, 25 and with top velocities 7, 8, 1, 5.
        # First, competitor 2 (we count from 0) is taken out at time 1/2 by 1.
        # Then, competitor 0 is taken out by competitor 1 at time 5.
        # Finally, competitor 3 is taken out by competitor 1 at time 20/3.
        self.assertEqual(3, rank(4, 30, [7, 8, 1, 5], [10, 5, 13, 25]))

    def test_rank_others(self):
        # We reuse the example above, but try other competitors as 0.
        self.assertEqual(1, rank(4, 30, [8, 7, 1, 5], [5, 10, 13, 25]))
        self.assertEqual(4, rank(4, 30, [1, 8, 7, 5], [3, 5, 10, 25]))
        self.assertEqual(2, rank(4, 30, [5, 8, 1, 7], [25, 5, 13, 10]))

    def test_rank_multiple_takeovers_at_the_same_time(self):
        self.assertEqual(3, rank(4, 32, [7, 46, 35, 100], [4, 30, 27, 17]))


if __name__ == '__main__':
    unittest.main()

