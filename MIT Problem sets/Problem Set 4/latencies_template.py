###################################
##########  PROBLEM 4-4 ###########
###################################
#
# PART A: Fill in the code for part a
# 
def latencies(N, L):
    """
    Compute the latencies between every pair of servers in the 6006LE network. 
    The servers are numbered with IDs from 0...N-1.

    Parameters
    ----------
    N : int
        number of servers in the network
    L : function 
        L(i,j), where i and j are server IDs, will output the latency for the router connection between i and j. Latency must be a positive float value, in the range [0, float('inf')]

    Returns
    -------
    A : [][] (list of lists)
        N by N matrix, where A[i][j] is the latency of the shortest walk from i to j.
    """
    # YOUR CODE HERE
    A = [[[0 for x in range(N)] for y in range(N)] for z in range(N+1)]
    


    raise NotImplementedError

#
# PART B: Fill in the code for part b
#
def conservative_latencies(N, L):
    """
    Compute the latencies between every pair of servers in the 6006LE network. 
    The servers are numbered with IDs from 0...N-1.

    Parameters
    ----------
    N : int
        number of servers in the network
    L : function 
            L(i,j), where i and j are server IDs, will output the latency for the router connection between i and j. Latency must be a positive float value, in the range [0, float('inf')]

    Returns
    -------
    B : [][] (list of lists)
        N by N matrix, where B[i][j] is the latency of the SECOND shortest walk from i to j.
    """
    # YOUR CODE HERE
    A = [[[0 for x in range(N)] for y in range(N)] for z in range(N+1)]
    B = [[[0 for x in range(N)] for y in range(N)] for z in range(N+1)]
    

    raise NotImplementedError
