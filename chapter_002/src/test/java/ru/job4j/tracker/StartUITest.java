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
        Input input = new StubInput(new String[]{"0", "TestName", "TestDescription",
                "0", "TestName-1", "TestDescription-1",
                "0", "TestName-1", "TestDescription-1",
                "2", tracker.findByName("TestName-1")[0].getId(), "y", "NameCHANGED", "y", "DescCHANGED", "6"});

        new StartUI(input, tracker).init();
    }
}