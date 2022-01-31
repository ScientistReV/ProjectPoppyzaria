package Util;

//Serve para converter as datas tanto para sql como para java
public class ConvertDate {
	public static String ConvertToSql(String Data) {
		return Data.substring(6,10) + "-" + Data.substring(3,5) + "-" + Data.substring(0,2);
	}
	
	public static String ConvertToJava(String Data) {
		return Data.substring(8,10) + "/" + Data.substring(5,7) + "/" + Data.substring(0,4);
	}
	
}
