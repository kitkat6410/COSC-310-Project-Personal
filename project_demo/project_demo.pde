PImage bg;
int y;
color g;
color r;

void setup(){
  size(484, 390);
  bg = loadImage("office_layout_final.png");
  g = color(#008631);
  r = color(#FF0000);
  
}


void draw(){
  background(bg);
  strokeWeight(2);
  stroke(r);
  line(61, 58, 140, 58);    // main entrance line
  line(27,244,66,244);      // kitchen line
  line(121, 244, 157, 244); // office 1 line
  line(233, 244, 273, 244); // office 2 line
  line(348, 244, 387, 244); // big office line
  line(396, 188, 396, 228); // bathroom 1 line
  line(396, 130, 396, 170); // bathroom 2 line
  line(279, 113, 355, 113);   // conference room line

}
