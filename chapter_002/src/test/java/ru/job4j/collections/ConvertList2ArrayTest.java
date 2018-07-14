package ru.job4j.collections;

import org.junit.Test;
import ru.job4j.collections.search.ConvertList2Array;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertList2ArrayTest {
    @Test
    public void when7ElementsThen() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                2
        );
        int[][] expect = {
                {1, 2, 3, 4},
                {5, 6, 7, 0}

        };
        assertThat(result, is(expect));
    }

    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void whenInListArrays() {
        ConvertList2Array array = new ConvertList2Array();
        int[] one = {1, 2, 3, 44};
        int[] two = {55, 6};
        int[] thre = {7, 8, 9, 10, 1};
        List<int[]> input = Arrays.asList(one, two, thre);
        List<Integer> expect = Arrays.asList(
                1, 2, 3, 44, 55, 6, 7, 8, 9, 10, 1
        );
        List<Integer> result = array.convert(input);
        assertThat(result, is(expect));
    }
}