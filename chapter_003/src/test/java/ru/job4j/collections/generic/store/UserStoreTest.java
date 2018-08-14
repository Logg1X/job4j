package ru.job4j.collections.generic.store;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.collections.generic.SimpleArray;
import ru.job4j.collections.generic.store.model.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class UserStoreTest {

    UserStore userStore;
    User aa;
    User bb;
    User cc;
    User dd;
    User ee;

    @Before
    public void setUp() throws Exception {
        this.userStore = new UserStore(new SimpleArray<>(5));
        aa = new User("AA");
        bb = new User("BB");
        cc = new User("CC");
        dd = new User("DD");
        ee = new User("EE");
        userStore.add(aa);
        userStore.add(bb);
        userStore.add(cc);
        userStore.add(dd);
        userStore.add(ee);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddUserInUserStoreWithException() {
        userStore.add(new User("DDDD"));
    }

    @Test
    public void whenGetUserIntoUserStore() {
        assertThat(aa, is(userStore.findById("AA")));
        assertThat(bb, is(userStore.findById("BB")));
        assertThat(cc, is(userStore.findById("CC")));
        assertThat(dd, is(userStore.findById("DD")));
        assertThat(ee, is(userStore.findById("EE")));
    }

    @Test
    public void whenReplaceUserIntoUserStore() {
        User expected = new User("FF");
        this.userStore.replace("CC", expected);
        User result = this.userStore.findById("FF");
        assertThat(result, is(expected));
    }

    @Test(expected = RuntimeException.class)
    public void whenReplaceUserWithRuntimeException() {
        this.userStore.replace("FFF", new User("ddd"));
    }

    @Test
    public void whenDeleteUserWithIdIsCC() {
        assertTrue(this.userStore.delete("CC"));
    }

    @Test(expected = RuntimeException.class)
    public void whenDeleteUserWithRuntimeException() {
        this.userStore.delete("LLL");
    }
}