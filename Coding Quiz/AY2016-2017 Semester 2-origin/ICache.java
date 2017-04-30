/**
 * ICache interface for the Coding Quiz for CS2020 2017
 * 
 * This interface specifies how the cache should behave.  See below for details.
 */

// This is the default package for the Coding Quiz for CS2020 2017
package cs2020;

/**
 * ICache interface
 * 
 * This interface is parameterized by two parameters: 
 *    K for the key type and 
 *    V for the value type
 * The key type K must be Comparable, as the cache will
 * be searchable by key.
 * 
 * The goal of a cache is to store the most recently used items.
 * The size of the cache should be specified in the constructor.
 * 
 * Every time a user searches for or inserts an item, we consider that item
 * to be "accessed."  When the cache is full, it discards the (key, value) pair
 * associated with the key that has been least recently accessed, i.e., that has 
 * not been either inserted or searched for the longest time.
 *
 * @param <K>
 * @param <V>
 */
public interface ICache<K extends Comparable<K>, V> {
	
	/**
	 * inserts 
	 * 
	 * Adds (or updates) a (key, value) pair in the cache.
	 * 
	 * On an insert, the key is accessed.
	 * 
	 * The cache has a limited size.  When the cache is full (and hence
	 * an insert is not possible), it discards the least recently accessed
	 * key so that there is room for the new (key, value) pair.
	 * 
	 * If a key is inserted that is already in the cache, then:
	 *   (1) The key is accessed (even if there is no change to the value).
	 *   (2) If the insert has a new value, then the value is updated.
	 * 
	 * @param key
	 * @param value
	 */
	void insert(K key, V value);
	
	/**
	 * search
	 * 
	 * Returns the value associated with the given key in the cache.
	 * If the key is not found in the cache, then it returns null.
	 * 
	 * If the key is in the cache, then it is accessed.
	 * 
	 * If the key is null, then it should return null.
	 * 
	 * @param key 
	 * @return value associated with the specified key
	 */
	V search(K key);

}
