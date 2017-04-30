import unittest
from search_template import roll_forward, exact_search
from rolling_hash import rolling_hash
import random
import string
import time

# You can run these tests to check if your code works.
# These are NOT the same tests the autograder is going to use!

# In particular, the autograder will be using much larger inputs for
# exact_search and wildcard_search

def compute_hash(rh, input):
    hash_val   = 0
    # Store current power of a to multiply by.
    power_of_a = 1
    for letter in input[::-1]:
        # Incrementally compute hash.
        letter_val = rh.alphabet_map[letter]
        hash_val   = (hash_val + letter_val * power_of_a) % rh.m
        # Compute next power of a.
        power_of_a      = (power_of_a * rh.a) % rh.m
    return hash_val

def random_string(length, alphabet):
    return "".join(random.choice(alphabet) for i in range(length))

# Test for part (a)
class TestRollForward(unittest.TestCase):
    def test_roll_forward_basic(self):
        pattern = "thispsetissomuchfun"

        # initialize rolling hash with pattern and student function
        rh = rolling_hash(len(pattern))
        rh.set_roll_forward_fn(roll_forward)
        start = rh.init_hash(pattern)

        rh.roll_forward("a")
        expect = compute_hash(rh, pattern[1:] + "a")
        self.assertEqual(rh.hash_val, expect, "roll_forward is incorrect")

class TestExactSearch(unittest.TestCase):
    def test_exact_search_exists(self):
        pattern = "big big"
        doc = "this is a big big document just kidding a small document"
        rh = rolling_hash(len(pattern))
        result = exact_search(rh, pattern, doc)
        self.assertEqual(len(rh.roll_history), 10, "roll_forward called incorrect number of times")
        self.assertEqual("".join(rh.roll_history), ' a big big', "roll_forward called with incorrect inputs")
        self.assertEqual(result, 10, "exact_search is incorrect")

    def test_exact_search_doesnt_exist(self):
        pattern = "muahahaha i dont exist"
        doc = "this is a big big document just kidding a small document"
        rh = rolling_hash(len(pattern))
        result = exact_search(rh, pattern, doc)
        self.assertEqual(len(rh.roll_history), 34, "roll_forward called incorrect number of times")
        self.assertEqual("".join(rh.roll_history), 'ment just kidding a small document', "roll_forward called with incorrect inputs")
        self.assertTrue(result is None, "exact_search is incorrect")

    def test_exact_search_same_hash_different_string(self):
        # "aoopfemfonai" has the same hash as "just kidding"
        pattern = "aoopfemfonai"
        doc = "this is a big big document just kidding a small document"
        rh = rolling_hash(len(pattern))
        result = exact_search(rh, pattern, doc)
        self.assertEqual(len(rh.roll_history), 44, "roll_forward called incorrect number of times")
        self.assertEqual("".join(rh.roll_history), 'g big document just kidding a small document', "roll_forward called with incorrect inputs")
        self.assertTrue(result is None, "exact_search is incorrect")

# These are randomized tests that check for both runtime and accuracy.
class TestLong(unittest.TestCase):
    def test_roll_forward_long(self):
        pattern = "algorithmsareliterallybaeilovethemsomuch"

        # Initialize rolling hash.
        rh = rolling_hash(len(pattern))
        rh.set_roll_forward_fn(roll_forward)
        start = rh.init_hash(pattern)

        # Measure the time it takes to finish this test.
        start_time = time.time()

        # Let's roll forward by a bunch of characters!!
        for i in range(100000):
            # Roll forward by a random character.
            c = random.choice(string.ascii_lowercase)
            rh.roll_forward(c)
            # Update the pattern, just to make sure this works.
            pattern = pattern[1:] + c

        # The new hash value should equal the hash of the new pattern.
        expect = compute_hash(rh, pattern)
        self.assertEqual(rh.hash_val, expect, "roll_forward is incorrect")

        # If it took too long, return an error.
        total_time = time.time() - start_time
        print("roll_forward long test took", total_time, "seconds")
        self.assertTrue(total_time < 5, "roll_forward takes too long!")

    def test_exact_search_long(self):
        patternLen = 10000
        totalLength = 1000000

        # Generate random left/right tail lengths, but don't make the left too small.
        leftLen = random.randint(totalLength - patternLen - totalLength/2, totalLength - patternLen)
        rightLen = totalLength - patternLen - leftLen

        left = random_string(leftLen, string.ascii_lowercase)
        right = random_string(rightLen, string.ascii_lowercase)
        pattern = random_string(patternLen, string.ascii_lowercase)

        s = left + pattern + right
        rh = rolling_hash(len(pattern))

        # Time the search.
        start = time.time()
        result = exact_search(rh, pattern, s)
        total_time = time.time() - start

        # print(rh.roll_history, len(rh.roll_history))

        # Make sure it's accurate and within the time limits.
        print("exact_search long test took", total_time, "seconds")
        self.assertTrue(result == s.index(pattern), "exact_search is incorrect")
        self.assertTrue(total_time < 5, "exact_search takes too long!")

if __name__ == '__main__':
    unittest.main()


