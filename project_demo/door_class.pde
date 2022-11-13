public class Door{ //<>//
  String RoleName; //GUEST, EMPLOYEE, CEO, CONFERENCE
  String DoorToAccess;
  boolean access; //whether access was granted or denied
  String time;

  
  public Door(String RN, String DTA, boolean a, String t){
    RoleName = RN;
    DoorToAccess = DTA;
    access = a;
    time = t;
  }

  public String getDoorToAccess(){
   return this.DoorToAccess;
  }
  public boolean getAccess(){
    return this.access;
  }
  public void setTime(String t){
    this.time = t;
  }
}
