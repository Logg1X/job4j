package ru.job4j.tracker.action;

import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.input.Input;

public interface UserAction {
    int key();

    void execute(Input input, Tracker tracker);

    String info();
}
