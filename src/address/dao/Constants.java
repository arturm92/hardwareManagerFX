package address.dao;

//Change these parameters according to your DB
public class Constants {
	public static String dbClass = "org.postgresql.Driver";
	private static String dbName= "hardwareManager";
	public static String dbUrl = "jdbc:postgresql://127.0.0.1:5432/"+dbName;
	public static String dbUser = "postgres";
	public static String dbPwd = "artur";
}
