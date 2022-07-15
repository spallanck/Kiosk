// Database.java
import java.util.*;
import java.sql.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import javax.swing.*;
import java.io.FileNotFoundException;

class Database {
	
	// Data classes	
   public static ArrayList<PersonData> pData;
   public static MapData mData;
   public static ArrayList<EventData> eData;
   public static ArrayList<EventData> cData;
   public static ArrayList<String> sData;
   private static Connection conn;
   private final static String params = "config/database_credentials.txt";

	// Loads all data from database
   public static void LoadData() {
      Properties connectprops = new Properties();
      
      try {
         connectprops.load(new FileInputStream(params));
         String dburl = connectprops.getProperty("dburl");
         String username = connectprops.getProperty("user");
         conn = DriverManager.getConnection(dburl, connectprops);
         System.out.printf("Database connection %s as %s established.%n", dburl, username);
         
         LoadPeople();
         LoadMap();
         LoadEvents();
         LoadClubs();
         LoadSlides();
        
         conn.close();
         System.out.printf("Database connection %s as %s terminated.%n", dburl, username);
      } catch (Exception exc) {
         exc.printStackTrace();
      }
      
   }

	// Loads all person data
   private static void LoadPeople() {
      pData = new ArrayList<>();
      try {
         final PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Person ORDER BY Name ASC");
         final ResultSet myRs = stmt.executeQuery();
         while (myRs.next()) {
            PersonData data = new PersonData();
            data.setID(myRs.getInt("ID"));
            data.setName(myRs.getString("Name"));
            data.setEmail(myRs.getString("Email"));
            data.setPhoneNum(myRs.getString("Phone"));
            data.setRoom(myRs.getString("Room"));
            String ints = "<html>" + myRs.getString("Interests");
            data.setResearchInterests(ints);
            String clas = "<html>" + myRs.getString("Class");
            data.setClassList(clas);
            String biog = "<html>" + myRs.getString("Biography");
            data.setBiography(biog);
            data.setMainPic(myRs.getString("MainPic"));
            data.setProfilePic(myRs.getString("ProfilePic"));
            pData.add(data);     
         }
      } catch (Exception exc) {
         exc.printStackTrace();
      }
   
   
   }
	
	// Load all map data
   private static void LoadMap() {
      mData = new MapData();
      File file = new File("config/map_config.txt");
      try{
         Scanner sc = new Scanner(file);
         String line;
         while(sc.hasNextLine()){
            line = sc.nextLine();
            String[] split = line.split("\\s+");
            RoomData room = new RoomData(split[0],Integer.parseInt(split[1]),Integer.parseInt(split[2])-160,Integer.parseInt(split[3]),Integer.parseInt(split[4]));
            mData.addRoom(room);
         }
      }
      catch (FileNotFoundException e) {
      e.printStackTrace();
      }
      }

   // Load all event data
   private static void LoadEvents() {
      eData = new ArrayList<>();
      try {
         final PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Event ORDER BY Title ASC"); 
         final ResultSet myRs = stmt.executeQuery();
         while (myRs.next()) {
            // if (myRs.getBool("Include");
            EventData data = new EventData();
            data.setID(myRs.getInt("ID"));
            data.setTitle(myRs.getString("Title"));
            data.setLocation(myRs.getString("Location"));
            data.setDescription(myRs.getString("Description"));
            data.setImage(myRs.getString("Image"));
            data.setDate(myRs.getString("Date"));
            data.setTime(myRs.getString("Time"));
            eData.add(data);   
         }
      } catch (Exception exc) {
         exc.printStackTrace();
      }
   }

   // Load all club data
   private static void LoadClubs() {
      cData = new ArrayList<>();
      try {
         final PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Clubs ORDER BY Title ASC"); 
         final ResultSet myRs = stmt.executeQuery();
         while (myRs.next()) {
            // if (myRs.getBool("Include");
            EventData data = new EventData();
            data.setID(myRs.getInt("ID"));
            data.setTitle(myRs.getString("Title"));
            data.setDescription(myRs.getString("Description"));
            data.setDate(myRs.getString("Day"));
            data.setTime(myRs.getString("Time"));
            cData.add(data);   
         }
      } catch (Exception exc) {
         exc.printStackTrace();
      }
   }

   // Load all slide data
   private static void LoadSlides() {
      sData = new ArrayList<>();
      try {
         final PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Slides"); 
         final ResultSet myRs = stmt.executeQuery();
         while (myRs.next()) {
            String slideImg = myRs.getString("Image");
            sData.add(slideImg);
         }
      } catch (Exception exc) {
         exc.printStackTrace();
      }
   }

	// -----

   public static ArrayList<PersonData> getPersonData() {
      return pData;
   }

   public MapData getMapData() {
      return mData;
   }

   public static ArrayList<EventData> getEventData() {
      return eData;
   }

   public static ArrayList<EventData> getClubData() {
      return cData;
   }
   
   public static ArrayList<String> getSlideData() {
      return sData;
   }
   
   public static PersonData getPerson(int id) {
      PersonData p = new PersonData();
      for (int i = 0; i < pData.size(); i++) {
         p = pData.get(i);
         if (p.getID() == id) {
            return p;
         }
      }
      return p;
   }
   
   public static EventData getEvent(int id) {
      EventData e = new EventData();
      for (int i = 0; i < eData.size(); i++) {
         e = eData.get(i);
         if (e.getID() == id) {
            return e;
         }
      }
      return e;
   }
   
   public static EventData getClub(int id) {
      EventData c = new EventData();
      for (int i = 0; i < cData.size(); i++) {
         c = cData.get(i);
         if (c.getID() == id) {
            return c;
         }
      }
      return c;
   }

}
