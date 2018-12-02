package parser.vacancy;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;


public class Dao {

    private static final Logger LOG = LogManager.getLogger(Dao.class.getName());
    private final Connection connection;
    private final Properties sqlQueryProp = new Properties();

    public Dao(String connectPropPath, String queryPropPath) {
        this.connection = setConnection(connectPropPath);
        initSQLQuery(queryPropPath);
        this.createTableVacancy();
        this.createTableDateUpdate();
    }

    private Connection setConnection(String config) {
        Properties properties = new Properties();
        Connection connection = null;
        try (InputStream loadFile = Dao.class.getClassLoader().getResourceAsStream(config)) {
            properties.load(loadFile);
            Class.forName(properties.getProperty("sqlite.driver"));
            connection = DriverManager.getConnection(properties.getProperty("sqlite.db.url"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return connection;
    }

    private void initSQLQuery(String sqlQueryPropPath) {
        try (InputStream in = Dao.class.getClassLoader().getResourceAsStream(sqlQueryPropPath)) {
            sqlQueryProp.load(in);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void insertVacancyInDB(Set<Vacancy> vacancies, String queryName) {
        try (PreparedStatement statement = connection.prepareStatement(sqlQueryProp.getProperty(queryName))) {
            connection.setAutoCommit(false);
            int vacansyCount = 0;
            for (Vacancy vacancy : vacancies) {
                statement.setString(1, vacancy.getTitle());
                statement.setString(2, vacancy.getAuthor());
                statement.setString(3, vacancy.getDate().toString());
                statement.setString(4, vacancy.getLink());
                statement.addBatch();
                vacansyCount++;
            }
            LOG.info(String.format("Добавлено задач в БД по запросу %s : %s! %s.", queryName, vacansyCount, statement.toString()));
            statement.executeBatch();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    private void getVacasyListWithResultSet(ResultSet rs, List<Vacancy> resultList) throws SQLException {
        while (rs.next()) {
            int vacancyId = rs.getInt("id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            String textDate = rs.getString("date_create");
            LocalDateTime date = LocalDateTime.parse(textDate);
            String link = rs.getString("link");
            resultList.add(new Vacancy(vacancyId, title, link, author, date));
        }
    }

    private void createTableVacancy() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlQueryProp.getProperty("CREATE_TABLE_VACANCY_SQL_RU"));
            statement.executeUpdate(sqlQueryProp.getProperty("CREATE_TABLE_VACANCY_HH_RU"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void createTableDateUpdate() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlQueryProp.getProperty("CREATE_TABLE_UPDATE_DATE"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void insertDateUpdate() {
        try (PreparedStatement statement = connection.prepareStatement(sqlQueryProp.getProperty("INSERT_DATE_UPDATE"))) {
            statement.setString(1, LocalDateTime.now().toString());
            statement.executeUpdate();
            LOG.info(String.format("Дата обновления: %s", LocalDateTime.now().toString()));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void updateDateUpdate() {
        try (PreparedStatement statement = connection.prepareStatement(sqlQueryProp.getProperty("UPDATE_DATE_UPDATE"))) {
            statement.setString(1, LocalDateTime.now().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public List<DateUpdate> getDateUpdate() {
        List<DateUpdate> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQueryProp.getProperty("GET_LAST_UPDATE"));
            resultSet.next();
            if (!resultSet.isClosed()) {
                String date = resultSet.getString("date_last_update");
                result.add(new DateUpdate(date));
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public static class DateUpdate {
        private final String date;

        public DateUpdate(String date) {
            this.date = date;
        }

        public String getDate() {
            return date;
        }
    }
}
