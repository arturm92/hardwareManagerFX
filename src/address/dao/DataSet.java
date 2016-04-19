package address.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSet {

	private static Connection con;

	public static ObservableList<String> getCategoryDataSet() throws Exception {
		ObservableList<String> returnList = FXCollections.observableArrayList();
		con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "select \"VALUE\" from helpers where \"DOMAIN\" = 'HARDWARE_CATEGORY'";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			returnList.add(rs.getString(1));
		}
		return returnList;
	}

	public static ObservableList<String> getOwnerDataSet() throws Exception {
		ObservableList<String> returnList = FXCollections.observableArrayList();
		con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "select \"ID\" from workers";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			returnList.add(rs.getString(1));
		}
		return returnList;
	}

	public static Map<String,String> getCategoryDataSetMap() throws Exception {
		Map<String,String> tmp = new HashMap<String, String>();
		con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "select \"VALUE\" , \"ID\" from helpers where \"DOMAIN\" = 'HARDWARE_CATEGORY'";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			tmp.put(rs.getString(1), rs.getString(2));
		}
		return tmp;
	}

}
