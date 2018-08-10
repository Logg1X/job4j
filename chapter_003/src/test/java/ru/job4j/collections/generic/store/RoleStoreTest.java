package ru.job4j.collections.generic.store;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.collections.generic.SimpleArray;
import ru.job4j.collections.generic.store.model.Role;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class RoleStoreTest {
    RoleStore<Role> roleStore;
    Role aa;
    Role bb;
    Role cc;
    Role dd;
    Role ee;

    @Before
    public void setUp() throws Exception {
        this.roleStore = new RoleStore<>(new SimpleArray<>(5));
        aa = new Role("AA");
        bb = new Role("BB");
        cc = new Role("CC");
        dd = new Role("DD");
        ee = new Role("EE");
        roleStore.add(aa);
        roleStore.add(bb);
        roleStore.add(cc);
        roleStore.add(dd);
        roleStore.add(ee);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddRoleInRoleStoreWithException() {
        roleStore.add(new Role("DDDD"));
    }

    @Test
    public void whenGetRoleIntoRoleStore() {
        assertThat(aa, is(roleStore.findById("AA")));
        assertThat(ee, is(roleStore.findById("EE")));
    }

    @Test
    public void whenReplaceRoleIntoRoleStore() {
        Role expected = new Role("FF");
        this.roleStore.replace("CC", expected);
        Role result = (Role) this.roleStore.findById("FF");
        assertThat(result, is(expected));
    }

    @Test(expected = RuntimeException.class)
    public void whenReplaceRoleWithRuntimeException() {
        this.roleStore.replace("FFF", new Role("ddd"));
    }

    @Test
    public void whenDeleteRoleWithIdIsCC() {
        assertTrue(this.roleStore.delete("CC"));
    }

    @Test(expected = RuntimeException.class)
    public void whenDeleteRoleWithRuntimeException() {
        this.roleStore.delete("LLL");
    }
}