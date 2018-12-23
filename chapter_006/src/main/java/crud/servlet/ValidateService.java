package crud.servlet;

import crud.servlet.models.User;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ValidateService implements Validate {

    private static ValidateService logic = new ValidateService();
    private final Store store = DBStore.getInstance();

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        return logic;
    }

    @Override
    public int add(Map<String, String[]> param) {
        User user = new User(
                param.get("name")[0],
                param.get("login")[0],
                param.get("password")[0],
                param.get("email")[0]
        );
        this.loginIsExist(user.getLogin());
        this.validateMailAddres(user.getMail());
        this.mailIsExist(user.getMail());
        store.add(user);
        return user.getId();
    }

    @Override
    public User update(Map<String, String[]> param) {
        int id = Integer.valueOf(param.get("id")[0]);
        this.userIsNotExist(id);
        User user = new User(
                id,
                param.get("name")[0],
                param.get("login")[0],
                param.get("email")[0],
                param.get("password")[0],
                param.get("role")[0]
        );
        this.validateMailAddres(user.getMail());
        this.mailIsExist(user.getMail(), user.getId());
        this.loginIsExist(user.getLogin(), user.getId());
        User replaced = store.findById(id);
        if (replaced != null) {
            replaced = store.update(user);
        }
        return replaced;
    }

    @Override
    public User delete(Map<String, String[]> param) {
        int id = Integer.valueOf(param.get("id")[0]);
        this.userIsNotExist(id);
        return store.delete(id);
    }

    @Override
    public List<User> findAll() {
        List<User> result = store.findAll();
        if (result == null) {
            throw new StoresException("Users list is empty");
        }
        return result;
    }

    @Override
    public User findById(Map<String, String[]> param) {
        int id = Integer.valueOf(param.get("id")[0]);
        this.userIsNotExist(id);
        return store.findById(id);
    }

    private void loginIsExist(String login) {
        if (store.findAll()
                .stream()
                .anyMatch(user -> user.getLogin().equalsIgnoreCase(login))) {
            throw new StoresException("login already exists!");
        }
    }

    private void loginIsExist(String login, int id) {
        if (store.findAll()
                .stream()
                .anyMatch(user -> user.getLogin().equalsIgnoreCase(login) && user.getId() != id)) {
            throw new StoresException("login already exists!");
        }
    }

    private void userIsNotExist(int id) {
        if (store.findById(id) == null) {
            throw new StoresException("user does not exist!");
        }
    }

    private void validateMailAddres(String mail) {
        Pattern pattern = Pattern.compile("(?!(.*[А-Яа-я].*)).*@.*\\..*$");
        if (!pattern.matcher(mail).matches()) {
            throw new StoresException("email is incorrect!");
        }
    }

    private void mailIsExist(String mail, int id) {
        if (store.findAll()
                .stream()
                .anyMatch(user -> user.getMail().equalsIgnoreCase(mail) && user.getId() != id)) {
            throw new StoresException("A user with this email exists!");
        }
    }

    private void mailIsExist(String mail) {
        if (store.findAll()
                .stream()
                .anyMatch(user -> user.getMail().equalsIgnoreCase(mail))) {
            throw new StoresException("A user with this email exists!");
        }
    }
}
