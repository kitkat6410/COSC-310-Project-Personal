void mouseClicked(){
  if(mouseX>=61 && mouseX <=140 && mouseY >=56 && mouseY <=60){
    maindoor = true;
    b = millis();  
  }else if(mouseX>=27 && mouseX <=66 && mouseY>=242 && mouseY <= 246){
    kitchendoor = true;
    b = millis();
    }
    else if(mouseX>=121 && mouseX <= 157 && mouseY >=242 && mouseY <= 246){
      officedoor1 = true;
      b = millis();
    }
    else if(mouseX >= 233 && mouseX <= 273 && mouseY >= 242 && mouseY <= 246){
      officedoor2 = true;
      b = millis();
    }
    else if(mouseX >= 348 && mouseX <= 387 && mouseY >= 242 && mouseY <=246){
      bigofficedoor = true;
      b = millis();
    }
    else if(mouseX >= 394 && mouseX <= 398 && mouseY >= 188 && mouseY <= 228){
      bathroomdoor1 = true;
      b = millis();
    }
    else if(mouseX >= 394 && mouseX <= 398 && mouseY >= 130 && mouseY <= 170){
      bathroomdoor2 = true;
      b = millis();
    }
    else if(mouseX >= 279 && mouseX <= 355 && mouseY >= 112 && mouseY <= 116){
      confdoor = true;
      b = millis();
    }
    
}
