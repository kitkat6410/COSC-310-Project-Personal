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
boolean maindoor;
boolean kitchendoor;
boolean officedoor1;
boolean officedoor2;
boolean bigofficedoor;
boolean bathroomdoor1;
boolean bathroomdoor2;
boolean confdoor;
double a;
double b;



void setup(){
  size(484, 390);
  bg = loadImage("office_layout_final.png");
  //frameRate(300);
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
  maindoor = false;
  kitchendoor = false;
  officedoor1 = false;
  officedoor2 = false;
  bigofficedoor = false;
  bathroomdoor1 = false;
  bathroomdoor2 = false;
  confdoor = false;


    
  
}


void draw(){ 
  if(a-b >= 2000){
    maindoor = false;
      kitchendoor = false;
  officedoor1 = false;
  officedoor2 = false;
  bigofficedoor = false;
  bathroomdoor1 = false;
  bathroomdoor2 = false;
  confdoor = false;
  }
  a = millis();
  background(bg);
  strokeWeight(2);
  stroke(col_main);
  line(61, 58, 140, 58);    // main entrance line, done
  stroke(col_kitchen);
  line(27,244,66,244);      // kitchen line, done
  stroke(col_office_1);
  line(121, 244, 157, 244); // office 1 line, done
  stroke(col_office_2);
  line(233, 244, 273, 244); // office 2 line, done
  stroke(col_big_office);
  line(348, 244, 387, 244); // big office line, done
  stroke(col_bathroom_1);
  line(396, 188, 396, 228); // bathroom 1 line, done
  stroke(col_bathroom_2);
  line(396, 130, 396, 170); // bathroom 2 line, done
  stroke(col_conference);
  line(279, 114, 355, 114); // conference room line
  if(maindoor){
   col_main = g;
  }
  else{
    col_main = r;
  }
  if(kitchendoor){
    col_kitchen = g;
  }
  else{
    col_kitchen = r;
  }
  if(officedoor1){
    col_office_1 = g;
  }
  else{
    col_office_1 = r;
  }
  if(officedoor2){
    col_office_2 = g;
  }
  else{
    col_office_2 = r;
  }
  if(bigofficedoor){
    col_big_office = g;
  }
  else{
    col_big_office = r;
  }
  if(bathroomdoor1){
    col_bathroom_1 = g;
  }
  else{
    col_bathroom_1 = r;
  }
  if(bathroomdoor2){
    col_bathroom_2 = g;
  }
  else{
    col_bathroom_2 = r;
  }
  if(confdoor){
    col_conference = g;
  }
  else{
    col_conference = r;
  }

  
}



  
