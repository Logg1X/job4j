package jdbc.tracker;

import jdbc.tracker.models.Item;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TrackerTest {

    public Connection init() {
        Connection connection;
        try (InputStream in = Tracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return connection;
    }

    @Test
    public void whenCreateItem() throws SQLException, IOException {
        try (Tracker tracker = new Tracker(ConnectionRollback.create(this.init()), "query.sql")) {
            tracker.add(new Item("name", "desc"));
            assertThat(tracker.findByName("name").size(), is(1));
        }
    }

    @Test
    public void whenUpdateItem() throws SQLException, IOException {
        try (Tracker tracker = new Tracker(ConnectionRollback.create(this.init()), "query.sql")) {
            Item item1 = tracker.add(new Item("name", "desc"));
            Item item2 = new Item("name111", "Desk111");
            item2.setId(item1.getId());
            assertTrue(tracker.replace(item1.getId(), item2));
            assertThat(tracker.findById(item1.getId()).getName(), is("name111"));
            assertThat(tracker.findById(item1.getId()).getDescription(), is("Desk111"));
        }
    }

    @Test
    public void whenFindByName() throws SQLException, IOException {
        try (Tracker tracker = new Tracker(ConnectionRollback.create(this.init()), "query.sql")) {
            Item item1 = tracker.add(new Item("name", "desc"));
            Item item2 = tracker.add(new Item("name", "desc1"));
            assertThat(tracker.findById(item1.getId()).getName(), is("name"));
            assertThat(tracker.findById(item2.getId()).getName(), is("name"));
            assertThat(tracker.findByName("name").size(), is(2));
        }
    }

    @Test
    public void whenDeleteItem() throws SQLException, IOException {
        try (Tracker tracker = new Tracker(ConnectionRollback.create(this.init()), "query.sql")) {
            Item item1 = tracker.add(new Item("name", "desc"));
            assertThat(tracker.findByName("name").size(), is(1));
            assertTrue(tracker.delete(item1.getId()));
            assertThat(tracker.findByName("name").size(), is(0));
        }
    }
}