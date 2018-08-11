package ru.job4j.collections.search;

import ru.job4j.collections.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class PhoneDictionary {

    private List<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Возвращает список всех пользователей, который содержат key в любых полях.
     *
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        return this.persons.stream()
                .filter(person -> person.toString().contains(key))
                .collect(Collectors.toList()
                );
//        list<Person> result = new ArrayList<>();
//        for (Person person : persons) {
//            if (person.toString().contains(key)) {
//                result.add(person);
//            }
//        }
//        return result;
    }
}

