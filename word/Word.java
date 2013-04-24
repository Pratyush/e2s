/*  Word.java  */

package word;

/**
 * Word is a ADT that contains a word as well as the following information about it:
 * 
 * 1) Sindarin Translation(s)
 * 2) Part of speech
 * 
 * @author ar-curunir
 *
 */
public class Word {
	
	String word;
	String translation;
	String pos;
	
	/**
	 * Default constructor for Word.
	 * Initializes each field with empty strings.
	 */
  public Word() {
  	word = "";
  	translation = "";
  	pos = "";
  }
  
  /**
   * Parameterized constructor for a Word object which initializes each object with 
   * the input values.
   * @param word the English word for which the translation is to be made.
   * @param translation the Sindarin equivalent of word.
   * @param pos the part of speech that translation belongs to.
   */
  
  public Word(String word, String translation, String pos) {
  	this.word = word;
  	this.translation = translation;
  	this.pos = pos;
  }
  
  /**
   * @return the English word for which the Sindarin equivalent is to be found.
   */
  public String word() {
  	return word;
  }
  
  /**
   * @return the Sindarin translation of the English word stored in this entry.
   */
  public String translation() {
  	return translation;
  }
  
  /**
   * @return the part of speech to which this entry belongs.
   */
  
  public String partOfSpeech() {
  	return pos;
  }
}
