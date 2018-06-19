package ru.job4j.tracker;

import java.util.Arrays;
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
    public void replace(String id, Item item) {
        this.items[getIndexAsInt(id)] = item;
    }

    /**
     * Метод узаляет заявку из массива.
     *
     * @param id номер заявки, которую нужно удалить.
     */
    public void delete(String id) {
        int index = getIndexAsInt(id);
        int numMoved = this.items.length - index - 1;
        System.arraycopy(this.items, index + 1, this.items, index, numMoved);
        this.position--;
    }

    /**
     * Метод показывает все заявки.
     *
     * @return массив заявок.
     */
    public Item[] findAll() {
        return Arrays.copyOf(this.items, this.position + 1);

    }

    /**
     * Метод находит дубликаты имен заявок.
     *
     * @param key Имя заявки для поиска.
     * @return массив дубликатов.
     */
    public Item[] findByName(String key) {
        int counter = 0;
        Item[] result = new Item[this.items.length];
        for (Item item : this.items) {
            if (item != null && item.getName().equals(key)) {
                result[counter] = item;
                counter++;
            }
        }
        return Arrays.copyOf(result, counter);
    }

    /**
     * Метод находит заявку по ее номеру.
     *
     * @param id номер заявки
     * @return заявка.
     */
    public Item findById(String id) {
        int index = getIndexAsInt(id);
        return index >= 0 ? this.items[index] : null;
    }

    private int getIndexAsInt(String id) {
        int index = -1;
        for (Item item : this.items) {
            if (item == null) {
                break;
            } else if (item.getId().equals(id)) {
                index = Arrays.asList(this.items).indexOf(item);
            }
        }
        return index;
    }
}
