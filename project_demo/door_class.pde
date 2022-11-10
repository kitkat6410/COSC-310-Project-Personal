public class Door{
  int numDoor; //door access
  boolean access; //whether access was granted or denied
  int accessLevel; //level 1 is guest, level 2 is employee, level 3 is CEO
  String accessType; //??
  String time;
  
  public Door(int num, int level, String type){
    numDoor = num;
    accessLevel = level;
    accessType = type;
  }
  public int getNum(){
    return this.numDoor;
  }
  public int getLevel(){
    return this.accessLevel;
  }
  public void setAccess(boolean c){
    this.access = c;
  }
  public boolean getAccess(){
    return this.access;
}
}
