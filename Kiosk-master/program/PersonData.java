// PersonData.java

import java.util.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class PersonData {

   private int personID;
   private String name;
   private String profilePic;
   private String mainPic;
   private String email;
   private String phoneNum;
   private String room;
   private String researchInterests;
   private String classList;
   private String biography;

   // --------------------------------------------------------------------------------------------//

   // CONSTRUCTORS //

   // Constructor for staff

   public PersonData() {
   
   }
   
   public PersonData(int id, String name, String email, String phoneNum, String room) {
      this.personID = id;
      this.name = name;
      this.email = email;
      this.phoneNum = phoneNum;
      this.room = room;
   }

   // Constructor for faculty
   public PersonData(int id, String name, String email, String phoneNum, String room,
           String researchInterests, String classList, String biography) {
      this.personID = id;
      this.name = name;
      this.email = email;
      this.phoneNum = phoneNum;
      this.room = room;
      this.researchInterests = researchInterests;
      this.classList = classList;
      this.biography = biography;
   }

   // --------------------------------------------------------------------------------------------//

   // GETTER AND SETTER METHODS

   public int getID() {
      return personID;
   }

   public void setID(int id) {
      this.personID = id;
   }
   public String getName() {
      return name;
   }

   public void setName(String eName) {
      this.name = eName;
   }

   public String getProfilePic() {
      return profilePic;
   }
   
   public void setProfilePic(String eProfilePic) {
      this.profilePic = eProfilePic;
   }
   
   public String getMainPic() {
      return mainPic;
   }

   public void setMainPic(String eMainPic) {
      this.mainPic = eMainPic;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String eEmail) {
      this.email = eEmail;
   }

   public String getPhoneNum() {
      return phoneNum;
   }

   public void setPhoneNum(String ePhoneNum) {
      this.phoneNum = ePhoneNum;
   }

   public String getRoom() {
      return room;
   }

   public void setRoom(String eRoom) {
      this.room = eRoom;
   }

   public String getResearchInterests() {
      return researchInterests;
   }
   public void setResearchInterests(String newResearch) {
      researchInterests = newResearch;
   }

   public String getClassList() {
      return classList;
   }
   
   public void setClassList(String newClassList) {
      classList = newClassList;
   }

   public String getBiography() {
      return biography;
   }

   public void setBiography(String eBiography) {
      this.biography = eBiography;
   }

}