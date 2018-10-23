package ru.job4j.tracker.action;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Tracker;

public interface UserAction {
    int key();

    void execute(Input input, Tracker tracker);

    String info();
}
