// EventData.java
// A simple class dedicated to storing data related 
// to an event or club 
import java.util.*;
import javax.swing.*;



class EventData {
    private int eventID;
    private String title;
    private String location;
    private String description;
    private String image;
    private String date;
    private String time;

    public EventData() {
    
    }

    public EventData(int eID, String eventTitle, String eventLocation, String eventDescription, String img, String eventDate, String eventTime) {
        eventID = eID;
        title = eventTitle;
        location = eventLocation;
        description = eventDescription;
        image = img;
        date = eventDate;
        time = eventTime;
    }


    public int getID() {
        return eventID;
    }
    
    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }

    public void setID(int id) {
        eventID = id;
    }
    
    public void setTime(String t) {
        time = t;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public void setLocation(String newLocation) {
        location = newLocation;
    }

    public void setDescription(String newDescription) {
        description = newDescription;
    }

    public void setImage(String newImage) {
        image = newImage;
    }

    public void setDate(String newDate) {
        date = newDate;
    }
}
