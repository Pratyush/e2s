/**
 * 
 */
package src.map;

import java.util.ArrayList;
import java.util.HashMap;

import src.word.Word;

/**
 * @author ar-curunir
 *
 */
public class MultiMap extends HashMap<String, ArrayList<Word>> {
  // Serial Version ID to enable easier serizalization
  private static final long serialVersionUID = 1L;
  
  
  /**
   * Parameterized constructor which constructs a new HashMap of the required
   * size.
   * @param size the size of the HashMap to be constructed.
   */
  
  public MultiMap(int size) {
    super(size);
  }
  
  /**
   * An overridden put function to insert new items into the HashMap.
   * @param key
   * @param word
   */
  public void put(String key, Word word) {
    ArrayList<Word> current = get(key);
    if (current == null) {
        current = new ArrayList<Word>();
        super.put(key, current);
    }
    current.add(word);
  }

}
