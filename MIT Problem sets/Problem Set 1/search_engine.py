import os
import math
import re

def extract_corpus(corpus_dir = "articles"):
	"""
	Returns a corpus of articles from the given directory.

	Args:
		corpus_dir (str): The location of the corpus.

	Returns:
		dict: A dictionary with key = title of the article, 
			  value = list of words in the article
	"""
	corpus = {}
	num_documents = 0
	for filename in os.listdir(corpus_dir):
		with open(os.path.join(corpus_dir, filename)) as f:
			corpus[filename] = re.sub("[^\w]", " ",  f.read()).split()
	return corpus

class SearchEngine(object):
	"""
	Represents an instance of a search engine. Instances of the search engine are 
	initialized with a corpus.

	Args:
		corpus (dict): A dictionary of (article title, article text) pairs.
	"""
	def __init__(self, corpus):
		# The corpus of (article title, article text) pairs.
		self.corpus = corpus

	def get_relevant_articles_doc_dist(self, title, k):
		"""
		Returns the articles most relevant to a given document, limited to at most
		k results. Uses the normal document distance score.

		Args:
			title (str): The title of the article being queried (assume it exists). 


		Returns:
			An array of the k most relevant (article title, document distance) pairs, ordered 
			by decreasing relevance. 

			Specifications:
				* Case is ignored entirely
				* If two articles have the same distance, titles should be in alphabetical order
		"""
		# TODO: Implement this for part (a)
		return []

	def get_relevant_articles_tf_idf(self, title, k):
		"""
		Returns the articles most relevant to a given document, limited to at most
		k results. Uses the document distance with TF-IDF scores.

		Args:
			title (str): The title of the article being queried (assume it exists). 

		Returns:
			An array of the k most relevant (article title, document distance) pairs, ordered 
			by decreasing relevance. 

			Specifications:
				* Case is ignored entirely
				* If two articles have the same distance, titles should be in alphabetical order
		"""
		# TODO: Implement this for part (b)
		return []

	def search(self, query, k):
		"""
		Returns the articles most relevant to a given query, limited to at most
		k results.

		Args:
			query (str): The query for the search engine. Doesn't contain any special characters.

		Returns:
			An array of the k best (article title, tf-idf score) pairs, ordered by decreasing score. 

		    Specifications: 
			    * Only consider articles with a positive tf-idf score. 
			    * If there are fewer than k results with a positive tf-idf score, return those results.
				  If there are more, return only the k best results.
			    * If two articles have the same score, titles should be in alphabetical order
		"""
		# TODO: Implement this for part (c)
		return []
		
if __name__ == '__main__':
	corpus = extract_corpus()
	e = SearchEngine(corpus)
	print("Welcome to 6006LE! We hope you have a wonderful experience. To exit, type 'exit.'")
	print("\nSuggested searches: the yummiest fruit in the world, child prodigy, operating system, red tree, coolest algorithm....")
	while True:
		query = input('\nEnter query here: ').strip()
		if query == "exit":
			print("Good bye!")
			break
		results = e.search(query, 5)
		if len(results) == 0:
			print("There are no results for that query. :(")
		else:
			print("Top results: ")
			for title, score in e.search(query, 5):
				print ("    - %s (score %f)" % (title, score))
