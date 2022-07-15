// Event.java
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Event {

   public static final String ClubOn = Kiosk.buttonImgPath + "view_clubs.png";
   public static final String EventOff = Kiosk.buttonImgPath + "view_events_off.png";
   public static final String UpArrow = Kiosk.buttonImgPath + "up_arrow.png";
   public static final String DownArrow = Kiosk.buttonImgPath + "down_arrow.png";

   public NavCard nav[];
   public Interact intr;
   public static Config con;
   static Config.Text conData;
   static Config.EventEnum eEnum;
   
   static JPanel mainPage;
   static JPanel subPage;
   static JPanel cards[];

   public static ArrayList<EventData> eData;

   private int cardPageTotal;
   private int curPage;
   private boolean cardPageRemain;

   public Event() {
      nav = new NavCard[6];
      cards = new JPanel[6];
      for (int i = 0; i < 6; i++) {
         nav[i] = new NavCard();
      }
      con = Config.getInstance();
      intr = Interact.getInstance();
      eData = Database.getEventData();
      
      cardPageTotal = eData.size() / 6;
      if (eData.size() % 6 != 0) {
         cardPageTotal++;
         cardPageRemain = true;
      } else {
         cardPageRemain = false;
      }
      curPage = 1;
   }
   
   public JPanel create() {
      ArrayList<JPanel> panels = Display.createLayout();
      JPanel panel = panels.get(0);
      
      JPanel title = panels.get(1);
      title.setBackground(Kiosk.DarkBlue);
      JPanel subTitle = panels.get(2);
      subTitle.setBackground(Kiosk.DarkBlue);
      mainPage = panels.get(3);
      mainPage.setBackground(Kiosk.PaleBlue);
      mainPage.setLayout(null);
      JPanel subNav = panels.get(4);
      subNav.setBackground(Kiosk.LightBlue);
      subNav.setLayout(null);
      subPage = panels.get(5);
      subPage.setBackground(Kiosk.PaleBlue);
      subPage.setLayout(null);
      JPanel mainNav = panels.get(6);
      mainNav.setBackground(Kiosk.DarkBlue);
      JPanel subSearch = panels.get(7);
      subSearch.setBackground(Kiosk.DarkBlue);
      subSearch.setLayout(null);
      
      Display.attachTitle(title, "Upcoming Events", 30);
      Display.attachTitle(subTitle, "Event List", 100);
      Display.attachBackButton(mainNav, "Back to Menu", intr);
      
      makeEventCard(eData.get(0));
      updateSide("up");
      
      Display.attachScaledImg(subNav, 5, (subNav.getHeight() / 2) - 150, 140, 140, UpArrow);
      Display.attachScaledImg(subNav, 5, (subNav.getHeight() / 2) + 10, 140, 140, DownArrow);
      Display.attachInvisButton(subNav, 5, (subNav.getHeight() / 2) - 150, 140, 140, "Up");
      Display.attachInvisButton(subNav, 5, (subNav.getHeight() / 2) + 10, 140, 140, "Down");
      
      Display.attachScaledImg(subSearch, 0, 0, 480, 200, EventOff);
      Display.attachScaledImg(subSearch, 480, 0, 480, 200, ClubOn);
      Display.attachInvisButton(subSearch, 480, 0, 480, 200, "View Clubs");
    
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
   public void updateSide(String direction){
      if(direction.equals("up")){
         if (curPage == 0) {
            return;   
         } else {
            curPage--;
         }
      } else { // "down"
         if (curPage == cardPageTotal-1) {
            return;   
         } else {
            curPage++;
         }  
      }
      subPage.removeAll();
      
      int index = curPage * 6;
      int offset = 0;
      if (curPage == cardPageTotal-1 && cardPageRemain) { // Odd number entries
         for (int i = index; i < eData.size(); i++) {
            cards[offset] = nav[offset].createCard(eData.get(i), cards[offset]);
            cards[offset].setBounds(0, 300 * offset, 960, 300);
            subPage.add(cards[offset]);
            offset++;
         }
      } else { // Full page of enties
         for(int i = index; offset < 6; i++) {
            cards[offset] = nav[offset].createCard(eData.get(i), cards[offset]);
            cards[offset].setBounds(0, 300 * offset, 960, 300);
            subPage.add(cards[offset]);
            offset++;
         }
      }
      
      subPage.revalidate();
      subPage.repaint();
   }
   
   public void showEvent(int id) {
      EventData e = Database.getEvent(id);
      makeEventCard(e);
   }

  
   public void makeEventCard(EventData e) {
      mainPage.removeAll();
      
      String imglink = Kiosk.eventImgPath + e.getImage();
      conData = con.getEvent(eEnum.pic);
      JLabel eventPic = new JLabel();
      ImageIcon imageIcon = new ImageIcon(imglink);
      Image image = imageIcon.getImage();
      Image newimg = image.getScaledInstance(conData.w, conData.h, java.awt.Image.SCALE_SMOOTH);
   	
      imageIcon = new ImageIcon(newimg);
      eventPic.setIcon(imageIcon);
      eventPic.setBounds(conData.x, conData.y, conData.w, conData.h);
      mainPage.add(eventPic);
      
      String title = e.getTitle();
      conData = con.getEvent(eEnum.title);
      JLabel eventName = new JLabel(e.getTitle());
      eventName.setFont(new Font("Dialog", Font.BOLD, conData.fs));
      eventName.setForeground(Color.BLACK);
      eventName.setVerticalAlignment(SwingConstants.TOP);
      eventName.setBounds(conData.x, conData.y, conData.w, conData.h);
      mainPage.add(eventName);
      
      conData = con.getEvent(eEnum.aboutLabel);
      JTextArea textArea = new JTextArea(5, 20);
      textArea.append("ABOUT");
      textArea.setLineWrap(true);
      textArea.setOpaque(false);
      Font font = new Font("Dialog", Font.BOLD, conData.fs);
      textArea.setFont(font);
      textArea.setEditable(false);
      textArea.setForeground(Color.BLACK);
      textArea.setWrapStyleWord(true);
      textArea.setBounds(conData.x, conData.y, conData.w, conData.h);
      mainPage.add(textArea);
   
      conData = con.getEvent(eEnum.desc);
      JTextArea eventDes = new JTextArea(5, 20);
      eventDes.append(e.getDescription());
      eventDes.setLineWrap(true);
      eventDes.setOpaque(false);
      font = new Font("Dialog", Font.PLAIN, conData.fs);
      eventDes.setFont(font);
      eventDes.setEditable(false);
      eventDes.setForeground(Color.BLACK);
      eventDes.setWrapStyleWord(true);
      eventDes.setBounds(conData.x, conData.y, conData.w, conData.h);
      mainPage.add(eventDes);
      
      conData = con.getEvent(eEnum.whenLabel);
      JTextArea when = new JTextArea(5, 20);
      when.append("WHEN");
      when.setLineWrap(true);
      when.setWrapStyleWord(true);
      font = new Font("Dialog", Font.BOLD, conData.fs);
      when.setOpaque(false);
      when.setEditable(false);
      when.setFont(font);
      when.setForeground(Color.BLACK);
      when.setBounds(conData.x, conData.y, conData.w, conData.h);
      mainPage.add(when);
   
      conData = con.getEvent(eEnum.when);
      JTextArea eventTime = new JTextArea(5, 20);
      eventTime.append(e.getDate() + " " + e.getTime());
      eventTime.setLineWrap(true);
      eventTime.setWrapStyleWord(true);
      font = new Font("Dialog", Font.PLAIN, conData.fs);
      eventTime.setOpaque(false);
      eventTime.setEditable(false);
      eventTime.setFont(font);
      eventTime.setForeground(Color.BLACK);
      eventTime.setBounds(conData.x, conData.y, conData.w, conData.h);
      mainPage.add(eventTime);
      
      conData = con.getEvent(eEnum.whereLabel);
      JTextArea where = new JTextArea(5, 20);
      where.append("WHERE");
      where.setLineWrap(true);
      where.setWrapStyleWord(true);
      where.setOpaque(false);
      font = new Font("Dialog", Font.BOLD, conData.fs);
      where.setFont(font);
      where.setEditable(false);
      where.setForeground(Color.BLACK);
      where.setBounds(conData.x, conData.y, conData.w, conData.h);
      mainPage.add(where);
   
      conData = con.getEvent(eEnum.where);
      JTextArea eventLoc = new JTextArea(5, 20);
      eventLoc.append(e.getLocation());
      eventLoc.setLineWrap(true);
      eventLoc.setWrapStyleWord(true);
      eventLoc.setOpaque(false);
      font = new Font("Dialog", Font.PLAIN, conData.fs);
      eventLoc.setFont(font);
      eventLoc.setEditable(false);
      eventLoc.setForeground(Color.BLACK);
      eventLoc.setBounds(conData.x, conData.y, conData.w, conData.h);
      mainPage.add(eventLoc);
   
      mainPage.revalidate();
      mainPage.repaint();
   }

}