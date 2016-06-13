package ithm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import ithm.model.HardwareDelivery;
import ithm.model.HardwareDeviceDelivery;
import ithm.model.HardwareDeviceToGive;
import ithm.model.HardwareHistory;
import ithm.model.HardwareView;
import ithm.model.User;
import ithm.model.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBConnection {

	private static Connection con;

	@SuppressWarnings("finally")
	public static Connection createConnection() throws Exception {
		try {
			Class.forName(Constants.dbClass);
			String url = "jdbc:mysql://localhost:3306/p481579_ithm?characterEncoding=utf8";
			String username = "test2";
			String password = "";
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			throw e;
		} finally {
			return con;
		}
	}


	public static ObservableList<HardwareView> getHardwareDeviceData() throws Exception {
		ObservableList<HardwareView> returnList = FXCollections.observableArrayList();
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM vdevicetree";
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
		String owner;
		if (con == null) {
			con = createConnection();
		}
		if (worker.getLastName() == null) {
			owner = worker.getFirstName();
		} else {
			owner = worker.getLastName() + ", " + worker.getFirstName();
		}
		Statement stmt = con.createStatement();
		if (type.equals("Wszystkie")) {
			query = "SELECT * FROM vdevicetree WHERE Jednostka LIKE '" + owner + "'";
		} else {
			query = "SELECT * FROM vdevicetree where Typ LIKE '" + type + "' and Jednostka LIKE '" + owner + "'";
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
			query = "SELECT * FROM vdevicetree";
		} else {
			query = "SELECT * FROM vdevicetree where Typ LIKE '" + type + "'";
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
		Worker worker;
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM Employee where personalNb ='" + personalNumber + "'";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			worker= new Worker();
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

	public static ObservableList<Worker> getLoggedWorker(String personalNumber) throws Exception {
		ObservableList<Worker> returnList = FXCollections.observableArrayList();
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

			String query2 = "Select name from Organization where Id=" + rs.getInt("organizationId");
			Statement stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery(query2);
			if (rs2.next()) {
				worker.setOrganisationName(rs2.getString("name"));
			}

			String query3 = "Select rola from Rola where Id=" + rs.getInt("roleId");
			Statement stmt3 = con.createStatement();
			ResultSet rs3 = stmt3.executeQuery(query3);
			if (rs3.next()) {
				worker.setRoleName(rs3.getString("rola"));
			}

			returnList.add(worker);
		}
		return returnList;
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
		String query = "SELECT * FROM vdevicetree WHERE Jednostka LIKE '" + owner + "'";
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
		String query = "SELECT * FROM vdevicetree WHERE Jednostka LIKE '" + owner + "'";
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
		String query = "SELECT * FROM VMyHistory WHERE Jednostka LIKE '" + owner + "'";
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
			item.setNum(rs.getRow());
			item.setId(rs.getInt("Id"));
			item.setCompany(rs.getString("Company"));
			item.setInvoice(rs.getString("Invoice"));
			item.setUser(rs.getString("ModUser"));
			item.setDate(rs.getString("Date"));
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
			item.setNum(rs.getRow());
			item.setId(rs.getInt("ddId"));
			item.setDeliveryId(rs.getInt("ddDeliveryId"));
			item.setDeviceId(rs.getInt("ddDeviceId"));
			item.setCost(rs.getString("ddCost"));
			item.setInternalNumber(rs.getString("dInternalNr"));
			item.setSerialNumber(rs.getString("dSerialNr"));
			item.setNote(rs.getString("dNote"));
			item.setModel(rs.getString("dmModel"));
			item.setType(rs.getString("dtType"));

			String query2 = "SELECT * FROM `devicehistory` where deviceId = " + item.getId();
			ResultSet rs2 = stmtInternal.executeQuery(query2);
			if (rs2.next()) {
				item.setAdded("Tak");
			} else {
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
			String[] userInfo = getUserId(user, null).split("#");
			int userId = Integer.valueOf(userInfo[0]);
			String typeOfUser = userInfo[1];
			String eventId;
			int stateId;
			if (device.getStorage().equalsIgnoreCase("Sekcja ServiceDesk")) {
				eventId = "1";
				stateId = 1;
			} else {
				eventId = "2";
				stateId = DBConnection.getStateId(device.getState());
			}
			int deviceId = getDeviceId(device.getInternalNumber());

			Statement stmt = con.createStatement();
			if (typeOfUser.equals("E")) {
				query = "INSERT INTO devicehistory(DeviceId, EventTypeId, StateId,"
						+ " OwnerEId, OwnerOId, OldOwnerEId, OldOwnerOId, Date, Note, ModUser,ModDate) VALUES (	"
						+ deviceId + "," + eventId + "," + stateId + "," + userId + ",null,null,null,STR_TO_DATE('"
						+ day + "/" + month + "/" + year + " " + hour + ":" + min + ":" + sek
						+ "', '%d/%m/%Y %h:%i:%s'),'" + note + "'," + loggedUser.getId() + ",STR_TO_DATE('" + day + "/"
						+ month + "/" + year + " " + hour + ":" + min + ":" + sek + "', '%d/%m/%Y %h:%i:%s'))";
			} else {
				query = "INSERT INTO devicehistory(DeviceId, EventTypeId, StateId,"
						+ " OwnerEId, OwnerOId, OldOwnerEId, OldOwnerOId, Date, Note, ModUser,ModDate) VALUES (	"
						+ deviceId + "," + eventId + "," + stateId + ",null," + userId + ",null,null,STR_TO_DATE('"
						+ day + "/" + month + "/" + year + " " + hour + ":" + min + ":" + sek
						+ "', '%d/%m/%Y %h:%i:%s'),'" + note + "'," + loggedUser.getId() + ",STR_TO_DATE('" + day + "/"
						+ month + "/" + year + " " + hour + ":" + min + ":" + sek + "', '%d/%m/%Y %h:%i:%s'))";
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

	private static int getStateId(String state) throws Exception {
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "SELECT id FROM statedic where state like '" + state + "'";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			return rs.getInt("id");
		}
		return 0;
	}

	private static int getDeviceId(String internalNumber) throws Exception {

		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM Device where InternalNr='" + internalNumber + "'";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			return rs.getInt("id");
		}
		return 0;

	}

	private static String getUserId(User user, Worker worker) {
		try {
			if (con == null) {
				con = createConnection();
			}
			String personalNumber = "";
			String value = "";
			if (user != null) {
				personalNumber = user.getPersonalNumber();
			}
			if (worker != null) {
				personalNumber = worker.getPersonalNumber();
			}
			Statement stmt = con.createStatement();
			String query = "Select * from Employee where PersonalNb=" + personalNumber;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				value = rs.getString("id");
			}
			if (value.isEmpty()) {
				query = "Select * from Organization where Number=" + personalNumber;
				ResultSet rs2 = stmt.executeQuery(query);
				while (rs2.next()) {
					value = rs2.getString("id") + "#O";
				}
			} else {
				value += "#E";
			}
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static ObservableList<HardwareView> getFilteredHardwareDeviceData(Worker worker, String where)
			throws Exception {
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
		String query = "SELECT * FROM VUserDevices WHERE Jednostka LIKE '" + owner + "' " + where;
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
		String query = "SELECT * FROM VUserDevices " + where;
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

	public static ObservableList<HardwareHistory> getMyHardwareFilteredHistoryData(Worker worker, String where)
			throws Exception {
		ObservableList<HardwareHistory> returnList = FXCollections.observableArrayList();
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
		String query = "SELECT * FROM VMyHistory WHERE Jednostka LIKE '" + owner + "' " + where;
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
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

	public static int insertDelivery(HardwareDelivery delivery, Worker worker) {
		try {
			if (con == null) {
				con = createConnection();
			}
			String query = "";

			Date data = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(data);
			String monthStr = "";
			String dayStr = "";
			String hourStr = "";
			String minStr = "";
			int year = calendar.get(Calendar.YEAR);
			Integer month = calendar.get(Calendar.MONTH) + 1;
			if (month < 10) {
				monthStr = "0" + month.toString();
			} else {
				monthStr = month.toString();
			}
			Integer day = calendar.get(Calendar.DAY_OF_MONTH);
			if (day < 10) {
				dayStr = "0" + day.toString();
			} else {
				dayStr = day.toString();
			}
			Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
			if (hour < 10) {
				hourStr = "0" + hour.toString();
			} else {
				hourStr = hour.toString();
			}
			Integer min = calendar.get(Calendar.MINUTE);
			if (calendar.get(Calendar.MINUTE) < 10) {
				minStr = "0" + min.toString();
			} else {
				minStr = min.toString();
			}
			String date = year + monthStr + dayStr + " " + hourStr + minStr;

			String[] userInfo = getUserId(null, worker).split("#");
			int userId = Integer.valueOf(userInfo[0]);

			Statement stmt = con.createStatement();
			query = "INSERT INTO `delivery`(`Date`, `Invoice`, `Company`, `ModUser`, `ModDate`) "
					+ "VALUES (STR_TO_DATE('" + date + "','%Y%m%d %H%i'),'" + delivery.getInvoice() + "','" + delivery.getCompany() + "'," + userId
					+ ",STR_TO_DATE('" + date + "','%Y%m%d %H%i'))";

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

	public static int createNewType(String typeName) {
		try {
			if (con == null) {
				con = createConnection();
			}
			Statement stmt = con.createStatement();
			String query = "INSERT INTO `devicetype`(`Type`) VALUES ('" + typeName + "')";

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

	@SuppressWarnings("finally")
	public static String getTypeId(String typeName) {
		String typeId = "";
		try {
			if (con == null) {
				con = createConnection();
			}
			Statement stmt = con.createStatement();
			String query = "SELECT id FROM `devicetype` WHERE `Type` LIKE '" + typeName + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				typeId = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return typeId;
		}

	}

	public static int createNewModel(String modelName, String typeId) {
		try {
			if (con == null) {
				con = createConnection();
			}
			Statement stmt = con.createStatement();
			String query = "INSERT INTO `devicemodel`(`Model`, `TypeId`) VALUES ('" + modelName + "','" + typeId + "')";

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

	@SuppressWarnings("finally")
	public static String getModelId(String modelName) {
		String modelId = "";
		try {
			if (con == null) {
				con = createConnection();
			}
			Statement stmt = con.createStatement();
			String query = "SELECT `Id` FROM `devicemodel` WHERE `Model` LIKE '" + modelName + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				modelId = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return modelId;
		}

	}

	@SuppressWarnings("finally")
	public static String getNextInternalNumber(String typeName) {

		String nextInternalNumber = "";
		String lastInternalNumber = "";
		try {
			if (con == null) {
				con = createConnection();
			}
			Statement stmt = con.createStatement();
			String query = "SELECT `NrWewnetrzny` FROM `vdeviceshortinfo` where `Typ` LIKE '" + typeName
					+ "' ORDER BY `NrWewnetrzny` DESC";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				lastInternalNumber = rs.getString(1);
			} else {
				lastInternalNumber = "PL-" + typeName.substring(0, 3).toUpperCase() + "0000";
			}
			String[] editInternal = lastInternalNumber.split("-");
			editInternal[0] = 	editInternal[0] + "-" + editInternal[1].substring(0, 3);
			editInternal[1] = editInternal[1].substring(3);
			int number = 1000 * Integer.parseInt(editInternal[1].substring(0, 1))
					+ 100 * Integer.parseInt(editInternal[1].substring(1, 2))
					+ 10 * Integer.parseInt(editInternal[1].substring(2, 3))
					+ Integer.parseInt(editInternal[1].substring(3, 4)) + 1;
			String internalNext = "";
			if (number < 10) {
				internalNext = "000";
			} else if (number < 100) {
				internalNext = "00";
			} else if (number < 1000) {
				internalNext = "0";
			}
			nextInternalNumber = editInternal[0] + internalNext + number;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return nextInternalNumber;
		}

	}

	public static int createNewDevice(HardwareDeviceDelivery device) {
		try {
			if (con == null) {
				con = createConnection();
			}
			Statement stmt = con.createStatement();
			String query = "INSERT INTO `device`(`ModelId`, `SerialNr`, `InternalNr`, `Note`)" + " VALUES ("
					+ device.getModelId() + ",'" + device.getSerialNumber() + "','" + device.getInternalNumber() + "','"
					+ device.getNote() + "')";
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

	public static int addDeviceForDelivery(HardwareDeviceDelivery device, int deliveryId) {
		try {
			if (con == null) {
				con = createConnection();
			}
			int deviceId = getDeviceId(device.getInternalNumber());
			Statement stmt = con.createStatement();
			String query = "INSERT INTO `deliverydevice`(`DeliveryId`, `DeviceId`, `Cost`)" + " VALUES (" + deliveryId
					+ "," + deviceId + "," + device.getCost() + ")";
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

	public static int getDelivetyToSystem(Worker loggedUser, HardwareDeviceDelivery device) {
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
			String[] userInfo = getUserId(null, loggedUser).split("#");
			int userId = Integer.valueOf(userInfo[0]);
			int deviceId = getDeviceId(device.getInternalNumber());

			Statement stmt = con.createStatement();
			query = "INSERT INTO devicehistory(DeviceId, EventTypeId, StateId,"
					+ " OwnerEId, OwnerOId, OldOwnerEId, OldOwnerOId, Date, Note, ModUser,ModDate) VALUES (	" + deviceId
					+ ",3,1,null,4,null,null,STR_TO_DATE('" + day + "/" + month + "/" + year + " " + hour + ":" + min
					+ ":" + sek + "', '%d/%m/%Y %H:%i:%s'),'" + device.getNote() + "'," + userId + ",STR_TO_DATE('"
					+ day + "/" + month + "/" + year + " " + hour + ":" + min + ":" + sek + "', '%d/%m/%Y %H:%i:%s'))";
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

	public static int updateDelivery(HardwareDelivery delivery, Worker worker) {
		try {
			if (con == null) {
				con = createConnection();
			}
			String query = "";

			Date data = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(data);
			String monthStr = "";
			String dayStr = "";
			String hourStr = "";
			String minStr = "";
			int year = calendar.get(Calendar.YEAR);
			Integer month = calendar.get(Calendar.MONTH) + 1;
			if (month < 10) {
				monthStr = "0" + month.toString();
			} else {
				monthStr = month.toString();
			}
			Integer day = calendar.get(Calendar.DAY_OF_MONTH);
			if (day < 10) {
				dayStr = "0" + day.toString();
			} else {
				dayStr = day.toString();
			}
			Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
			if (hour < 10) {
				hourStr = "0" + hour.toString();
			} else {
				hourStr = hour.toString();
			}
			Integer min = calendar.get(Calendar.MINUTE);
			if (calendar.get(Calendar.MINUTE) < 10) {
				minStr = "0" + min.toString();
			} else {
				minStr = min.toString();
			}
			String date = year + monthStr + dayStr + " " + hourStr + minStr;

			String[] userInfo = getUserId(null, worker).split("#");
			int userId = Integer.valueOf(userInfo[0]);

			Statement stmt = con.createStatement();
			query = "UPDATE `delivery` SET `Invoice`='" + delivery.getInvoice() + "', `Company`='" + delivery.getCompany() + "', `ModUser`="
					+ userId + ", `ModDate`=STR_TO_DATE('" + date + "','%Y%m%d %H%i') where id="+delivery.getId();

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


	public static ObservableList<HardwareDeviceDelivery> getDeliveryDevicesDataFiltered(int deliveryId, String where) throws Exception {
		ObservableList<HardwareDeviceDelivery> returnList = FXCollections.observableArrayList();
		if (con == null) {
			con = createConnection();
		}
		Statement stmt = con.createStatement();
		Statement stmtInternal = con.createStatement();
		String query = "SELECT * FROM v2d where ddDeliveryId = " + deliveryId + " " + where;
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			HardwareDeviceDelivery item = new HardwareDeviceDelivery();
			item.setNum(rs.getRow());
			item.setId(rs.getInt("ddId"));
			item.setDeliveryId(rs.getInt("ddDeliveryId"));
			item.setDeviceId(rs.getInt("ddDeviceId"));
			item.setCost(rs.getString("ddCost"));
			item.setInternalNumber(rs.getString("dInternalNr"));
			item.setSerialNumber(rs.getString("dSerialNr"));
			item.setNote(rs.getString("dNote"));
			item.setModel(rs.getString("dmModel"));
			item.setType(rs.getString("dtType"));

			String query2 = "SELECT * FROM `devicehistory` where deviceId = " + item.getId();
			ResultSet rs2 = stmtInternal.executeQuery(query2);
			if (rs2.next()) {
				item.setAdded("Tak");
			} else {
				item.setAdded("Nie");
			}
			returnList.add(item);
		}
		return returnList;
	}

}
