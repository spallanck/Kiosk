// KioskDisplay

import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.util.*;

public class KioskDisplay extends JFrame {

   // Swing variables
   JLayeredPane kPane;
   JLabel kioskLabel;
   ImageIcon kioskIcon;
   int height, width;
   
   // Constructor
   public KioskDisplay() {
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      // Set background image
      kioskLabel = new JLabel();
      kioskIcon =  new ImageIcon("Demo Image.jpg");
      kioskLabel.setIcon(kioskIcon);
      height = kioskIcon.getIconHeight();
      width = kioskIcon.getIconWidth();
      
      // Layered pane
      kPane = getLayeredPane();
      kPane.setLayout(null);
      kPane.add(kioskLabel, new Integer(0));
      kioskLabel.setBounds(0,0,width,height);
      
      // Set size of GUI
      setSize(kioskIcon.getIconWidth(),kioskIcon.getIconHeight());
   }
}