package ru.job4j.tracker;

public class MenuOutException extends RuntimeException {

    public MenuOutException(String msg) {
        System.out.print(msg);
    }
}
