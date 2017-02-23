package test_store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainTestConnectToDB {

    public static void main(String[] args) throws SQLException {
	
	Connection connection = null;
	
	try {
	    Class.forName("org.postgresql.Driver");
	    connection = DriverManager.getConnection("jdbc:postgresql://localhost/base_store", "postgres", "13183343");
	    InputOutputManager inputOutputManager = new InputOutputManager(connection);
	    for (int i = 0; i < 10; i++) {
		inputOutputManager.insertProductInTable();
	    }
	    inputOutputManager.showTable();
	    inputOutputManager.updateAllOddProductsId();
	    inputOutputManager.showTable();
	    inputOutputManager.deleteDivThreeProductsId();
	    inputOutputManager.showTable();
	} catch (ClassNotFoundException e) {
	    System.out.println("Драйвер небыл найден " + e);
	    return;
	} catch (SQLException e) {
	    System.out.println("Ошибка SQL " + e);
	} finally {
	    connection.close();
	}

    }
    
}
