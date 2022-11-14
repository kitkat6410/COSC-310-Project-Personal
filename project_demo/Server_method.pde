
public void ServerMethod(String message) {
  String[] arr = message.split(",");
  println(arr);
  androidInfo(arr[0], arr[1], parseBoolean(arr[2]), "");
}
