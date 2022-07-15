// NavCard.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class NavCard {
   
   private static final String cardImgPerson = Kiosk.buttonImgPath + "card.png";
   private static final String cardImgEvent = Kiosk.buttonImgPath + "blank_card.png";
   private static final String defaultPersonImg = Kiosk.ImgPath + "default_profile.png";
   
   private int cardID;
   private int cardW, cardH, picW, picH;
   // Other config values 
   private ActionListener cardListen;
   
   
   // Initialize the navCard from config properties and card type
   public NavCard() {
      
      // Make dynamic from config
      cardW = 960;
      cardH = 300;
      picW = 200;
      picH = 200;
      
      // Listener for buttons
      cardListen = 
         new ActionListener() {
            private Controller control = Controller.getInstance();
            public void actionPerformed(ActionEvent ae) {
               String buttonName = ae.getActionCommand();
               if (buttonName.equals("Map")) {
                  control.highlightMap(cardID);
               } else if (buttonName.equals("Info")) {
                  control.switchToPerson(cardID);
               } else if (buttonName.equals("Event")) {
                  control.switchToEvent(cardID);
               } else if (buttonName.equals("Testing")) {
                  System.out.println("Test: " + cardID);
               }
            }
         };
   }
   
   public JPanel createCard(EventData eData) { return new JPanel();}
   // Creates a card for use in the navagation side panel for events
   public JPanel createCard(EventData eData, JPanel newCard) {
      this.cardID = eData.getID();
      if (newCard == null) {
         newCard = new JPanel();
         newCard.setSize(cardW, cardH);
         newCard.setLayout(null);
         newCard.setVisible(true);
      } else {
         newCard.removeAll();
      } 
     
      // Card Text
      JLabel eventText = new JLabel("text",SwingConstants.CENTER);
      eventText.setBounds(50,50,cardW-100,cardH-100); // Config values here
      eventText.setFont(new Font("Dialog", Font.BOLD, 80));
      eventText.setForeground(Color.WHITE);
      eventText.setText("<html>" + eData.getTitle());
      newCard.add(eventText);
      
       // Background Img
      JLabel backImg = new JLabel();
      backImg.setBounds(0,0,cardW,cardH);
      ImageIcon cardImg = new ImageIcon(cardImgEvent);
      backImg.setIcon(cardImg);
      newCard.add(backImg);
      
      // Navigation Button
      JButton eventButton = Display.invisButton("Event",0,0,cardW,cardH);
      newCard.add(eventButton);
      eventButton.addActionListener(cardListen);
   
      newCard.setVisible(true);
      return newCard;
   }
   
   // Creates a card for use in the navagation side panel for people
   public JPanel createCard(PersonData pData, JPanel newCard) { 
      this.cardID = pData.getID();
      if (newCard == null) {
         newCard = new JPanel();
         newCard.setSize(cardW, cardH);
         newCard.setLayout(null);
         newCard.setVisible(true);
      } else {
         newCard.removeAll();
      }
      
      // Profile Img
      String pic = pData.getProfilePic();
      if (pic == null) {
         pic = defaultPersonImg;
      } else {
         pic = Kiosk.personImgPath + pic;
      }
      JLabel faceLabel = new JLabel();
      faceLabel.setBounds(50,50,200,200); // Config values here
      ImageIcon faceIcon = new ImageIcon(pic);
      Image scaled = faceIcon.getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH); // Config vales here
      faceIcon = new ImageIcon(scaled);
      faceLabel.setIcon(faceIcon);
      newCard.add(faceLabel); 
      
      // Card Text
      JLabel nameText = new JLabel();
      JLabel roomText = new JLabel();
      nameText.setBounds(500,50,600,50); // Config values here
      roomText.setBounds(500,120,600,50); // Config values here
      nameText.setFont(new Font("Dialog", Font.PLAIN, 46));
      roomText.setFont(new Font("Dialog", Font.PLAIN, 46));
      nameText.setForeground(Color.WHITE);
      roomText.setForeground(Color.WHITE);
      nameText.setText(pData.getName());
      roomText.setText(String.valueOf(pData.getRoom()));
      newCard.add(nameText);
      newCard.add(roomText);
      
      // Background Img
      JLabel cardLabel = new JLabel();
      cardLabel.setBounds(0,0,cardW,cardH);
      ImageIcon cardImg = new ImageIcon(cardImgPerson);
      cardLabel.setIcon(cardImg);
      newCard.add(cardLabel);
      
      // Navigation Buttons 
      JButton mapButton = Display.invisButton("Map",321,194,250,50); // Config values here
      JButton infoButton = Display.invisButton("Info",631,194,250,50); // And here
      newCard.add(mapButton);
      newCard.add(infoButton);
      mapButton.addActionListener(cardListen);
      infoButton.addActionListener(cardListen);
      
      newCard.revalidate();
      newCard.repaint();
      return newCard;
   }
   
}