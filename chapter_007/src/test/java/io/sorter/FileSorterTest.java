package io.sorter;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class FileSorterTest {

    @Test
    public void whenSortTestFile() throws IOException {
        Sorter sorter = new FileSorter(50);
        sorter.sort(new File("TestText.txt"), new File("SortedTextForTest.txt"));
        StringBuilder result = new StringBuilder();
        try (var reader = new BufferedReader(new FileReader(new File("SortedTextForTest.txt")))) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                result.append(temp);
            }
        }
        StringBuilder expected = new StringBuilder()
                .append("Детство")
                .append("Сомнения")
                .append("Зрелость")
                .append("Результат")
                .append("Первые попытки")
                .append("Переломный момент")
                .append("Как стать программистом?")
                .append("Как стать работающим программистом?")
                .append("Как стать работающим программистом в 40 лет?");
        assertThat(result.toString(), is(expected.toString()));
    }

    @Test (expected = FileNotFoundException.class)
    public void whenException() throws IOException {
        Sorter sorter = new FileSorter(50);
            sorter.sort(new File("sdcf.txt"), new File("SortedTextForTest.txt"));
    }
}