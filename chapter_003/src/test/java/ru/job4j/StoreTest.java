package ru.job4j;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StoreTest {
    Store store;
    Store.User user1;
    Store.User user2;
    Store.User user3;
    Store.User user4;
    Store.User user5;
    List<Store.User> previoues;
    List<Store.User> current;

    @Before
    public void setUp() throws Exception {
        store = new Store();
        previoues = new ArrayList<>();
        current = new ArrayList<>();
        user1 = new Store.User(1111, "Pasha");
        user2 = new Store.User(2222, "Dima");
        user3 = new Store.User(3333, "Vika");
        user4 = new Store.User(4444, "Lena");
        user5 = new Store.User(5555, "Sasha");
        previoues.add(user1);
        previoues.add(user2);
        previoues.add(user3);
        previoues.add(user4);
        previoues.add(user5);
    }

    @Test
    public void whenDelete2UserAndChanged2Users() {
        current.addAll(previoues);
        current.remove(user5);
        current.remove(user4);
        current.set(1, new Store.User(2222, "Kolya"));
        current.set(2, new Store.User(3333, "Grisha"));
        assertThat(store.diff(previoues, current).toString(), is("New: 1, Deleted: 2, Changed: 2"));
    }

    @Test
    public void whenDeletedAllElements() {
        current.addAll(previoues);
        current.clear();
        assertThat(store.diff(previoues, current).toString(), is("New: 0, Deleted: 5, Changed: 0"));
    }

    @Test
    public void whenAdd2ElementsChanged2elementsAndDelete2Elements() {
        current.addAll(previoues);
        current.add(new Store.User(7777, "Viktor"));
        current.remove(user1);
        current.remove(user2);
        current.set(0, new Store.User(3333, "Yuadadliya"));
        current.set(1, new Store.User(4444, "GGGJHGJ"));
        assertThat(store.diff(previoues, current).toString(), is("New: 2, Deleted: 2, Changed: 2"));
    }
}