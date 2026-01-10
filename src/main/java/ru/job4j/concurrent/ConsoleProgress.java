package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        var index = 4;
        var process = new char[] {'-', '\\', '|', '/'};
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.print("\rLoading ..." + process[index++ % 4]);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("\r\nПроцесс завершен");
                }
            }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}
