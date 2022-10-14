PImage bg;
int y;
color g;
color r;
color col_main;
color col_kitchen;
color col_office_1;
color col_office_2;
color col_big_office;
color col_bathroom_1;
color col_bathroom_2;
color col_conference;


void setup(){
  size(484, 390);
  bg = loadImage("office_layout_final.png");
  g = color(#008631);
  r = color(#FF0000);
  col_main = r;
  col_kitchen = r;
  col_office_1 = r;
  col_office_2 = r;
  col_big_office = r;
  col_bathroom_1 = r;
  col_bathroom_2 = r;
  col_conference = r;

    
  
}


void draw(){
  background(bg);
  strokeWeight(2);
  stroke(col_main);
  line(61, 58, 140, 58);    // main entrance line
  stroke(col_kitchen);
  line(27,244,66,244);      // kitchen line
  stroke(col_office_1);
  line(121, 244, 157, 244); // office 1 line
  stroke(col_office_2);
  line(233, 244, 273, 244); // office 2 line
  stroke(col_big_office);
  line(348, 244, 387, 244); // big office line
  stroke(col_bathroom_1);
  line(396, 188, 396, 228); // bathroom 1 line
  stroke(col_bathroom_2);
  line(396, 130, 396, 170); // bathroom 2 line
  stroke(col_conference);
  line(279, 114, 355, 114); // conference room line
  if(maindoor()){
    col_main = g;
  }
  

}

public boolean maindoor(){
  if(mouseX>=61 && mouseX<=140 && mouseY == 58){
    return true;
}
  else{
    return false;
  }
}
