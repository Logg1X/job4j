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
     * Метод реализует добавленяи новый задачи в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой задачи --------------");
        String name = this.input.ask("Введите имя задачи :");
        String desc = this.input.ask("Введите описание задачи :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая задача с номером : " + item.getId() + "-----------");
    }

    /**
     * Метод выводит в консоль список всех задач.
     */
    private void showAllItems() {
        System.out.println("------------ Список всех задач --------------");
        for (Item item : tracker.findAll()) {
            System.out.println(item.toString());
        }
    }

    /**
     * Метод реализует изменение имени и описания дадачи.
     */
    private void editItem() {
        String yes = "y";
        String name = "";
        String desc = "";
        Item item = new Item(name, desc);
        System.out.println("------------ Изменение задачи  --------------");
        String id = this.input.ask("Введите номер задачи :");
        if (tracker.findById(id) != null) {
            String editName = this.input.ask("Хотите изменить имя задачи? ('y' - YES / 'n' - NO):");
            if (yes.equals(editName)) {
                item.setName(this.input.ask("Введите новое имя задачи :"));
                System.out.println("----- Имя задачи № " + id + " изменено.");
            } else {
                item.setName(tracker.findById(id).getName());
                System.out.println("----- Имя задачи № " + id + " осталось без изменений.");
            }
            String editDesc = this.input.ask("Хотите изменить описание задачи? ('y' - YES / 'n' - NO):");
            if (editDesc.equals(yes)) {
                item.setDescripton(this.input.ask("Введите новое описание задачи :"));
                System.out.println("----- Описание задачи № " + id + "изменено.");
            } else {
                item.setDescripton(tracker.findById(id).getDescripton());
                System.out.println("----- Описание задачи № " + id + " осталось без изменений.");
            }
            tracker.replace(id, item);
            System.out.println("--------------Задача " + item.getId() + " обновлена.--------------");
        } else {
            System.out.println("Задачи с таким номером не существует!"
                    + System.lineSeparator()
                    + "Ознакомьтесь со списком задач, выбрав пункт '3'.");
        }
    }
    /**
     * Метод реализует удаление задачи из хранилища.
     */
    private void delete() {
        String id = this.input.ask("Введите номер задачи :");
        if (tracker.findById(id) != null) {
            tracker.delete(id);
            System.out.println("------------ Задача №: " + id + " удалена! ------------");
        } else {
            System.out.println("Удалить задачу № " + id + " не удалось."
                    + ". Возможно такой задачи не существует.");
        }
    }/**
     * Метод осуществляет поиск задач по номеру.
     * Выводит информацию о задаче в консоль.
     */
    private void findById() {
        String id = this.input.ask("Введите номер задачи :");
        if (tracker.findById(id) != null) {
            System.out.println(tracker.findById(id).toString());
        } else {
            System.out.println("Задачи с таким номером не существует!"
                    + System.lineSeparator()
                    + "Ознакомьтесь со списком задач, выбрав пункт '3'.");
        }
    }
    /**
     * Метод осуществляет поиск дубликатов задач в хранилище.
     * Выводит дубликаты в консоль.
     */
    private void duplicateNames() {
        String name = this.input.ask("Введите имя задачи :");
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