package ru.job4j.collections.models;

public class User implements Comparable<User> {
    private int id;
    private int age;
    private String name;
    private String city;


    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @Override
    public int compareTo(User o) {
        return Integer.compare(this.age, o.age);
    }

    @Override
    public String toString() {
        return "Id: " + this.id + System.lineSeparator()
                + "Age: " + this.age + System.lineSeparator()
                + "Name: " + this.name + System.lineSeparator()
                + "City: " + this.city + System.lineSeparator();
    }
}
