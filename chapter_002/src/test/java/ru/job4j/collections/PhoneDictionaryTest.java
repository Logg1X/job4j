package ru.job4j.collections;

import org.junit.Test;
import ru.job4j.collections.models.Person;
import ru.job4j.collections.search.PhoneDictionary;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> people = phones.find("Petr");
        assertThat(people.iterator().next().getSurname(), is("Arsentev"));
        }
}
