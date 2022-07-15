// Map.java

import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class KioskMap {

   public static final String MapImg = Kiosk.ImgPath + "high-res-cf.png";
   public static final String highlightImg = Kiosk.ImgPath + "circle_marker.png";
   public static final String UpArrow = Kiosk.buttonImgPath + "up_arrow.png";
   public static final String DownArrow = Kiosk.buttonImgPath + "down_arrow.png";
   
   public PersonCard perCard;
   public NavCard nav[];
   public Interact intr;
   public static Config con;
   
   static JPanel mainPage;
   static JPanel subPage;
   static JPanel cards[];

   public ArrayList<PersonData> currentPersonData;
   public ArrayList<RoomData> mapRooms;
   
   private int cardPageTotal;
   private int curPage;
   private boolean cardPageRemain;

   public KioskMap() {
      perCard = new PersonCard();
      nav = new NavCard[6];
      cards = new JPanel[6];
      for (int i = 0; i < 6; i++) {
         nav[i] = new NavCard();
      }     
      con = Config.getInstance();
      intr = Interact.getInstance();
      currentPersonData = Database.getPersonData();
      mapRooms = Database.mData.getRooms();
      
      cardPageTotal = currentPersonData.size() / 6;
      if (currentPersonData.size() % 6 != 0) {
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
      mainPage.setBackground(Color.white);
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
   
      Display.attachTitle(title, "CF: Floor 4", 25);
      Display.attachTitle(subTitle, "Department Personnel", 100);
      Display.attachBackButton(mainNav, "Back to Menu", intr);
      
      resetView();
      updateSide("up");
      
      Display.attachScaledImg(subNav, 5, (subNav.getHeight() / 2) - 150, 140, 140, UpArrow);
      Display.attachScaledImg(subNav, 5, (subNav.getHeight() / 2) + 10, 140, 140, DownArrow);
      Display.attachInvisButton(subNav, 5, (subNav.getHeight() / 2) - 150, 140, 140, "Up");
      Display.attachInvisButton(subNav, 5, (subNav.getHeight() / 2) + 10, 140, 140, "Down");
      
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
   
   public void highlight(int id) {
      mainPage.removeAll();
      mainPage.setBackground(Color.WHITE);

      RoomData curRoom = new RoomData();
      RoomData finRoom = new RoomData();
      PersonData person = Database.getPerson(id);
      String roomNum = person.getRoom();
      for(int i = 0; i < mapRooms.size(); i++){
         curRoom = (RoomData)mapRooms.get(i);
         if(curRoom.getNum().equals(roomNum)){
            finRoom = curRoom;
            break;
         }
      }
      
      ImageIcon arrowImg = new ImageIcon(highlightImg);
      JLabel arrow = new JLabel(arrowImg);
      arrow.setIcon(arrowImg);
      arrow.setBounds((finRoom.getX()+(finRoom.getW()/2)-50), (finRoom.getY()+(finRoom.getH()/2)-50), arrowImg.getIconWidth(), arrowImg.getIconHeight()); 

      mainPage.add(arrow);
      Display.attachScaledImg(mainPage, 0, 200, 2730, 1400, MapImg);
      mainPage.revalidate();
      mainPage.repaint();
   }
   
   public void showPerson(int id) {
      PersonData p = Database.getPerson(id);
      mainPage = perCard.makeCard(p, mainPage);
   }
   
   public void resetView() { 
      mainPage.removeAll();
      mainPage.setBackground(Color.WHITE);
      Display.attachScaledImg(mainPage, 0, 200, 2730, 1400, MapImg);
      mainPage.revalidate();
      mainPage.repaint();
   }
   
   public void updateSide(String direction){
      if(direction.equals("up")){
         if (curPage == 0) {
            return;   
         } else {
            curPage--;
         }
      } else { // "Down"
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
         for (int i = index; i < currentPersonData.size(); i++) {
            cards[offset] = nav[offset].createCard(currentPersonData.get(i), cards[offset]);
            cards[offset].setBounds(0, 300 * offset, 960, 300);
            subPage.add(cards[offset]);
            offset++;
         }
      } else { // Full page of enties
         for(int i = index; offset < 6; i++) {
            cards[offset] = nav[offset].createCard(currentPersonData.get(i), cards[offset]);
            cards[offset].setBounds(0, 300 * offset, 960, 300);
            subPage.add(cards[offset]);
            offset++;
         }
      }
      
      subPage.revalidate();
      subPage.repaint();
   }
   
}
