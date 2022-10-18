void mouseClicked(){
  if(mouseX>=150 && mouseX <=336 && mouseY >=165 && mouseY <= 171){
    maindoor = true;
    b1 = millis();  
  }else if(mouseX>=68 && mouseX <=172 && mouseY>=649 && mouseY <= 655){
    kitchendoor = true;
    b2 = millis();
    }
    else if(mouseX>=316 && mouseX <= 416 && mouseY >=649 && mouseY <= 655){
      officedoor1 = true;
      b3 = millis();
    }
    else if(mouseX >= 617 && mouseX <= 722 && mouseY >= 649 && mouseY <= 655){
      officedoor2 = true;
      b4 = millis();
    }
    else if(mouseX >= 924 && mouseX <= 1027 && mouseY >= 649 && mouseY <=655){
      bigofficedoor = true;
      b5 = millis();
    }
    else if(mouseX >= 1047 && mouseX <= 1053 && mouseY >= 506 && mouseY <= 608){
      bathroomdoor1 = true;
      b6 = millis();
    }
    else if(mouseX >= 1047 && mouseX <= 1053 && mouseY >= 348 && mouseY <= 453){
      bathroomdoor2 = true;
      b7 = millis();
    }
    else if(mouseX >= 774 && mouseX <= 938 && mouseY >= 302 && mouseY <= 308){
      confdoor = true;
      b8 = millis();
    }
    
}
