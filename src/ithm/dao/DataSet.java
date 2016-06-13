package ithm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import ithm.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class DataSet {

	public static TreeItem<String> getTypeForTreeView() throws Exception {
		TreeItem<String> rootItem = new TreeItem<String>("Wszystkie");
		Connection con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT Type FROM devicetype";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			rootItem.getChildren().add(new TreeItem<String>(rs.getString(1)));
		}
		return rootItem;
	}

	public static ObservableList<String> getAllInternalNrDataSet() throws Exception {
		ObservableList<String> returnList = FXCollections.observableArrayList();
		Connection con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT `InternalNr` FROM `device` ORDER BY  `InternalNr` ASC";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String item = rs.getString(1);
			returnList.add(item);
		}
		return returnList;
	}

	public static ObservableList<String> getAllModelDataSet() throws Exception {
		ObservableList<String> returnList = FXCollections.observableArrayList();
		Connection con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT `Model` FROM `devicemodel`order by model asc";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String item = rs.getString(1);
			returnList.add(item);
		}
		return returnList;
	}

	public static ObservableList<String> getAllStateDataSet() throws Exception {
		ObservableList<String> returnList = FXCollections.observableArrayList();
		Connection con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT `State` FROM `statedic`order by `State` asc";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String item = rs.getString(1);
			returnList.add(item);
		}
		return returnList;
	}

	public static ObservableList<String> getAllSerialNrDataSet() throws Exception {
		ObservableList<String> returnList = FXCollections.observableArrayList();
		Connection con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT `SerialNr` FROM `device` ORDER BY  `SerialNr` ASC";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String item = rs.getString(1);
			returnList.add(item);
		}
		return returnList;
	}

	public static ObservableList<String> getAllOwnerDataSet() throws Exception {
		ObservableList<String> returnList = FXCollections.observableArrayList();
		Connection con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM VUsers";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String item = rs.getString("Jednostka");
			returnList.add(item);
		}
		return returnList;
	}

	public static ObservableList<String> getAllTypeDataSet() throws Exception {
		ObservableList<String> returnList = FXCollections.observableArrayList();
		Connection con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT type FROM deviceType";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String type = rs.getString(1);
			returnList.add(type);
		}
		return returnList;
	}

	public static ObservableList<String> getAllModelTypeFilteredDataSet(String typeName) throws Exception {
		ObservableList<String> returnList = FXCollections.observableArrayList();
		Connection con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT `Model` FROM `devicemodel` where typeId =" + DBConnection.getTypeId(typeName)
				+ " order by model asc";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String item = rs.getString(1);
			returnList.add(item);
		}
		return returnList;
	}

	public static ObservableList<String> getState() throws Exception {
		ObservableList<String> returnList = FXCollections.observableArrayList();
		Connection con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT state FROM statedic where id != 1 ORDER BY  id ASC";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String item = rs.getString(1);
			returnList.add(item);
		}
		return returnList;
	}

	public static ObservableList<String> getUserDataSet() throws Exception {
		ObservableList<String> returnList = FXCollections.observableArrayList();
		Connection con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT `Jednostka` FROM `vusers`";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String item = rs.getString(1);
			returnList.add(item);
		}
		return returnList;
	}

	public static ObservableList<String> getPersonalNrDataSet() throws Exception {
		ObservableList<String> returnList = FXCollections.observableArrayList();
		Connection con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT `Number` FROM `organization` "
				+ "union all SELECT personalNb from employee ";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String item = rs.getString(1);
			returnList.add(item);
		}
		returnList = returnList.sorted();
		return returnList;
	}

	public static ObservableList<String> getStorageDataSet() throws Exception {
		ObservableList<String> returnList = FXCollections.observableArrayList();
		Connection con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT name FROM `organization`";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String item = rs.getString(1);
			returnList.add(item);
		}
		return returnList;
	}

}
