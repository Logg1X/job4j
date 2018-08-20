package ru.job4j.collections.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

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
        List<String> expect = Arrays.asList(
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK1"
        );

        assertThat(result.toString(), is(expect.toString()));
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
        System.out.println(result);
        List<String> expect = Arrays.asList(
                "K2",
                "K2\\SK2",
                "K2\\SK2\\SSK1",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK1\\SSK3",
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK2\\SSK3",
                "K1\\SK1\\SSK2\\SSK3\\SSK4",
                "K1\\SK1\\SSK2\\SSK3\\SSK4\\SSK5");
        System.out.println(expect.toString());
        assertThat(result.toString(), is(expect.toString()));
    }
}
