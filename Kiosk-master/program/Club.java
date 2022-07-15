// Club.java
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Club {

   public static final String ClubOff = Kiosk.buttonImgPath + "view_clubs_off.png";
   public static final String EventOn = Kiosk.buttonImgPath + "view_events.png";
   public static final String UpArrow = Kiosk.buttonImgPath + "up_arrow.png";
   public static final String DownArrow = Kiosk.buttonImgPath + "down_arrow.png";

   public NavCard nav[];
   public Interact intr;
   public static Config con;
   static Config.Text conData;
   static Config.ClubEnum cEnum;
   
   static JPanel mainPage;
   static JPanel subPage;
   static JPanel cards[];
   
   public ArrayList<EventData> cData;
   
   private int cardPageTotal;
   private int curPage;
   private boolean cardPageRemain;

   public Club() {
      nav = new NavCard[6];;
      cards = new JPanel[6];
      for (int i = 0; i < 6; i++) {
         nav[i] = new NavCard();
      }
      con = Config.getInstance();
      intr = Interact.getInstance();
      cData = Database.getClubData();
      
      cardPageTotal = cData.size() / 6;
      if (cData.size() % 6 != 0) {
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
      
      Display.attachTitle(title, "Weekly Clubs", 25);
      Display.attachTitle(subTitle, "Club List", 100);
      Display.attachBackButton(mainNav, "Back to Menu", intr);
      
      makeClubCard(cData.get(0));
      updateSide("up");
      
      Display.attachScaledImg(subNav, 5, (subNav.getHeight() / 2) - 150, 140, 140, UpArrow);
      Display.attachScaledImg(subNav, 5, (subNav.getHeight() / 2) + 10, 140, 140, DownArrow);
      Display.attachInvisButton(subNav, 5, (subNav.getHeight() / 2) - 150, 140, 140, "Up");
      Display.attachInvisButton(subNav, 5, (subNav.getHeight() / 2) + 10, 140, 140, "Down");
        
      Display.attachScaledImg(subSearch, 0, 0, 480, 200, EventOn);
      Display.attachInvisButton(subSearch, 0, 0, 480, 200, "View Events");
      Display.attachScaledImg(subSearch, 480, 0, 480, 200, ClubOff);
            
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
         for (int i = index; i < cData.size(); i++) {
            cards[offset] = nav[offset].createCard(cData.get(i), cards[offset]);
            cards[offset].setBounds(0, 300 * offset, 960, 300);
            subPage.add(cards[offset]);
            offset++;
         }
      } else { // Full page of enties
         for(int i = index; offset < 6; i++) {
            cards[offset] = nav[offset].createCard(cData.get(i), cards[offset]);
            cards[offset].setBounds(0, 300 * offset, 960, 300);
            subPage.add(cards[offset]);
            offset++;
         }
      }
      
      subPage.revalidate();
      subPage.repaint();
   }

   public void showClub(int id) {
      EventData c = Database.getClub(id);
      makeClubCard(c);
   }
   
   public void makeClubCard(EventData cData) {
      mainPage.removeAll();
      
      int w = con.getDisplay(Config.DisplayEnum.mainPage).w;
      int h = con.getDisplay(Config.DisplayEnum.mainPage).h;
   
      conData = con.getClub(cEnum.title);
      JLabel eventName = new JLabel(cData.getTitle(), SwingConstants.CENTER);
      eventName.setFont(new Font("Dialog", Font.BOLD, conData.fs));
      eventName.setForeground(Color.BLACK);
      eventName.setVerticalAlignment(SwingConstants.TOP);
      eventName.setBounds(conData.x, conData.y, w, h);
      mainPage.add(eventName);
      
      conData = con.getClub(cEnum.aboutLabel);
      JLabel eventTitle = new JLabel("ABOUT", SwingConstants.CENTER);
      eventTitle.setFont(new Font("Dialog", Font.BOLD, conData.fs));
      eventTitle.setForeground(Color.BLACK);
      eventTitle.setVerticalAlignment(SwingConstants.TOP);
      eventTitle.setBounds(conData.x, conData.y, w, h);
      mainPage.add(eventTitle);
      
      conData = con.getClub(cEnum.desc);
      JLabel eventDes = new JLabel("<html>" + cData.getDescription(), SwingConstants.CENTER);
      eventDes.setFont(new Font("Dialog", Font.PLAIN, conData.fs));
      eventDes.setForeground(Color.BLACK);
      eventDes.setVerticalAlignment(SwingConstants.TOP);
      eventDes.setBounds(conData.x, conData.y, w, h);
      mainPage.add(eventDes);
   
      if (cData.getDate() != null) {
         conData = con.getClub(cEnum.whenLabel);
         JLabel when = new JLabel("WHEN", SwingConstants.CENTER);
         when.setFont(new Font("Dialog", Font.BOLD, conData.fs));
         when.setForeground(Color.BLACK);
         when.setVerticalAlignment(SwingConstants.TOP);
         when.setBounds(conData.x, conData.y, w, h);
         mainPage.add(when);
         
         conData = con.getClub(cEnum.time);
         JLabel eventTime = new JLabel(cData.getDate(), SwingConstants.CENTER);
         eventTime.setFont(new Font("Dialog", Font.PLAIN, conData.fs));
         eventTime.setForeground(Color.BLACK);
         eventTime.setVerticalAlignment(SwingConstants.TOP);
         eventTime.setBounds(conData.x, conData.y, w, h);
         mainPage.add(eventTime);    
      }

      mainPage.revalidate();
      mainPage.repaint();
      }

}