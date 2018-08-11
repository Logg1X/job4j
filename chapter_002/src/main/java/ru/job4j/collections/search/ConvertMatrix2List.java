package ru.job4j.collections.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class ConvertMatrix2List {

    public List<Integer> toList(int[][] array) {
        List<Integer> res = new ArrayList<>();
        Arrays.stream(array)
                .flatMapToInt(Arrays::stream)
                .forEach(res::add);
//                list<Integer> list = new ArrayList<>();
//        for (int[] ints : array) {
//            for (int anInt : ints) {
//                list.add(anInt);
//            }
//        }
        return res;
    }
}
