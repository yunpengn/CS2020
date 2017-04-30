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
	for title in os.listdir(corpus_dir):
		with open(os.path.join(corpus_dir, title)) as f:
			corpus[title] = re.sub("[^\w]", " ",  f.read()).split()
	return corpus

class SearchEngine(object):
	"""
	Represents an instance of a search engine. Instances of the search engine are 
	initialized with a corpus.

	Args:
		corpus (dict): A dictionary of (article title, article text) pairs.
	"""
	def __init__(self, corpus):
		# the corpus of (article title, article text) pairs
		self.corpus = corpus
		# for each title, contains a map of (term, frequency) pairs
		self.tf = {}
		# contains, for each term, the number of documents it appears in
		df = {}
		# contains, for each term, its inverse document frequency
		self.idf = {}
		# the number of documents in the corpus
		self.num_docs = len(corpus)

		for title in self.corpus:
			tf = {}
			for word in self.corpus[title]:
				word = word.lower()
				# first time showing up in this document, increment document frequency
				if word not in tf:
					df.setdefault(word, 0)
					df[word] += 1
					tf[word] = 0
				tf[word] += 1
			self.tf[title] = tf

		# now, set the idf's given the df's
		for w in df:
			self.idf[w] = math.log(self.num_docs * 1.0/df[w])


	def dot(self, vec1, vec2, use_idf):
		"""
		Computes the dot product between two vectors in {key: value} form.
		If use_idf is true, it multiplies each term by the idf of the word.

		Args:
			vec1 (dict): The first vector
			vec2 (dict): The second vector
		"""
		dot = 0
		for key in vec1:
			# add to dot product if the key exists, and ignore otherwise
			prod = vec1[key] * vec2.get(key, 0)
			# if we're using idf, then multiply by the idf, once for each vector
			if use_idf:
				prod = prod * (self.idf.get(key, 0))**2
			dot += prod
		return dot


	def distance(self, title1, title2, use_idf):
		mag1 = self.dot(self.tf[title1], self.tf[title1], use_idf)
		mag2 = self.dot(self.tf[title2], self.tf[title2], use_idf)
		dot = self.dot(self.tf[title1], self.tf[title2], use_idf)
		# return angle, in radians, between the two vectors
		# (there's some bounding in case rounding errors lead to arrcosine domain issues)
		return math.acos(min(dot/math.sqrt(mag1)/math.sqrt(mag2), 1))

	def get_relevant_articles_doc_dist(self, title, k):
		"""
		Returns the articles most relevant to a given document, limited to at most
		k results. Uses the normal document distance score.

		Args:
			title (str): The title of the article being queried  


		Returns:
			An array of the k most relevant (article title, document distance) pairs, ordered 
			by decreasing relevance. 

			Specifications:
				* Case is ignored entirely
				* If two articles have the same distance, titles should be in alphabetical order
		"""
		# get a list of (title, distance) pairs
		scores = [(t, self.distance(t, title, False)) for t in self.corpus if t != title]
		# sort by distance and in alphabetical order
		res = sorted(scores, key = lambda x : (x[1], x[0]))
		# return the first k
		return res[:k] if k < len(res) else res

	def get_relevant_articles_tf_idf(self, title, k):
		"""
		Returns the articles most relevant to a given document, limited to at most
		k results. Uses the document distance with TF-IDF scores.

		Args:
			title (str): The title of the article being queried  

		Returns:
			An array of the k most relevant (article title, document distance) pairs, ordered 
			by decreasing relevance. 

			Specifications:
				* Case is ignored entirely
				* If two articles have the same distance, titles should be in alphabetical order
		"""
		# get a list of (title, distance) pairs
		scores = [(t, self.distance(title, t, True)) for t in self.corpus if t != title]
		# sort by distance and in alphabetical order
		res = sorted(scores, key = lambda x : (x[1], x[0]))
		# return the first k
		return res[:k] if k < len(res) else res

	def search(self, query, k):
		"""
		Returns the articles most relevant to a given query, limited to at most
		k results.

		Args:
			query (str): The query for the search engine.

		Returns:
			An array of the k best (article title, tf-idf score) pairs, ordered by decreasing score. 

		    Specifications: 
			    * Only consider articles with a positive tf-idf score. 
			    * If there are fewer than k results with a positive tf-idf score, return those results.
				  If there are more, return only the k best results.
			    * If two articles have the same score, titles should be in alphabetical order
		"""
		scores = {}
		for title in self.corpus:
			score = 0
			# for each distinct query term, sum its tf*idf value.
			for word in set(query.lower().split()):
				idf = self.idf.get(word, 0)
				tf = self.tf[title].get(word, 0)
				score += tf * idf
			scores[title] = score

		# Sort by decreasing score and in alphabetical order
		res = sorted([x for x in scores.items() if x[1] > 0], key = lambda x : (-x[1], x[0]))
		# return the first k results
		return res[:k] if k < len(res) else res
		
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
				print("    - %s (score %f)" % (title, score))

