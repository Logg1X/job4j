package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Date;

public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
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
        long millis = date.getTime();
        int  random =  (int) (Math.random()*100);
        return String.valueOf(millis + "#" + random);
    }

    public void replace(String id, Item item) {
        this.items[getIndexAsInt(id)] = item;
    }

    public void delete(String id) {
        int index = getIndexAsInt(id);
        int numMoved = items.length - index - 1;
        System.arraycopy(items, index + 1, items, index, numMoved);
    }

    public Item[] findAll() {
        return null;

    }

    public Item[] findByName(String key) {
        Item[] result ;
        for (Item item : items) {
            if (item.getName().equals(key)) {

            }
        }
        return null;
    }


    public Item findById(String id) {
        int index = getIndexAsInt(id);
        return index > 0 ? items[index] : null;
    }

    private int getIndexAsInt(String id) {
        int index = -1;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                index = Arrays.asList(items).indexOf(item);
            }
        }
        return index;
    }
}
