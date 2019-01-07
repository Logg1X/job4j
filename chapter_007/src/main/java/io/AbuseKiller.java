package io;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;

public class AbuseKiller {
    private static final Logger L = LogManager.getLogger(AbuseKiller.class.getName());

    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (var input = new BufferedReader(new InputStreamReader(in));
             var output = new BufferedWriter(new OutputStreamWriter(out))) {
            String s;
            int countLine = 1;
            while ((s = input.readLine()) != null) {
                for (String s1 : abuse) {
                    s = s.replaceAll("\\b" + s1 + "\\b", "");
                    L.info(String.format("Удаление слова '%s' из входного потока", s1));
                }
                output.write(s.replaceAll("\\s+", " ").strip() + "\n");
                L.info(String.format("Строка №%s: '%s' записана в исходящий поток!", countLine++, s));
            }
        } catch (IOException e) {
            L.error(e.getMessage(), e);
        }

    }
}
