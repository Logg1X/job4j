package storagesql;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SAXParsersTest {
    StoreSQL sql;
    XmlUsage xmlUsage;
    ConvertXSQT converter;
    SAXParsers parser;

    @Before
    public void setUp() throws Exception {
        parser = new SAXParsers();
        sql = new StoreSQL("config.properties");
        sql.generateData(10);
        xmlUsage = new XmlUsage();
        xmlUsage.saveXML(sql.selectData(), "stor.xml");
        sql.close();
        converter = new ConvertXSQT();
        converter.convert(new File("stor.xml"), new File("ConvertingXML.xml"), new File("scheme.xsl"));
    }

    @Test
    public void whenParsingXMLFileThenSumFields55() throws IOException, SAXException, ParserConfigurationException {
        long result = this.parser.getSumXMLFields(new File("ConvertingXML.xml"), "entry", "field");
        assertThat(result, is(55L));
    }
}