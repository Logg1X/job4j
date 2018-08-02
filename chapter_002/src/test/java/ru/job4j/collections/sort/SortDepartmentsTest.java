package ru.job4j.collections.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SortDepartmentsTest {
    private ArrayList<String> dep = new ArrayList<>();
    private SortDepartments departments;

    @Before
    public void setUp() throws Exception {
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
        ArrayList<String> result = this.departments.sortAscending(dep);
        ArrayList<String> expect = new ArrayList<>();
        expect.add("K1");
        expect.add("K1\\SK1");
        expect.add("K1\\SK1\\SSK1");
        expect.add("K1\\SK1\\SSK2");
        expect.add("K1\\SK2");
        expect.add("K2");
        expect.add("K2\\SK1\\SSK1");
        expect.add("K2\\SK1\\SSK2");
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortDiminition() {
        ArrayList<String> result = this.departments.sortDiminition(dep);
        ArrayList<String> expect = new ArrayList<>();
        expect.add("K2");
        expect.add("K2\\SK1\\SSK2");
        expect.add("K2\\SK1\\SSK1");
        expect.add("K1");
        expect.add("K1\\SK2");
        expect.add("K1\\SK1");
        expect.add("K1\\SK1\\SSK2");
        expect.add("K1\\SK1\\SSK1");
        assertThat(result, is(expect));
    }

}