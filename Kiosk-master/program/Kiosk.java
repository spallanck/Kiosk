// Kiosk.java

import java.awt.*;

public class Kiosk {

   private static Controller control;
   
   // Western approved colors
   public static final Color DarkBlue = new Color(0,63,135);
   public static final Color LightBlue = new Color(0,122,200);
   public static final Color PaleBlue = new Color(193,231,255);
   
   // File paths for images
   public static final String buttonImgPath = "images/buttons/";
   public static final String slideImgPath = "images/slide/";
   public static final String eventImgPath = "images/event/";
   public static final String personImgPath = "images/person/";
   public static final String ImgPath = "images/";

   // Main()
	public static void main(String args[]) {
   
		control = Controller.getInstance();
      control.loadData();
      control.screenSetup();
      control.loadModules();
      control.addModules();
      control.go();
      
	}

}
