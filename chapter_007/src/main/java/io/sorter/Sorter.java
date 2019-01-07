package io.sorter;

import java.io.File;
import java.io.IOException;

public interface Sorter {
    void sort(File source, File distance) throws IOException;
}
