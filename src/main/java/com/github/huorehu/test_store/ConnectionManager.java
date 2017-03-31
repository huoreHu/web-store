package com.github.huorehu.test_store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ConnectionManager {

    private static int idCount = 1;

    private Connection connection;

    private PreparedStatement statement;

    private ResultSet resultSet;

    private final String url;

    private final String login;

    private final String password;

    Scanner scanner = new Scanner(System.in);

    public ConnectionManager(final String driver, final String url, final String login, final String password) {
	try {
	    Class.forName(driver);
	} catch (ClassNotFoundException e) {
	    System.out.println("Driver not found!");
	    e.printStackTrace();
	}
	this.url = url;
	this.login = login;
	this.password = password;
    }

    public int addRow() {
	connect(QueryCommands.CREATE);
	try {
	    statement.setLong(1, idCount++);
	    statement.setString(2, scanner.nextLine());
	} catch (SQLException e) {
	    System.out.println("Exception during prepare statement!");
	    e.printStackTrace();
	}
	return updateQuery();

    }

    public int showTable() {
	int rowsCounter = 0;
	readData();
	try {
	    while (resultSet.next()) {
		System.out.println(resultSet.getString(2));
		rowsCounter++;
	    }
	} catch (SQLException e) {
	    System.out.println("SQL Exception!");
	    e.printStackTrace();
	} finally {
	    closeResources();
	}
	return rowsCounter;

    }

    public int updateData(int id, String name) {
	connect(QueryCommands.UPDATE);
	try {
	    statement.setString(1, name);
	    statement.setLong(2, id);
	} catch (SQLException e) {
	    System.out.println("Exception during prepare statement!");
	    e.printStackTrace();
	}
	return updateQuery();

    }

    public boolean deleteData(int id) {
	int countDelete = 0;
	connect(QueryCommands.DELETE);
	try {
	    statement.setLong(1, id);
	    countDelete = statement.executeUpdate();
	} catch (SQLException e) {
	    System.out.println("SQL Exception!");
	    e.printStackTrace();
	} finally {
	    closeResources();
	}
	if (0 == countDelete) {
	    return false;
	} else {
	    return true;
	}

    }

    public boolean closeScanner() {
	if (null == scanner) {
	    scanner.close();
	}
	return true;
    }

    private void readData() {
	connect(QueryCommands.READ);
	try {
	    resultSet = statement.executeQuery();
	} catch (SQLException e) {
	    System.out.println("SQL Exception!");
	    e.printStackTrace();
	}
    }

    private void connect(QueryCommands query) {
	try {
	    connection = DriverManager.getConnection(url, login, password);
	} catch (SQLException e) {
	    System.out.println("Connection don't created!");
	    e.printStackTrace();
	}
	try {
	    statement = connection.prepareStatement(query.toString());
	} catch (SQLException e) {
	    System.out.println("Statement don't created!");
	    e.printStackTrace();
	}

    }

    private boolean closeResources() {
	boolean allResourceClosed = true;
	if (statement != null) {
	    try {
		statement.close();
	    } catch (SQLException e) {
		System.out.println("Statement don't closed!");
		e.printStackTrace();
		allResourceClosed = false;
	    }
	}

	if (connection != null) {
	    try {
		connection.close();
	    } catch (SQLException e) {
		System.out.println("Connection don't closed!");
		e.printStackTrace();
		allResourceClosed = false;
	    }
	}

	return allResourceClosed;

    }

    private int updateQuery() {
	int rowsUpdated = 0;
	try {
	    rowsUpdated = statement.executeUpdate();
	} catch (SQLException e) {
	    System.out.println("SQL Exception!");
	    e.printStackTrace();
	} finally {
	    closeResources();
	}
	return rowsUpdated;
    }

}
