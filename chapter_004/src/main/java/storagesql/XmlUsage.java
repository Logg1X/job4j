package storagesql;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;
import java.util.Objects;

public class XmlUsage {

    @XmlRootElement(name = "Entries")
    public static class Entry {
        private List<Field> entry;

        public Entry() {

        }

        public Entry(List<Field> fields) {
            this.entry = fields;
        }

        public List<Field> getEntry() {
            return entry;
        }

        public Entry setEntry(List<Field> entry) {
            this.entry = entry;
            return this;
        }
    }

    public static class Field {
        private int field;

        public Field() {

        }

        public Field(int field) {
            this.field = field;
        }

        public int getField() {
            return field;
        }

        public Field setField(int field) {
            this.field = field;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Field entry = (Field) o;
            return field == entry.field;
        }

        @Override
        public int hashCode() {
            return Objects.hash(field);
        }
    }

    private void saveXML(List<Field> fields) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entry.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new Entry(fields), new File("test.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws JAXBException {
//        sql = new StoreSQL("config.properties");
//
//        sql.generateData(10);
//        List<Field> entries = new Entry(sql.selectData()).getEntries();
//        JAXBContext jaxbContext = JAXBContext.newInstance(XmlUsage.Entry.class);
//        Marshaller marshaller = jaxbContext.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.marshal(entries, new File("test.xml"));
        StoreSQL sql = new StoreSQL("config.properties");
        XmlUsage xmlUsage = new XmlUsage();
        xmlUsage.saveXML(sql.selectData());


    }
}
