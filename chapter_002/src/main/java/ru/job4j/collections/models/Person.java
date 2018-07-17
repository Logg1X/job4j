package ru.job4j.collections.models;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class Person {
    /**
     * Имя.
     */
    private String name;
    /**
     * Фамилия.
     */
    private String surname;
    /**
     * Телефон.
     */
    private String phone;
    /**
     * Адресс
     */
    private String address;

    /**
     * Конструктор класса.
     * @param name имя
     * @param surname фамилия
     * @param phone телефон
     * @param address фврес
     */
    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Имя: " + name + System.lineSeparator()
                + "Фамилия: " + surname + System.lineSeparator()
                + "Тел.: " + phone + System.lineSeparator()
                + "Адрес: " + address + System.lineSeparator();

    }

}
