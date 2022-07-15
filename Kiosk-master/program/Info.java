// Info.java
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Info {

   public static final String AboutDept = Kiosk.buttonImgPath + "about_dept.png";
   public static final String AboutKiosk = Kiosk.buttonImgPath + "about_kiosk.png";
   
   public int sWidth;
   public int sHeight;
   private JButton button;
   private JFrame frame;
   public static Config con;
   
   public Info() {
      con = Config.getInstance();
      this.sWidth = con.getWidth();
      this.sHeight = con.getHeight();
   }

   public JPanel displayDepart() {
      ArrayList<JPanel> panels = Display.createLayout();
      
      Interact intr = Interact.getInstance();      
      
      
      JPanel panel = panels.get(0);
      panel.setBackground(Color.white);
      JPanel title = panels.get(1);
      title.setBackground(Kiosk.DarkBlue);
      JPanel subTitle = panels.get(2);
      subTitle.setBackground(Kiosk.DarkBlue);
      JPanel mainPage = panels.get(3);
      mainPage.setBackground(Kiosk.PaleBlue);
      JPanel subNav = panels.get(4);
      subNav.setBackground(Kiosk.LightBlue);
      JPanel subPage = panels.get(5);
      subPage.setBackground(Kiosk.PaleBlue);
      JPanel mainNav = panels.get(6);
      mainNav.setBackground(Kiosk.DarkBlue);
      JPanel subSearch = panels.get(7);
      subSearch.setBackground(Kiosk.DarkBlue);

      Display.attachTitle(title, "About the Department", 25);

      
      
      Display.attachBackButton(mainNav, "Back to Menu", intr);

      String text1 = "\n\n\nComputer Science at Western offers a collaborative, inclusive, and immersive learning experience. We prepare students to excel and innovate in exciting and fast-changing technology careers. Major-level courses are capped at 35 students and are taught by full-time faculty. The majority of our elective courses are project-based, in which students work in teams to complete term-length projects, mimicking the type of creative and team-oriented atmosphere that is now commonplace at many companies where you'll find Western CS alumni. These include Google, Microsoft, Amazon, Facebook, as well as many other national and regional companies.";
      JTextArea textArea = new JTextArea(20, 30);
      textArea.append(text1);
      textArea.setLineWrap(true);
      textArea.setOpaque(false);
      Font font = new Font("Dialog", Font.PLAIN, 80);
      textArea.setFont(font);
      textArea.setEditable(false);
      textArea.setForeground(Color.BLACK);
      textArea.setWrapStyleWord(true);
      mainPage.add(textArea);
      
      subPage.setLayout(null);
      Display.attachBackButton(mainNav, "Back to Menu", intr);
      ImageIcon about_dept = new ImageIcon(AboutKiosk);
      JLabel imageLabel = new JLabel();
      imageLabel.setBounds(0, 0, 960, 300);
      imageLabel.setIcon(about_dept);
      subPage.add(imageLabel);
      button = Display.invisButton("About The Kiosk", 0, 0, 960, 300); // room
      subPage.add(button);
      button.addActionListener(intr);

      panel.add(title);
      panel.add(subTitle);
      panel.add(mainPage);
      panel.add(subNav);
      panel.add(subPage);
      panel.add(mainNav);
      panel.add(subSearch);
      panel.setVisible(true);
      return panel;
   }
   
   public JPanel displayInfo() {
      ArrayList<JPanel> panels = Display.createLayout();
      
      Interact intr = Interact.getInstance();     
      
      
      JPanel panel = panels.get(0);
      panel.setBackground(Color.white);
      JPanel title = panels.get(1);
      title.setBackground(Kiosk.DarkBlue);
      JPanel subTitle = panels.get(2);
      subTitle.setBackground(Kiosk.DarkBlue);
      JPanel mainPage = panels.get(3);
      mainPage.setBackground(Kiosk.PaleBlue);
      JPanel subNav = panels.get(4);
      subNav.setBackground(Kiosk.LightBlue);
      JPanel subPage = panels.get(5);
      subPage.setBackground(Kiosk.PaleBlue);
      JPanel mainNav = panels.get(6);
      mainNav.setBackground(Kiosk.DarkBlue);
      JPanel subSearch = panels.get(7);
      subSearch.setBackground(Kiosk.DarkBlue);
      

      Display.attachTitle(title, "About the Kiosk", 25);
      String text1 = "\n\n\n\nFor Western Washington University Computer Science students and faculty who want to learn more about the Computer Science department and CS-related events, the Kiosk is an interactive touch screen located on the fourth floor of the Communications Facility. Unlike posters, emails, and the Western Washington University Computer Science webpage, our product aims to consolidate the information, news, and events pertaining to the Computer Science department and help students find the faculty members' offices.";
      JTextArea textArea = new JTextArea(20, 30);
      textArea.append(text1);
      textArea.setLineWrap(true);
      textArea.setOpaque(false);
      Font font = new Font("Dialog", Font.PLAIN, 80);
      textArea.setFont(font);
      textArea.setEditable(false);
      textArea.setForeground(Color.BLACK);
      textArea.setWrapStyleWord(true);
      mainPage.add(textArea);

      
      subPage.setLayout(null);
      Display.attachBackButton(mainNav, "Back to Menu", intr);
      ImageIcon about_dept = new ImageIcon(AboutDept);
      JLabel imageLabel = new JLabel();
      imageLabel.setBounds(0, 0, 960, 300);
      imageLabel.setIcon(about_dept);
      subPage.add(imageLabel);
      button = Display.invisButton("About The Department", 0, 0, 960, 300); // room
      subPage.add(button);
      button.addActionListener(intr);
      
      panel.add(title);
      panel.add(subTitle);
      panel.add(mainPage);
      panel.add(subNav);
      panel.add(subPage);
      panel.add(mainNav);
      panel.add(subSearch);
      panel.setVisible(true);
      return panel;

   }

}

