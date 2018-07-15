package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final ArrayList<Item> items = new ArrayList<>();

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        Date date = new Date();
        long millis = date.getTime() % 1000;
        int random = (int) (Math.random() * 100);
        return String.valueOf(millis + "#" + random);
    }

    /**
     * Метод заменяет Item в массиве items.
     *
     * @param id   номер заявки, который нужно заменить.
     * @param item на эту заяку меняем.
     */
    public boolean replace(String id, Item item) {
        boolean result;
        Item temp = this.findById(id);
        if (temp != null) {
            item.setId(temp.getId());
            items.set(items.indexOf(temp), item);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Метод узаляет заявку из массива.
     *
     * @param id номер заявки, которую нужно удалить.
     */
    public boolean delete(String id) {
        return items.remove(this.findById(id));
    }


    /**
     * Метод показывает все заявки.
     *
     * @return массив заявок.
     */
    public ArrayList<Item> findAll() {
        return this.items;
    }

    /**
     * Метод находит дубликаты имен заявок.
     *
     * @param key Имя заявки для поиска.
     * @return массив дубликатов.
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> result = new ArrayList<>();
        this.items.stream().filter(item -> item.getName().equals(key)).forEach(result::add);
        return result;
    }

    /**
     * Метод находит заявку по ее номеру.
     *
     * @param id номер заявки
     * @return заявка.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                result = item;
            }
        }
        return result;
    }

//    private int getIndexAsInt(String id) {
//        int index = -1;
//        for (int i = 0; i < this.position; i++) {
//            if (items[i].getId().equals(id)) {
//                index = i;
//            }
//        }
//        return index;
//    }
}
