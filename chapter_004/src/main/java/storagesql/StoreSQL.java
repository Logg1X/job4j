package storagesql;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class StoreSQL implements AutoCloseable {
    private final static Logger LOG = LoggerFactory.getLogger(StoreSQL.class);
    private Connection connection;
    private XmlUsage.Entries entries;


    public StoreSQL(String configConnection) {
        this.connection = setConnection(configConnection);
    }


    private Connection setConnection(String config) {
        Properties properties = new Properties();
        try (InputStream in = StoreSQL.class.getClassLoader().getResourceAsStream(config)) {
            properties.load(in);
            Class.forName(properties.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return connection;
    }

    private void setConnection(File file) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
            Class.forName(properties.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(properties.getProperty("url"));
        } catch (SQLException | ClassNotFoundException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void createTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE entry");
//            statement.executeUpdate("CREATE TABLE IF NOT EXISTS entry(field INTEGER )");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void generateData(int n) {
        try (Statement statement = this.connection.createStatement()) {
            this.createTable();
            connection.setAutoCommit(false);
            for (int i = 1; i <= n; i++) {
                statement.executeUpdate(String.format("INSERT INTO entry VALUES (%s)", i));
            }
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                System.out.println("rollback не удался.");
                LOG.error(e1.getMessage(), e1);
            }
        }
    }

    public List<XmlUsage.Field> selectData() {
        List<XmlUsage.Field> list = new ArrayList<>();
        try (Statement statement = this.connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM entry")) {
            while (resultSet.next()) {
                list.add(new XmlUsage.Field(resultSet.getInt("field")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean connectionIsClose() {
        boolean result = false;
        try {
            if (this.connection.isClosed()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
