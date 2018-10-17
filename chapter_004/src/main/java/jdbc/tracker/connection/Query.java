package jdbc.tracker.connection;

public class Query {
    public static final String create_table = "CREATE TABLE IF NOT EXISTS tracker(" + System.lineSeparator()
            + "id serial PRIMARY KEY," + System.lineSeparator()
            + "name VARCHAR(200)," + System.lineSeparator()
            + "description TEXT," + System.lineSeparator()
            + "create_date TIMESTAMP WITHOUT TIME ZONE," + System.lineSeparator()
            + "update_date TIMESTAMP WITHOUT TIME ZONE" + System.lineSeparator()
            + ")";
    public static final String add_item = "INSERT INTO tracker(" + System.lineSeparator()
            + "name," + System.lineSeparator()
            + "description," + System.lineSeparator()
            + "create_date,"+ System.lineSeparator()
            + "update_date"+ System.lineSeparator()
            + ") VALUES (?, ?, ?, ?)";
    public static final String find_by_id = "SELECT * FROM tracker WHERE id=?";
    public static final String edit_item = "UPDATE tracker SET name = ? , description = ?,update_date = ? where id = ?";
}
