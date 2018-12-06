package crud.servlet;

import java.util.List;

public interface Store {

    int add(User user);

    User update(User user);

    User delete(int id);

    List<User> findAll();

    User findById(int id);

}
