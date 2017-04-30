###################################
##########  PROBLEM 3-4 ###########
###################################


from rolling_hash import rolling_hash

def roll_forward(rolling_hash_obj, next_letter):
    """
    "Roll the hash forward" by discarding the oldest input character and
    appending next_letter to the input. Return the new hash, and save it in rolling_hash_obj.hash_val as well

    Parameters
    ----------
    rolling_hash_obj : rolling_hash
        Instance of rolling_hash
    next_letter : char
        New letter to append to input.

    Returns
    -------
    hsh : int
        Hash of updated input.
    """

    # Pop a letter from the left and get the mapped value of the popped letter
    # YOUR CODE HERE

    # Push a letter to the right.
    # YOUR CODE HERE

    # Set the hash_val in the rolling hash object
    #   Hint: rolling_hash_obj.a_to_k_minus_1 may be useful

    # Return.
    raise NotImplementedError


def exact_search(rolling_hash_obj, pattern, document):
    """
    Search for string pattern in document. Return the position of the first match,
    or None if no match.

    Parameters
    ----------
    rolling_hash_obj : rolling_hash
        Instance of rolling_hash, with parameters guaranteed to be already filled in based on the inputs we will test: the hash length (k) and alphabet (alphabet) are already set
        You will need to create atleast one additional instance of rolling_hash_obj
    pattern : str
        String to search in document.
    document : str
        Document to search.

    Returns
    -------
    pos : int or None
        (zero-indexed) Position of first approximate match of S in T, or None if no match.
    """

    # may be helpful for you
    n = len(document)
    k = len(pattern)

    ## DO NOT MODIFY ##
    rolling_hash_obj.set_roll_forward_fn(roll_forward)
    rolling_hash_obj.init_hash(document[:k])
    ## END OF DO NOT MODIFY ##

    raise NotImplementedError

