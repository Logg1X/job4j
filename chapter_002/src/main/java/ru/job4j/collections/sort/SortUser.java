package ru.job4j.collections.sort;

import ru.job4j.collections.models.User;

import java.util.*;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class SortUser {
    /**
     * Лексеграфичесая сортировка пользователей по имени
     *
     * @param users список пользователей
     * @return отсортированный список
     */
    public Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }

    /**
     * Сортировка пользователей по длинне имени
     *
     * @param users список пользователей
     * @return отсортированный список
     */
    public List<User> sortNameLength(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getName().length(), o2.getName().length());
            }
        });
        return users;
    }

    /**
     * Лексеграфичесая сортировка пользователей по имени,
     * если имена идентичны - сортировка по возрасту
     *
     * @param users список пользователей
     * @return отсортированный список
     */
    public List<User> sortByAllFields(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(final User o1, final User o2) {
                final int compareName = o1.getName().compareTo(o2.getName());
                final int compareAge = Integer.compare(o1.getAge(), o2.getAge());
                return compareName == 0 ? compareAge : compareName;
            }
        });
        return users;
    }
}
