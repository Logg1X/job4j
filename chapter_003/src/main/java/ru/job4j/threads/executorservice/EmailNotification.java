package ru.job4j.threads.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

public class EmailNotification {
    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        pool.submit(() -> {
                    this.send(
                            "Notification " + user.getName() + " to email" + user.getEmail() + ".",
                            "Add a new event to " + user.getName(),
                            user.getEmail()
                    );
                    System.out.println(
                            "Поток " + Thread.currentThread().getName() + System.lineSeparator()
                                    + " отправил сообение на " + user.getEmail()
                    );
                }
        );
    }

    public void send(String suject, String body, String email) {
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
