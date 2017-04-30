from collections import deque, defaultdict

class rolling_hash(object):
    """
    Implements a rolling hash of a fixed diameter, supporting two operations:
    - hashing a string of a fixed length relative to a fixed alphabet
    - incrementally "rolling the hash forward" by one character
    """
    # Constants for rolling hash function.
    a = 263 # max length of alphabet is set of unicode-8 chars
    m = 2**16 - 17

    def __init__(self, hash_length, alphabet="abcdefghijklmnopqrstuvwxyz "):
        # Store simple parameters.
        self.d        = len(alphabet)
        self.alphabet = alphabet
        self.k        = hash_length

        def temp_roll_forward_fn(rolling_hash, new_char):
            raise NotImplementedError

        # set by the two setter methods
        self.roll_forward_fn = temp_roll_forward_fn

        # Initialize data structures.
        self.alphabet_map   = dict(zip(alphabet, range(self.d)))
        self.sliding_window = None
        self.hash_val = None
        self.roll_history = [] # roll forward history

    def init_hash(self, inp):
        """
        Initialize the sliding window and compute the hash of the initial input.

        Parameters
        ----------
        inp : str
            String to compute hash of.

        Returns
        -------
        hsh : int
            Hash of ``inp''.
        """
        # Ensure that len(inp) == k.
        if len(inp) != self.k:
            raise ValueError("inp must have length k!")
        # Ensure that there are no invalid characters.
        if (set(inp) - set(self.alphabet_map)):
            raise ValueError("inp contains invalid characters!")

        # Add inp to deque of sliding_window.
        self.sliding_window = deque(inp)

        # Iterate over characters backwards. Compute hash.
        hash_val   = 0
        # Store current power of a to multiply by.
        power_of_a = 1
        for letter in inp[::-1]:
            # Incrementally compute hash.
            letter_val = self.alphabet_map[letter]
            hash_val   = (hash_val + letter_val * power_of_a) % self.m

            # Compute next power of a.
            last_power_of_a = power_of_a
            power_of_a      = (power_of_a * self.a) % self.m

        # Store hash_val and a^{k-1}.
        self.hash_val       = hash_val
        self.a_to_k_minus_1 = last_power_of_a

        # Return.
        return hash_val

    def set_roll_forward_fn(self, roll_forward_fn):
        self.roll_forward_fn = roll_forward_fn

    def roll_forward(self, next_letter):
        self.roll_history.append(next_letter)
        return self.roll_forward_fn(self, next_letter)


