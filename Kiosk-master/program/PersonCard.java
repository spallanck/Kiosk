// PersonCard.java

import javax.swing.*;
import java.awt.*;
import java.util.*;

class PersonCard {

   public static Config con;
   static Config.PersonEnum pEnum;
   public final String xButtonImg = Kiosk.buttonImgPath + "x_button.png";
   public final String defaultProfile = Kiosk.ImgPath + "default_profile.png";
   
	public PersonCard() {
      con = Config.getInstance();   
	}

	public JPanel makeCard(PersonData person, JPanel panel) {
      panel.removeAll();
      panel.setBackground(Kiosk.PaleBlue);
      panel.setLayout(null);

      // Profile Pic
      String imglink = person.getMainPic();
      if(imglink == null) {
         imglink = person.getProfilePic();
      }
      if(imglink == null) {
         imglink = defaultProfile;
      } else {
         imglink = Kiosk.personImgPath + imglink;
      }
      
      Display.attachScaledImg(panel, con.getPerson(pEnum.pic), imglink);
      
      // Labels
      Display.attachBoldText(panel, con.getPerson(pEnum.nameLabel), "Name: ");
      Display.attachBoldText(panel, con.getPerson(pEnum.roomLabel), "Room: ");
      Display.attachBoldText(panel, con.getPerson(pEnum.emailLabel), "Email: ");
      Display.attachBoldText(panel, con.getPerson(pEnum.phoneLabel), "Phone: ");
      Display.attachBoldText(panel, con.getPerson(pEnum.classLabel), "Class List: ");
      Display.attachBoldText(panel, con.getPerson(pEnum.researchLabel), "Research: ");
      Display.attachBoldText(panel, con.getPerson(pEnum.bioLabel), "Biography: ");
      
		// Data
      Display.attachNormalText(panel, con.getPerson(pEnum.name), person.getName());
      Display.attachNormalText(panel, con.getPerson(pEnum.room), person.getRoom());
      Display.attachNormalText(panel, con.getPerson(pEnum.email), person.getEmail());
      Display.attachNormalText(panel, con.getPerson(pEnum.phone), person.getPhoneNum());
      Display.attachNormalText(panel, con.getPerson(pEnum.classes), person.getClassList());
      Display.attachNormalText(panel, con.getPerson(pEnum.research), person.getResearchInterests());
      Display.attachNormalText(panel, con.getPerson(pEnum.bio), person.getBiography());
      Display.attachNormalText(panel, con.getPerson(pEnum.name), person.getName());

      // Back button
      Display.attachScaledImg(panel, con.getPerson(pEnum.button), xButtonImg);
      Display.attachInvisButton(panel, con.getPerson(pEnum.button), "X");

      panel.revalidate();
      panel.repaint();
		return panel;
	}

}