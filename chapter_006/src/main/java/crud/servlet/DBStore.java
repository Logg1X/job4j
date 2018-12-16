package crud.servlet;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();

    private DBStore() {
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/users");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("505132580");
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        this.createTableUSers();
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    private void createTableUSers() {
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users("
                    + "ID_USER serial PRIMARY KEY NOT NULL,"
                    + "NAME_USER VARCHAR(100),"
                    + "LOGIN_USER VARCHAR (100),"
                    + "EMAIL_USER VARCHAR (50),"
                    + "DATE_CREATE timestamp )");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int add(User user) {
        var id = user.getId();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO users(name_user,login_user,email_user,date_create)"
                             + "VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS
             )) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getMail());
            statement.setTimestamp(4, Timestamp.valueOf(user.getCreateDate()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public User update(User user) {
        User previous = this.findById(user.getId());
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE users SET name_user= ?, login_user=?,email_user = ? where id_user = ?"
             )) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getMail());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            previous = null;
        }
        return previous;
    }

    @Override
    public User delete(int id) {
        var deleted = this.findById(id);
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM users WHERE ID_USER = ?"
             )) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    @Override
    public List<User> findAll() {
        var allUsers = new ArrayList();
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * from users"
             )) {
            while (resultSet.next()) {
                allUsers.add(new User(
                        resultSet.getInt("ID_USER"),
                        resultSet.getString("NAME_USER"),
                        resultSet.getString("LOGIN_USER"),
                        resultSet.getString("EMAIL_USER"),
                        resultSet.getTimestamp("DATE_CREATE").toLocalDateTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    @Override
    public User findById(int id) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT  * FROM users WHERE ID_USER = ?"
             )) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                result = new User(resultSet.getInt("ID_USER"),
                        resultSet.getString("NAME_USER"),
                        resultSet.getString("LOGIN_USER"),
                        resultSet.getString("EMAIL_USER"),
                        resultSet.getTimestamp("DATE_CREATE").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
