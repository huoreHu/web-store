package test_store;

public enum QueryCommands {
    
    CREATE("INSERT INTO items (id, name) values (?, ?)"),
    READ("SELECT * FROM items"),
    UPDATE("UPDATE items SET name = ? WHERE id = ?"),
    DELETE("DELETE FROM items WHERE id = ?");
    
    private final String query;
    
    private QueryCommands(String query) {
	this.query = query;
    }
    
    @Override
    public String toString() {
        return query;
    }

}
