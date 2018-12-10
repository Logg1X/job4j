package crud.servlet;

import java.util.List;
import java.util.Map;

public interface Validate {
    int add(Map<String, String[]> param);

    User update(User user);

    User delete(int id);

    List<User> findAll();

    User findById(int id);
}
