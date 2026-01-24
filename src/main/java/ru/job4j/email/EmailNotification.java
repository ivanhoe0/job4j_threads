package ru.job4j.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        var subject = "Notification + " + user.getName() + "to email " + user.getEmail();
        var body = "Add a new event to " + user.getName();
        pool.submit(() -> send(subject, body, user.getEmail()));
    }

    public void close() {
        pool.shutdown();
    }

    public void send(String subject, String body, String email) {

    }
}
