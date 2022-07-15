// Controller.java

import javax.swing.*;
import java.awt.*;
import java.util.*;

class Controller {

   // Singleton
   private static Controller singleton = null;
   private Controller() { /* Private constructor for singleton */ }
   public static Controller getInstance() {
      if (singleton == null) {
         singleton = new Controller();
      }
      return singleton;
   }
   
   // String representations of kiosk modes
   private final String[] modes = {
   "About", "Club", "Depart", "Event", "Map", "Menu", "Slide"
   };
   
   // Enumeration for kiosk modes
   public enum Mode { About, Club, Depart, Event, Map, Menu, Slide };
   private Mode current;
   private JFrame MainFrame;
   private CardLayout cl;
   private Config config;
   private ArrayList<JPanel> panels;
   private JPanel container;
   
   // Feature Classes
   // If new classes are added, they will need declared here
   private Slideshow kSlide;
   private KioskMap kMap;
   private Menu kMenu;
   private Info kInfo;
   private Event kEvent;
   private Club kClub;
   
   
   // -----
   // Functions for Kiosk startup
   // -----
   
   
   // Loads data from database and config
   public void loadData() {
      Database.LoadData();
      config = Config.getInstance();
   }
   
   // Set main frame to be fullscreen
   public void screenSetup() {
      MainFrame = new JFrame("MainFrame");
      MainFrame.setSize(new Dimension(config.getWidth(), config.getHeight()));
      MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      MainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      MainFrame.setUndecorated(true);
      MainFrame.setResizable(false);
      MainFrame.setVisible(true);
      
      GraphicsDevice machine = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
      machine.setFullScreenWindow(MainFrame);  
   }
   
   // Initialize individual features of the kiosk
   // If new features are added, they will need declared here
   public void loadModules() {
      kClub = new Club();
      kEvent = new Event();
      kInfo = new Info();
      kMap = new KioskMap();
      kMenu = new Menu();
      kSlide = new Slideshow();
      
      // "About", "Club", "Depart", "Event", "Map", "Menu", "Slide"
      panels = new ArrayList<>();
      panels.add(kInfo.displayInfo());
      panels.add(kClub.create());
      panels.add(kInfo.displayDepart());
      panels.add(kEvent.create());
      panels.add(kMap.create());
      panels.add(kMenu.create());
      panels.add(kSlide.create());
   }
   
   // Add individual features to the kiosk
   public void addModules() {
      cl = new CardLayout();
      container = new JPanel(cl);
      
      for (int i = 0; i < panels.size(); i++) {
         cl.addLayoutComponent(panels.get(i), modes[i]);
         container.add(panels.get(i));
      }
   }
   
   // Start the kiosk
   public void go() {
      cl.show(container, modes[Mode.Menu.ordinal()]);
      MainFrame.add(container);
   }
   
   
   // -----
   // Control calls for runtime events
   // -----
   
   
   // Control call to change kiosk modes
   public void changeMode(Mode m) {
      // If already on mode, nothing
      if (m == current) { 
         kSlide.updateIdle();
         return;
      }
      // Slideshow timers if needed
      if (current == Mode.Slide) { 
         kSlide.endSlideshow();
      } 
      // Switch mode
      switch (m) {
         case About:
            cl.show(container, modes[Mode.About.ordinal()]);
            break;
         case Club:
            cl.show(container, modes[Mode.Club.ordinal()]);
            break;
         case Depart:
            cl.show(container, modes[Mode.Depart.ordinal()]);
            break;
         case Event:
            cl.show(container, modes[Mode.Event.ordinal()]);
            break;
         case Map:
            cl.show(container, modes[Mode.Map.ordinal()]);
            break;
         case Menu:
            cl.show(container, modes[Mode.Menu.ordinal()]);
            break;
         case Slide:
            cl.show(container, modes[Mode.Slide.ordinal()]);
            kSlide.runSlideshow();
            break;
      }
      // Update current mode
      current = m;
   }
   
   // Control call to reset a kiosk mode
   public void defaultMode(Mode m) {
      changeMode(m);
      switch (m) {
         case Map:
            kMap.resetView();
            break;
      }
   }
   
   // Control call to switch to a specified event/club
   public void switchToEvent(int id) {
      switch (current) {
         case Club:
            kClub.showClub(id);
            kSlide.updateIdle();
            break;
         case Event:
            kEvent.showEvent(id);
            kSlide.updateIdle();
            break;
         case Slide:
            changeMode(Mode.Event);
            break;
      }
   }
   
   // Control call to switch to a specified personCard
   public void switchToPerson(int id) { 
      changeMode(Mode.Map);
      kMap.showPerson(id);
   }
   
   // Control call to highlight a specified room
   public void highlightMap(int id) { 
      changeMode(Mode.Map);
      kMap.highlight(id);
   }
   
   // Control call to update sidebar display
   public void sideBar(String dir) {
      kSlide.updateIdle();
      switch (current) {
         case Club:
            kClub.updateSide(dir);
            break;
         case Event:
            kEvent.updateSide(dir);
            break;
         case Map:
            kMap.updateSide(dir);
            break;  
      }
   }
   
   // Control call for slideshow
   public void moveSlide(String dir) {
      if (dir.equals("Right")) {
         kSlide.nextSlide();
      } else {
         kSlide.prevSlide();
      }
   }
   
}