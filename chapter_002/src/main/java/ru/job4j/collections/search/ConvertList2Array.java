package ru.job4j.collections.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
//        int cells = (list.size() + rows - 1) / rows;
        int cells = list.size() % rows == 0 ? list.size() / rows : list.size() / rows + 1;
        int[][] array = new int[rows][cells];
        int x = 0;
        int y = 0;
        for (Integer integer : list) {
            if (x < cells) {
                array[y][x++] = integer;
            } else {
                x = 0;
                array[++y][x++] = integer;
            }
        }
        return array;
    }

    //task № [#10037]
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<Integer>();
        list.stream()
                .map(Arrays::stream)
                .flatMapToInt(intStream -> intStream)
                .forEach(result::add);
//
//        for (int[] ints : list) {
//            for (int anInt : ints) {
//                result.add(anInt);
//            }
//        }
        return result;
    }
}


