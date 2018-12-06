package crud.servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class UserStore implements Store {
    private static UserStore instance = new UserStore();
    private final ConcurrentHashMap<Integer, User> userStore = new ConcurrentHashMap<>();

    private UserStore() {
    }

    public static UserStore getInstance() {
        return instance;
    }

    @Override
    public int add(User user) {
        int id = user.getId();
        userStore.put(id, user);
        return id;
    }

    @Override
    public User update(User user) {
        return userStore.replace(user.getId(), user);
    }

    @Override
    public User delete(int id) {
        return userStore.remove(id);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userStore.values());
    }

    @Override
    public User findById(int id) {
        return userStore.get(id);
    }
}
