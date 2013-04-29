/* HashTableChained.java */

package dict;

import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/

  private DList[] hashTable;
  private int hashTableSize;
  private int numberOfEntries;



  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    int nearestPrime = primeSieve((int)(sizeEstimate*4/3));
    hashTable = new DList[nearestPrime];
    hashTableSize = nearestPrime;
    makeEmpty();
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    hashTable = new DList[103];
    hashTableSize = 103;
    makeEmpty();
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    return ((7 * code + 17) % 99991 + 99991) % hashTableSize;  
    }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    return numberOfEntries;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {

    return (numberOfEntries == 0);
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    Entry insertValue = new Entry();
    insertValue.key = key;
    insertValue.value = value;
    if (key instanceof String) {
    	hashTable[compFunction(StringHashCode((String)key))].insertBack(insertValue);
    } else {
	    hashTable[compFunction(key.hashCode())].insertBack(insertValue);
    }  
    numberOfEntries++;
    return insertValue;
  }
  
  /**
   * StringHashCode() returns a good hashcode for a string.
   * @param key the key for which the hashcode is to be found.
   * @return an integer representing the hashcode.
   */
  
  private static int StringHashCode(String key) {
  	int hashVal = 0;
  	for (int i = 0; i  < key.length(); i++) {
  		hashVal = (127 * hashVal + key.charAt(i)) % 16908799;
  	}
  	return hashVal;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
  	
  	int arrayIndex = compFunction(key.hashCode());
  	if (key instanceof String) {
  		arrayIndex = compFunction(StringHashCode((String)key));
  	}
    if (hashTable[arrayIndex].length() == 0) {
    	return null;
    } else {
      DListNode temp = hashTable[arrayIndex].front();
      for (int i = 0; i < hashTable[arrayIndex].length(); i++) {
        if (((Entry)(temp.item())).key().equals(key)) {
          return (Entry)(temp.item());
        }
        try {
          temp = temp.next();
        } catch (InvalidNodeException e) {
          break;
        }
      } 
    }
    return null;
  }
  
  /**
   * findAll() finds all the entries in hash table which match the given key.
   * If such entries are found, it returns a DList containg these entries.
   * Otherwise it returns null.
   * @param key the search key
   * @return a DList containing all values which match the search key
   */
  
  public DList findAll(Object key) {
  	int arrayIndex = 0;
  	if (key instanceof String) {
  		arrayIndex = compFunction(StringHashCode((String)key));
  	} else {
  		arrayIndex = compFunction(key.hashCode());
  	}
    if (hashTable[arrayIndex].length() == 0) {
    	return null;
    } else {
    	DList entries = new DList();
      DListNode temp = hashTable[arrayIndex].front();
      for (int i = 0; i < hashTable[arrayIndex].length(); i++) {
        if (((Entry)(temp.item())).key().equals(key)) {
        	entries.insertBack((Entry)(temp.item()));
        }
        try {
          temp = temp.next();
        } catch (InvalidNodeException e) {
          break;
        }
      } 
      return entries;
    }
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    int arrayIndex = compFunction(key.hashCode());
    try {
    	DList removeList = hashTable[arrayIndex];
    	DListNode temp = removeList.front();
    	Entry returnEntry = (Entry) temp.item();
    	while (temp.isValidNode()) { 
    	  if (((Entry)temp.item()).value().equals(key)) {
      	  temp.remove();
      	  numberOfEntries--;
    	  }
    	  temp = temp.next();
    	}
    	return returnEntry;
    } catch (InvalidNodeException e) {
      return null;
    }
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    for (int i = 0; i < hashTableSize; i++) {
      hashTable[i] = new DList();
    }
    numberOfEntries = 0;
  }

  /**
   *  Returns the number of collisions
   */

  public int countCollisions() {
    int collisionCount = 0;
    for (int i = 0; i <  hashTableSize; i++) {
      if (hashTable[i].length() > 1) {
        collisionCount += hashTable[i].length() - 1;
      }
    }
    return collisionCount;
  }

  /**
   *  Returns the size of the hashTable.
   */

  public int numberOfBuckets() {
    return hashTableSize;
  }

  /**
   *  Prints out a histogram depicting the number of elements in each bucket.
   */

  public void printTable() {
    for (int i = 0; i < hashTableSize; i++) {
      System.out.println("Bucket " + i + ":");
      for (int j = 0; j < hashTable[i].length(); j++) {
        System.out.print("*");
      }
      System.out.println();
    }
  }

  /**
   *  Retun the greatest prime number just smaller than the input n.
   *  @param n the number for which the closest prime is to be found.
   */ 

  private int primeSieve(int n) {
    boolean[] primeArray = new boolean[n+1];
    int nearestPrime = 2;
    for (int i = 2; i <= n; i++) {
      primeArray[i] = true;
    } 
    for (int i = 2; i*i <= n; i++) {
      if (primeArray[i]) {
        for (int j = 2*i; j <= n; j += i) {
          primeArray[j] = false;
        }
      }
    }
    for (int i = n; i >= 1; i--) {
      if (primeArray[i]) {
        nearestPrime = i;
        break;
      }
    }
    return nearestPrime;
  }
}


