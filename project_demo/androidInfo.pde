
public void androidInfo(String RN, String DTA, boolean a, String t){
  Door door = new Door(RN, DTA, a, t);
  int d = day();    // Values from 1 - 31
  int m = month();  // Values from 1 - 12
  int y = year();   // 2003, 2004, 2005, etc.
  int h = hour();
  int min = minute();
  int sec = second();

  //String str = String.valueOf(d);
  String month = "";
  switch(m){
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
  switch(doornum){
    case 0: //maindoor
     if(tmp){
      maindoor = true;
     }    
    b1 = millis();
    break;
    case 1: //confdoor
     if(tmp){
      confdoor = true;      
     }
    b2 = millis();
    break;
    case 2: //bathroomdoor1
     if(tmp){
     bathroomdoor1 = true;     
     }
    b3 = millis();
    break;
    case 3: //bathroomdoor2
     if(tmp){
      officedoor2 = true;         
    }   
    b4 = millis();
    break;
    case 4: //bigofficedoor
     if(tmp){
      bigofficedoor = true;
     }
    b5 = millis();
    break;
    case 5: //officedoor2
     if(tmp){
      bathroomdoor1 = true;      
    }  
    b6 = millis();
    break;
    case 6: //officedoor1
     if(tmp){
      bathroomdoor2 = true; 
    }   
    b7 = millis();
    break;
    case 7: //kitchendoor
     if(tmp){
      confdoor = true;
     }
    b8 = millis();
    break;
  
  
  }
  //C:\Users\quirk\Desktop\COSC-310-Project\project_demo
   String dir = "C:/Users/quirk/Desktop/COSC-310-Project/project_demo"; //<>//
     boolean r = false;
        File folder = new File(dir); //<>//
        File[] k = folder.listFiles(); //k is null f**k, not null but that's not generic dir
        //FileWriter outputfile = new FileWriter("data.csv");
        for(int i = 0; i < k.length; i++){
  
              try{
     
                if(k[i].getName().equals("database.csv")) {
                  r = true;
                CSVWriter out = new CSVWriter(new FileWriter(k[i].getName()), ',', CSVWriter.NO_QUOTE_CHARACTER,
                                         CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                         CSVWriter.DEFAULT_LINE_END);
                String[] dis = {RN, DTA, String.valueOf(a), t}; //<>//
                out.writeNext(dis);
                out.close();
                }else if(!r && i == k.length-1) {
                  File data = new File("database.csv"); //it ran why didn't it create the file //<>//
                  try{
                  CSVWriter out = new CSVWriter(new FileWriter(data), ',', CSVWriter.NO_QUOTE_CHARACTER,
                                         CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                         CSVWriter.DEFAULT_LINE_END);
                  String[] head = {"RoleName","DoorToAccess","AccessStatus","DATETIME"};
                  out.writeNext(head);
                  String[] dis = {RN, DTA, String.valueOf(a), t};
                  out.writeNext(dis);
                  out.close();
            }
              catch(Exception e){
              }
                    
        } 

        }catch(Exception e){
          
        }
        }

}
