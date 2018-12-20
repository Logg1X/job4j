package crud.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsLoader {
    private final Properties props = new Properties();

    private PropsLoader() {
    }

    public static PropsLoader getPropsLoader() {
        return PropsHolder.LOADER;
    }

    public Properties load(Class aClass, String propertyPath) {
        try (InputStream stream = aClass.getClassLoader().getResourceAsStream(propertyPath)) {
            props.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    private static class PropsHolder {
        private static final PropsLoader LOADER = new PropsLoader();
    }
}
