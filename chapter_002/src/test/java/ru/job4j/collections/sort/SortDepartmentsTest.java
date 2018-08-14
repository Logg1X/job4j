package ru.job4j.collections.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortDepartmentsTest {
    private ArrayList<String> dep = new ArrayList<>();
    private SortDepartments departments;

    @Before
    public void setUp() {
        this.departments = new SortDepartments();
        this.dep.add("K1\\SK1");
        this.dep.add("K1\\SK2");
        this.dep.add("K1\\SK1\\SSK1");
        this.dep.add("K1\\SK1\\SSK2");
        this.dep.add("K2");
        this.dep.add("K2\\SK1\\SSK1");
        this.dep.add("K2\\SK1\\SSK2");
    }

    @Test
    public void whenSortAscending() {
        Set<String> result = this.departments.sortAscending(dep);
        Set<String> expect = new TreeSet<>();
        expect.add("K1");
        expect.add("K1\\SK1");
        expect.add("K1\\SK1\\SSK1");
        expect.add("K1\\SK1\\SSK2");
        expect.add("K1\\SK2");
        expect.add("K2");
        expect.add("K2\\SK1");
        expect.add("K2\\SK1\\SSK1");
        expect.add("K2\\SK1\\SSK2");
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortDiminition() {
        Set<String> result = this.departments.sortDiminition(dep);
        Set<String> expect = new TreeSet<>();
        expect.add("K2");
        expect.add("K2\\SK1");
        expect.add("K2\\SK1\\SSK2");
        expect.add("K2\\SK1\\SSK1");
        expect.add("K1");
        expect.add("K1\\SK2");
        expect.add("K1\\SK1");
        expect.add("K1\\SK1\\SSK2");
        expect.add("K1\\SK1\\SSK1");
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortAscendingListWithOneLine() {
        List<String> depOneLine = new ArrayList<>();
        depOneLine.add("K1\\SK1\\SSK1\\SSSK2");
        Set<String> result = this.departments.sortAscending(depOneLine);
        Set<String> expect = new TreeSet<>();
        expect.add("K1");
        expect.add("K1\\SK1");
        expect.add("K1\\SK1\\SSK1");
        expect.add("K1\\SK1\\SSK1\\SSSK2");
        assertThat(result, is(expect));
    }

    @Test
    public void when5Levels() {
        List<String> depOneLine = new ArrayList<>();
        depOneLine.add("K1\\SK1\\SSK2\\SSK3\\SSK4\\SSK5");
        depOneLine.add("K2\\SK2\\SSK1");
        depOneLine.add("K2\\SK1\\SSK2");
        depOneLine.add("K2\\SK1\\SSK1\\SSK3");
        Set<String> result = this.departments.sortDiminition(depOneLine);
        Set<String> expect = new TreeSet<>();
        expect.add("K2");
        expect.add("K2\\SK2");
        expect.add("K2\\SK2\\SSK1");
        expect.add("K2\\SK1");
        expect.add("K2\\SK1\\SSK2");
        expect.add("K2\\SK1\\SSK1");
        expect.add("K2\\SK1\\SSK1\\SSK3");
        expect.add("K1");
        expect.add("K1\\SK1");
        expect.add("K1\\SK1\\SSK2");
        expect.add("K1\\SK1\\SSK2\\SSK3");
        expect.add("K1\\SK1\\SSK2\\SSK3\\SSK4");
        expect.add("K1\\SK1\\SSK2\\SSK3\\SSK4\\SSK5");
        assertThat(result, is(expect));
    }


}