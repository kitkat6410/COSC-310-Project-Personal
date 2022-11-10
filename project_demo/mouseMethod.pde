void mouseClicked(){
  if(mouseX>=150 && mouseX <=336 && mouseY >=165 && mouseY <= 171){ //main entrance
    
    if(door.getLevel() >=1){
      maindoor = true;
      door.setAccess(true);      
    }
    else{
      door.setAccess(false);
    }
    b1 = millis();  // how many milliseconds it has been since the start of the program
  }else if(mouseX>=68 && mouseX <=172 && mouseY>=649 && mouseY <= 655){ //kitchen
     if(door.getLevel() >=1){
      door.setAccess(true);
      kitchendoor = true;
    }
     else{
      door.setAccess(false);
    }
    
    b2 = millis();
    }
    else if(mouseX>=316 && mouseX <= 416 && mouseY >=649 && mouseY <= 655){
      if(door.getLevel() >=1){
      door.setAccess(true);   
      officedoor1 = true;
    }
      else{
      door.setAccess(false);
    }
      b3 = millis();
    }
    else if(mouseX >= 617 && mouseX <= 722 && mouseY >= 649 && mouseY <= 655){
      if(door.getLevel() >=1){
      door.setAccess(true);   
      officedoor2 = true;
    }
      else{
      door.setAccess(false);
    }
      
      b4 = millis();
    }
    else if(mouseX >= 924 && mouseX <= 1027 && mouseY >= 649 && mouseY <=655){
      if(door.getLevel() >=1){
      door.setAccess(true); 
      bigofficedoor = true;
    }
      else{
      door.setAccess(false);
    }
      b5 = millis();
    }
    else if(mouseX >= 1047 && mouseX <= 1053 && mouseY >= 506 && mouseY <= 608){
      if(door.getLevel() >=1){
      door.setAccess(true); 
       bathroomdoor1 = true;
    }
      else{
      door.setAccess(false);
    }
      b6 = millis();
    }
    else if(mouseX >= 1047 && mouseX <= 1053 && mouseY >= 348 && mouseY <= 453){
        if(door.getLevel() >=1){
      door.setAccess(true); 
       bathroomdoor2 = true;
    }
      else{
      door.setAccess(false);
    }
      b7 = millis();
    }
    else if(mouseX >= 774 && mouseX <= 938 && mouseY >= 302 && mouseY <= 308){
        if(door.getLevel() >=1){
      door.setAccess(true); 
       confdoor = true;
    }
      else{
      door.setAccess(false);
    }
      b8 = millis();
    }
    
}
