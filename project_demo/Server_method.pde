public void ServerMethod(String message) {
  mes = message;
  if (message=="FIRE") { //During FIRE special case, all doors turn green indefinitely
    col_main = g;
    col_kitchen = g;
    col_office_1 = g;
    col_office_2 = g;
    col_big_office = g;
    col_bathroom_1 = g;
    col_bathroom_2 = g;
    col_conference =g;
    androidInfo("FIRE", "", false, ""); //DoorToAccess variable is not relevant for this special case. DATETIME variable will still be calculated in androidInfo
  } else if (message == "INTRUDER") { //During INTRUDER special case, all doors turn yellow indefinitely
    col_main = y;
    col_kitchen = y;
    col_office_1 = y;
    col_office_2 = y;
    col_big_office = y;
    col_bathroom_1 = y;
    col_bathroom_2 = y;
    col_conference =y;
    androidInfo("INTRUDER", "", false, ""); //DoorToAccess variable is not relevant for this special case. DATETIME variable will still be calculated in androidInfo
  } else {
    String[] arr = message.split(","); //android studio client sends all the variables we need (except for DATETIME as a string separated by commas.
    androidInfo(arr[0], arr[1], parseBoolean(arr[2]), ""); //DATETIME IS CALCULATED within androidInfo
  }
}
