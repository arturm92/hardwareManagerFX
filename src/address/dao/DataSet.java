package address.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
}
