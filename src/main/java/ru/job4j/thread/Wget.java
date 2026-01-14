package ru.job4j.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        var file = new File("downloaded.xml");
        try (var input = new URL(url).openStream();
        var output = new FileOutputStream(file)) {
            var dataBuffer = new byte[1024];
            int bytesRead;
            var totalBytes = 0;
            var currTime = System.currentTimeMillis();
            while ((bytesRead = input.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                totalBytes += bytesRead;
                if (totalBytes >= speed) {
                    var measuredTime = System.currentTimeMillis() - currTime;
                    if (1000 > measuredTime) {
                        Thread.sleep(1000 - measuredTime);
                    }
                    currTime = System.currentTimeMillis();
                    totalBytes = 0;
                }
                output.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean validateInput(String url, int speed) {
        if (speed <= 0) {
            System.out.println("Скорость не может быть отрицательной, введите корректное значение скорости");
            return false;
        }
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            System.out.println("Неверный формат адреса URL");
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            throw new RuntimeException("Необходимо указать адрес для скачивания и ограничение по скорости");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        if (validateInput(url, speed)) {
            Thread wget = new Thread(new Wget(url, speed));
            wget.start();
            wget.join();
        }
    }
}