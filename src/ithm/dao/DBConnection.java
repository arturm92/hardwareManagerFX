package ithm.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import ithm.model.Company;
import ithm.model.HardwareDelivery;
import ithm.model.HardwareDeviceDelivery;
import ithm.model.HardwareDeviceToGive;
import ithm.model.HardwareHistory;
import ithm.model.HardwareView;
import ithm.model.User;
import ithm.model.Worker;
import ithm.tmp.HardwareItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBConnection {

	private static Connection con;

	@SuppressWarnings("finally")
	public static Connection createConnection2() throws Exception {
		try {
			// Class.forName(Constants.dbClass);
			con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPwd);
		} catch (Exception e) {
			throw e;
		} finally {
			return con;
		}
	}

	@SuppressWarnings("finally")
	public static Connection createConnection() throws Exception {
		try {
			Class.forName(Constants.dbClass);
			String url = "jdbc:mysql://localhost:3306/p481579_ithm?characterEncoding=utf8";
			String username = "test2";
			String password = "";

			/*
			 * String connectionString =
			 * "jdbc:sqlserver://ithm.database.windows.net:1433;" +
			 * "databaseName=ITHardwareManager;" + "user=superacc@ithm;" +
			 * "password=OliviaArtur123;" + "encrypt=true;" +
			 * "trustServerCertificate=false;" +
			 * "hostNameInCertificate=*.database.windows.net;" +
			 * "loginTimeout=30;";
			 */
			// con = DriverManager.getConnection(connectionString);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			throw e;
		} finally {
			return con;
		}
	}

	/*
	 * public static ObservableList<Worker> initWorkersData() throws Exception {
	 * 
	 * ObservableList<Worker> returnList = FXCollections.observableArrayList();
	 * if (con == null) { con = createConnection(); } Statement stmt =
	 * con.createStatement(); String query =
	 * "select * from workers order by \"ID\""; ResultSet rs =
	 * stmt.executeQuery(query); while (rs.next()) { Worker worker = new
	 * Worker(); worker.setId(rs.getInt("id"));
	 * worker.setName(rs.getString("name"));
	 * worker.setSection(rs.getInt("dzial"));
	 * worker.setMasterSection(rs.getInt("oddzial"));
	 * worker.setJob(rs.getInt("stanowisko"));
	 * worker.setAddress(rs.getInt("adres"));
	 * worker.setRoom(rs.getInt("nr_pokoju")); returnList.add(worker); } return
	 * returnList; }
	 */

	public static ObservableList<HardwareView> getHardwareDeviceData() throws Exception {
		ObservableList<HardwareView> returnList = FXCollections.observableArrayList();
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM VUserDevices";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			HardwareView item = new HardwareView();
			item.setNum(rs.getRow());
			item.setInternalNumber(rs.getString("NrWewnetrzny"));
			item.setModel(rs.getString("Model"));
			item.setSerialNumber(rs.getString("NrSeryjny"));
			item.setStorage(rs.getString("Jednostka"));
			item.setState(rs.getString("Stan"));
			item.setDate(rs.getString("Data"));
			item.setType(rs.getString("Typ"));
			returnList.add(item);
		}
		return returnList;
	}

	public static ObservableList<HardwareView> getHardwareDeviceDataForUserFilteredByType(String type, Worker worker)
			throws Exception {
		ObservableList<HardwareView> returnList = FXCollections.observableArrayList();
		String query;
		if (con == null) {
			con = createConnection();
		}
		String owner = worker.getLastName() + ", " + worker.getFirstName();
		Statement stmt = con.createStatement();
		if (type.equals("Wszystkie")) {
			query = "SELECT * FROM VUserDevices WHERE Jednostka LIKE '" + owner + "'";
		} else {
			query = "SELECT * FROM VUserDevices where Typ LIKE '" + type + "' and Jednostka LIKE '" + owner + "'";
		}
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			HardwareView item = new HardwareView();
			item.setNum(rs.getRow());
			item.setInternalNumber(rs.getString("NrWewnetrzny"));
			item.setModel(rs.getString("Model"));
			item.setSerialNumber(rs.getString("NrSeryjny"));
			item.setStorage(rs.getString("Jednostka"));
			item.setState(rs.getString("Stan"));
			item.setDate(rs.getString("Data"));
			item.setType(rs.getString("Typ"));
			returnList.add(item);
		}
		return returnList;
	}

	public static ObservableList<HardwareView> getHardwareDeviceDataFilteredByType(String type) throws Exception {
		ObservableList<HardwareView> returnList = FXCollections.observableArrayList();
		String query;
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		if (type.equals("Wszystkie")) {
			query = "SELECT * FROM VUserDevices";
		} else {
			query = "SELECT * FROM VUserDevices where Typ LIKE '" + type + "'";
		}
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			HardwareView item = new HardwareView();
			item.setNum(rs.getRow());
			item.setInternalNumber(rs.getString("NrWewnetrzny"));
			item.setModel(rs.getString("Model"));
			item.setSerialNumber(rs.getString("NrSeryjny"));
			item.setStorage(rs.getString("Jednostka"));
			item.setState(rs.getString("Stan"));
			item.setDate(rs.getString("Data"));
			item.setType(rs.getString("Typ"));
			returnList.add(item);
		}
		return returnList;
	}

	public static ObservableList<Worker> getAllWorkers() throws Exception {
		ObservableList<Worker> returnList = FXCollections.observableArrayList();
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM Employee";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			Worker worker = new Worker();
			worker.setId(rs.getInt("id"));
			worker.setFirstName(rs.getString("firstName"));
			worker.setLastName(rs.getString("lastName"));
			worker.setEmail(rs.getString("email"));
			worker.setPassword(rs.getString("password"));
			worker.setPersonalNumber(rs.getString("personalNb"));
			worker.setRoleId(rs.getInt("roleId"));
			worker.setOrganisationId(rs.getInt("organizationId"));
			returnList.add(worker);
		}
		return returnList;
	}

	public static Worker getWorker(String personalNumber) throws Exception {

		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM Employee where personalNb ='" + personalNumber + "'";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			Worker worker = new Worker();
			worker.setId(rs.getInt("id"));
			worker.setFirstName(rs.getString("firstName"));
			worker.setLastName(rs.getString("lastName"));
			worker.setEmail(rs.getString("email"));
			worker.setPassword(rs.getString("password"));
			worker.setPersonalNumber(rs.getString("personalNb"));
			worker.setRoleId(rs.getInt("roleId"));
			worker.setOrganisationId(rs.getInt("organizationId"));
			return worker;
		}
		return null;
	}

	public static ObservableList<HardwareView> getMyHardwareDeviceData(Worker worker) throws Exception {
		ObservableList<HardwareView> returnList = FXCollections.observableArrayList();
		String owner;
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		if (worker.getLastName() == null) {
			owner = worker.getFirstName();
		} else {
			owner = worker.getLastName() + ", " + worker.getFirstName();
		}
		String query = "SELECT * FROM VUserDevices WHERE Jednostka LIKE '" + owner + "'";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			HardwareView item = new HardwareView();
			item.setNum(rs.getRow());
			item.setInternalNumber(rs.getString("NrWewnetrzny"));
			item.setModel(rs.getString("Model"));
			item.setSerialNumber(rs.getString("NrSeryjny"));
			item.setStorage(rs.getString("Jednostka"));
			item.setState(rs.getString("Stan"));
			item.setDate(rs.getString("Data"));
			item.setType(rs.getString("Typ"));
			returnList.add(item);
		}
		return returnList;
	}
	
	public static ObservableList<HardwareView> getMyHardwareDeviceData2(Worker worker) throws Exception {
		ObservableList<HardwareView> returnList = FXCollections.observableArrayList();
		String owner;
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		if (worker.getLastName() == null) {
			owner = worker.getFirstName();
		} else {
			owner = worker.getLastName() + ", " + worker.getFirstName();
		}
		String query = "SELECT * FROM Vdevicetree WHERE Jednostka LIKE '" + owner + "'";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			HardwareView item = new HardwareView();
			item.setNum(rs.getRow());
			item.setInternalNumber(rs.getString("NrWewnetrzny"));
			item.setModel(rs.getString("Model"));
			item.setSerialNumber(rs.getString("NrSeryjny"));
			item.setStorage(rs.getString("Jednostka"));
			item.setState(rs.getString("Stan"));
			item.setDate(rs.getString("Data"));
			item.setType(rs.getString("Typ"));
			returnList.add(item);
		}
		return returnList;
	}

	public static ObservableList<HardwareHistory> getMyHardwareHistoryData(Worker worker) throws Exception {
		ObservableList<HardwareHistory> returnList = FXCollections.observableArrayList();
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM VMyHistory WHERE Jednostka LIKE '" + worker.getLastName() + ", "
				+ worker.getFirstName() + "';";
		ResultSet rs = stmt.executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i < columnsNumber; i++) {
				System.out.println(rsmd.getColumnName(i) + rs.getString(i));
			}
			HardwareHistory item = new HardwareHistory();
			item.setNum(rs.getRow());
			item.setInternalNumber(rs.getString("NrWewnetrzny"));
			item.setModel(rs.getString("Model"));
			item.setSerialNumber(rs.getString("NrSeryjny"));
			item.setType(rs.getString("Typ"));
			item.setEvent(rs.getString("Zdarzenie"));
			item.setDate(rs.getString("Data") + " " + rs.getString("Godzina"));
			returnList.add(item);
		}
		return returnList;
	}

	public static ObservableList<HardwareHistory> getDeviceData(Worker worker) throws Exception {
		ObservableList<HardwareHistory> returnList = FXCollections.observableArrayList();
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM Device";
		ResultSet rs = stmt.executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i < columnsNumber; i++) {
				System.out.println(rsmd.getColumnName(i) + rs.getString(i));
			}
			HardwareHistory item = new HardwareHistory();
			/*
			 * item.setNum(rs.getRow());
			 * item.setInternalNumber(rs.getString("NrWewnetrzny"));
			 * item.setModel(rs.getString("Model"));
			 * item.setSerialNumber(rs.getString("NrSeryjny"));
			 * item.setType(rs.getString("Typ"));
			 * item.setEvent(rs.getString("Zdarzenie"));
			 * item.setDate(rs.getString("Data") + " " +
			 * rs.getString("Godzina"));
			 */
			returnList.add(item);
		}
		return returnList;
	}

	public static ObservableList<User> getUserData() throws Exception {
		ObservableList<User> returnList = FXCollections.observableArrayList();
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM VUsers";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			User item = new User();
			item.setNum(rs.getRow());
			item.setPersonalNumber(rs.getString("Nr"));
			item.setStorage(rs.getString("Dzial"));
			item.setUserName(rs.getString("Jednostka"));
			item.setUserDevices(rs.getString("Urzadzenia"));
			returnList.add(item);
		}
		return returnList;
	}

	public static ObservableList<HardwareDelivery> getDelivetyData() throws Exception {
		ObservableList<HardwareDelivery> returnList = FXCollections.observableArrayList();
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM Delivery";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			HardwareDelivery item = new HardwareDelivery();
			item.setNum(rs.getInt("Id"));
			item.setCompany(rs.getString("Company"));
			item.setInvoice(rs.getString("Invoice"));
			item.setUser(rs.getString("ModUser"));
			item.setDate(rs.getString("Date"));
			item.setCompleted("");
			returnList.add(item);
		}
		return returnList;
	}

	public static ObservableList<HardwareDeviceDelivery> getDelivetyDevicesData(int deliveryId) throws Exception {
		ObservableList<HardwareDeviceDelivery> returnList = FXCollections.observableArrayList();
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		Statement stmtInternal = con.createStatement();
		String query = "SELECT * FROM v2d where ddDeliveryId = " + deliveryId;
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			HardwareDeviceDelivery item = new HardwareDeviceDelivery();
			item.setNum(rs.getInt("ddId"));
			item.setDeliveryId(rs.getInt("ddDeliveryId"));
			item.setDeviceId(rs.getInt("ddDeviceId"));
			item.setCost(rs.getDouble("ddCost"));
			item.setInternalNumber(rs.getString("dInternalNr"));
			item.setSerialNumber(rs.getString("dSerialNr"));
			item.setNote(rs.getString("dNote"));
			item.setModel(rs.getString("dmModel"));
			item.setType(rs.getString("dtType"));
			
			String query2 = "SELECT * FROM device where id = " + item.getNum();
			ResultSet rs2 = stmtInternal.executeQuery(query2);
			if (rs2.next()) {
				item.setAdded("Tak");
			}else{
				item.setAdded("Nie");
			}
			returnList.add(item);
		}
		return returnList;
	}

	public static ObservableList<HardwareDeviceToGive> getDevicesToGiveData() throws Exception {
		ObservableList<HardwareDeviceToGive> returnList = FXCollections.observableArrayList();
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM vdevicetree";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			HardwareDeviceToGive item = new HardwareDeviceToGive();
			item.setNum(rs.getRow());
			item.setNote(rs.getString("Notatki"));
			item.setState(rs.getString("Stan"));
			item.setDateDay(rs.getString("Data"));
			item.setDateHours(rs.getString("Godzina"));
			item.setInternalNumber(rs.getString("NrWewnetrzny"));
			item.setSerialNumber(rs.getString("NrSeryjny"));
			item.setModel(rs.getString("Model"));
			item.setType(rs.getString("Typ"));
			item.setStorage(rs.getString("Jednostka"));
			returnList.add(item);
		}
		return returnList;
	}
	
	public static ObservableList<HardwareDeviceToGive> getDevicesToGiveData(String where) throws Exception {
		ObservableList<HardwareDeviceToGive> returnList = FXCollections.observableArrayList();
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM vdevicetree " + where;
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			HardwareDeviceToGive item = new HardwareDeviceToGive();
			item.setNum(rs.getRow());
			item.setNote(rs.getString("Notatki"));
			item.setState(rs.getString("Stan"));
			item.setDateDay(rs.getString("Data"));
			item.setDateHours(rs.getString("Godzina"));
			item.setInternalNumber(rs.getString("NrWewnetrzny"));
			item.setSerialNumber(rs.getString("NrSeryjny"));
			item.setModel(rs.getString("Model"));
			item.setType(rs.getString("Typ"));
			item.setStorage(rs.getString("Jednostka"));
			returnList.add(item);
		}
		return returnList;
	}

	public static int giveUserDevice(HardwareDeviceToGive device, User user, Worker loggedUser, String note) {
		try {
			if (con == null) {
				con = createConnection();
			}
			String query = "";
			
			Date data = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(data);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1; // Jan = 0, dec = 11
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int min = calendar.get(Calendar.MINUTE);
			int sek = calendar.get(Calendar.SECOND);
			String[] userInfo = getUserId(user).split("#");
			int userId = Integer.valueOf(userInfo[0]);
			String typeOfUser = userInfo[1];
			String eventId;
			if (device.getStorage().equalsIgnoreCase("Sekcja ServiceDesk")){
				eventId = "1";
			}else{
				eventId = "2";
			}
			int deviceId = getDeviceId(device);
			
			Statement stmt = con.createStatement();
			if (typeOfUser.equals("E")){
				query = "INSERT INTO devicehistory(DeviceId, EventTypeId, StateId,"
						+ " OwnerEId, OwnerOId, OldOwnerEId, OldOwnerOId, Date, Note, ModUser,ModDate) VALUES (	" + deviceId
						+ ","+eventId+",1," + userId + ",null,null,null,STR_TO_DATE('" + day + "/" + month + "/"
						+ year + " " + hour + ":" + min + ":" + sek + "', '%d/%m/%Y %h:%i:%s'),'"+note+"'," + loggedUser.getId() + ",STR_TO_DATE('" + day + "/" + month + "/"
						+ year + " " + hour + ":" + min + ":" + sek + "', '%d/%m/%Y %h:%i:%s'))";
			}else{
				query = "INSERT INTO devicehistory(DeviceId, EventTypeId, StateId,"
						+ " OwnerEId, OwnerOId, OldOwnerEId, OldOwnerOId, Date, Note, ModUser,ModDate) VALUES (	" + deviceId
						+ ","+eventId+",1,null," + userId + ",null,null,STR_TO_DATE('" + day + "/" + month + "/"
						+ year + " " + hour + ":" + min + ":" + sek + "', '%d/%m/%Y %h:%i:%s'),'"+note+"'," + loggedUser.getId() + ",STR_TO_DATE('" + day + "/" + month + "/"
						+ year + " " + hour + ":" + min + ":" + sek + "', '%d/%m/%Y %h:%i:%s'))";
			}
			
			int rs = stmt.executeUpdate(query);
			if (rs > 0) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	private static int getDeviceId(HardwareDeviceToGive device) throws Exception {

		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM Device where InternalNr='" + device.getInternalNumber() + "'";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			return rs.getInt("id");
		}
		return 0;

	}

	private static String getUserId(User user) {
		try {
			if (con == null) {
				con = createConnection();
			}
			String value = "";
			Statement stmt = con.createStatement();
			String query = "Select * from Employee where PersonalNb=" + user.getPersonalNumber();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				value = rs.getString("id");
			}
			if (value.isEmpty()) {
				query = "Select * from Organization where Number=" + user.getPersonalNumber();
				ResultSet rs2 = stmt.executeQuery(query);
				while (rs2.next()) {
					value = rs2.getString("id")+"#O";
				}
			}else{
				value +="#E";
			}
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static ObservableList<HardwareView> getFilteredHardwareDeviceData(Worker worker,String where) throws Exception {
		ObservableList<HardwareView> returnList = FXCollections.observableArrayList();
		String owner;
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		if (worker.getLastName() == null) {
			owner = worker.getFirstName();
		} else {
			owner = worker.getLastName() + ", " + worker.getFirstName();
		}
		String query = "SELECT * FROM VUserDevices WHERE Jednostka LIKE '" + owner + "' "+ where;
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			HardwareView item = new HardwareView();
			item.setNum(rs.getRow());
			item.setInternalNumber(rs.getString("NrWewnetrzny"));
			item.setModel(rs.getString("Model"));
			item.setSerialNumber(rs.getString("NrSeryjny"));
			item.setStorage(rs.getString("Jednostka"));
			item.setState(rs.getString("Stan"));
			item.setDate(rs.getString("Data"));
			item.setType(rs.getString("Typ"));
			returnList.add(item);
		}
		return returnList;
	}
	
	public static ObservableList<HardwareView> getFilteredHardwareDeviceData(String where) throws Exception {
		ObservableList<HardwareView> returnList = FXCollections.observableArrayList();
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM VUserDevices "+ where;
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			HardwareView item = new HardwareView();
			item.setNum(rs.getRow());
			item.setInternalNumber(rs.getString("NrWewnetrzny"));
			item.setModel(rs.getString("Model"));
			item.setSerialNumber(rs.getString("NrSeryjny"));
			item.setStorage(rs.getString("Jednostka"));
			item.setState(rs.getString("Stan"));
			item.setDate(rs.getString("Data"));
			item.setType(rs.getString("Typ"));
			returnList.add(item);
		}
		return returnList;
	}
	
	public static ObservableList<HardwareHistory> getMyHardwareFilteredHistoryData(Worker worker, String where) throws Exception {
		ObservableList<HardwareHistory> returnList = FXCollections.observableArrayList();
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM VMyHistory WHERE Jednostka LIKE '" + worker.getLastName() + ", "
				+ worker.getFirstName() + "' "+ where;
		ResultSet rs = stmt.executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i < columnsNumber; i++) {
				System.out.println(rsmd.getColumnName(i) + rs.getString(i));
			}
			HardwareHistory item = new HardwareHistory();
			item.setNum(rs.getRow());
			item.setInternalNumber(rs.getString("NrWewnetrzny"));
			item.setModel(rs.getString("Model"));
			item.setSerialNumber(rs.getString("NrSeryjny"));
			item.setType(rs.getString("Typ"));
			item.setEvent(rs.getString("Zdarzenie"));
			item.setDate(rs.getString("Data") + " " + rs.getString("Godzina"));
			returnList.add(item);
		}
		return returnList;
	}

}
