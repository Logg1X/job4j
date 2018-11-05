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
    StoreSQL sql;
    XmlUsage xmlUsage;
    ConvertXSQT converter;


    @Before
    public void setUp() throws Exception {
        converter = new ConvertXSQT();
        sql = new StoreSQL("config.properties");
        sql.generateData(5);
        xmlUsage = new XmlUsage();
        xmlUsage.saveXML(sql.selectData(), "stor.xml");
        sql.close();
    }

    @Test
    public void whenConvertXMLWithXSL() throws IOException {
        assertTrue(sql.connectionIsClose());
        converter.convert(new File("stor.xml"), new File("ConvertingXml.xml"), new File("scheme.xsl"));
        List<String> result = Files.readAllLines(Paths.get("ConvertingXML.xml"));
        List<String> expected = Files.readAllLines(Paths.get("ConvertingXmlForTest.xml"));
        assertThat(result, is(expected));
    }
}