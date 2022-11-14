public void ServerMethod(String message) {
  mes = message;
  if(message=="FIRE"){
    col_main = g;
 col_kitchen = g;
 col_office_1 = g;
 col_office_2 = g;
 col_big_office = g;
 col_bathroom_1 = g;
 col_bathroom_2 = g;
 col_conference =g;
 androidInfo("FIRE", "", false, "");
    
  }
  else if(message == "INTRUDER"){
        col_main = y;
 col_kitchen = y;
 col_office_1 = y;
 col_office_2 = y;
 col_big_office = y;
 col_bathroom_1 = y;
 col_bathroom_2 = y;
 col_conference =y;
 androidInfo("INTRUDER", "", false, "");
    
  }
  else{
  String[] arr = message.split(",");
  androidInfo(arr[0], arr[1], parseBoolean(arr[2]), "");
  }
}
