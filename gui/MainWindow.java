/*  e2sUI.java  */
package gui;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import list.DList;
import list.DListNode;
import list.InvalidNodeException;
import word.Word;
import xmldoc.DictSDEN;
import xmldoc.XMLDocument;
import dict.Entry;
import dict.HashTableChained;

/**
 *
 * @author ar-curunir
 */
public class MainWindow extends javax.swing.JFrame {
  
  //Variables declaration - do not modify
  String input;
  XMLDocument sindarinData;
  DictSDEN sindarinToEnglish;
  HashTableChained sindarinDict;
  HashTableChained usageDict;
  
  public javax.swing.JButton enter;
  public javax.swing.JButton exit;
  public javax.swing.JTextField inputTextField;
  private javax.swing.JScrollPane jScrollPane1;
  public javax.swing.JPanel listTranslationPanel;
  public javax.swing.JLabel translateLabel;
  public javax.swing.JPanel translationScreen;
  private javax.swing.JTextArea translations;
  private JButton btnAbout;
  // End of variables declaration
 
  /** Creates new form e2sUI */
  public MainWindow() {
    
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    final int WIDTH = screenSize.width;
    final int HEIGHT = screenSize.height;
    // Setup the frame accordingly
    // This is assuming you are extending the JFrame //class

    this.setLocation(WIDTH/4, HEIGHT/4);
    initComponents();
    sindarinData = new XMLDocument("/assets/dict-en-sd.xml");
    sindarinDict = sindarinData.toHashTableChained();
    sindarinToEnglish = new DictSDEN("/assets/dict-sd-en.xml");
    usageDict = sindarinToEnglish.toHashTableChained(); 
    
    setTitle("e2s");
    try {
      
      Font droidSans = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/assets/DroidSans.ttf")).deriveFont(14f);
      Font albertus = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/assets/Albertus.ttf")).deriveFont( 16f);
      translations.setFont(albertus);
      translateLabel.setFont(droidSans);
      inputTextField.setFont(albertus);
      enter.setFont(droidSans);
      exit.setFont(droidSans);
      btnAbout.setFont(droidSans);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */

  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    translationScreen = new javax.swing.JPanel();
    translateLabel = new javax.swing.JLabel();
    inputTextField = new javax.swing.JTextField();
    enter = new javax.swing.JButton();
    exit = new javax.swing.JButton();
    listTranslationPanel = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    translations = new javax.swing.JTextArea();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    translateLabel.setText("Enter the word you want translated:");

    inputTextField.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        inputTextFieldActionPerformed(evt);
      }
    });

    enter.setText("Enter");
    enter.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        enterActionPerformed(evt);
      }
    });

    exit.setText("Exit");
    exit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        exitActionPerformed(evt);
      }
    });

    translations.setColumns(20);
    translations.setRows(5);
    jScrollPane1.setViewportView(translations);

    javax.swing.GroupLayout listTranslationPanelLayout = new javax.swing.GroupLayout(listTranslationPanel);
    listTranslationPanel.setLayout(listTranslationPanelLayout);
    listTranslationPanelLayout.setHorizontalGroup(
      listTranslationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
    );
    listTranslationPanelLayout.setVerticalGroup(
      listTranslationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
    );
    
    btnAbout = new JButton("About");
    btnAbout.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String[] crap = {""};
        AboutWindow.main(crap);
      }
    });

    javax.swing.GroupLayout translationScreenLayout = new javax.swing.GroupLayout(translationScreen);
    translationScreenLayout.setHorizontalGroup(
      translationScreenLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(translationScreenLayout.createSequentialGroup()
          .addContainerGap()
          .addGroup(translationScreenLayout.createParallelGroup(Alignment.TRAILING)
            .addGroup(translationScreenLayout.createSequentialGroup()
              .addComponent(listTranslationPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(ComponentPlacement.UNRELATED)
              .addGroup(translationScreenLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(exit)
                .addComponent(btnAbout)))
            .addGroup(translationScreenLayout.createSequentialGroup()
              .addGroup(translationScreenLayout.createParallelGroup(Alignment.TRAILING)
                .addComponent(translateLabel)
                .addComponent(inputTextField, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
              .addPreferredGap(ComponentPlacement.RELATED)
              .addComponent(enter)
              .addGap(90)))
          .addContainerGap(87, Short.MAX_VALUE))
    );
    translationScreenLayout.setVerticalGroup(
      translationScreenLayout.createParallelGroup(Alignment.TRAILING)
        .addGroup(translationScreenLayout.createSequentialGroup()
          .addContainerGap()
          .addGroup(translationScreenLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(translationScreenLayout.createSequentialGroup()
              .addComponent(translateLabel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
              .addGap(12)
              .addGroup(translationScreenLayout.createParallelGroup(Alignment.BASELINE)
                .addComponent(inputTextField, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addComponent(enter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
              .addPreferredGap(ComponentPlacement.UNRELATED)
              .addComponent(listTranslationPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
              .addContainerGap())
            .addGroup(Alignment.TRAILING, translationScreenLayout.createSequentialGroup()
              .addComponent(btnAbout)
              .addPreferredGap(ComponentPlacement.RELATED)
              .addComponent(exit)
              .addGap(43))))
    );
    translationScreen.setLayout(translationScreenLayout);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    layout.setHorizontalGroup(
      layout.createParallelGroup(Alignment.LEADING)
        .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
          .addContainerGap()
          .addComponent(translationScreen, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(Alignment.LEADING)
        .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
          .addContainerGap()
          .addComponent(translationScreen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    getContentPane().setLayout(layout);

    pack();
  }

  private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
    System.exit(0);
  }

  private void inputTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputTextFieldActionPerformed
    enterActionPerformed(evt);
  }//GEN-LAST:event_inputTextFieldActionPerformed

  private void enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterActionPerformed
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
          translations.append("Sindarin translation: " + currentWord.translation() + "\n");
          translations.append("Part of Speech: " + currentWord.partOfSpeech() + "\n");
          translations.append("Tense: " + currentWord.tense() + "\n");
          if (!((String) usageDict.find(currentWord.translation()).value()).equals(" ")) {
            translations.append("Usage: " + usageDict.find(currentWord.translation())+ "\n");
          } else {
            translations.append("Usage: " + "-"  +"\n");
          }
          translations.append("\n");
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
  }//GEN-LAST:event_enterActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    try { 
      // Set System L&F 
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
    } catch (Exception e) { 
      e.printStackTrace();
    }
    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {

      public void run() {
        new MainWindow().setVisible(true);
      }
    });
  }
}
