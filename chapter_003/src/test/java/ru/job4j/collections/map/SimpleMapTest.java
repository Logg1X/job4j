package ru.job4j.collections.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    private SimpleMap<User, String> map;
    private User pasha;
    private User sasha;
    private User yulya;

    @Before
    public void setUp() throws Exception {
        pasha = new User("Pasha", 26);
        sasha = new User("Sasha", 27);
        yulya = new User("Yula", 24);

        map = new SimpleMap<>(5);
        map.insert(pasha, "Qiwi");
        map.insert(sasha, "Qiwi");
        map.insert(yulya, "Bosh");
    }

    @Test
    public void whenAddSomeOneKeyAndValue() {
        User somUser2 = new User("SimeUser2", 66666);
        assertThat(map.insert(somUser2, "sdfsf"), is(true));
    }

    @Test
    public void whenCollizionThenFalse() {
        User somUser1 = new User("SimeUser1", 9999);
        assertFalse(map.insert(somUser1, "SomeWork"));
    }

    @Test
    public void whenDuplicateKey() {
        User pasha23 = new User("Pasha", 26);
        assertFalse(map.insert(pasha23, "Epam"));
    }

    @Test
    public void whenGetSomOneElement() {
        assertThat(map.get(pasha), is("Qiwi"));
    }

    @Test
    public void whenNoElementToMap() {
        User someUser1 = new User("SimeUser1", 9999);
        assertNull(map.get(someUser1));
    }

    @Test
    public void whenGetValue() {
        assertThat(map.get(pasha), is("Qiwi"));
    }

    @Test
    public void whenDeleteSomOneElement() {
        assertThat(map.get(pasha), is("Qiwi"));
        assertTrue(map.delete(pasha));
        assertNull(map.get(pasha));
    }

    @Test
    public void whenDeleteNoExistElement() {
        User somUser1 = new User("SimeUser1", 9999);
        assertFalse(map.delete(somUser1));
    }

    @Test
    public void whenIterateMap() {
        Iterator iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next().toString(), is("Yula 24 лет. Bosh"));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next().toString(), is("Sasha 27 лет. Qiwi"));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next().toString(), is("Pasha 26 лет. Qiwi"));
        assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateWithNoSuchElementException() {
        Iterator iterator = map.iterator();
        assertThat(iterator.next().toString(), is("Yula 24 лет. Bosh"));
        assertThat(iterator.next().toString(), is("Sasha 27 лет. Qiwi"));
        assertThat(iterator.next().toString(), is("Pasha 26 лет. Qiwi"));
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIterateWithConcurrentModificationException() {
        Iterator iterator = map.iterator();
        iterator.next();
        assertTrue(map.delete(pasha));
        iterator.next();
    }

    private class User {
        private String firstName;
        private int age;

        public User(String firstName, int age) {
            this.firstName = firstName;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {

                return true;
            }
            if (o == null || getClass() != o.getClass()) {

                return false;
            }
            User user = (User) o;
            return age == user.age
                    && Objects.equals(firstName, user.firstName);
        }

        @Override
        public int hashCode() {

            return Objects.hash(firstName, age);
        }

        @Override
        public String toString() {
            return firstName
                    + " "
                    + age
                    + " лет.";
        }
    }
}
