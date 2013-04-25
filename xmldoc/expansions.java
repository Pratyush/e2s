package xmldoc;
import dict.HashTableChained;
public class expansions {
  HashTableChained exp;
  
  public expansions() {
    exp = new HashTableChained(50);
    exp.insert("abst.", "abstract");
    exp.insert("adj.", "adjextive");
    exp.insert("adj. num.", "number/numerical adjective");
    exp.insert("adv.", "adverb");
    exp.insert("Arch.", "archaism");
    exp.insert("art.", "article");
    exp.insert("Astron.", "Astronomy");
    exp.insert("augm.", "augmentative");
    exp.insert("aux.", "auxiliary");
    exp.insert("Biol.", "Biology");
    exp.insert("Bot.", "Botany");
    exp.insert("by ext.", "by extension");
    exp.insert("Cal.", "Calendar");
    exp.insert("card", "cardinal (number)");
    exp.insert("class pl.", "class plural");
    exp.insert("coll.", "collective plural");
    exp.insert("conj.", "conjunction");
    exp.insert("dem", "demonstrative");
    exp.insert("der", "derivative");
    exp.insert("dim.", "diminutive");
    exp.insert("dual pl.", "dual");
    exp.insert("etym.", "etymology");
    exp.insert("f.", "feminine");;
    exp.insert("Geog.", "Geography");
    exp.insert("Geol.", "Geology");
    exp.insert("ger.", "gerund");
    exp.insert("hypo.", "affectionate diminutive");
    exp.insert("imp.", "imperative");
    exp.insert("inf.", "infinitive");
    exp.insert("interj.", "interjection");
    exp.insert("invar.", "invariable");
    exp.insert("irreg. pa. t.", "irregular past tense");
    exp.insert("Ling.", "Linguistics");
    exp.insert("lit.", "literally");
    exp.insert("m.", "masculine");
    exp.insert("Mil.", "Military domain");
    exp.insert("Orn.", "Ornithology");
    exp.insert("Phil.", "Philosophy");
    exp.insert("Pop.", "Population");
    exp.insert("Poet.", "literary or poetic language");
    exp.insert("Theo.", "Theology");
    exp.insert("Zool.", "Zoology");
  }
}
