package test_store;

public class Main {

    public static void main(String[] args) {

	ConnectionManager connectionManager = new ConnectionManager("org.postgresql.Driver", "jdbc:postgresql://localhost/base_store", "postgres", "13183343");

	for (int i = 0; i < 10; i++) {
	    connectionManager.addRow();
	}
	connectionManager.showTable();
	connectionManager.updateOdd();
	System.out.println("$$$$$$$$$$$$$");
	connectionManager.showTable();
	connectionManager.deleteDiv(3);
	System.out.println("$$$$$$$$$$$$$");
	connectionManager.showTable();
	connectionManager.queryWithDivider(QueryCommands.UPDATE, 4);
	System.out.println("$$$$$$$$$$$$$");
	connectionManager.showTable();
	connectionManager.queryWithDivider(QueryCommands.DELETE, 5);
    }

}
