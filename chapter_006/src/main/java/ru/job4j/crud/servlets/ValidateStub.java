package ru.job4j.crud.servlets;

import ru.job4j.crud.servlets.models.Role;
import ru.job4j.crud.servlets.models.Rule;
import ru.job4j.crud.servlets.models.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ValidateStub implements Validate {
    private Map<String, User> users = new HashMap<>();
    private Map<Role, List<Rule>> roleRule = new HashMap<>();
    private static final Rule CREATE = new Rule(1, "CREATE", "/createUser");
    private static final Rule UPDATE = new Rule(2, "UPDATE", "/edit");
    private static final Rule DELETE = new Rule(3, "DELETE", "/users");
    private static final Rule SELECT = new Rule(4, "SELECT", "/listUsr");
    private static final Validate INSTANCE = new ValidateStub();

    private ValidateStub() {
        users.put("1", new User(1,
                "Admin",
                "Admin",
                "Admin@Admin.Admin",
                "Admin",
                Role.ADMIN,
                LocalDateTime.now()
        ));
        users.put("2", new User(
                2,
                "User",
                "User",
                "User@User.User",
                "User",
                Role.USER,
                LocalDateTime.now()
        ));
        users.put("3", new User(
                3,
                "User1",
                "User1",
                "User1@User1.User1",
                "User1",
                Role.USER,
                LocalDateTime.now()
        ));
        roleRule.put(Role.ADMIN, List.of(SELECT, CREATE, UPDATE, DELETE));
        roleRule.put(Role.USER, List.of(SELECT));
    }

    public static Validate getInstance() {
        return INSTANCE;
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
        var id = users.keySet().size() + 1;
        user.setId(id);
        users.put(String.valueOf(id), user);
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
                param.get("role")[0],
                param.get("createDate")[0]
        );
        this.validateMailAddres(user.getMail());
        this.mailIsExist(user.getMail(), user.getId());
        this.loginIsExist(user.getLogin(), user.getId());
        return users.replace(String.valueOf(id), user);
    }

    @Override
    public User delete(Map<String, String[]> param) {
        int id = Integer.valueOf(param.get("id")[0]);
        this.userIsNotExist(id);
        return users.remove(String.valueOf(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User findById(Map<String, String[]> param) {
        int id = Integer.valueOf(param.get("id")[0]);
        this.userIsNotExist(id);
        return users.get(String.valueOf(id));
    }

    public User getByCredentional(String login, String password) {
        return users.values()
                .stream()
                .filter(
                        user ->
                                user.getLogin().equals(login)
                                        && user.getPassword().equals(password))
                .findFirst().orElse(null);
    }

    public boolean isAllowedaccess(User usr, String path) {
        Boolean result = false;
        if (usr != null) {
            result = roleRule.get(usr.getRole())
                    .stream()
                    .anyMatch(rule -> rule.getAccessPath().contains(path));
        }
        return result;
    }

    @Override
    public void close() {

    }

    private void loginIsExist(String login) {
        if (users.values()
                .stream()
                .anyMatch(user -> user.getLogin().equalsIgnoreCase(login))) {
            throw new StoresException("login already exists!");
        }
    }

    private void loginIsExist(String login, int id) {
        if (users.values()
                .stream()
                .anyMatch(user -> user.getLogin().equalsIgnoreCase(login) && user.getId() != id)) {
            throw new StoresException("login already exists!");
        }
    }

    private void userIsNotExist(int id) {
        if (!users.keySet().contains(String.valueOf(id))) {
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
        if (users.values()
                .stream()
                .anyMatch(user -> user.getMail().equalsIgnoreCase(mail) && user.getId() != id)) {
            throw new StoresException("A user with this email exists!");
        }
    }

    private void mailIsExist(String mail) {
        if (users.values()
                .stream()
                .anyMatch(user -> user.getMail().equalsIgnoreCase(mail))) {
            throw new StoresException("A user with this email exists!");
        }
    }
}
