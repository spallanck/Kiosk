// MapData.java
import java.util.*;

class MapData {
    private String floorImgSrc;
    private ArrayList<RoomData> rooms;

    public MapData() {
        rooms = new ArrayList<>();
    }

    public String getImg(){
        return floorImgSrc;
    }
    public void setImg(String newSrc){
        floorImgSrc = newSrc;
    }
    public ArrayList<RoomData> getRooms(){
        return rooms;
    }
    public void setRooms(ArrayList<RoomData> newRooms){
        rooms = newRooms;
    }

    public void addRoom(RoomData newRoom){
        rooms.add(newRoom);
    }
}
