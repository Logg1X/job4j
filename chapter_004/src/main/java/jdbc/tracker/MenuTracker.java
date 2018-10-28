package jdbc.tracker;

import jdbc.tracker.action.BaseAction;
import jdbc.tracker.action.UserAction;
import jdbc.tracker.input.Input;
import jdbc.tracker.models.Comments;
import jdbc.tracker.models.Item;

import java.util.ArrayList;
import java.util.List;

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
        this.actions.add(new AddComment(6, "Comment the item   |"));
        this.actions.add(new DeleteComment(7, "Delete comment     |"));
        this.actions.add(new ExitTracker(7, "EXIT               |", ui));
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
                item.setDateUpdate(System.currentTimeMillis());
                if (tracker.replace(item.getId(), item)) {
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
            Item item = null;
            while (item == null) {
                item = tracker.findById(id);
                if (item != null) {
                    System.out.println(item.toString());
                } else {
                    System.out.println("------Задачи с таким номером не существует!"
                            + System.lineSeparator()
                            + "Ознакомьтесь со списком задач, выбрав пункт '2'.");
                    break;
                }
                String showComments = input.ask("Показать комментарии? Y/N");
                if (showComments.equalsIgnoreCase("Y")) {
                    List<Comments> list = tracker.getCommentsByItem(id);
                    if (list.isEmpty()) {
                        System.out.println("--------Комментариев к задаче пока нет.-------");
                    } else {
                        System.out.println(System.lineSeparator()
                                + "   ↓ ↓ ↓ ↓ ↓ КОММЕНТАРИИ К ЗАДАЧЕ ↓ ↓ ↓ ↓ ↓ ");
                        list.forEach(comments -> System.out.println(comments.toString()));
                    }
                }
            }
        }
    }

    /**
     * Осуществляет поиск  задач в хранилище по имени.
     * Выводит задачи в консоль.
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
            return 8;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("Вы вышли из программы...");
            this.ui.stop();
        }
    }

    /**
     * Осуществляет добавление комментариев к задаче.
     */
    private class AddComment extends BaseAction {

        protected AddComment(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            Item item = null;
            String idItem = input.ask("Введите № задачи в которой хотите оставить комментарий: ");
            while (item == null) {
                item = tracker.findById(idItem);
                if (item == null) {
                    System.out.println("-------Задачи с таким номером не существует!"
                            + System.lineSeparator()
                            + "Ознакомьтесь со списком задач, выбрав пункт '2'.");
                    break;
                }
                String messge = input.ask("Введите сообщение:");
                tracker.addComments(idItem, messge);
            }
        }
    }

    public class DeleteComment extends BaseAction {
        protected DeleteComment(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите ID комментария, который хотите удалить");
            if (tracker.deleteComment(id)) {
                System.out.println(String.format("Комментарий c ID - %s удален.", id));
            } else {
                System.out.println("Удалить комментарий не удалось!" + System.lineSeparator()
                        + "Возможно введен некорректный ID.");
            }

        }
    }

}