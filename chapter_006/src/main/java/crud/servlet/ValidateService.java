package crud.servlet;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ValidateService implements Validate {

    private static ValidateService logic = new ValidateService();
    private final Store store = UserStore.getInstance();

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
                param.get("email")[0]
        );
        int id = user.getId();
        this.userIsExist(id);
        this.validateMailAddres(user.getMail());
        this.mailIsExist(user.getMail(), user.getId());
        store.add(user);
        return id;
    }

    @Override
    public User update(User user) {
        int id = user.getId();
        this.userIsNotExist(id);
        this.validateMailAddres(user.getMail());
        this.mailIsExist(user.getMail(), user.getId());
        User replaced = store.findById(id);
        if (replaced != null) {
            replaced = store.update(user);
        }
        return replaced;
    }

    @Override
    public User delete(int id) {
        this.userIsNotExist(id);
        return store.delete(id);
    }

    @Override
    public List<User> findAll() {
        List<User> result = store.findAll();
        if (result.isEmpty()) {
            throw new StoresException("Users list ois empty");
        }
        return result;
    }

    @Override
    public User findById(int id) {
        this.userIsNotExist(id);
        return store.findById(id);
    }

    private void userIsExist(int id) {
        User user = store.findById(id);
        if (user != null && store.findAll().contains(user)) {
            throw new StoresException("such user exists!");
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
        for (User user : store.findAll()) {
            if (user.getMail().equalsIgnoreCase(mail) && user.getId() != id) {
                throw new StoresException("A user with this email exists!");
            }
        }
    }
}
