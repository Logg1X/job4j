package ru.job4j.collections.sort;

import ru.job4j.collections.models.User;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
//        users.stream().sorted(Comparator.comparingInt(value -> value.getName().length()));
        users.sort(Comparator.comparingInt(o -> o.getName().length()));
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
        users.sort((o1, o2) -> {
            final int compareName = o1.getName().compareTo(o2.getName());
            final int compareAge = Integer.compare(o1.getAge(), o2.getAge());
            return compareName == 0 ? compareAge : compareName;
        });
        return users;
    }
}
