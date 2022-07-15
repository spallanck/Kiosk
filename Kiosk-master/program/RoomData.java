// RoomData.java

class RoomData {
    private String roomNum;
    private String owner;
    private int x;
    private int y;
    private int w;
    private int h;
    

    public RoomData(){
        roomNum = "";
        owner = "not found";
    }

    public RoomData(String roomNumber, String newOwner){
        roomNum = roomNumber;
        owner = newOwner;
    }
    
    public RoomData(String roomNumber, String newOwner, int eX, int eY, int eW, int eH){
        roomNum = roomNumber;
        owner = newOwner;
        x=eX;
        y=eY;
        w=eW;
        h=eH;
    }
    
    public RoomData(String roomNumber, int eX, int eY, int eW, int eH){
        roomNum = roomNumber;
        x=eX;
        y=eY;
        w=eW;
        h=eH;
    }
    
    public int getX(){
        return x;
    }
    public void setX(int newX){
        x= newX;
    }
   
   public int getY(){
        return y;
    }
     public void setY(int newY){
        y= newY;
    } 
    
    public int getW(){
        return w;
    }
    public void setW(int newW){
        w= newW;
    }
    
    public int getH(){
        return h;
    }
    public void setH(int newH){
        h= newH;
    }

   
    public String getNum(){
        return roomNum;
    }
    public void setNum(String newNum){
        roomNum = newNum;
    }
    public String getOwner(){
        return owner;
    }
    public void setOwner(String newOwner){
        owner = newOwner;
    }
}
