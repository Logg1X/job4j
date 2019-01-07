package io;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamNumbers {
    private static final Logger L = LogManager.getLogger(StreamNumbers.class.getName());

    public boolean isNumbers(InputStream inputStream) {
        boolean result = false;
        try {
            try (var in = inputStream;
                 var stream = new BufferedInputStream(in)
            ) {
                var num = stream.read();
                if (num % 2 == 0) {
                    result = true;
                    L.info(String.format("%s IS EVEN NUMBER", num));
                } else {
                    L.info(String.format("%s IS ODD NUMBER", num));
                }
            }
        } catch (IOException e) {
            L.error(e.getMessage(), e);
        }
        return result;
    }
}
