package ithm.messages;

public class Messages {

	private static final String insertWorker = "Dodano nowego pracownika";
	private static final String upadteWorker = "Zmieniono dane pracownika";
	private static final String deleteWorker = "Usunieto pracownika";
	private static final String selectCompany = "Wybierz swoje przedsiebiorstwo";
	private static final String selectWorker = "Wybierz pracownika do usuniecia";
	private static final String deleteDevice = "Usunieto sprzet";
	private static final String selectDevice = "Wybierz sprzet do usuniecia";
	private static final String insertDevice = "Dodano nowy sprzet";
	private static final String updateDevice = "Zmieniono dane szczególy szpetu";
	private static final String loginOk = "Zalogowano";
	private static final String loginError = "Blad logowania";

	public String getInsertworker() {
		return insertWorker;
	}

	public String getUpadteworker() {
		return upadteWorker;
	}

	public String getDeleteworker() {
		return deleteWorker;
	}

	public String getSelectcompany() {
		return selectCompany;
	}

	public String getSelectworker() {
		return selectWorker;
	}

	public String getLoginOk() {
		return loginOk;
	}

	public String getLoginError() {
		return loginError;
	}

	public static String getDeleteDevice() {
		return deleteDevice;
	}

	public String getSelectDevice() {
		return selectDevice;
	}

	public String getInsertDevice() {
		return insertDevice;
	}

	public String getUpdateDevice() {
		return updateDevice;
	}

}
