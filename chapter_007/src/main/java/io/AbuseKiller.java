package io;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;


public class AbuseKiller {
    private static final Logger LOGGER = LogManager.getLogger(AbuseKiller.class.getName());

    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        var wordRegexp = createRegexpPattern(abuse);
        var gapRegexp = ("\\s+");
        try (var input = new BufferedReader(new InputStreamReader(in));
             var output = new BufferedWriter(new OutputStreamWriter(out))) {
            String stringStream;
            while ((stringStream = input.readLine()) != null) {
                stringStream = stringStream
                        .replaceAll(wordRegexp, "")
                        .replaceAll(gapRegexp, " ")
                        .strip() + System.lineSeparator();
                output.write(stringStream);
                LOGGER.info(String.format("Строка: '%s' записана в исходящий поток!", stringStream.substring(0, stringStream.length() - 2)));
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private String createRegexpPattern(String... word) {
        var textPattern = new StringBuilder();
        for (String strings : word) {
            textPattern.append(String.format("%s|", strings));
        }
        return textPattern.toString();
    }
}
