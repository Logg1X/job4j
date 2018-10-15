package jdbc.tracker;

import jdbc.tracker.connection.ConnectionPSQL;
import jdbc.tracker.connection.Query;
import jdbc.tracker.models.Item;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();
    /**
     * Соединение с БД.
     */
    private final Connection connection;

    /**
     * Коструктор класса.
     * @param connection Соединение с БД.
     */
    public Tracker(ConnectionPSQL connection) {
        this.connection = connection.getConnectionPSQL();
        this.createTrackerTable();
    }

    /**
     * Создает таблицу 'tracker' в БД, если ее тамнет.
     */
    private void createTrackerTable() {
        try (Statement statement = this.connection.createStatement()) {
            statement.executeUpdate(Query.create_table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        try (PreparedStatement statement = this.connection.prepareStatement(Query.add_item, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1,item.getName());
            statement.setString(2,item.getDescription());
            statement.setTimestamp(3, Timestamp.valueOf(item.getDateCreating()));
            statement.setTimestamp(4, Timestamp.valueOf(item.getDateCreating()));
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    item.setId(String.valueOf(resultSet.getInt("id")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        item.setId(this.generateId());
//        this.items.add(item);
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
     * @param id   номер заявки, который нужно заменить.
     * @param item на эту заяку меняем.
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int i = 0; i < this.items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                item.setId(id);
                this.items.set(i, item);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод узаляет заявку из массива.
     *
     * @param id номер заявки, которую нужно удалить.
     */
    public boolean delete(String id) {
        return items.removeIf(item -> item.getId().equals(id));
    }


    /**
     * Метод показывает все заявки.
     *
     * @return массив заявок.
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Метод находит дубликаты имен заявок.
     *
     * @param key Имя заявки для поиска.
     * @return массив дубликатов.
     */
    public List<Item> findByName(String key) {
        ArrayList<Item> result = new ArrayList<>();
        this.items
                .stream()
                .filter(item -> item.getName().equals(key))
                .forEach(result::add);
        return result;
    }

    /**
     * Метод находит заявку по ее номеру.
     *
     * @param id номер заявки
     * @return заявка.
     */
    public Item findById(String id) {
        return items
                .stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
