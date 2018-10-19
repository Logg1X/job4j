package jdbc.tracker.connection;

public class Query {
    public static final String CREATE_TABLE_TRACKER = "CREATE TABLE IF NOT EXISTS tracker(" + System.lineSeparator()
            + "id serial PRIMARY KEY," + System.lineSeparator()
            + "name VARCHAR(200)," + System.lineSeparator()
            + "description TEXT," + System.lineSeparator()
            + "create_date TIMESTAMP WITHOUT TIME ZONE," + System.lineSeparator()
            + "update_date TIMESTAMP WITHOUT TIME ZONE" + System.lineSeparator()
            + ")";
    public static final String CREATE_TABLE_COMMENTS = "CREATE TABLE IF NOT EXISTS comments("
            + "id serial PRIMARY KEY," + System.lineSeparator()
            + "message TEXT," + System.lineSeparator()
            + "create_date TIMESTAMP WITHOUT TIME ZONE," + System.lineSeparator()
            + "id_item INTEGER REFERENCES tracker(id))";
    public static final String ADD_ITEM = "INSERT INTO tracker(" + System.lineSeparator()
            + "name," + System.lineSeparator()
            + "description," + System.lineSeparator()
            + "create_date," + System.lineSeparator()
            + "update_date" + System.lineSeparator()
            + ") VALUES (?, ?, ?, ?)";
    public static final String ADD_COMMENT = "INSERT INTO comments(message, create_date, id_item) VALUES (?,?,?)";
    public static final String FIND_BY_ID = "SELECT * FROM tracker WHERE id=?";
    public static final String EDIT_ITEM = "UPDATE tracker SET name = ? , description = ?,update_date = ? where id = ?";
    public static final String DELETE_ITEM = "DELETE FROM tracker WHERE id = ?";
    public static final String DELETE_COMMENT = "DELETE FROM comments WHERE id = ?";
    public static final String GET_ALL_ITEMS = "SELECT * FROM tracker";
    public static final String GET_ALL_COMMENTS = "SELECT * FROM comments WHERE id_item = ?";
    public static final String FIND_BY_NAME = "SELECT * FROM tracker WHERE tracker.name LIKE ?";
}
