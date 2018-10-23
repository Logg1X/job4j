package jdbc.tracker;

//import jdbc.tracker.connection.ConnectionPSQL;
import jdbc.tracker.input.ConsoleInput;
import jdbc.tracker.input.Input;
import jdbc.tracker.input.ValidateInput;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class StartUI {
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    private boolean exit = true;

    private int[] range;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище задач.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions(this);
        do {
            menu.show();
            menu.select(input.ask("Введите пункт меню : ", fillingRange()) - 1);

        } while (exit);
    }

    public void stop() {
        this.exit = false;
    }

    private int[] fillingRange() {
        MenuTracker menuTracker = new MenuTracker(this.input, this.tracker);
        menuTracker.fillActions(this);
        this.range = new int[menuTracker.getActions().size()];
        for (int i = 0; i < range.length; i++) {
            range[i] = i;
        }
        return range;
    }

    /**
     * Запускт программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker("query.sql","app.properties")).init();
    }
}