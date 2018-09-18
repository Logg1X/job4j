package ru.job4j.threads.executorservice;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class EmailNotificationTest {
    private EmailNotification executor;
    private User user1;
    private User user2;
    private User user3;
    private User user4;
    private User user5;
    private User user6;
    private List<User> users;

    @Before
    public void setUp() throws Exception {
        executor = new EmailNotification();
        user1 = new User("Pavel", "per4mancerror@gmail.com");
        user2 = new User("Masha", "Mari@rumbler.com");
        user3 = new User("Dima", "Dimasik@cll.com");
        user4 = new User("Valentin", "Lenny314@yahoo.com");
        user5 = new User("Kolya", "Kolebasik99@yandex.com");
        user6 = new User("Aleksey", "Alex-Ovs3000@mail.com");
        users = Arrays.asList(user1, user2, user3, user4, user5, user6);
    }

    @Test
    public void whenEmailToThenSendingMessages() {
        users.forEach(user -> executor.emailTo(user));
        executor.close();
    }
}