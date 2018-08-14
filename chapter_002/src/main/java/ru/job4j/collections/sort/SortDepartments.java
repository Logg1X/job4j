package ru.job4j.collections.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class SortDepartments {

    public Set<String> sortAscending(List<String> array) {
        return checkDep(array);
    }

    public Set<String> sortDiminition(List<String> array) {
        Set<String> result = new TreeSet<>((o1, o2) -> {
            int result1;
            if (o1.length() < o2.length() && o2.startsWith(o1)) {
                result1 = -1;
            } else if (o1.length() > o2.length() && o1.startsWith(o2)) {
                result1 = 1;
            } else {
                result1 = o2.compareTo(o1);
            }
            return result1;
        });
        result.addAll(checkDep(array));
        return result;
    }

    private Set<String> checkDep(List<String> array) {
        Set<String> result = new TreeSet<>();
        for (String s : array) {
            int index = s.length();
            while (index != -1) {
                s = s.substring(0, index);
                result.add(s);
                index = s.lastIndexOf("\\");
            }
        }
        return result;
    }
}
