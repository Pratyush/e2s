/*  StartWindows.java  */

package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import map.MultiMap;
import word.Word;
import dbutil.MapEnToSd;
import dbutil.MapSdToEn;

public class StartWindow {

  private JFrame frmEnglishsindarin;
  private JTextField inputTextField;
  private JTextPane translations;
  JScrollPane scrlPaneTranslations;
  String input;
  MapEnToSd sindarinData;
  MapSdToEn sindarinToEnglish;
  MultiMap sindarinDict;

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
    sindarinDict = new MapEnToSd("/assets/dict-en-sd.xml").getMap();
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
      ArrayList<Word> listOfEntries = sindarinDict.get(input.toLowerCase());
      if (listOfEntries != null) {

        for (Word word : listOfEntries) {

          translations.setText(translations.getText() + "Sindarin translation: " + word.translation() + "\n");
          translations.setText(translations.getText() + "Part of Speech: " + word.partOfSpeech() + "\n");
          if (!word.tense().equals("-")) {
            translations.setText(translations.getText() + "Tense: " + word.tense() + "\n");  
          }
          if (!word.usage().equals("-")) {
            translations.setText(translations.getText() + "Usage: " + word.usage() + "\n");
          }
          translations.setText(translations.getText() + "\n");
        }
      } else {
        translations.setText("Word not found in dictionary. Sorry!");
      }
    }
  }
}
