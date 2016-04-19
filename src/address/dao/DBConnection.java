package address.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import address.model.Company;
import address.model.HardwareItem;
import address.model.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBConnection {

	private static Connection con;

	@SuppressWarnings("finally")
	public static Connection createConnection() throws Exception {
		try {
			Class.forName(Constants.dbClass);
			con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPwd);
		} catch (Exception e) {
			throw e;
		} finally {
			return con;
		}
	}

	public static ObservableList<Company> initCompanyData() throws Exception {

		ObservableList<Company> returnList = FXCollections.observableArrayList();
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "select * from company";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			Company company = new Company();
			company.setId(rs.getInt("id"));
			company.setName(rs.getString("name"));
			company.setPassword(rs.getString("password"));
			returnList.add(company);
		}
		return returnList;
	}

	public static ObservableList<Worker> initWorkersData() throws Exception {

		ObservableList<Worker> returnList = FXCollections.observableArrayList();
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "select * from workers order by \"ID\"";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			Worker worker = new Worker();
			worker.setId(rs.getInt("id"));
			worker.setFirstName(rs.getString("imie"));
			worker.setLastName(rs.getString("nazwisko"));
			worker.setSection(rs.getInt("dzial"));
			worker.setMasterSection(rs.getInt("oddzial"));
			worker.setJob(rs.getInt("stanowisko"));
			worker.setAddress(rs.getInt("adres"));
			worker.setRoom(rs.getInt("nr_pokoju"));
			returnList.add(worker);
		}
		return returnList;
	}

	public static ObservableList<HardwareItem> initHardwareItemsData() throws Exception {
		ObservableList<HardwareItem> returnList = FXCollections.observableArrayList();
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "select * from hardware_items_view order by \"ID\"";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			HardwareItem item = new HardwareItem();
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setOwnerId(rs.getInt("owner_id"));
			item.setKitId(rs.getInt("kit_id"));
			item.setCategoryId(rs.getString("category_id"));
			item.setDescription(rs.getString("description"));
			item.setValue(rs.getInt("value"));
			item.setCategoryDesc(rs.getString("category_desc"));
			item.setOwnerDesc(rs.getString("owner_desc"));
			returnList.add(item);
		}
		return returnList;
	}

	public static int insertWorker(Worker newWorker) {
		int record = 0;
		try {
			int seqNumber = 0;
			Statement stmt = con.createStatement();
			String seqQuery = "select nextval('workers_seq')";
			ResultSet rs = stmt.executeQuery(seqQuery);
			while (rs.next()) {
				seqNumber = rs.getInt(1);
			}
			if (seqNumber != 0) {
				String query = "INSERT into workers(\"ID\", \"IMIE\", \"NAZWISKO\", \"DZIAL\",\"STANOWISKO\",\"ODDZIAL\",\"NR_POKOJU\",\"ADRES\") values("
						+ seqNumber + ",'" + newWorker.getFirstName() + "','" + newWorker.getLastName() + "',"
						+ newWorker.getSection() + "," + newWorker.getJob() + "," + newWorker.getMasterSection() + ","
						+ newWorker.getRoom() + "," + newWorker.getAddress() + ")";
				record = stmt.executeUpdate(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;

	}

	public static int updateWorker(Worker worker) {
		int record = 0;
		try {
			Statement stmt = con.createStatement();
			String query = "UPDATE workers set(\"IMIE\", \"NAZWISKO\", \"DZIAL\",\"STANOWISKO\",\"ODDZIAL\",\"NR_POKOJU\",\"ADRES\") = ('"
					+ worker.getFirstName() + "','" + worker.getLastName() + "'," + worker.getSection() + ","
					+ worker.getJob() + "," + worker.getMasterSection() + "," + worker.getRoom() + ","
					+ worker.getAddress() + ") where \"ID\" = " + worker.getId();
			record = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;

	}

	public static int deleteWorker(Worker worker) {
		int record = 0;
		try {
			Statement stmt = con.createStatement();
			String query = "DELETE from workers where \"ID\" = " + worker.getId();
			record = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;

	}

	public static int deleteItem(HardwareItem item) {
		int record = 0;
		try {
			Statement stmt = con.createStatement();
			String query = "DELETE from hardware_items where \"ID\" = " + item.getId();
			record = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;
	}

	public static int insertItem(HardwareItem newItem) {
		int record = 0;
		try {
			int seqNumber = 0;
			Statement stmt = con.createStatement();
			String seqQuery = "select nextval('item_seq')";
			ResultSet rs = stmt.executeQuery(seqQuery);
			while (rs.next()) {
				seqNumber = rs.getInt(1);
			}
			if (seqNumber != 0) {

				String query = "INSERT INTO hardware_items(\"ID\", \"OWNER_ID\", \"KIT_ID\", \"DESCRIPTION\", \"VALUE\", \"CATEGORY_ID\", \"NAME\") VALUES ("
						+ seqNumber + "," + newItem.getOwnerId() + "," + newItem.getKitId() + ","
						+ newItem.getDescription() + "," + newItem.getValue() + ",'" + newItem.getCategoryId() + "','"
						+ newItem.getName() + "')";
				record = stmt.executeUpdate(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;

	}

	public static int updateItem(HardwareItem item) {
		int record = 0;
		try {
			Statement stmt = con.createStatement();
			String query = "UPDATE hardware_items set(\"OWNER_ID\", \"KIT_ID\", \"DESCRIPTION\", \"VALUE\", \"CATEGORY\", \"NAME\") VALUES ("
					+ item.getOwnerId() + "," + item.getKitId() + "," + item.getDescription() + "," + item.getValue()
					+ ",'" + item.getCategoryId() + "','" + item.getName() + ") where \"ID\" = " + item.getId();
			record = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;
	}

	public static ObservableList<String> getWorkersName() throws Exception {
		ObservableList<String> returnList = FXCollections.observableArrayList();
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "select \"IMIE\", \"NAZWISKO\" , \"PASSWORD\" from workers order by \"ID\"";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String firstName = (rs.getString("imie"));
			String lastName = (rs.getString("nazwisko"));
			//String password = (rs.getString("password"));
			String worker = firstName + " " +lastName;// + password;
			returnList.add(worker);
		}
		return returnList;
	}

	public static Worker checkLoginProperties(String user, String password) throws Exception {
		con = DBConnection.createConnection();
		Statement stmt = con.createStatement();
		String[] userData = user.split(" ");
		String query = "select * from workers where \"IMIE\" = '"+userData[0] +"' and \"NAZWISKO\"= '"+userData[1]+"' and  \"PASSWORD\" = '"+password+"'";;
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			Worker worker = new Worker();
			if (rs.getString("id") != null){
				worker.setId(Integer.valueOf(rs.getString("id")));
				worker.setFirstName(rs.getString("imie"));
				worker.setLastName(rs.getString("nazwisko"));
				worker.setRole(Integer.valueOf(rs.getString("rola")));
				return worker;
			}else{
				return null;
			}
		}
		return null;
	}

}
