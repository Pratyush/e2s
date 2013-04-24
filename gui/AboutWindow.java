package gui;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class AboutWindow {

  private JFrame frmAbout;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          AboutWindow window = new AboutWindow();
          window.frmAbout.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public AboutWindow() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmAbout = new JFrame();
    frmAbout.setTitle("About");
    frmAbout.setBounds(100, 100, 450, 300);
    frmAbout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frmAbout.getContentPane().setLayout(null);
    
    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 438, 288);
    frmAbout.getContentPane().add(panel);
    panel.setLayout(null);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(91, 32, 272, 177);
    panel.add(scrollPane);
    
    JTextPane txtpnMan = new JTextPane();
    txtpnMan.addHyperlinkListener(new HyperlinkListener() {
      public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
          URL url = e.getURL();
          try {
            Desktop.getDesktop().browse(url.toURI());
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
    });
    scrollPane.setViewportView(txtpnMan);
    txtpnMan.setEditable(false);
    txtpnMan.setContentType("text/html");
    
    try {
      Font droidSans = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/assets/DroidSans.ttf")).deriveFont(Font.BOLD, 15f);
      
      txtpnMan.setText("<html><body style=\"font-family: "+ droidSans.getFamily() + "\"<br>" +
      		             "Created By Pratyush Mishra (C) 2013.<br>" +
      		             "<a href =mailto:english2sindarin@gmail.com>english2sindarin@gmail.com</a>" +
      		             "<br><br>e2s uses the Hiswelókë English to Sindarin dictionary available" +
      		             " <a href=http://www.jrrvf.com/hisweloke/sindar/online/english.html>here</a>.</html>");
    } catch (Exception e) {
      e.printStackTrace();
    }
    JButton btnOk = new JButton("OK");
    btnOk.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frmAbout.setVisible(false);
      }
    });
    btnOk.setBounds(325, 223, 62, 25);
    panel.add(btnOk);
    
    try {
      Font droidSans = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/assets/DroidSans.ttf")).deriveFont(14f);
      panel.setFont(droidSans);
      btnOk.setFont(droidSans);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
