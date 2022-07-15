// Slideshow.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class Slideshow {

   public static final String left_Arrow = Kiosk.buttonImgPath + "left_arrow.png";
   public static final String right_Arrow = Kiosk.buttonImgPath + "right_arrow.png";
   public static final String info_On = Kiosk.buttonImgPath + "info_button.png";
   public static final String info_Off = Kiosk.buttonImgPath + "no_info_button.png";
   
   public static JPanel slide;
   
   private Timer timer, idleTimer;
   private int sTime, navTime, idleTime, curSlide, totalSlide;
   private static Config con;
   private ArrayList<Slide> slideList;
   
   public Slideshow() {
      con = Config.getInstance();
      sTime = con.getSlideTime();
      navTime = con.getNavTime();
      idleTime = con.getIdleTime();
      
      // Used for normal slide timer
      ActionListener slideListen = 
         new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
               nextSlide();
            }
         };
         
      // Used for idle timer
      ActionListener idleListen = 
         new ActionListener() {
            public void actionPerformed(ActionEvent ae) { 
               Controller control = Controller.getInstance();
               control.changeMode(Controller.Mode.Slide);
            }
         };
         
      timer = new Timer(sTime, slideListen);
      timer.setInitialDelay(sTime);
      idleTimer = new Timer(idleTime, idleListen);
      idleTimer.setInitialDelay(idleTime);  
   }
   
   public JPanel create() {
      slideList = new ArrayList<>();
      
      ArrayList<EventData> eventList = Database.getEventData();
      for (int i = 0; i < eventList.size(); i++) {
         String eImg = eventList.get(i).getImage();
         if (eImg != null) {
            slideList.add(new Slide(Kiosk.eventImgPath + eImg, eventList.get(i).getID()));
         }
      }
      
      ArrayList<String> slideImgs = Database.getSlideData();
      for (int i = 0; i < slideImgs.size(); i++) {
         slideList.add(new Slide(Kiosk.slideImgPath + slideImgs.get(i), 0));
      }
      
      // Could shuffle slideList at this point if wanted

      totalSlide = slideList.size();
      curSlide = 0;   
      
      slide = slideList.get(0).create(slide);
      slide.revalidate();
      slide.repaint();
      
      return slide;
   }


   // Runtime slideshow functions
   public void runSlideshow() {
      idleTimer.stop();
      timer.setInitialDelay(sTime);
      timer.start();
   }
   public void endSlideshow() {
      timer.stop();
      idleTimer.start();
   }
   public void updateIdle() {
      if (idleTimer.isRunning()) {
         idleTimer.restart();
      }
   }
   public void nextSlide() {
      timer.stop();
      timer.setInitialDelay(navTime);
      moveSlide(true); //right
      timer.start();
   }
   public void prevSlide() {
      timer.stop();
      timer.setInitialDelay(navTime);
      moveSlide(false); //left
      timer.start();
   }
   
   
   // -----
   // Private methods
   // -----
   
   private void moveSlide(boolean right) {
      if (right) {
         curSlide++;
         if (curSlide >= totalSlide) {
            curSlide = 0;
         }
      } else {
         curSlide--;
         if (curSlide <= -1) {
            curSlide = totalSlide-1;
         }
      }
   
      slide = slideList.get(curSlide).create(slide); 
      slide.revalidate();
      slide.repaint();
      
      System.gc();
   }
   
      

}