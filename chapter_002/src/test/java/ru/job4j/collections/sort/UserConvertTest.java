package ru.job4j.collections.sort;

import org.junit.Test;
import ru.job4j.collections.models.User;
import ru.job4j.collections.search.UserConvert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;

public class UserConvertTest {
    @Test
    public void whenListUserConvertToMap() {
        UserConvert convertToMap = new UserConvert();
        User user = new User(35, "pasha", "MSK");
        User user1 = new User(25, "paha", "MSK");
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        Map<Integer, User> result = convertToMap.process(users);
        assertThat(result.keySet(), contains(35, 25));
        assertThat(result.get(35), is(user));
        assertThat(result.get(25), is(user1));
    }
}
