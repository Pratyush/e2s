/*  StartWindows.java  */

package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.Font;
import javax.swing.JScrollPane;

import dict.*;

import word.*;
import xmldoc.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import list.*;
import java.awt.Color;
import javax.swing.UIManager;

public class StartWindow {

  private JFrame frmEnglishsindarin;
  private JTextField inputTextField;
  private JTextPane translations;
  JScrollPane scrlPaneTranslations;
  String input;
  XMLDocument sindarinData;
  DictSDEN sindarinToEnglish;
  HashTableChained sindarinDict;
  HashTableChained usageDict;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          StartWindow window = new StartWindow();
          window.frmEnglishsindarin.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public StartWindow() {
    sindarinData = new XMLDocument("/assets/dict-en-sd.xml");
    sindarinDict = sindarinData.toHashTableChained();
    sindarinToEnglish = new DictSDEN("/assets/dict-sd-en.xml");
    usageDict = sindarinToEnglish.toHashTableChained();
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmEnglishsindarin = new JFrame();
    frmEnglishsindarin.setTitle("english2sindarin");
    frmEnglishsindarin.setBounds(100, 100, 512, 353);
    frmEnglishsindarin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frmEnglishsindarin.getContentPane().setLayout(null);
    
    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 508, 327);
    frmEnglishsindarin.getContentPane().add(panel);
    panel.setLayout(null);
    
    JButton btnExit = new JButton();
    btnExit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    btnExit.setText("Exit");
    btnExit.setBounds(420, 252, 57, 27);
    panel.add(btnExit);
    
    JButton btnAbout = new JButton("About");
    btnAbout.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String[] nothing = {""};
        AboutWindow.main(nothing);
      }
    });
    btnAbout.setBounds(420, 213, 72, 27);
    panel.add(btnAbout);
    
    JLabel translateLabel = new JLabel();
    translateLabel.setForeground(new Color(255, 255, 255));
    translateLabel.setText("Enter the word you want translated:");
    translateLabel.setBackground(UIManager.getColor("OptionPane.warningDialog.border.background"));
    translateLabel.setBounds(135, 9, 305, 27);
    panel.add(translateLabel);
    
    inputTextField = new JTextField();
    inputTextField.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        enterActionPerformed(e);
      }
    });
    inputTextField.setBounds(135, 48, 130, 35);
    panel.add(inputTextField);
    
    JButton btnEnter = new JButton();
    btnEnter.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        enterActionPerformed(e);
      }
    });
    btnEnter.setText("Enter");
    btnEnter.setBounds(318, 53, 67, 27);
    panel.add(btnEnter);
    
    scrlPaneTranslations = new JScrollPane();
    scrlPaneTranslations.setBounds(39, 114, 369, 189);
    panel.add(scrlPaneTranslations);
    
    translations = new JTextPane();
    translations.setEditable(false);
    translations.setBackground(new Color(255, 255, 240));
    scrlPaneTranslations.setViewportView(translations);
    
    JLabel backImg = new JLabel("");
    backImg.setBounds(0, 0, 508, 328);
    panel.add(backImg);
    
    try {
      
      Font droidSans = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/assets/DroidSans.ttf")).deriveFont(14f);
      Font albertus = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/assets/Albertus.ttf")).deriveFont( 16f);
      
      translations.setFont(albertus);
      translateLabel.setFont(albertus);
      inputTextField.setFont(albertus);
      btnEnter.setFont(droidSans);
      btnExit.setFont(droidSans);
      btnAbout.setFont(droidSans);
      
      Image img = ImageIO.read(getClass().getResourceAsStream("/assets/middle-earth-map.jpg"));
      //BufferedImage img = ImageIO.read(backImgStrm);

      backImg.setIcon(new ImageIcon(img));
      
    } catch (Exception e) {
      System.err.println(e);
    }
  }
  
  private void enterActionPerformed(java.awt.event.ActionEvent evt) {
    scrlPaneTranslations.setOpaque(true);
    translations.setOpaque(true);
    input = inputTextField.getText();
    if (input.equalsIgnoreCase("")) {
      translations.setText("Please input a valid word.");
    } else {
      translations.setText("");
      DList listOfEntries = sindarinDict.findAll(input.toLowerCase());
      if (listOfEntries != null && listOfEntries.length() != 0) {
            
        DListNode temp = listOfEntries.front();
        Word currentWord = ((Word) ((Entry) (temp.item())).value());

        for (int i = 0; i < listOfEntries.length(); i++) {

          currentWord = ((Word) ((Entry) (temp.item())).value());
          translations.setText(translations.getText() + "Sindarin translation: " + currentWord.translation() + "\n");
          translations.setText(translations.getText() + "Part of Speech: " + currentWord.partOfSpeech() + "\n");
          if (!currentWord.tense().equals("-")) {
          translations.setText(translations.getText() + "Tense: " + currentWord.tense() + "\n");  
          }
          
          if (usageDict.find(currentWord.translation()) != null) {
          if (!((String) usageDict.find(currentWord.translation()).value()).equals("-") &&
            !((String) usageDict.find(currentWord.translation()).value()).equals("")) {
            translations.setText(translations.getText() + "Usage: " + usageDict.find(currentWord.translation())+ "\n");
          }
          }
          translations.setText(translations.getText() + "\n");
          try {
            temp = temp.next();
          } catch (InvalidNodeException e) {
            e.printStackTrace();
          }
        }
      } else {
        translations.setText("Word not found in dictionary. Sorry!");
      }
    }
  }
}
