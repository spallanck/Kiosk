// Display.java

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Display {

   private static final String backMenuImg = Kiosk.buttonImgPath + "back_to_menu_button.png"; 


   public static JButton invisButton(String bText, int bX, int bY, int bW, int bH) {
      JButton button = new JButton(bText);
      button.setBounds(bX, bY, bW, bH);
      button.setOpaque(false);
      button.setContentAreaFilled(false);
      button.setBorderPainted(false);
      return button;
   }

   public static void attachTitle(JPanel panel, String s, int sizeDown) {
      Config con = Config.getInstance();
      JLabel label = new JLabel();
      label.setForeground(Color.white);
      label.setFont(new Font("Dialog", Font.BOLD, panel.getHeight() - sizeDown));
      label.setText(s);
      panel.add(label);
   }

   public static void attachBackButton(JPanel panel, String s, Interact intr) {
      panel.setLayout(null);
      
      ImageIcon backImg = new ImageIcon(backMenuImg);
      JLabel backLabel = new JLabel();
      backLabel.setBounds(2230,0,500,200); // Config?
      backLabel.setIcon(backImg);
      panel.add(backLabel);
      
      JButton backBtn = invisButton("Back to Menu", 2230, 0, 500, 200); // Config?
      backBtn.addActionListener(intr);
      panel.add(backBtn);
   }

   static Config.DisplayEnum dEnum;
   static Config.Dim dim;
   public static ArrayList<JPanel> createLayout() {
      ArrayList<JPanel> panels = new ArrayList<>();
      Config con = Config.getInstance();
      
      JPanel p = new JPanel();
      p.setSize(1920, 1080);
      p.setLayout(null);
      panels.add(p);

      dim = con.getDisplay(dEnum.mainTitle);
      JPanel title = new JPanel();
      title.setBounds(dim.x, dim.y, dim.w, dim.h);
      panels.add(title);
   
      dim = con.getDisplay(dEnum.subTitle);
      JPanel subTitle = new JPanel();
      subTitle.setBounds(dim.x, dim.y, dim.w, dim.h);
      panels.add(subTitle);
   
      dim = con.getDisplay(dEnum.mainPage);
      JPanel mainPage = new JPanel();
      mainPage.setBounds(dim.x, dim.y, dim.w, dim.h);
      panels.add(mainPage);
   
      dim = con.getDisplay(dEnum.subNav);
      JPanel subNav = new JPanel();
      subNav.setBounds(dim.x, dim.y, dim.w, dim.h);
      panels.add(subNav);
   
      dim = con.getDisplay(dEnum.subPage);
      JPanel subPage = new JPanel();
      subPage.setBounds(dim.x, dim.y, dim.w, dim.h);
      panels.add(subPage);
   
      dim = con.getDisplay(dEnum.mainNav);
      JPanel mainNav = new JPanel();
      mainNav.setBounds(dim.x, dim.y, dim.w, dim.h);
      panels.add(mainNav);
   
      dim = con.getDisplay(dEnum.subSearch);
      JPanel subSearch = new JPanel();
      subSearch.setBounds(dim.x, dim.y, dim.w, dim.h);
      panels.add(subSearch);
      return panels;
   }
   
   public static void attachScaledImg(JPanel panel, Config.Dim dim, String img) {
      JLabel label = new JLabel();
      label.setBounds(dim.x, dim.y, dim.w, dim.h);
      ImageIcon icon = new ImageIcon(img);
      Image scaled = icon.getImage().getScaledInstance(dim.w, dim.h, Image.SCALE_SMOOTH);
      icon = new ImageIcon(scaled);    
      label.setIcon(icon);
      panel.add(label);
      label = null;
      scaled = null;
      icon = null;
   }
   public static void attachScaledImg(JPanel panel, Config.Text dim, String img) {
      JLabel label = new JLabel();
      label.setBounds(dim.x, dim.y, dim.w, dim.h);
      ImageIcon icon = new ImageIcon(img);
      Image scaled = icon.getImage().getScaledInstance(dim.w, dim.h, Image.SCALE_SMOOTH);
      icon = new ImageIcon(scaled);    
      label.setIcon(icon);
      panel.add(label);
      label = null;
      scaled = null;
      icon = null;
   }
   public static void attachScaledImg(JPanel panel, int x, int y, int w, int h, String img) {
      JLabel label = new JLabel();
      label.setBounds(x, y, w, h);
      ImageIcon icon = new ImageIcon(img);
      Image scaled = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
      icon = new ImageIcon(scaled);    
      label.setIcon(icon);
      panel.add(label);
      label = null;
      scaled = null;
      icon = null;
   }
   
   // Uses Interact listener by default
   public static void attachInvisButton(JPanel panel, Config.Dim dim, String bName) {
      JButton button = new JButton(bName);
      button.setBounds(dim.x, dim.y, dim.w, dim.h);
      button.setOpaque(false);
      button.setForeground(Color.BLACK);
      button.setContentAreaFilled(false);
      button.setBorderPainted(false);
      button.addActionListener(Interact.getInstance());
      panel.add(button);
   }
   public static void attachInvisButton(JPanel panel, Config.Text dim, String bName) {
      JButton button = new JButton(bName);
      button.setBounds(dim.x, dim.y, dim.w, dim.h);
      button.setOpaque(false);
      button.setForeground(Color.BLACK);
      button.setContentAreaFilled(false);
      button.setBorderPainted(false);
      button.addActionListener(Interact.getInstance());
      panel.add(button);
   }
   public static void attachInvisButton(JPanel panel, int x, int y, int w, int h, String bName) {
      JButton button = new JButton(bName);
      button.setBounds(x, y, w, h);
      button.setOpaque(false);
      button.setForeground(Color.BLACK);
      button.setContentAreaFilled(false);
      button.setBorderPainted(false);
      button.addActionListener(Interact.getInstance());
      panel.add(button);
   }
      
   public static void attachNormalText(JPanel panel, Config.Text text, String str) {
      JLabel label = new JLabel(str);
      label.setVerticalAlignment(SwingConstants.TOP);
      label.setFont(new Font("Dialog", Font.PLAIN, text.fs));
      label.setBounds(text.x, text.y, text.w, text.h);
      panel.add(label);
   }
   
   public static void attachBoldText(JPanel panel, Config.Text text, String str) {
      JLabel label = new JLabel(str);
      label.setFont(new Font("Dialog", Font.BOLD, text.fs));
      label.setBounds(text.x, text.y, text.w, text.h);
      panel.add(label);
   }
}
