package address.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import address.model.HardwareModel;
import address.model.HardwareType;
import address.model.Worker;
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

	public static ObservableList<Worker> getOwnerDataSet() throws Exception {
		ObservableList<Worker> returnList = FXCollections.observableArrayList();
		con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "select * from workers order by \"ID\"";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			Worker worker = new Worker();
			worker.setId(rs.getInt("id"));
			worker.setName(rs.getString("name"));
			returnList.add(worker);
		}
		return returnList;
	}

	public static ObservableList<HardwareType> getHardwareTypeDataSet() throws Exception {
		ObservableList<HardwareType> returnList = FXCollections.observableArrayList();
		con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "select * from \"hardwareType\" order by \"id\"";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			HardwareType hardwareType = new HardwareType();
			hardwareType.setId(rs.getInt("id"));
			hardwareType.setTypeName(rs.getString("typeName"));
			returnList.add(hardwareType);
		}
		return returnList;
	}

	public static ObservableList<HardwareModel> getHardwareModelDataSet() throws Exception {
		ObservableList<HardwareModel> returnList = FXCollections.observableArrayList();
		con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String query = "select * from \"hardwareModel\" order by \"id\"";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			HardwareModel hardwareModel = new HardwareModel();
			hardwareModel.setId(rs.getInt("id"));
			hardwareModel.setModelName(rs.getString("modelName"));
			returnList.add(hardwareModel);
		}
		return returnList;
	}

}
