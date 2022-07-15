// Slide.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class Slide {
 
   private Config con;
   private Config.Dim leftB, infoB, rightB, slide,
      areaLeft, areaCenter, areaRight;
   private String slideImg;
   private int slideID, screenWidth, screenHeight;
 
   public Slide(String img, int id) {
      con = Config.getInstance();
      slideImg = img;
      slideID = id;
      setImgData();
      setButtonData();
   }
   
   public JPanel create(JPanel panel) {
      if (panel == null) {
         panel = new JPanel();
         panel.setLayout(null);
         panel.setSize(new Dimension(screenWidth, screenHeight));
         panel.setLayout(null);
         panel.setBackground(new Color(0, 0, 0)); 
         panel.setVisible(true);
      } else {
         panel.removeAll();
      }
      
      Display.attachScaledImg(panel, leftB, Slideshow.left_Arrow);
      Display.attachScaledImg(panel, rightB, Slideshow.right_Arrow);
      if (slideID != 0) {
         Display.attachScaledImg(panel, infoB, Slideshow.info_On);
      } else {
         Display.attachScaledImg(panel, infoB, Slideshow.info_Off);
      }
      Display.attachScaledImg(panel, slide, slideImg);
      
      Display.attachInvisButton(panel, areaLeft, "LeaveSS");
      Display.attachInvisButton(panel, areaCenter, "LeaveSS");
      Display.attachInvisButton(panel, areaRight, "LeaveSS");
      Display.attachInvisButton(panel, leftB, "LeftSS");
      Display.attachInvisButton(panel, rightB, "RightSS");
      if (slideID != 0) {
         // Used for info button
         ActionListener infoListen = 
            new ActionListener() {
               public void actionPerformed(ActionEvent ae) { 
                  Controller control = Controller.getInstance();
                  control.switchToEvent(slideID);
               }
            };
         JButton button = Display.invisButton("InfoSS", infoB.x, infoB.y, infoB.w, infoB.h);
         button.addActionListener(infoListen);
         panel.add(button);
      }
    
      return panel;
   }
   
   // -----
   // Private methods
   // -----
   
   private void setImgData() {
      screenWidth = con.getWidth();
      screenHeight = con.getHeight();
      ImageIcon rawImg = new ImageIcon(slideImg);
      int imgW = rawImg.getIconWidth();
      int imgH = rawImg.getIconHeight();
      float wRatio = (float) screenWidth / (float) imgW;
      float hRatio = (float) screenHeight / (float) imgH;
      slide = con.newDim();
      if (wRatio > hRatio) { // Scale by height
         imgW = (int) (imgW * hRatio);
         imgH = (int) (imgH * hRatio);
         slide.x = (screenWidth - imgW) / 2;
         slide.y = 0;
         slide.w = imgW;
         slide.h = imgH;
      } else { // Scale by width
         imgH = (int) (imgH * wRatio);
         imgW = (int) (imgW * wRatio);
         slide.x = 0;
         slide.y = (screenHeight - imgH) / 2;
         slide.w = imgW;
         slide.h = imgH;
      }
   }
   
   private void setButtonData() {
      Config.SlideEnum sEnum = Config.SlideEnum.left;
      leftB = con.getSlide(sEnum.left);
      infoB = con.getSlide(sEnum.info);
      rightB = con.getSlide(sEnum.right);
      
      areaLeft = con.newDim();
      areaLeft.x = 0;
      areaLeft.y = leftB.y;
      areaLeft.w = leftB.x;
      areaLeft.h = leftB.h;
      
      areaCenter = con.newDim();
      areaCenter.x = 0;
      areaCenter.y = 0;
      areaCenter.w = screenWidth;
      areaCenter.h = screenHeight - infoB.h;
      
      areaRight = con.newDim();
      areaRight.x = rightB.x + rightB.w;
      areaRight.y = rightB.y;
      areaRight.w = screenWidth - areaRight.x;
      areaRight.h = rightB.h;
   }
   
   
      
}