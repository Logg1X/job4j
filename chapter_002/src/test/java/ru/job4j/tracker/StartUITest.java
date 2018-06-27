package ru.job4j.tracker;

import org.junit.Test;

public class StartUITest {

    @Test
    public void wenAddItemUseMenu0() {
        Input input = new StubInput(new String[]{"0", "TestName", "TestDescription", "6"});
        Tracker tracker = new Tracker();
        new StartUI(input, tracker).init();
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
    }
}