package storagesql;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreSQLTest {

    @Test
    public void whenCheckConnection() throws Exception {
        StoreSQL sql = new StoreSQL("config.properties");
        assertFalse(sql.connectionIsClose());
        sql.close();
        assertTrue(sql.connectionIsClose());
    }

    @Test
    public void whenGenerateEntriesThen10entry() throws Exception {
        StoreSQL sql = new StoreSQL("config.properties");
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