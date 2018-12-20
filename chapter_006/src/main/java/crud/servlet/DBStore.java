package crud.servlet;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBStore implements Store {
    private static final Logger LOG = LogManager.getLogger(DBStore.class.getName());
    private static final BasicDataSource SOURCE = new BasicDataSource();


    private DBStore() {
        Properties props = PropsLoader
                .getPropsLoader()
                .load(DBStore.class, "app.properties");
        SOURCE.setUrl(props.getProperty("url"));
        SOURCE.setUsername(props.getProperty("username"));
        SOURCE.setPassword(props.getProperty("password"));
        SOURCE.setDriverClassName(props.getProperty("driver-class-name"));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    //Создание схемы производится через модуль liquibase.
    public static DBStore getInstance() {
        return DBStoreHolder.INSTANCE;
    }

    @Override
    public int add(User user) {
        int id = user.getId();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO users(name_user,login_user,email_user,date_create)"
                             + "VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS
             )) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getMail());
            statement.setTimestamp(4, Timestamp.valueOf(user.getCreateDate()));
            id = statement.executeUpdate();
            LOG.info(String.format("User with id: %s added in DB", id));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
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
            LOG.info(String.format("User with id: %s Updated!", previous.getId()));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            previous = null;
        }
        return previous;
    }

    @Override
    public User delete(int id) {
        User deleted = this.findById(id);
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM users WHERE ID_USER = ?"
             )) {
            statement.setInt(1, id);
            statement.execute();
            LOG.info(String.format("User: %s \n has been deleted!", deleted.toString()));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return deleted;
    }

    @Override
    public List<User> findAll() {
        List<User> allUsers = new ArrayList();
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * from users"
             )) {
            while (resultSet.next()) {
                allUsers.add(this.getByResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
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
                result = getByResultSet(resultSet);
            }
            LOG.info(String.format("getting user by id: %s", result.toString()));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    private final User getByResultSet(ResultSet set) throws SQLException {
        return new User(
                set.getInt("ID_USER"),
                set.getString("NAME_USER"),
                set.getString("LOGIN_USER"),
                set.getString("EMAIL_USER"),
                set.getTimestamp("DATE_CREATE").toLocalDateTime());
    }

    private static class DBStoreHolder {
        private static final DBStore INSTANCE = new DBStore();
    }
}
