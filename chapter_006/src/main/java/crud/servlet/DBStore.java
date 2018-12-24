package crud.servlet;

import crud.servlet.models.Role;
import crud.servlet.models.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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
        this.createAllRole(Arrays.asList(Role.values()));
    }

    //Создание схемы производится через модуль liquibase.
    public static DBStore getInstance() {
        return DBStoreHolder.INSTANCE;
    }

    private static class DBStoreHolder {
        private static final DBStore INSTANCE = new DBStore();
    }

    @Override
    public int add(User user) {
        int id = user.getId();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO users(name_user,email_user,date_create,id_role)"
                             + "VALUES(?,?,?,?) RETURNING id_user", Statement.RETURN_GENERATED_KEYS
             );
             PreparedStatement st = connection.prepareStatement(
                     "INSERT INTO credentional(login,password,id_user) VALUES (?,?,?)"
             )
        ) {
            connection.setAutoCommit(false);
            if (user.getRole() == null) {
                user.setRole(Role.USER);
            }
            int idRole = this.getRoleId(user.getRole().toString());
            statement.setString(1, user.getName());
            statement.setString(2, user.getMail());
            statement.setTimestamp(3, Timestamp.valueOf(user.getCreateDate()));
            statement.setInt(4, idRole);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            rs.close();
            st.setString(1, user.getLogin());
            st.setString(2, user.getPassword());
            st.setInt(3, id);
            st.executeUpdate();
            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return id;
    }

    @Override
    public User update(User user) {
        User previous = this.findById(user.getId());
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement userStat = connection.prepareStatement(
                     "UPDATE users SET name_user= ?,email_user = ?,id_role=? where id_user = ?"
             );
             PreparedStatement credStat = connection.prepareStatement(
                     "UPDATE credentional SET login=?,password=? where id_user = ?"
             )) {
            connection.setAutoCommit(false);
            userStat.setString(1, user.getName());
            userStat.setString(2, user.getMail());
            userStat.setInt(3, this.getRoleId(user.getRole().toString()));
            userStat.setInt(4, user.getId());
            credStat.setString(1, user.getLogin());
            credStat.setString(2, user.getPassword());
            credStat.setInt(3, user.getId());
            userStat.executeUpdate();
            credStat.executeUpdate();
            try {
                connection.commit();
                LOG.info(String.format("User with id: %s Updated!", previous.getId()));
            } catch (SQLException e) {
                connection.rollback();
                LOG.error(e.getMessage(), e);
            }
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
             PreparedStatement userState = connection.prepareStatement(
                     "DELETE FROM users WHERE ID_USER = ?"
             );
             PreparedStatement credState = connection.prepareStatement(
                     "DELETE from credentional WHERE id_user = ?"
             )) {
            connection.setAutoCommit(false);
            userState.setInt(1, id);
            credState.setInt(1, id);
            credState.execute();
            userState.execute();
            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                LOG.error(e.getMessage(), e);
            }
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
                     "SELECT * from users u join  credentional cred ON u.id_user = cred.id_user join role r on u.id_role = r.id;"
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
                     "select * from users u join  credentional cred ON u.id_user = cred.id_user join role r on u.id_role = r.id Where u.id_user = ?"
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

    public User getByCredentional(String login, String password) {
        User user = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * from users join credentional c2 on users.id_user = c2.id_user join role r on users.id_role = r.id where c2.login = ? and c2.password =?");
        ) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    user = this.getByResultSet(rs);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    private final User getByResultSet(ResultSet set) throws SQLException {

        return new User(set.getInt("ID_USER"),
                set.getString("NAME_USER"),
                set.getString("login"),
                set.getString("EMAIL_USER"),
                set.getString("password"),
                Role.valueOf(set.getString("name_role")),
                set.getTimestamp("DATE_CREATE").toLocalDateTime());
    }

    private void createAllRole(List<Role> roles) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "insert into role(name_role) VALUES (?) ON CONFLICT DO NOTHING"
             )) {
            for (Role role : roles) {
                statement.setString(1, role.toString());
                statement.executeUpdate();
                LOG.info(String.format("Role: %s added in table", role.toString()));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private int getRoleId(String nameRole) {
        int idRole = 0;
        try (Connection connection = SOURCE.getConnection();
             ResultSet resultSet = connection.createStatement().executeQuery(String.format("select id from role WHERE role.name_role like '%s'", nameRole))) {
            resultSet.next();
            connection.setAutoCommit(false);
            idRole = resultSet.getInt(1);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return idRole;
    }

    private List<Role> getListRoles() {
        List<Role> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT name_role from role"
             )) {
            while (resultSet.next()) {
                result.add(Role.valueOf(resultSet.getString("name_role")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
