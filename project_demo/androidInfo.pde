public void androidInfo(String RN, String DTA, boolean a, String t) {
  Door door = new Door(RN, DTA, a, t);
  int d = day();    // Values from 1 - 31
  int m = month();  // Values from 1 - 12
  int y = year();   // 2003, 2004, 2005, etc.
  int h = hour();
  int min = minute();
  int sec = second();

  //String str = String.valueOf(d);
  String month = "";
  switch(m) {
  case 1:
    month = "Jan";
    break;
  case 2:
    month = "Feb";
    break;
  case 3:
    month = "Mar";
    break;
  case 4:
    month = "Apr";
    break;
  case 5:
    month = "May";
    break;
  case 6:
    month = "Jun";
    break;
  case 7:
    month = "Jul";
    break;
  case 8:
    month = "Aug";
    break;
  case 9:
    month = "Sep";
    break;
  case 10:
    month = "Oct";
    break;
  case 11:
    month = "Nov";
    break;
  case 12:
    month = "Dec";
    break;
  }
  t = String.valueOf(d) + "-" + String.valueOf(month) + "-" +String.valueOf(y) + " " + String.valueOf(h) + ":" + String.valueOf(min) + ":" + String.valueOf(sec);
  door.setTime(t);





  int doornum = Integer.parseInt(door.getDoorToAccess());
  boolean tmp = door.getAccess();
  switch(doornum) {
  case 0: //maindoor
    if (tmp) {
      maindoor = true;
    }
    b1 = millis();
    break;
  case 1: //confdoor
    if (tmp) {
      confdoor = true;
    }
    b2 = millis();
    break;
  case 2: //bathroomdoor1
    if (tmp) {
      bathroomdoor2 = true;
    }
    b3 = millis();
    break;
  case 3: //bathroomdoor2
    if (tmp) {
      bathroomdoor1 = true;
    }
    b4 = millis();
    break;
  case 4: //bigofficedoor
    if (tmp) {
      bigofficedoor = true;
    }
    b5 = millis();
    break;
  case 5: //officedoor2
    if (tmp) {
      officedoor2 = true;
    }
    b6 = millis();
    break;
  case 6: //officedoor1
    if (tmp) {
      officedoor1 = true;
    }
    b7 = millis();
    break;
  case 7: //kitchendoor
    if (tmp) {
      kitchendoor = true;
    }
    b8 = millis();
    break;
  }
  //"C:/Users/quirk/Desktop/COSC-310-Project/project_demo/database.csv";
  String dir = "./database.csv";
  String[] head = {"RoleName", "DoorToAccess", "AccessStatus", "DATETIME"};
  File tempFile = new File("./database.csv");
 // temp = "EMPLOYEE,2,true,''"; //EXAMPLE ONLY**
  Boolean check = tempFile.exists();
  String[] dis = {RN, DTA, String.valueOf(a), t};
  try(CSVWriter writer = new CSVWriter(new FileWriter(dir, true))) {
    if (check) {
      writer.writeNext(dis);
    } else {
      List<String[]> entries = new ArrayList<>();
      entries.add(head);
      entries.add(dis);
      writer.writeAll(entries);
    }
  }
  catch(Exception e) {
    print(e);
  }
}
