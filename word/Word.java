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
	String tense;
	
	/**
	 * Default constructor for Word.
	 * Initializes each field with empty strings.
	 */
  public Word() {
  	word = "";
  	translation = "";
  	pos = "";
  	tense = "";
  }
  
  /**
   * Parameterized constructor for a Word object which initializes each object with 
   * the input values.
   * @param word the English word for which the translation is to be made.
   * @param translation the Sindarin equivalent of word.
   * @param pos the part of speech that translation belongs to.
   */
  
  public Word(String word, String translation, String pos, String tns) {
  	this.word = word;
  	this.translation = translation;
  	this.pos = pos;
  	this.tense = tns;
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
  
  public String tense() {
    return tense;
  }
}
