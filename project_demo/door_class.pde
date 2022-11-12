public class Door{
  int numDoor; //door access
  boolean access; //whether access was granted or denied
  int accessLevel; //level 1 is guest, level 2 is employee, level 3 is CEO
  String accessType; //??
  String time;
  
  public Door(int num, boolean acc, int level, String type, String t){
    numDoor = num;
    access = acc;
    accessLevel = level;
    accessType = type;
    time = t;
  }
  public int getNum(){
    return this.numDoor;
  }
  public int getLevel(){
    return this.accessLevel;
  }

}
