package jdbc.tracker.action;

import jdbc.tracker.Tracker;
import jdbc.tracker.input.Input;

public interface UserAction {
    int key();

    void execute(Input input, Tracker tracker);

    String info();
}
