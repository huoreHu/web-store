package test_store;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InputOutputManager {
    
    private Scanner scanner = new Scanner(System.in);
    
    private Executor executor;
    
    private static int idCount;
    
    private ResultSet resultSet;
    
    public InputOutputManager(Connection connection) throws SQLException {
	this.executor = new Executor(connection);
	
	idCount = executor.getLastIndexInTable() + 1;
    }
    
    public int insertProductInTable() throws SQLException {
    	StringBuilder updateDB = new StringBuilder()
    		.append("INSERT INTO items (id, name) VALUES (")
    		.append(idCount)
    		.append(", '")
    		.append(scanner.nextLine())
    		.append("')");
    	idCount++;
	return executor.execUpdate(updateDB.toString());
        
    }

    public void updateAllOddProductsId() throws SQLException {
	int tempResultId;
	getValuesAllTable();
	while (resultSet.next()) {
	    if (0 == (tempResultId = resultSet.getInt("id")) % 2) {
        	StringBuilder updateDB = new StringBuilder()
        		.append("UPDATE items SET name = '")
        		.append(scanner.nextLine())
        		.append("' WHERE id = ")
        		.append(tempResultId);
        	System.out.println(updateDB);
        	executor.execUpdate(updateDB.toString());
	    }
	    
	}
	
    }
    
    public void deleteDivThreeProductsId() throws SQLException {
	int tempResultId;
	getValuesAllTable();
	while (resultSet.next()) {
	    if (0 == (tempResultId = resultSet.getInt("id")) % 3) {
        	StringBuilder updateDB = new StringBuilder()
        		.append("DELETE FROM items")
        		.append(" WHERE id = ")
        		.append(tempResultId);
        	System.out.println(updateDB);
        	executor.execUpdate(updateDB.toString());
	    }
	    
	}
    }
    
    public void showTable() throws SQLException {
	getValuesAllTable();
        while(resultSet.next()) {
    		System.out.printf("id: %s, Name: %s\n", resultSet.getString(1), resultSet.getString(2));
        }
        
    }
    
    private void getValuesAllTable() throws SQLException {
	resultSet = executor.execQuery("SELECT * FROM items");
    }
    
}
