package test_store;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {

    private final Connection connection;

    private static final int INDEX_COLUMN_NUMBER = 1;

    public Executor(Connection connection) {
	this.connection = connection;
    }

    public int execUpdate(String update) throws SQLException {
	Statement statement = connection.createStatement();
	int countRows = statement.executeUpdate(update);
	statement.close();
	return countRows;
    }

    public ResultSet execQuery(String query) throws SQLException {
	Statement statement = connection.createStatement();
	ResultSet resultSet = statement.executeQuery(query);
	return resultSet;

    }

    public int getLastIndexInTable() throws SQLException {
	ResultSet resultSet = execQuery("SELECT MAX (id) FROM items");
	resultSet.next();
	return resultSet.getInt(INDEX_COLUMN_NUMBER);
    }

}
