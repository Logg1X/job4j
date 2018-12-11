package crud.servlet;

import java.util.List;
import java.util.Map;

public interface Validate {
    int add(Map<String, String[]> param);

    User update(Map<String, String[]> user);

    User delete(Map<String, String[]> param);

    List<User> findAll();

    User findById(Map<String, String[]> param);
}
