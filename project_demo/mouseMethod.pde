void mouseClicked(){
  if(mouseX>=61 && mouseX <=140 && mouseY >=56 && mouseY <=60){
    maindoor = true;
    b = millis();  
  }else if(mouseX>=27 && mouseX <=66 && mouseY>=242 && mouseY <= 244){
    kitchendoor = true;
    b = millis();
    }
}
