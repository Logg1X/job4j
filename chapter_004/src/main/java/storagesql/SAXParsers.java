package storagesql;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.IOException;


public class SAXParsers {

public long getSumXMLFields(File file, String nameHandler, String atributeName) throws ParserConfigurationException, SAXException, IOException {
    SAXParserFactory spf = SAXParserFactory.newInstance();
    SAXParser saxParser = spf.newSAXParser();
    XMLReader xmlReader = saxParser.getXMLReader();
    Counter counter = new Counter(nameHandler, atributeName);
    xmlReader.setContentHandler(counter);
    xmlReader.parse(file.getAbsolutePath());
    return counter.getSum();
}


    private static class Counter extends DefaultHandler {

        private final String nameHandler;
        private final String atributeName;
        private long sum;

        private Counter(String nameHandler, String atributeHandler) {
            this.nameHandler = nameHandler;
            this.atributeName = atributeHandler;
            sum = 0;
        }

        public long getSum() {
            return sum;
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals(nameHandler)) {
                sum += Long.valueOf(attributes.getValue(atributeName));
            }
        }
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        SAXParsers parsers = new SAXParsers();
        long n = parsers.getSumXMLFields(new File("dest.xml"), "entry", "field");
        System.out.println(n);
    }
}
