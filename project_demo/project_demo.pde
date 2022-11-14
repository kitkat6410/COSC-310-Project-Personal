 //<>// //<>// //<>// //<>//
import java.io.FileWriter;
import java.net.Socket;
import java.io.DataInputStream;
import java.net.ServerSocket;
import com.opencsv.CSVWriter;
import java.util.ArrayList;
import java.util.List;


PImage bg;
color g;
color r;
color y;
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
boolean bathroomdoor2;
boolean bathroomdoor1;
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
String mes;







void setup() {
  size(1280, 1038);
  bg = loadImage("Office_Building_Layout_Final.png");
  g = color(#008631);
  r = color(#FF0000);
  y = color(#FFBF00);
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

  //ServerMethod("CONFERENCE,1,true"); //<>//
  ServerMethod("FIRE");
//ServerMethod("INTRUDER");
}




void draw() {

  thread("server");

  a = millis();//a - b# = how much time has passed since a certain door has been clicked //<>//
  
  if((!(mes.equals("FIRE")))&&(!(mes.equals("INTRUDER")))) {

  if (a-b1 >= 3000) {  //b1 is specific to the main door. After 3000 milliseconds, the door will turn red.
    maindoor = false;
    col_main = r;
    if (a-b2 >= 3000) { //conferencedoor
      confdoor = false;
      col_conference = r;
    }
    if (a-b3 >= 3000) { //bathroom1
      bathroomdoor1 = false;
      col_bathroom_1 = r;
    }
    if (a-b4 >= 3000) { //bathroom2
      bathroomdoor2 = false;
      col_bathroom_2 = r;
    }
    if (a-b5 >= 3000) { //ceo office
      bigofficedoor = false;
      col_big_office = r;
    }
    if (a-b6 >= 3000) { //office2
      officedoor1 = false;
      col_office_1 = r;
    }
    if (a-b7 >= 3000) { //office1
      officedoor2 = false;
      col_office_2 = r;
    }
    if (a-b8>= 3000) { //kitchen
      kitchendoor = false;
      col_kitchen = r;
    }
  }
  }

  background(bg);
  strokeWeight(6);
  stroke(col_main);
  line(150, 168, 336, 168);    // main entrance line
  stroke(col_kitchen);
  line(68, 652, 172, 652);        // kitchen line
  stroke(col_office_1);
  line(617, 652, 722, 652);    // office 1 line
  stroke(col_office_2);
  line(316, 652, 416, 652);    // office 2 line
  stroke(col_big_office);
  line(924, 652, 1027, 652);   // big office line
  stroke(col_bathroom_1);
  line(1050, 348, 1050, 453);  // bathroom 1 line
  stroke(col_bathroom_2);
  line(1050, 506, 1050, 608);  // bathroom 2 line
  stroke(col_conference);
  line(774, 305, 938, 305);    // conference room line


  if (maindoor) { //if the main door access has been authorized, the color of the door will turn green
    col_main = g;
  }
  if (kitchendoor) {
    col_kitchen = g;
  }

  if (officedoor2) {
    col_office_2 = g;
  }

  if (officedoor1) {
    col_office_1 = g;
  }

  if (bigofficedoor) {
    col_big_office = g;
  }

  if (bathroomdoor2) {
    col_bathroom_2 = g;
  }

  if (bathroomdoor1) {
    col_bathroom_1 = g;
  }

  if (confdoor) {
    col_conference = g;
  }
}

void server() { //connect to AndroidStudio
  DataInputStream dataInputStream = null;
  try(ServerSocket serverSocket = new ServerSocket(4000)) {
    Socket clientSocket = serverSocket.accept();
    System.out.println(clientSocket+" connected\n");
    dataInputStream = new DataInputStream(clientSocket.getInputStream());
    String message;
    while (true) {
      message = dataInputStream.readUTF();
      System.out.println(message);
      ServerMethod(message); //call server method to collect data
    }
  }
  catch (Exception e) {
  }
}
