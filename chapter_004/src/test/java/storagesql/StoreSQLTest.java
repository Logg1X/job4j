package storagesql;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreSQLTest {
    private StoreSQL sql;

    @Before
    public void init() {
        sql = new StoreSQL("config.properties");
    }

    @Test
    public void whenCheckConnection() throws Exception {
        assertFalse(this.sql.connectionIsClose());
        this.sql.close();
        assertTrue(this.sql.connectionIsClose());
    }

    @Test
    public void whenGenerateEntriesThen10entry() throws Exception {
        sql.generateData(10);
        List<XmlUsage.Field> result = sql.selectData();
        sql.close();
        List<XmlUsage.Field> expected = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            XmlUsage.Field field = new XmlUsage.Field();
            field.setField(i);
            expected.add(field);
        }
        assertThat(result, is(expected));
    }
}