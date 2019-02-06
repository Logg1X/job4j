package io.finder;

import java.io.IOException;

public interface Finder {
    void find(String startDirectory, String searchString, String logNameFile) throws IOException;
}
