package xmldoc;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import dict.HashTableChained;

public class DictSDEN {
  
  Document dictData;
  NodeList items;
  HashTableChained dictionary;
  
  public DictSDEN(String filename) {
    DocumentBuilderFactory dbf  = DocumentBuilderFactory.newInstance();
    InputStream xmlFileStream = getClass().getResourceAsStream(filename);
    try {
      
      DocumentBuilder db = dbf.newDocumentBuilder();
      dictData = db.parse(xmlFileStream);
      dictData.getDocumentElement().normalize();
      items = dictData.getElementsByTagName("entry");
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public HashTableChained toHashTableChained() {
    dictionary = new HashTableChained(2690);
    try {
      Element node = (Element) items.item(0);
      for (int i = 0; i < items.getLength();) {
        String usg = "-";
        String word = node.getElementsByTagName("orth").item(0).getTextContent();
        if (node.getElementsByTagName("sense").item(0) != null) {
          Element sense = (Element) node.getElementsByTagName("sense").item(0);
          if (sense.getElementsByTagName("usg").item(0) != null) {
            usg = sense.getElementsByTagName("usg").item(0).getTextContent()
                                                           .trim()
                                                           .replace("\t\t\t\t\t\t\t\t", " ")
                                                           .replace("\n", "")
                                                           .replace("  ", " ");
          }
        }
        if (usg.equals(" ") || usg.equals("\n")) {
          usg = "-";
        }
        expansions abbrevEx = new expansions();
        if (abbrevEx.exp.find(usg) != null) {
          usg = (String) abbrevEx.exp.find(usg).value();
        }
        dictionary.insert(word, usg);
        node = (Element) items.item(++i);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dictionary;
  }
}
