package ru.job4j.collections.models;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class Person {
    private String name;
    private String surname;
    private String phone;
    private String address;

    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Имя: " + name + System.lineSeparator()
                + "Фамилия: " + surname + System.lineSeparator()
                + "Тел.: " + phone + System.lineSeparator()
                + "Адрес: " + address + System.lineSeparator();

    }

}
