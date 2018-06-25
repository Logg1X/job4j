package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    private static final String SHOW_ALL = "1";
    private static final String EDIT = "2";
    private static final String DELL = "3";
    private static final String GET = "4";
    private static final String DUBLICATES = "5";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW_ALL.equals(answer)) {
                showAllItems();
            } else if (EDIT.equals(answer)) {
                editItem();
            } else if (DELL.equals(answer)) {
                delete();
            } else if (GET.equals(answer)) {
                findById();
            } else if (DUBLICATES.equals(answer)) {
                duplicateNames();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с номером : " + item.getId() + "-----------");
    }

    private void showAllItems() {
        System.out.println("------------ Список всех заявок --------------");
        for (Item item : tracker.findAll()) {
            System.out.println(item.toString());
        }
    }

    private void editItem() {
        String yes = "y";
        String name = "";
        String desc = "";
        Item item = new Item(name, desc);
        System.out.println("------------ Изменение заявки  --------------");
        String id = this.input.ask("Введите номер заявки :");
        if (tracker.findById(id) != null) {
            String editName = this.input.ask("Хотите изменить имя заявки? ('y' - YES / 'n' - NO):");
            if (yes.equals(editName)) {
                item.setName(this.input.ask("Введите новое имя заявки :"));
            } else {
                item.setName(tracker.findById(id).getName());
            }
            String editDesc = this.input.ask("Хотите изменить описание заявки? ('y' - YES / 'n' - NO):");
            if (editDesc.equals(yes)) {
                item.setDescripton(this.input.ask("Введите новое описание заявки :"));
            } else {
                item.setDescripton(tracker.findById(id).getDescripton());
            }
            tracker.replace(id, item);
            System.out.println("--------------Заявка " + item.getId() + " обновлена.--------------");
        } else {
            System.out.println("Заявки с таким номером не существует!"
                    + System.lineSeparator()
                    + "Ознакомьтесь со списком заявок, выбрав пункт '3'.");
        }
    }

    private void delete() {
        String id = this.input.ask("Введите номер заявки :");
        if (tracker.findById(id) != null) {
            tracker.delete(id);
            System.out.println("------------ Задача №: " + id + " удалена! ------------");
        } else {
            System.out.println("Удалить заявку № " + id + " не удалось."
                    + ". Возможно такой заявки не существует.");
        }
    }

    private void findById() {
        String id = this.input.ask("Введите номер заявки :");
        if (tracker.findById(id) != null) {
            System.out.println(tracker.findById(id).toString());
        } else {
            System.out.println("Заявки с таким номером не существует!"
                    + System.lineSeparator()
                    + "Ознакомьтесь со списком заявок, выбрав пункт '3'.");
        }
    }

    private void duplicateNames() {
        String name = this.input.ask("Введите имя заявки :");
        for (Item item : tracker.findByName(name)) {
            System.out.println(item.toString());
        }

    }


    private void showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("_________- МЕНЮ -________").append(System.lineSeparator());
        menu.append("| 0. Add new Item       |").append(System.lineSeparator());
        menu.append("|_______________________|").append(System.lineSeparator());
        menu.append("| 1. Show all items     |").append(System.lineSeparator());
        menu.append("|_______________________|").append(System.lineSeparator());
        menu.append("| 2. Edit item          |").append(System.lineSeparator());
        menu.append("|_______________________|").append(System.lineSeparator());
        menu.append("| 3. Delete item        |").append(System.lineSeparator());
        menu.append("|_______________________|").append(System.lineSeparator());
        menu.append("| 4. Find item by Id    |").append(System.lineSeparator());
        menu.append("|_______________________|").append(System.lineSeparator());
        menu.append("| 5. Find items by name |").append(System.lineSeparator());
        menu.append("|_______________________|").append(System.lineSeparator());
        menu.append("| 6. Exit Program       |").append(System.lineSeparator());
        menu.append("|_______________________|").append(System.lineSeparator());
        System.out.println(menu);


        // добавить остальные пункты меню.
    }

    /**
     * Запускт программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}