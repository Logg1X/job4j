package ru.job4j.collections.sort;

import org.junit.Test;
import ru.job4j.collections.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    @Test
    public void whenSortUser() {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        users.add(new User(22, "Pasha"));
        users.add(new User(12, "Vika"));
        users.add(new User(10, "Lina"));
        users.add(new User(32, "Dima"));
        Set<User> sorted = sortUser.sort(users);
        int iter = 0;
        String[] result = new String[sorted.size()];
        for (User user : sorted) {
            result[iter] = user.getName();
            iter++;
        }
        String[] expecteg = {"Lina", "Vika", "Pasha", "Dima"};
        assertThat(expecteg, is(result));
    }

    @Test
    public void whenSortUserNameLength() {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        users.add(new User(22, "Pa"));
        users.add(new User(12, "Pasha"));
        users.add(new User(10, "P"));
        users.add(new User(32, "Pash"));
        List<User> sorted = sortUser.sortNameLength(users);
        int iter = 0;
        String[] result = new String[sorted.size()];
        for (User user : sorted) {
            result[iter] = user.getName();
            iter++;
        }
        String[] expected = {"P", "Pa", "Pash", "Pasha"};
        assertThat(expected, is(result));
    }

    @Test
    public void whenSortByAllFields() {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        users.add(new User(22, "Pasha"));
        users.add(new User(12, "Vika"));
        users.add(new User(10, "Pasha"));
        users.add(new User(32, "Dima"));
        List<User> sorted = sortUser.sortByAllFields(users);
        int iter = 0;
        int[] result = new int[sorted.size()];
        for (User user : sorted) {
            result[iter] = user.getAge();
            iter++;
        }
        int[] expected = new int[]{32, 10, 22, 12};
        assertThat(expected, is(result));
    }
}

