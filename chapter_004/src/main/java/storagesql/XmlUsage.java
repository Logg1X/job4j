package storagesql;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class XmlUsage {

    @XmlRootElement
    public static class Entries {
        private List<Field> entry;

        public Entries() {
        }

        public Entries(List<Field> fields) {
            this.entry = fields;
        }

        public List<Field> getEntry() {
            return entry;
        }

        public Entries setEntry(List<Field> entry) {
            this.entry = entry;
            return this;
        }
    }
@XmlRootElement
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

    @Override
    public String toString() {
        return "Field{" +
                "field=" + field +
                '}';
    }
}

    public void saveXML(List<Field> fields) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new Entries(fields), new File("test.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public Entries unmarshalXML(File file) {
        Entries entries = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            entries = (Entries) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return entries;
    }

    public static void main(String[] args) throws JAXBException {
        StoreSQL sql = new StoreSQL("config.properties");
        sql.generateData(10);
        XmlUsage xmlUsage = new XmlUsage();
        xmlUsage.saveXML(sql.selectData());
        Entries entries = xmlUsage.unmarshalXML(new File("test.xml"));
        for (Field field : entries.getEntry()) {
            System.out.println(field.getField());
        }

    }
}
