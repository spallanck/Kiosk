
// Interact.java
import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

public class Interact implements ActionListener {

   private static Interact singleton = null;
   private Interact() { /* Private constructor for singleton */ 
      control = Controller.getInstance();
   }
   public static Interact getInstance() {
      if (singleton == null) {
         singleton = new Interact();
      }
      return singleton;
   }
   
   private Controller control;

   public void actionPerformed(ActionEvent e) {
   
      String buttonName = e.getActionCommand();
      if (buttonName.equals("Slideshow")) {
         control.changeMode(Controller.Mode.Slide);
      }
      if (buttonName.equals("LeaveSS")) {
         control.changeMode(Controller.Mode.Menu);
      }
      if (buttonName.equals("RightSS")) {
         control.moveSlide("Right");
      }
      if (buttonName.equals("LeftSS")) {
         control.moveSlide("Left");
      }
      if (buttonName.equals("Map")) {
         control.defaultMode(Controller.Mode.Map);
      }
      if (buttonName.equals("About")) {
         control.changeMode(Controller.Mode.About);
      }
      if (buttonName.equals("Back to Menu")) {
         control.changeMode(Controller.Mode.Menu);
      }
      if (buttonName.equals("X")) {
         control.defaultMode(Controller.Mode.Map);
      }
      if (buttonName.equals("About The Department")) { // NAVCARD?
         control.changeMode(Controller.Mode.Depart);
      }
      if (buttonName.equals("About The Kiosk")) { // NAVCARD?
         control.changeMode(Controller.Mode.About);
      }
      if (buttonName.equals("Events")) {
         control.changeMode(Controller.Mode.Event);
      }
      if(buttonName.equals("View Clubs")) {
         control.changeMode(Controller.Mode.Club);
      }
      if(buttonName.equals("View Events")) {
         control.changeMode(Controller.Mode.Event);
      }
      if(buttonName.equals("Up")) {
         control.sideBar("up");
      }
      if(buttonName.equals("Down")) {
         control.sideBar("down");
      }
   
   }
}
