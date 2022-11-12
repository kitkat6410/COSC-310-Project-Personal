public void androidInfo(int num, boolean acc, int level, String type, String t){
  Door door = new Door(num, acc, level, type, t);
//  int d = day();    // Values from 1 - 31
//int m = month();  // Values from 1 - 12
//int y = year();   // 2003, 2004, 2005, etc.
//int h = hour();
//int min = minute();
//int sec = second();

//String str = String.valueOf(d); //<>//
//String month = "";
//switch(m){
 // case 1:
 // month = "Jan";
 // break;
//  case 2:
 // month = "Feb";
 // break;
 // case 3:
 // month = "Mar";
//  break;
//  case 4:
 // month = "Apr";
 // break;
 // case 5:
 // month = "May";
 // break;
//  case 6:
//  month = "Jun";
//  break;
//  case 7:
//  month = "Jul";
 // break;
 // case 8:
 // month = "Aug";
//  break;
//  case 9:
//  month = "Sep";
//  break;
//  case 10:
//  month = "Oct";
//  break;
//  case 11:
//  month = "Nov";
//  break;
//  case 12:
//  month = "Dec";
//  break;
  
  
//}
//String str = String.valueOf(d) + "-" + String.valueOf(month) + "-" +String.valueOf(y) + " " + String.valueOf(h) + ":" + String.valueOf(min) + ":" + String.valueOf(sec);
//door.setTime(str);
  
  
  
  
  
  int doornum = door.getNum();
  switch(doornum){
    case 1: //maindoor
     if(door.getLevel() >=1){
      maindoor = true;
     }
    
    b1 = millis();
    break;
    case 2: //kitchendoor
     if(door.getLevel() >=2){
      kitchendoor = true;
       
     }
    b1 = millis();
    break;
    case 3: //officedoor1
     if(door.getLevel() >=2){
      officedoor1 = true;
       
     }
    b1 = millis();
    break;
    case 4: //officedoor2
     if(door.getLevel() >=2){
      officedoor2 = true;
          
    }
    
    b1 = millis();
    break;
    case 5: //bigofficedoor
     if(door.getLevel() >=3){
      bigofficedoor = true;
     }
    b1 = millis();
    break;
    case 6: //bathroomdoor1
     if(door.getLevel() >=1){
      bathroomdoor1 = true;
       
    }
   
    b1 = millis();
    break;
    case 7: //bathroomdoor2
     if(door.getLevel() >=1){
      bathroomdoor2 = true;
  
    }
   
    b1 = millis();
    break;
    case 8: //confdoor
     if(door.getLevel() ==0){
      confdoor = true;
  
    b1 = millis();
    break;
  }
  
}
}
