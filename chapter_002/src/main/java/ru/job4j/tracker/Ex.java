package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class Ex {
    public static void main(String[] args) {
        ArrayList a = new ArrayList();
        a.add(33);
        Tracker tracker = new Tracker();
        tracker.add(new Item("test", "test", 123L));
        tracker.add(new Item("test4", "test", 123L));
        tracker.add(new Item("test", "test", 123L));
        tracker.add(new Item("test", "test", 123L));

        System.out.println(tracker.findByName("test").length);

        for (Item item : tracker.findByName("test")) {
            System.out.println(item.getId());
        }

    }
}
