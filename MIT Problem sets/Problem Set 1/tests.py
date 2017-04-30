import unittest
from search_engine import SearchEngine, extract_corpus

# You can run these tests to check if your code works. 
# These are NOT the same tests the autograder is going to use!

# Helper function to make sure the outputs are basically the same
def approx_equals(result, expected):
    # Allow for a little error
    threshold = 0.0001
    if len(result) != len(expected):
        return False
    for i in range(len(expected)):
        if result[i][0] != expected[i][0]:
            return False
        if abs(result[i][1] - expected[i][1]) > threshold:
            return False
    return True

# Intialize search engine from student code
corpus = extract_corpus()
e = SearchEngine(corpus)

class TestSearchEngine(unittest.TestCase):
    # Test for part (a)

    def test_doc_dist(self):
        expected = [
            ('Red-black tree', 0.45339), 
            ('Binary search algorithm', 0.4961), 
            ('Algorithm', 0.58189), 
            ('Hash table', 0.61123), 
            ("Dijkstra's algorithm", 0.61204)
        ]
        result = e.get_relevant_articles_doc_dist("Binary search tree", 5)
        self.assertTrue(approx_equals(result, expected), "part (a) test failed")

    # Test for part (b)
    def test_tf_idf(self):
        expected = [
            ('Strawberry', 1.22348), 
            ('Blueberry', 1.27202), 
            ('Blackberry', 1.33453), 
            ('Raspberry', 1.38068), 
            ('Grape', 1.46287)
        ]
        result = e.get_relevant_articles_tf_idf("Berry", 5)
        self.assertTrue(approx_equals(result, expected), "part (b) test failed")

    # Test for part (c)
    def test_search(self):
        expected = [
            ('Algorithm', 167.916), 
            ("Dijkstra's algorithm", 139.82119), 
            ('Dynamic programming', 135.76526), 
            ('Red-black tree', 70.75283), 
            ("Johnson's algorithm", 60.26834)
        ]
        result = e.search("should i use a graph algorithm or tree algorithm", 5)
        self.assertTrue(approx_equals(result, expected), "part (c) test failed")

if __name__ == '__main__':
    unittest.main()

