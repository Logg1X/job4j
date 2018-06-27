package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class StartUITest {

    @Test
    public void wenAddItemUseMenu0() {
        Input input = new StubInput(new String[]{"0", "TestName", "TestDescription",
                "0", "TestName1", "TestDescription1",
                "0", "TestName1", "TestDescription1",
                "6"});
        Tracker tracker = new Tracker();
        new StartUI(input, tracker).init();
        Item item = new Item("TestName1", "TestDescription1");
        item.setId(tracker.findAll()[1].getId());
        assertThat(tracker.findAll()[1].getName(), is(item.getName()));
        assertThat(tracker.findAll()[1].getDescripton(), is(item.getDescripton()));
        assertThat(tracker.findAll()[1].getId(), is(item.getId()));

    }


    @Test
    public void whenEditNameAndDescription() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("TestName", "TestDescription"));
        tracker.add(new Item("TestName2", "TestDescription2"));
        tracker.add(new Item("TestName3", "TestDescription3"));
        Input input = new StubInput(new String[]{
                "2", tracker.findByName("TestName2")[0].getId(), "y", "NameCHANGED", "y", "DescCHANGED", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[1].getName(), is("NameCHANGED"));
        assertThat(tracker.findAll()[1].getDescripton(), is("DescCHANGED"));
    }

    @Test
    public void whenEditDescription() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("TestName", "TestDescription"));
        tracker.add(new Item("TestName2", "TestDescription2"));
        tracker.add(new Item("TestName3", "TestDescription3"));
        Input input = new StubInput(new String[]{
                "2", tracker.findByName("TestName2")[0].getId(), "n", "y", "DescCHANGED", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[1].getDescripton(), is("DescCHANGED"));
        assertThat(tracker.findAll()[1].getName(), is("TestName2"));
    }

    @Test
    public void whenEditName() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("TestName", "TestDescription"));
        tracker.add(new Item("TestName2", "TestDescription2"));
        tracker.add(new Item("TestName3", "TestDescription3"));
        Input input = new StubInput(new String[]{
                "2", tracker.findByName("TestName2")[0].getId(), "y", "NameCHANGED", "n", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[1].getName(), is("NameCHANGED"));
        assertThat(tracker.findAll()[1].getDescripton(), is("TestDescription2"));

    }

    @Test
    public void whenDeleteItem() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("TestName", "TestDescription"));
        tracker.add(new Item("TestName2", "TestDescription2"));
        tracker.add(new Item("TestName3", "TestDescription3"));
        tracker.add(new Item("TestName4", "TestDescription4"));
        Input input = new StubInput(new String[]{
                "3", tracker.findByName("TestName3")[0].getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[2], is(tracker.findByName("TestName4")[0]));
    }
}