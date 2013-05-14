/*  XMLe2s.java  */

package dbutil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import map.MultiMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import word.Word;


/**
 * XMLDocument is a class that takes in the Hisweloke Dictionary in XML
 * format and parses it using the DOM API.
 * 
 * @author ar-curunir
 */	

public class MapEnToSd {

	Document dictData;
  NodeList items;
	/**
	 * A one-parameter constructor that opens and parses the file whose name 
	 * is passed in using the DOM API.
	 * @param fileName the name of the file that is to be parsed.
	 */
	public MapEnToSd(String fileName) {
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
	
	public MultiMap getMap() {
		MultiMap dictionary = new MultiMap(2633);
		String prevWord = null, word = "";
		ArrayList<Word> wordList  = new ArrayList<Word>();
		HashMap<String, String> usageDict = new MapSdToEn("/assets/dict-sd-en.xml").getMap();
		try {
			Element node = (Element) items.item(0);
			for (int i = 0; i < items.getLength();) {
				
			  word = node.getElementsByTagName("gloss").item(0).getTextContent().trim().replace("\t\t\t\t", "");
			  String pos = "";
				String translation = node.getElementsByTagName("orth").item(0).getTextContent().replaceAll("\"", "");
				String tense = "-";
				
				if (node.getElementsByTagName("pos").item(0) != null) {
					pos = node.getElementsByTagName("pos").item(0).getTextContent();
					tense = "-";
					try {
					  tense = node.getElementsByTagName("tns").item(0).getTextContent();
					} catch (NullPointerException e) {
			
					}
				} else {
				  pos = "v.";
					tense = node.getElementsByTagName("tns").item(0).getTextContent();
				}
				
				String usage = usageDict.get(translation);
				
				if (usage != null) {
          if (usage.equals("-") || usage.equals("")) {
            usage = "-";
          }
        } else {
          usage = "-";
        }
				Word nodeWord = new Word(word, translation, pos, tense, usage);
				if (prevWord == null || word.equals(prevWord)) {
				  wordList.add(nodeWord);
        } else {
          dictionary.put(prevWord, wordList);
          wordList = new ArrayList<Word>();
          wordList.add(nodeWord);
        }
				
				prevWord = word;
				node = (Element) items.item(++i);
			}
			dictionary.put(word, wordList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dictionary;
	}
	
	//big huge thesaurus API key: 8e5dcfc675837a2a83fb6725f68b11fd
}
