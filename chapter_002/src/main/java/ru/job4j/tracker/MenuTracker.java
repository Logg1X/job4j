package ru.job4j.tracker;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Реализует удаление задачи из хранилища.
 */
class DeleteItem implements UserAction {
    @Override
    public int key() {
        return 3;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите номер задачи :");
        if (tracker.findById(id) != null) {
            String wontDelete = input.ask("Удалить задачу №" + id + "? 'y': ");
            if ("y".equals(wontDelete)) {
                tracker.delete(id);
                System.out.println("------------ Задача №: " + id + " удалена! ------------");
            } else {
                System.out.println("Задача №" + id + " не удалена.");
            }
        } else {
            System.out.println("Удалить задачу № " + id + " не удалось."
                    + ". Возможно такой задачи не существует.");
        }
    }

    @Override
    public String info() {
        return String.format("%s %s. %s%s%s", "|", this.key() + 1, "Delete Item        |", System.lineSeparator(), "|_______________________|");
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
    private UserAction[] actions = new UserAction[7];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[0] = new AddItem();
        this.actions[1] = new MenuTracker.ShowAllItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindItemById();
        this.actions[5] = new FindItemByName();
        this.actions[6] = new ExitTracker();

    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            System.out.println(action.info());
        }
    }

    /**
     * Реализует добавленяи новый задачи в хранилище.
     */
    private class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
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

        @Override
        public String info() {
            return String.format("%s %s. %s%s%s", "|", this.key() + 1, "Add new Item       |", System.lineSeparator(), "|_______________________|");
        }
    }

    /**
     * Выводит в консоль список всех задач.
     */
    private static class ShowAllItems implements UserAction {
        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            if (tracker.findAll().length > 0) {
                System.out.println("-------------- Список всех задач ---------------");
                for (Item item : tracker.findAll()) {
                    System.out.println(item.toString());
                }
            } else System.out.println("-------------- Список задач пуст ---------------");
        }

        @Override
        public String info() {
            return String.format("%s %s. %s%s%s", "|", this.key() + 1, "Show all items     |", System.lineSeparator(), "|_______________________|");
        }
    }

    /**
     * Реализует изменение имени и описания дадачи.
     */
    private class EditItem implements UserAction {
        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String yes = "y";
            String name = "";
            String desc = "";
            Item item = new Item(name, desc);
            System.out.println("------------ Изменение задачи  --------------");
            String id = input.ask("Введите номер задачи :");
            if (tracker.findById(id) != null) {
                String editName = input.ask("Хотите изменить имя задачи? ('y' - YES / 'n' - NO):");
                if (yes.equals(editName)) {
                    item.setName(input.ask("Введите новое имя задачи :"));
                    System.out.println("----- Имя задачи № " + id + " изменено.");
                } else {
                    item.setName(tracker.findById(id).getName());
                    System.out.println("----- Имя задачи № " + id + " осталось без изменений.");
                }
                String editDesc = input.ask("Хотите изменить описание задачи? ('y' - YES / 'n' - NO):");
                if (editDesc.equals(yes)) {
                    item.setDescription(input.ask("Введите новое описание задачи :"));
                    System.out.println("----- Описание задачи № " + id + " изменено.");
                } else {
                    item.setDescription(tracker.findById(id).getDescription());
                    System.out.println("----- Описание задачи № " + id + " осталось без изменений.");
                }
                item.setDateUpdate(new SimpleDateFormat("dd.MM.yyyy' в 'HH:mm").format(new Date()));
                tracker.replace(id, item);

                System.out.println("--------------Задача " + item.getId() + " обновлена.--------------");
            } else {
                System.out.println("Задачи с таким номером не существует!"
                        + System.lineSeparator()
                        + "Ознакомьтесь со списком задач, выбрав пункт '1'.");
            }
        }

        @Override
        public String info() {
            return String.format("%s %s. %s%s%s", "|", this.key() + 1, "Edit item          |", System.lineSeparator(), "|_______________________|");
        }

    }

    /**
     * Осуществляет поиск задач по номеру.
     * Выводит информацию о задаче в консоль.
     */
    private class FindItemById implements UserAction {
        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите номер задачи :");
            if (tracker.findById(id) != null) {
                System.out.println(tracker.findById(id).toString());
            } else {
                System.out.println("Задачи с таким номером не существует!"
                        + System.lineSeparator()
                        + "Ознакомьтесь со списком задач, выбрав пункт '1'.");
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
    private class FindItemByName implements UserAction {
        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите имя задачи :");
            for (Item item : tracker.findByName(name)) {
                System.out.println(item.toString());
            }
        }

        @Override
        public String info() {
            return String.format("%s %s. %s%s%s", "|", this.key() + 1, "Find item by Name  |", System.lineSeparator(), "|_______________________|");
        }
    }

    private class ExitTracker implements UserAction {
        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            //не додумался до другой реализации....
            //конечно же, тут в будущем нужно будет создать отдельный метод (перед выполнением EXIT),
            //который будет все "очищать" и закрывать подключения к базе,
            // так что пока так.
            //если есть более изящные решения, буду признателен подсказке.
            String wontExit = input.ask("Хотите выйти из программы? (y): ");
            if (!"y".equals(wontExit)) {
            } else {
                System.out.println("Вы вышли из программы...");
                System.exit(0);
            }
        }

        @Override
        public String info() {
            return String.format("%s %s. %s%s%s", "|", this.key() + 1, "EXIT               |", System.lineSeparator(), "|_______________________|");
        }
    }


}
