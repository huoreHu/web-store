package com.github.huorehu.test_store;

public class Main {

    public static void main(String[] args) {

	final String driver = "org.postgresql.Driver";

	final String url = "jdbc:postgresql://localhost/base_store";

	final String login = "postgres";

	final String password = "13183343";

	ConnectionManager connectionManager = new ConnectionManager(driver, url, login, password);

	for (int i = 0; i < 5; i++) {
	    connectionManager.addRow();
	}
	connectionManager.updateData(3, "Vasya");
	connectionManager.showTable();
	connectionManager.deleteData(4);
	connectionManager.closeScanner();

    }

}
