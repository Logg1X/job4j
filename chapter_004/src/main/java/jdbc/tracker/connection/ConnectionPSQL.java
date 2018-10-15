package jdbc.tracker.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPSQL {
    /**
     * Путь к БД.
     */
    private final String url = "jdbc:postgresql://localhost:5432/tracker";
    /**
     * Имя пользователя (для подключения к БД).
     */
    private final String user = "postgres";
    /**
     * Пароль (для подключения к БД).
     */
    private final String pass = "505132580";
    /**
     * Соединение с БД.
     */
    private Connection connection;

    /**
     * Конструктор класса.
     */
    public ConnectionPSQL() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    /**
     * Получаем соединение с БД.
     * @return соединение с БД.
     */
    public Connection getConnectionPSQL() {
        return connection;
    }
}
