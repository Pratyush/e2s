package src.dbutil;

import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class MapSdToEn {
  
  Document dictData;
  NodeList items;
  
  public MapSdToEn(String filename) {
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
  
  public HashMap<String, String> getMap() {
    HashMap<String, String> map = new HashMap<String, String>(2690);
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
        Expansions abbrevEx = new Expansions();
        if (abbrevEx.exp.get(usg) != null) {
          usg = abbrevEx.exp.get(usg);
        }
        map.put(word, usg);
        node = (Element) items.item(++i);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    dictData = null;
    items = null;
    return map;
  }
}
