/**
 * Cache class for the Coding Quiz for CS2020 2017
 * 
 * Complete the implementation of the Cache class.
 * It should correctly implement the ICache interface.
 * See the ICache interface for details of how the operations should work.
 * 
 * You may modify any part of the class that you need to complete the 
 * implementation, including adding member variables, changing existing
 * methods, and adding new methods.
 */


// This is the default package for the Coding Quiz for CS2020 2017
package cs2020;

// This class will use the Java TreeMap.
// See the Coding Quiz for details of how to use the TreeMap that you may need.
import java.util.TreeMap;

/**
 * Cache
 * 
 * This is the main Cache class.  It has two parameters:
 *  (1) key type K and 
 *  (2) value type V.
 * 
 * It implements the ICache interface.  See the ICache interface
 * for details of how the operations should work.
 * 
 * The constructor of the Cache class specifies the size of the cache.
 *
 * @param <K>
 * @param <V>
 */
public class Cache<K extends Comparable<K>, V> implements ICache<K, V> {

	/* 
	 * Member variables for the cache class
	 */
	
	// The data in the cache is stored in this TreeMap
	// The TreeMap is parameterized by two types:
	//    (1) the key type K
	//    (2) a node type CNode that stores the information associated with the key.
	// Notice that the node type CNode can be used to implement a linked list that
	// keeps track of the order in which items are accessed.
	private TreeMap<K, CNode> m_cache;
	
	// Most recently and least recently accessed items.
	private CNode m_oldest = null;
	private CNode m_newest = null;
	
	// Add other members variables that you need here.
	
	/**
	 * Constructor
	 * 
	 * @param cacheSize specifies the size of the cache.
	 */
	Cache(int cacheSize){
		// Create a new TreeMap to store the cache.
		m_cache = new TreeMap<K, CNode>();
	}
	
	/**
	 * insert
	 * 
	 * Adds item key/value to the cache as the most recent item.
	 * If the key is already in the cache, then it updates the value.
	 * 
	 * It also updates the linked list to reflect that it is the most recent
	 * item accessed.
	 * 
	 * @param key the key to insert
	 * @param value the value to insert
	 */
	@Override
	public void insert(K key, V value) {
		// To add something to the TreeMap m_cache, use the following syntax:
		//     m_cache.put(x, y) 
		// where x and y are of the two types that parameterize the TreeMap.
	}

	/**
	 * search
	 * 
	 * Returns the value associated with the specified key, if it is in the cache.
	 * If it is not in the cache, then return null.
	 * 
	 * After accessing an item, it should be the most recently accessed item.
	 * 
	 * @param key
	 */
	@Override
	public V search(K key) {		
		// To search for a key in the TreeMap m_cache, use the following syntax:
		//    m_cache.get(x) 
		// where x is the first type that parameterize the TreeMap.
		return null;
	}

	/**
	 * printCacheKeys
	 * 
	 * This is a test routine that prints the keys in the cache in order of the keys.
	 */
	void printCacheKeys(){
		// Enumerate all the keys in the cache
		for (K key : m_cache.keySet()){
			// Get the associated value
			V value = m_cache.get(key).value;
			// Print (key, value)
			System.out.println(key + ", " + value);
		}
	}
	
	/**
	 * CNode
	 * 
	 * This is an inner class use to store the (key, value) pair
	 * in the cache.
	 * 
	 * It also stores next and and prev pointers to the items added
	 * immediately before and after it.
	 *
	 */
	private class CNode {
		
		// key and value for this node in the cache
		private K key;
		private V value;
		
		// next and prev pointers for the linked list that maintains the
		// order that nodes were accessed.
		private CNode next;
		private CNode prev;
		
		/**
		 * Constructor
		 * 
		 * Creates a new node with a given (key, value)
		 * 
		 * @param k key
		 * @param v value
		 */
		CNode(K k, V v){
			key = k;
			value = v;
			next = null;
			prev = null;
		}
	}
	
	/**
	 * main
	 * 
	 * Simple test code for the cache.
	 * 
	 * Inserts 20 items into a cache of size 5, and the prints out the
	 * keys in the cache.  It should print out the last 5 items inserted.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int size = 5;
		Cache<Integer, Integer> cache = new Cache<Integer, Integer>(size);
					
		for (int i=0; i<20; i++)
		{			
			cache.insert(20-i, i);
		}
			
		cache.printCacheKeys();
	}
	
}
