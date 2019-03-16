package gc.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextCache extends Cache<String, String> {

    private String path;

    public TextCache(final String path) {
        this.path = this.correctPAth(path);
    }

    @Override
    protected String loadData(final String name) {
        String value = "";
        try (BufferedReader br = new BufferedReader(new FileReader(path.concat(name)))) {
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

    private String correctPAth(final String path) {
        String result = path;
        if (path.charAt(path.length() - 1) != File.separatorChar) {
            result = path.concat(File.separator);
        }
        return result;
    }
}
