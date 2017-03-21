package test_store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ConnectionManager {

    private static int idCount = 1;

    private Connection connection;

    private ResultSet resultSet;

    private PreparedStatement createRow;

    private PreparedStatement readData;

    private PreparedStatement updateData;

    private PreparedStatement deleteData;

    private Scanner scanner = new Scanner(System.in);

    public ConnectionManager(String driver, String url, String login, String password) {
	try {
	    Class.forName(driver);
	    this.connection = DriverManager.getConnection(url, login, password);
	    this.createRow = connection.prepareStatement(QueryCommands.CREATE.toString());
	    this.readData = connection.prepareStatement(QueryCommands.READ.toString());
	    this.updateData = connection.prepareStatement(QueryCommands.UPDATE.toString());
	    this.deleteData = connection.prepareStatement(QueryCommands.DELETE.toString());
	} catch (SQLException e) {
	    System.out.println("Ошибка SQL");
	} catch (ClassNotFoundException e) {
	    System.out.println("Драйвер не найден!");
	}
    }

    public void addRow() {
	try {
	    createRow.setLong(1, idCount++);
	    createRow.setString(2, scanner.nextLine());
	    createRow.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    public void showTable() {
	this.readData();
	try {
	    while (resultSet.next()) {
		System.out.println(resultSet.getString(2));
	    }
	} catch (SQLException e) {
	    System.out.println("Ошибка SQL");
	    e.printStackTrace();
	} finally {
	    try {
		resultSet.close();
	    } catch (SQLException e) {
		System.out.println("Не удалось закрыть ресурс");
		e.printStackTrace();
	    }
	}

    }

    public void updateData(String name, int id) {
	try {
	    updateData.setString(1, name);
	    updateData.setLong(2, id);
	    updateData.execute();
	} catch (SQLException e) {
	    System.out.println("Ошибка SQL");
	    e.printStackTrace();
	}

    }

    public void deleteData(int id) {
	try {
	    deleteData.setLong(1, id);
	    deleteData.execute();
	} catch (SQLException e) {
	    System.out.println("Ошибка SQL");
	    e.printStackTrace();
	}

    }

    public void closeConnection() {
	try {
	    createRow.close();
	    readData.close();
	    updateData.close();
	    deleteData.close();
	    connection.close();
	    scanner.close();
	} catch (SQLException e) {
	    System.out.println("Не удалось закрыть ресурс");
	    e.printStackTrace();
	}

    }

    public void updateOdd() {
	int i;
	this.readData();
	try {
	    while (resultSet.next()) {
		i = resultSet.getInt(1);
		if ((i % 2) != 0) {
		    this.updateData(scanner.nextLine(), i);
		}
	    }
	} catch (SQLException e) {
	    System.out.println("Ошибка SQL");
	}

    }

    public void deleteDiv(int divider) {
	int i;
	this.readData();
	try {
	    while (resultSet.next()) {
		i = resultSet.getInt(1);
		if ((i % divider) == 0) {
		    this.updateData(scanner.nextLine(), i);
		}
	    }
	} catch (SQLException e) {
	    System.out.println("Ошибка SQL");
	}
    }

    // test
    public void queryWithDivider(QueryCommands query, int divider) {
	int id;
	this.readData();
	try {
	    while (resultSet.next()) {
		id = resultSet.getInt(1);
		if ((id % divider) == 0) {
		    identifQuery(query, id);
		}
	    }
	} catch (SQLException e) {
	    System.out.println("Ошибка SQL");
	}

    }

    private void identifQuery(QueryCommands query, int id) {
	if (query == QueryCommands.UPDATE) {
	    updateData(scanner.nextLine(), id);
	} else if (query == QueryCommands.DELETE) {
	    deleteData(id);
	} else {
	    System.out.println("Не существующая команда!");
	}

    }
    // test end

    private void readData() {
	try {
	    resultSet = readData.executeQuery();
	} catch (SQLException e) {
	    System.out.println("Ошибка SQL");
	    e.printStackTrace();
	}
    }

}
