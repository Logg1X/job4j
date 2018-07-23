package ru.job4j.bank.models;

public class User {
    /**
     * Имя пользователя.
     */
    private String name;
    /**
     * Серия + № паспорта
     */
    private String passport;

    public User() {
    }

    public User(String name, String pasport) {
        this.name = name;
        this.passport = pasport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result += passport != null ? passport.hashCode() : 0;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        User user = (User) obj;
        if (user.name != null
                && user.passport != null
                && user.name.equals(((User) obj).name)
                && user.passport.equals(((User) obj).passport)) {
            result = true;
        }
        return result;
    }
}
