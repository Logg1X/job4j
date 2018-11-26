package storagesql;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class XmlUsageTest {

    @Test
    public void whenSaveXMLWithDataInFile() throws Exception {
        StoreSQL sql = new StoreSQL("config.properties");
        sql.generateData(5);
        XmlUsage xmlUsage = new XmlUsage();
        xmlUsage.saveXML(sql.selectData(), "stor.xml");
        sql.close();
        List<String> result = Files.readAllLines(Paths.get("stor.xml"));
        List<String> expected = Files.readAllLines(Paths.get("storForTest.xml"));
        assertThat(result, is(expected));
    }
}