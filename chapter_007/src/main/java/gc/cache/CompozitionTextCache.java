package gc.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CompozitionTextCache {
    String path;
    private Cache<String, String> cache;

    public CompozitionTextCache(String path) {
        this.path = path;
        this.cache = new Cache<>() {
            @Override
            protected String loadData(String key) {
                String value = "";
                try (BufferedReader br = new BufferedReader(new FileReader(path.concat(key)))) {
                    String temp = br.readLine();
                    while (temp != null) {
                        value = value.concat(temp).concat(System.lineSeparator());
                        temp = br.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return value;
            }
        };
    }

    public Cache<String, String> getCache() {
        return cache;
    }
}

