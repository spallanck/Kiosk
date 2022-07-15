// Menu.java

import javax.swing.*;
import java.awt.*;
import java.util.*;

class Menu {

   // Constants - adjust when adding more buttons
   private final int border = 100;
   private final int rows = 2;
   private final int cols = 2;
   private final int numButtons = 4;
   
   private final String[] imageList = {
   Kiosk.buttonImgPath + "map_button.png",
   Kiosk.buttonImgPath + "slideshow_button.png",
   Kiosk.buttonImgPath + "about_button.png", 
   Kiosk.buttonImgPath + "events_button.png"
   };
   
   private final String[] nameList = {
   "Map", "Slideshow", "About", "Events"
   };
   
   // Data class
   private class ButtonData { 
      int x, y;
      String name;
      String image;
   }

   // Class variables
   private int sWidth;
   private int sHeight;
    
   private int buttonW;
   private int buttonH;
   private ArrayList<ButtonData> buttonList;
   public static Config con;

   // --- Class functions ---
 
   // Constructor
   public Menu() {
      con = Config.getInstance();
      this.sWidth = con.getWidth();
      this.sHeight = con.getHeight(); 
        
      this.buttonW = (this.sWidth - (this.border * (cols + 1))) / cols;
      this.buttonH = (this.sHeight - (this.border * (rows + 1))) / rows;
      fillButtonData(rows, cols);
   }

   // Creates the menu screen for the kiosk
   public JPanel create() {
      // Create the window
      JPanel panel = new JPanel();
      panel.setLayout(null);
      panel.setBackground(Kiosk.DarkBlue);
      Interact intr = Interact.getInstance();
      
      // Create buttons and image overlays
      for (int i = 0; i < numButtons; i++) {
         // Image overlay
         JLabel label = new JLabel();
         label.setBounds(buttonList.get(i).x, buttonList.get(i).y, buttonW, buttonH);
         ImageIcon icon = new ImageIcon(buttonList.get(i).image);
         Image scaled = icon.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_SMOOTH);
         icon = new ImageIcon(scaled);
         label.setIcon(icon);
         panel.add(label);
         
         // Button
         JButton button = Display.invisButton(buttonList.get(i).name, buttonList.get(i).x, buttonList.get(i).y, buttonW, buttonH);
         button.addActionListener(intr);
         panel.add(button);
      }
   
      return panel;
   }
    
   // Creates a list of dimensions for button locations on menu
   private void fillButtonData(int row, int col) {
      buttonList = new ArrayList<ButtonData>();
       
      // Button x,y locations
      for (int i = 0; i < row; i++) {
         for (int j = 0; j < col; j++) {
            ButtonData loc = new ButtonData();
            loc.x = ((j + 1) * border) + (j * buttonW);
            loc.y = ((i + 1) * border) + (i * buttonH);
            buttonList.add(loc);
         }
      }
      
      // Button name and image
      for (int i = 0; i < numButtons; i++) {
         buttonList.get(i).name = nameList[i];
         buttonList.get(i).image = imageList[i];
      }
   }

}
