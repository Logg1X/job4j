package storagesql;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SAXParsersTest {
    @Test
    public void whenParsingXMLFileThenSumFields55() throws Exception {
        SAXParsers parser = new SAXParsers();
        StoreSQL sql = new StoreSQL("config.properties");
        sql.generateData(10);
        XmlUsage xmlUsage = new XmlUsage();
        xmlUsage.saveXML(sql.selectData(), "stor.xml");
        sql.close();
        ConvertXSQT converter = new ConvertXSQT();
        converter.convert(new File("stor.xml"), new File("ConvertingXML.xml"), new File("scheme.xsl"));
        long result = parser.getSumXMLFields(new File("ConvertingXML.xml"), "entry", "field");
        assertThat(result, is(55L));
    }
}