/*  XMLParser.java  */

package xmldoc;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import word.Word;
import dict.HashTableChained;


/**
 * XMLDocument is a class that takes in the Hisweloke Dictionary in XML
 * format and parses it using the DOM API.
 * 
 * @author ar-curunir
 */	

public class XMLDocument {

	Document dictData;
  NodeList items;
	/**
	 * A one-parameter constructor that opens and parses the file whose name 
	 * is passed in using the DOM API.
	 * @param fileName the name of the file that is to be parsed.
	 */
	public XMLDocument(String fileName) {
		DocumentBuilderFactory dbf  = DocumentBuilderFactory.newInstance();
		InputStream xmlFileStream = getClass().getResourceAsStream(fileName);
		try {
			
		  DocumentBuilder db = dbf.newDocumentBuilder();
			dictData = db.parse(xmlFileStream);
      dictData.getDocumentElement().normalize();
      items = dictData.getElementsByTagName("item");
      
 		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * toHashTableChained() converts this XML Document into a HashTableChained object.
	 * @return a HashTableChained containing the entries in the XML Document.
	 */
	
	public HashTableChained toHashTableChained() {
		HashTableChained dictionary = new HashTableChained(2633);
		try {
			Element node = (Element) items.item(0);
			for (int i = 0; i < items.getLength();) {
				String pos = "";
				String word = node.getElementsByTagName("gloss").item(0).getTextContent();
				String translation = node.getElementsByTagName("orth").item(0).getTextContent().replaceAll("\"", "");
				if (node.getElementsByTagName("pos").item(0) != null) {
					pos = node.getElementsByTagName("pos").item(0).getTextContent();
				} else {
					pos = node.getElementsByTagName("tns").item(0).getTextContent();
				}
				Word nodeWord = new Word(word, translation, pos);
				dictionary.insert(word, nodeWord);
				
				node = (Element) items.item(++i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dictionary;
	}
	
	//big huge thesaurus API key: 8e5dcfc675837a2a83fb6725f68b11fd
}
