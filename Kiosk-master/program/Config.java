// Config.java

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Config {

   // Singleton 
   public static Config getInstance() {
      if (singleton == null) {
         singleton = new Config();
      }
      return singleton;
   }

   // Enumerations to handle indexing arrayLists in the subclasses
   public enum ClubEnum { title, aboutLabel, desc, whenLabel, time };
   public enum DisplayEnum { mainTitle, subTitle, mainPage, subNav, subPage, mainNav, subSearch };
   public enum EventEnum { pic, title, aboutLabel, desc, whenLabel, when, whereLabel, where };
   public enum PersonEnum { pic, nameLabel, roomLabel, emailLabel, phoneLabel, classLabel,
      researchLabel, bioLabel, name, room, email, phone, classes, research, bio, button };
   public enum SlideEnum { left, info, right };

   // Data retrieval functions
   public int getHeight() { return height; }
   public int getWidth() { return width; }
   public int getSlideTime() { return slideTime; }
   public int getNavTime() { return navTime; }
   public int getIdleTime() { return idleTime; }
   
   public Text getClub(ClubEnum e) { return club.get(e.ordinal()); }
   public Dim getDisplay(DisplayEnum e) { return display.get(e.ordinal()); }
   public Text getEvent(EventEnum e) { return event.get(e.ordinal()); }
   public Text getPerson(PersonEnum e) { return person.get(e.ordinal()); }
   public Dim getSlide(SlideEnum e) { return slide.get(e.ordinal()); }
   
   // Classes to hold basic dimensions
   public class Dim { int x, y, w, h; }
   public class Text { int x, y, w, h, fs; }
   public Dim newDim() { return new Dim(); }
   public Text newText() { return new Text(); }

   // -----
   // Private data/methods
   // -----

   // Config Data
   private int width, height;
   private int slideTime, navTime, idleTime;
   private ArrayList<Text> club;
   private ArrayList<Dim> display;
   private ArrayList<Text> event; 
   private ArrayList<Text> person;
   private ArrayList<Dim> slide;
   // Singleton
   private static Config singleton = null;
   private Config() { LoadData(); }
   
   // Loads all config data, called at construction
   private void LoadData() {
      LoadClub();
      LoadDisplay();
      LoadEvent();
      LoadPerson();
      LoadSlide();
   }
   
   // Functions for reading config files
   private void LoadClub() {
      club = new ArrayList<>();
      File file = new File("config/club_config_local.txt");
      if (!file.isFile()) {
         file = new File("config/club_config.txt");
      }
      try {
         Scanner sc = new Scanner(file);
         int entries = 0;
         int numEntries = ClubEnum.values().length;
         while (entries < numEntries) {
            club.add(getNextText(sc));
            entries++;
         }
         sc.close();
      } catch (FileNotFoundException e) {
         System.out.println("Failed to load club_config file");
         e.printStackTrace();
      }
   }
   
   private void LoadDisplay() {
      display = new ArrayList<>();
      File file = new File("config/display_config_local.txt");
      if (!file.isFile()) {
         file = new File("config/display_config.txt");
      }
      try {
         Scanner sc = new Scanner(file);
         width = getNextInt(sc);
         height = getNextInt(sc);
         int entries = 0;
         int numEntries = DisplayEnum.values().length;
         while (entries < numEntries) {
            display.add(getNextDim(sc));
            entries++;
         }
         sc.close();
      } catch (FileNotFoundException e) {
         System.out.println("Failed to load display_config file");
         e.printStackTrace();
      }
   }
   
   private void LoadEvent() {
      event = new ArrayList<>();
      File file = new File("config/event_config_local.txt");
      if (!file.isFile()) {
         file = new File("config/event_config.txt");
      }
      try {
         Scanner sc = new Scanner(file);
         event.add(getNextPadText(sc));
         int entries = 1;
         int numEntries = EventEnum.values().length;
         while (entries < numEntries) {
            event.add(getNextText(sc));
            entries++;
         }
         sc.close();
      } catch (FileNotFoundException e) {
         System.out.println("Failed to load event_config file");
         e.printStackTrace();
      }
   }

   private void LoadPerson() {
      person = new ArrayList<>();
      File file = new File("config/person_config_local.txt");
      if (!file.isFile()) {
         file = new File("config/person_config.txt");
      }
      try {
         Scanner sc = new Scanner(file);
         person.add(getNextPadText(sc));
         int entries = 1;
         int numEntries = PersonEnum.values().length;
         while (entries < numEntries) {
            person.add(getNextText(sc));
            entries++;
         }
         sc.close();
      } catch (FileNotFoundException e) {
         System.out.println("Failed to load person_config file");
         e.printStackTrace();
      }
   }
   
   private void LoadSlide() {
      slide = new ArrayList<>();
      File file = new File("config/slide_config_local.txt");
      if (!file.isFile()) {
         file = new File("config/slide_config.txt");
      }
      try {
         Scanner sc = new Scanner(file);
         slideTime = getNextInt(sc);
         navTime = getNextInt(sc);
         idleTime = getNextInt(sc);
         int entries = 0;
         int numEntries = SlideEnum.values().length;
         while (entries < numEntries) {
            slide.add(getNextDim(sc));
            entries++;
         }
         sc.close();
      } catch (FileNotFoundException e) {
         System.out.println("Failed to load slide_config file");
         e.printStackTrace();
      }
   }
   
   // Functions for scanning for specific patterns
   private Text getNextText(Scanner scan) {
      Text entry = new Text();
      entry.x = getNextInt(scan);
      entry.y = getNextInt(scan);
      entry.w = getNextInt(scan);
      entry.h = getNextInt(scan);
      entry.fs = getNextInt(scan);
      return entry;
   }  
   private Text getNextPadText(Scanner scan) {
      Text entry = new Text();
      entry.x = getNextInt(scan);
      entry.y = getNextInt(scan);
      entry.w = getNextInt(scan);
      entry.h = getNextInt(scan);
      entry.fs = 0;
      return entry;
   }
   private Dim getNextDim(Scanner scan) {
      Dim entry = new Dim();
      entry.x = getNextInt(scan);
      entry.y = getNextInt(scan);
      entry.w = getNextInt(scan);
      entry.h = getNextInt(scan);
      return entry;
   }
   private int getNextInt(Scanner scan) {
      int i = 0;
      while (scan.hasNextLine()) {
         if (scan.hasNextInt()) {
            i = scan.nextInt();
            break;
         } else {
            scan.nextLine();
         }
      }
      return i;
   }
}