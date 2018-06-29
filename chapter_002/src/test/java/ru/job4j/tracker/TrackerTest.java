package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        Item item1 = new Item("test2", "testDescription", 123L);
        Item item2 = new Item("test3", "testDescription", 123L);
        tracker.add(item);
        tracker.add(item1);
        tracker.add(item2);
        assertThat(tracker.findAll()[0], is(item));
        assertThat(tracker.findAll()[1], is(item1));
        assertThat(tracker.findAll()[2], is(item2));
    }


    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", 1234L);
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteItemWithIdThenReturnMassiveWithoutThisItem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "1testDescription1", 1L);
        Item item2 = new Item("test2", "2testDescription2", 2L);
        Item item3 = new Item("test3", "3testDescription3", 3L);
        Item item4 = new Item("test4", "4testDescription4", 4L);
        Item item5 = new Item("test5", "5testDescription4", 5L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.delete(item2.getId());
        tracker.delete(item3.getId());
        assertThat(tracker.findAll()[1], is(item4));
    }

    @Test
    public void whenInMassiveSeveralItemsWithTheSameNamesThenReturnThisDuplicates() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "1testDescription1", 1L);
        Item item2 = new Item("test", "2testDescription2", 2L);
        Item item3 = new Item("test3", "3testDescription3", 3L);
        Item item4 = new Item("test", "4testDescription4", 4L);
        Item item5 = new Item("test", "5testDescription4", 5L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.add(item5);
        Item[] result = {item2, item4, item5};
        assertThat(result, is(tracker.findByName("test")));
    }
}
