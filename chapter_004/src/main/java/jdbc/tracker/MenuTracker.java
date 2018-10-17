package jdbc.tracker;

import jdbc.tracker.action.BaseAction;
import jdbc.tracker.action.UserAction;
import jdbc.tracker.input.Input;
import jdbc.tracker.models.Item;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Реализует удаление задачи из хранилища.
 */
class DeleteItem extends BaseAction {
    protected DeleteItem(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите номер задачи :");
        if (tracker.delete(id)) {
            System.out.println("------------ Задача №: " + id + " удалена! ------------");
        } else {
            System.out.println("Удалить задачу № " + id + " не удалось."
                    + ". Возможно такой задачи не существует.");
        }
    }
}

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private ArrayList<UserAction> actions = new ArrayList<>();


    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions(StartUI ui) {
        this.actions.add(new AddItem(0, "Add new Item       |"));
        this.actions.add(new MenuTracker.ShowAllItems(1, "Show all items     |"));
        this.actions.add(new EditItem(2, "Edit item          |"));
        this.actions.add(new DeleteItem(3, "Delete Item        |"));
        this.actions.add(new FindItemById(4, "Find item by Id    |"));
        this.actions.add(new FindItemByName(5, "Find item by Name  |"));
        this.actions.add(new ExitTracker(6, "EXIT               |", ui));

    }

    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            System.out.println(action.info());
        }
    }

    public ArrayList<UserAction> getActions() {
        return this.actions;
    }

    /**
     * Реализует добавленяи новый задачи в хранилище.
     */
    private class AddItem extends BaseAction {
        protected AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой задачи --------------");
            String name = input.ask("Введите имя задачи :");
            String desc = input.ask("Введите описание задачи :");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("---- Новая задача с номером : " + item.getId() + " добавлена. ----");
        }
    }

    /**
     * Выводит в консоль список всех задач.
     */
    private static class ShowAllItems extends BaseAction {

        protected ShowAllItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            if (tracker.findAll().size() > 0) {
                System.out.println("-------------- Список всех задач ---------------");
                for (Item item : tracker.findAll()) {
                    System.out.println(item.toString());
                }
            } else {
                System.out.println("-------------- Список задач пуст ---------------");
            }
        }
    }

    /**
     * Реализует изменение имени и описания дадачи.
     */
    private class EditItem extends BaseAction {

        protected EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            Item item = null;
            System.out.println("------------ Изменение задачи  --------------");
            while (item == null) {
                String id = input.ask("Введите номер задачи :");
                item = tracker.findById(id);
                if (item == null) {
                    System.out.println("-------Задачи с таким номером не существует!"
                            + System.lineSeparator()
                            + "Ознакомьтесь со списком задач, выбрав пункт '2'.");
                    break;
                }
                item.setName(input.ask("Введите новое имя задачи :"));
                item.setDescription(input.ask("Введите новое описание задачи :"));
//            newName = input.ask("Введите новое имя задачи :");
//            newDesc = input.ask("Введите новое описание задачи :");
                item.setDateUpdate(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date()));
                if (tracker.replace(item)) {
                    System.out.println("--------------Задача №: " + item.getId() + " обновлена.--------------");
                } else {
                    System.out.println("-------Что-то пошло не так!"
                            + System.lineSeparator()
                            + "Измениь заявку не получилось.");
                }
            }

        }
    }

    /**
     * Осуществляет поиск задач по номеру.
     * Выводит информацию о задаче в консоль.
     */
    private class FindItemById extends BaseAction {
        protected FindItemById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите номер задачи :");
            if (tracker.findById(id) != null) {
                System.out.println(tracker.findById(id).toString());
            } else {
                System.out.println("------Задачи с таким номером не существует!"
                        + System.lineSeparator()
                        + "Ознакомьтесь со списком задач, выбрав пункт '2'.");
            }
        }

        @Override
        public String info() {
            return String.format("%s %s. %s%s%s", "|", this.key() + 1, "Find item by Id    |", System.lineSeparator(), "|_______________________|");
        }
    }

    /**
     * Осуществляет поиск дубликатов задач в хранилище.
     * Выводит дубликаты в консоль.
     */
    private class FindItemByName extends BaseAction {

        protected FindItemByName(int key, String name) {
            super(key, name);
        }

        @Override

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите имя задачи :");
            for (Item item : tracker.findByName(name)) {
                System.out.println(item.toString());
            }
        }
    }

    private class ExitTracker extends BaseAction {
        private final StartUI ui;

        private ExitTracker(int key, String name, StartUI ui) {
            super(key, name);
            this.ui = ui;
        }

        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("Вы вышли из программы...");
            this.ui.stop();
        }

        @Override
        public String info() {
            return String.format("%s %s. %s%s%s", "|", this.key() + 1, "EXIT               |", System.lineSeparator(), "|_______________________|");
        }
    }
}