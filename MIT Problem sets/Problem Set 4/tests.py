import unittest
import ast
from latencies import latencies, conservative_latencies

class TestLatencies(unittest.TestCase):
    def test_basic(self):
        N = 5
        inf = float("inf")
        cost_matrix =   [
                            [0,     1,      2,      2,      inf],
                            [1,     0,      inf,    3,      8],
                            [2,     inf,    0,      4,      1],
                            [2,     3,      4,      0,      1],
                            [inf,   8,      1,      1,      0]
                        ]
        L = lambda x, y: cost_matrix[x][y]
        student_res = latencies(N, L)
        expect_res = ast.literal_eval("[[0, 1, 2, 2, 3], [1, 0, 3, 3, 4], [2, 3, 0, 2, 1], [2, 3, 2, 0, 1], [3, 4, 1, 1, 0]]")
        self.assertEqual(student_res, expect_res, "Oops! latencies is incorrect")


class TestConservativeLatencies(unittest.TestCase):
    def test_conservative_basic(self):
        N = 5
        inf = float("inf")
        cost_matrix =   [
                            [0,     1,      2,      2,      inf],
                            [1,     0,      inf,    3,      8],
                            [2,     inf,    0,      4,      1],
                            [2,     3,      4,      0,      1],
                            [inf,   8,      1,      1,      0]
                        ]
        L = lambda x, y: cost_matrix[x][y]
        student_res = conservative_latencies(N, L)
        expect_res = ast.literal_eval("[[2, 3, 4, 4, 3], [3, 2, 5, 3, 4], [4, 5, 2, 4, 3], [4, 3, 4, 2, 3], [3, 4, 3, 3, 2]]")
        self.assertEqual(student_res, expect_res, "Oops! conservative_latencies is incorrect")

    def test_conservative_advanced(self):
        N = 6
        inf = float("inf")
        cost_matrix =   [
                            [0,     inf,      inf,      2,      3,   4],
                            [inf,     0,      2,    inf,      inf,   4],
                            [inf,   2,      0,      3,        2,   inf],
                            [2,     inf,     3,      0,      inf,  inf],
                            [3,     inf,      2,      inf,      0, inf],
                            [4,     4,      inf,     inf,    inf,    0]
                        ]
        L = lambda x, y: cost_matrix[x][y]
        student_res = conservative_latencies(N, L)
        expect_res = ast.literal_eval("[[4, 7, 5, 6, 7, 8], [7, 4, 6, 9, 8, 8], [5, 6, 4, 7, 6, 9], [6, 9, 7, 4, 5, 9], [7, 8, 6, 5, 4, 8], [8, 8, 9, 9, 8, 8]]")
        self.assertEqual(student_res, expect_res, "Oops! conservative_latencies is incorrect")

    def test_conservative_loops(self):
        N = 10
        inf = float("inf")
        cost_matrix =   [
                            [0, inf, 15, 18, 11, 9, 16, 9, inf, inf], 
                            [inf, 0, 8, 13, 7, 12, 17, inf, 16, 5] ,
                            [15, 8, 0, 18, 17, 11, 14, 20, 11, inf],
                            [18, 13, 18, 0, 9, 20, inf, inf, 5, inf], 
                            [11, 7, 17, 9, 0, 7, 11, 9, 16, 4] ,
                            [9, 12, 11, 20, 7, 0, 7, 14, 2, 2] ,
                            [16, 17, 14, inf, 11, 7, 0, 20, 6, 3],
                            [9, inf, 20, inf, 9, 14, 20, 0, inf, 12], 
                            [inf, 16, 11, 5, 16, 2, 6, inf, 0, inf],
                            [inf, 5, inf, inf, 4, 2, 3, 12, inf, 0]  
                        ]
        L = lambda x, y: cost_matrix[x][y]
        student_res = conservative_latencies(N, L)
        expect_res = ast.literal_eval("[[18, 18, 20, 18, 15, 13, 16, 20, 15, 15], [18, 10, 18, 14, 9, 11, 12, 17, 13, 9], [20, 18, 16, 18, 17, 13, 16, 24, 13, 13], [18, 14, 18, 10, 13, 11, 12, 21, 9, 13], [15, 9, 17, 13, 8, 7, 11, 16, 9, 8], [13, 11, 13, 11, 7, 4, 7, 14, 6, 6], [16, 12, 16, 12, 11, 7, 6, 16, 7, 7], [20, 17, 24, 21, 16, 14, 16, 18, 16, 13], [15, 13, 13, 9, 9, 6, 7, 16, 4, 8], [15, 9, 13, 13, 8, 6, 7, 13, 8, 4]]")
        self.assertEqual(student_res, expect_res, "Oops! conservative_latencies is incorrect. What should you do in your Floyd-Warshall algorithm when k = i or k = j?")

    def test_conservative_loops_advanced(self):
        N = 10
        inf = float("inf")
        cost_matrix =   [
                            [0, 8, 10, 9, 4, 2, inf, inf, 10, 5], 
                            [8, 0, 17, 2, 1, 7, 2, 4, 14, 18] ,
                            [10, 17, 0, 16, 10, 3, 14, 2, 8, 5],
                            [9, 2, 16, 0, 4, 14, inf, 13, 6, 9],
                            [4, 1, 10, 4, 0, inf, 11, inf, 6, 19], 
                            [2, 7, 3, 14, inf, 0, inf, inf, inf, inf],
                            [inf, 2, 14, inf, 11, inf, 0, 1, 19, inf],
                            [inf, 4, 2, 13, inf, inf, 1, 0, 13, 12] ,
                            [10, 14, 8, 6, 6, inf, 19, 13, 0, 17] ,
                            [5, 18, 5, 9, 19, inf, inf, 12, 17, 0] 
                        ]
        L = lambda x, y: cost_matrix[x][y]
        student_res = conservative_latencies(N, L)
        expect_res = ast.literal_eval("[[4, 7, 9, 8, 6, 6, 8, 8, 10, 9], [7, 2, 6, 4, 3, 7, 4, 4, 8, 10], [9, 6, 4, 8, 7, 7, 5, 4, 12, 9], [8, 4, 8, 4, 4, 9, 6, 6, 9, 12], [6, 3, 7, 4, 2, 8, 5, 5, 8, 11], [6, 7, 7, 9, 8, 4, 8, 7, 12, 8], [8, 4, 5, 6, 5, 8, 2, 3, 10, 10], [8, 4, 4, 6, 5, 7, 3, 2, 10, 9], [10, 8, 12, 9, 8, 12, 10, 10, 12, 15], [9, 10, 9, 12, 11, 8, 10, 9, 15, 10]]")
        self.assertEqual(student_res, expect_res, "Oops! conservative_latencies is incorrect. Make sure you're checking to see if a loop around some vertex on the shortest path results in a shorter second shortest path!")
    

if __name__ == '__main__':
    unittest.main()
