package jdbc.tracker.action;

import jdbc.tracker.input.Input;
import jdbc.tracker.Tracker;

public interface UserAction {
    int key();

    void execute(Input input, Tracker tracker);

    String info();
}
