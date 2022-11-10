public void androidInfo(int i, int j, String s){
  Door door = new Door(i, j, s);
  int doornum = door.getNum();
  switch(doornum){
    case 1: //maindoor
     if(door.getLevel() >=1){
      maindoor = true;
      door.setAccess(true);      
    }
     else{
      door.setAccess(false);
    }
    b1 = millis();
    break;
    case 2: //kitchendoor
     if(door.getLevel() >=2){
      kitchendoor = true;
      door.setAccess(true);      
    }
     else{
      door.setAccess(false);
    }
    b1 = millis();
    break;
    case 3: //officedoor1
     if(door.getLevel() >=2){
      officedoor1 = true;
      door.setAccess(true);      
    }
     else{
      door.setAccess(false);
    }
    b1 = millis();
    break;
    case 4: //officedoor2
     if(door.getLevel() >=2){
      officedoor2 = true;
      door.setAccess(true);      
    }
     else{
      door.setAccess(false);
    }
    b1 = millis();
    break;
    case 5: //bigofficedoor
     if(door.getLevel() >=3){
      bigofficedoor = true;
      door.setAccess(true);      
    }
     else{
      door.setAccess(false);
    }
    b1 = millis();
    break;
    case 6: //bathroomdoor1
     if(door.getLevel() >=2){
      bathroomdoor1 = true;
      door.setAccess(true);      
    }
     else{
      door.setAccess(false);
    }
    b1 = millis();
    break;
    case 7: //bathroomdoor2
     if(door.getLevel() >=2){
      bathroomdoor2 = true;
      door.setAccess(true);      
    }
     else{
      door.setAccess(false);
    }
    b1 = millis();
    break;
    case 8: //confdoor
     if(door.getLevel() >=2){
      confdoor = true;
      door.setAccess(true);      
    }
     else{
      door.setAccess(false);
    }
    b1 = millis();
    break;
  }
  
}
