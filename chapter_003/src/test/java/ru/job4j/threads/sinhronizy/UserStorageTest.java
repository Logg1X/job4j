package ru.job4j.threads.sinhronizy;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {
    private UserStorage storage;
    private UserStorage.User a;
    private UserStorage.User b;
    private List<UserStorage.User> st;

    @Before
    public void setUp() throws Exception {
        storage = new UserStorage();
        a = new UserStorage.User(1, 200);
        b = new UserStorage.User(2, 200);
        st = storage.getStorage();
    }

    @Test
    public void whenDoStorageOperation() throws InterruptedException {
        UserStorage.User c = new UserStorage.User(2, 100);
        Thread threadA = new Thread(() -> storage.add(this.a));
        Thread threadB = new Thread(() -> storage.add(this.b));
        threadA.start();
        threadA.join();
        threadB.start();
        threadB.join();
        assertTrue(storage.transfer(1, 2, 100));
        assertThat(st.get(0).getAmount(), is(100));
        assertThat(st.get(1).getAmount(), is(300));
        assertTrue(storage.update(c));
        assertThat(st.get(1).getAmount(), is(100));
        assertTrue(storage.update(b));
        assertTrue(storage.delete(this.a));
        assertTrue(storage.delete(this.b));
        assertFalse(storage.delete(this.b));
        assertFalse(storage.delete(c));
    }
}
