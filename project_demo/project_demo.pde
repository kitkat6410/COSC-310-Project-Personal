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
double b1;
double b2;
double b3;
double b4;
double b5;
double b6;
double b7;
double b8;



void setup(){
  //size(484, 390);
 size(1280, 1038);
  bg = loadImage("Office_Building_Layout_Final.png");
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
  if(a-b1 >= 2000){
    maindoor = false;
    col_main = r;
   if(a-b2 >= 2000){
    kitchendoor = false;
    col_kitchen = r;
   }
   if(a-b3 >= 2000){
    officedoor1 = false;
    col_office_1 = r;
    
   }
   if(a-b4 >= 2000){
    officedoor2 = false;
    col_office_2 = r;
   }
   if(a-b5 >= 2000){
    bigofficedoor = false;
    col_big_office = r;
   }
   if(a-b6 >= 2000){
    bathroomdoor1 = false;
    col_bathroom_1 = r;
   }
   if(a-b7 >= 2000){
    bathroomdoor2 = false;
    col_bathroom_2 = r;
   }
   if(a-b8>= 2000){
    confdoor = false;
    col_conference = r;
   }

  }
  a = millis();
  background(bg);
  strokeWeight(6);
  stroke(col_main);
  line(160, 168, 200, 168);    // main entrance line, done
  stroke(col_kitchen);
  //line(27,244,66,244);      // kitchen line, done
  stroke(col_office_1);
  //line(121, 244, 157, 244); // office 1 line, done
  stroke(col_office_2);
 // line(233, 244, 273, 244); // office 2 line, done
  stroke(col_big_office);
 // line(348, 244, 387, 244); // big office line, done
  stroke(col_bathroom_1);
 // line(396, 188, 396, 228); // bathroom 1 line, done
  stroke(col_bathroom_2);
 // line(396, 130, 396, 170); // bathroom 2 line, done
  stroke(col_conference);
 // line(279, 114, 355, 114); // conference room line
  if(maindoor){
   col_main = g;
  }
  if(kitchendoor){
    col_kitchen = g;
  }
  
  if(officedoor1){
    col_office_1 = g;
  }

  if(officedoor2){
    col_office_2 = g;
  }
 
  if(bigofficedoor){
    col_big_office = g;
  }
 
  if(bathroomdoor1){
    col_bathroom_1 = g;
  }
 
  if(bathroomdoor2){
    col_bathroom_2 = g;
  }
 
  if(confdoor){
    col_conference = g;
  }

  
}



  
