package ru.job4j.collections.sort;

import java.util.ArrayList;
import java.util.Collections;


public class SortDepartments {

    public ArrayList<String> sortAscending(ArrayList<String> array) {
        array = checkDep(array);
        Collections.sort(array);
        return array;
    }
    public ArrayList<String> sortDiminition(ArrayList<String> array) {
        array = checkDep(array);
        array.sort((o1, o2) -> {
            int result;
            if (o1.length() < o2.length() && o2.startsWith(o1)) {
                result = -1;
            } else if (o1.length() > o2.length() && o1.startsWith(o2)) {
                result = 1;
            } else {
                result = o2.compareTo(o1);
            }
            return result;
        });
        return array;
    }

    private ArrayList<String> checkDep(ArrayList<String> array) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).contains("\\")) {
                String dep = array.get(i).substring(0, array.get(i).indexOf("\\"));
                if (!array.contains(dep)) {
                    array.add(dep);
                }
            }
        }
        return array;
    }
}
