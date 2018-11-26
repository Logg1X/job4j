package storagesql;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ConvertXSQTTest {
    @Test
    public void whenConvertXMLWithXSL() throws Exception {
        ConvertXSQT converter = new ConvertXSQT();
        StoreSQL sql = new StoreSQL("config.properties");
        sql.generateData(5);
        XmlUsage xmlUsage = new XmlUsage();
        xmlUsage.saveXML(sql.selectData(), "stor.xml");
        sql.close();
        assertTrue(sql.connectionIsClose());
        converter.convert(new File("stor.xml"), new File("ConvertingXml.xml"), new File("scheme.xsl"));
        List<String> result = Files.readAllLines(Paths.get("ConvertingXML.xml"));
        List<String> expected = Files.readAllLines(Paths.get("ConvertingXmlForTest.xml"));
        assertThat(result, is(expected));
    }
}