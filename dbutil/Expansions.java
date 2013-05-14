package dbutil;
import java.util.HashMap;
public class Expansions {
  HashMap<String, String> exp;
  
  public Expansions() {
    exp = new HashMap<String, String>(50);
    exp.put("abst.", "abstract");
    exp.put("adj.", "adjextive");
    exp.put("adj. num.", "number/numerical adjective");
    exp.put("adv.", "adverb");
    exp.put("Arch.", "archaism");
    exp.put("art.", "article");
    exp.put("Astron.", "Astronomy");
    exp.put("augm.", "augmentative");
    exp.put("aux.", "auxiliary");
    exp.put("Biol.", "Biology");
    exp.put("Bot.", "Botany");
    exp.put("by ext.", "by extension");
    exp.put("Cal.", "Calendar");
    exp.put("card", "cardinal (number)");
    exp.put("class pl.", "class plural");
    exp.put("coll.", "collective plural");
    exp.put("conj.", "conjunction");
    exp.put("dem", "demonstrative");
    exp.put("der", "derivative");
    exp.put("dim.", "diminutive");
    exp.put("dual pl.", "dual");
    exp.put("etym.", "etymology");
    exp.put("f.", "feminine");;
    exp.put("Geog.", "Geography");
    exp.put("Geol.", "Geology");
    exp.put("ger.", "gerund");
    exp.put("hypo.", "affectionate diminutive");
    exp.put("imp.", "imperative");
    exp.put("inf.", "infinitive");
    exp.put("interj.", "interjection");
    exp.put("invar.", "invariable");
    exp.put("irreg. pa. t.", "irregular past tense");
    exp.put("Ling.", "Linguistics");
    exp.put("lit.", "literally");
    exp.put("m.", "masculine");
    exp.put("Mil.", "Military domain");
    exp.put("Orn.", "Ornithology");
    exp.put("Phil.", "Philosophy");
    exp.put("Pop.", "Population");
    exp.put("Poet.", "literary or poetic language");
    exp.put("Theo.", "Theology");
    exp.put("Zool.", "Zoology");
  }
}
