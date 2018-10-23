package jdbc.tracker;

import jdbc.tracker.connection.Query;
import jdbc.tracker.input.Input;
import jdbc.tracker.models.Comments;
import jdbc.tracker.models.Item;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class Tracker implements Closeable {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();
    /**
     * Соединение с БД.
     */
    private Connection connection;

    private Properties sql = new Properties();
    private Properties config = new Properties();

    /**
     * Конструктор класса.
     * @param pathQuery Путь к фаулу со скриптами, для работы с БД.
     * @param pathConfigConnection Путь с параметрами подключения к БД.
     */
    public Tracker(String pathQuery,String pathConfigConnection) {
        this.init(pathConfigConnection);
        this.initSql(pathQuery);
        this.createTrackerTable();
    }

    /**
     * Инициализация подключения к БД.
     * @param pathConfig путь к парамерам подключения.
     * @return true если соединение установлено, иначе false.
     */

    public boolean init(String pathConfig) {
        try (InputStream in = Tracker.class.getClassLoader().getResourceAsStream(pathConfig)) {
            this.config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    /**
     * Получение данных из файла со скриптами, для работы с БД.
     * @param pathSQL путь к файлу.
     * @return true если данные получены. Иначе false.
     */
    private boolean initSql(String pathSQL) {
        try (InputStream stream = Tracker.class.getClassLoader().getResourceAsStream(pathSQL)){
            this.sql.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sql);
        return !sql.isEmpty();
    }

    /**
     * Создает таблицу 'tracker' в БД, если ее там нет.
     */
    private void createTrackerTable() {
        try (final Statement statementTracker = this.connection.createStatement()) {
            System.out.println(this.sql.getProperty("CREATE_TABLE_TRACKER"));
            statementTracker.executeUpdate(this.sql.getProperty("CREATE_TABLE_TRACKER"));
            try (final Statement statementComments = this.connection.createStatement()) {
                statementComments.executeUpdate(this.sql.getProperty("CREATE_TABLE_COMMENTS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(final Item item) {
        try (PreparedStatement statement = this.connection.prepareStatement(this.sql.getProperty("ADD_ITEM"), PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setTimestamp(3, new Timestamp(item.getDateCreating()));
            statement.setTimestamp(4, new Timestamp(item.getDateCreating()));
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    item.setId(String.valueOf(resultSet.getInt("id")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        Date date = new Date();
        long millis = date.getTime() % 1000;
        int random = (int) (Math.random() * 100);
        return String.valueOf(millis + "#" + random);
    }

    /**
     * Метод заменяет Item в списке items.
     *
     * @param id
     * @param item на эту заяку меняем.
     */
    public boolean replace(String id, final Item item) {
        boolean result = false;
        try (final PreparedStatement statementIt = this.connection.prepareStatement(this.sql.getProperty("EDIT_ITEM"))) {
            statementIt.setString(1, item.getName());
            statementIt.setString(2, item.getDescription());
            statementIt.setTimestamp(3, new Timestamp(item.getDateUpdate()));
            statementIt.setInt(4, Integer.parseInt(item.getId()));
            statementIt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод узаляет заявку из массива.
     *
     * @param id номер заявки, которую нужно удалить.
     */
    public boolean delete(final String id) {
        boolean result = false;
        try (final PreparedStatement statementCom = this.connection.prepareStatement(this.sql.getProperty("DELETE_ALL_COMENTS_WITH_ITEM_ID"))) {
            try (final PreparedStatement statement = this.connection.prepareStatement(this.sql.getProperty("DELETE_ITEM"))) {
                Item item = this.findById(id);
                if (item != null) {
                    statement.setInt(1, Integer.parseInt(id));
                    statementCom.setInt(1, Integer.parseInt(id));
                    statementCom.executeUpdate();
                    statement.executeUpdate();
                    result = true;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Метод показывает все заявки.
     *
     * @return массив заявок.
     */
    public List<Item> findAll() {
        try (final Statement statement = connection.createStatement()) {
            try (final ResultSet set = statement.executeQuery(this.sql.getProperty("GET_ALL_ITEMS"))) {
                this.formation(set);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.items;
    }

    /**
     * Метод находит дубликаты имен заявок.
     *
     * @param key Имя заявки для поиска.
     * @return массив дубликатов.
     */
    public List<Item> findByName(String key) {
        try (final PreparedStatement statement = this.connection.prepareStatement(this.sql.getProperty("FIND_BY_NAME"))) {
            statement.setString(1, "%" + key + "%");
            try (final ResultSet resultSet = statement.executeQuery()) {
                this.formation(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Метод находит заявку по ее номеру.
     *
     * @param id номер заявки
     * @return заявка.
     */
    public Item findById(final String id) {
        Item item = null;
        try (final PreparedStatement statement = this.connection.prepareStatement(this.sql.getProperty("FIND_BY_ID"))) {
            statement.setInt(1, Integer.parseInt(id));
            try (final ResultSet set = statement.executeQuery()) {
                this.formation(set);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!items.isEmpty()) {
            item = items.get(0);
        }
        return item;
    }

    /**
     * Заполняет список заявок полученных из БД.
     *
     * @param resultSet результат запроса к БД.
     */
    private void formation(final ResultSet resultSet) {
        items.clear();
        try {
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getString("id"));
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                item.setDateCreating(resultSet.getTimestamp("create_date").getTime());
                item.setDateUpdate(resultSet.getTimestamp("update_date").getTime());
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Добавляет коментарий к задаче.
     *
     * @param id      № Задачи в которой делается комментарий.
     * @param message Содержание комментария.
     * @return
     */
    public boolean addComments(final String id, final String message) {
        Comments comment = new Comments(message);
        boolean result = false;
        try (final PreparedStatement statement = this.connection.prepareStatement(this.sql.getProperty("ADD_COMMENT"))) {
            statement.setString(1, message);
            statement.setTimestamp(2, new Timestamp(comment.getDateCreating()));
            statement.setInt(3, Integer.parseInt(id));
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Получает все комментарии к задаче.
     *
     * @param id № задачи.
     * @return Список комментариев.
     */

    public List<Comments> getCommentsByItem(final String id) {
        List<Comments> comments = new ArrayList<>();
        try (final PreparedStatement statement = this.connection.prepareStatement(this.sql.getProperty("GET_ALL_COMMENTS"))) {
            statement.setInt(1, Integer.parseInt(id));
            try (final ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Comments com = new Comments();
                    com.setId(resultSet.getInt("id"));
                    com.setMessage(resultSet.getString("message"));
                    com.setDateCreating(resultSet.getTimestamp("create_date").getTime());
                    comments.add(com);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public boolean deleteComment(final String idComment) {
        boolean result = false;
        try (final PreparedStatement statement = this.connection.prepareStatement(this.sql.getProperty("DELETE_COMMENT"))) {
            statement.setInt(1, Integer.parseInt(idComment));
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
