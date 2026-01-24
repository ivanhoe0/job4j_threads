package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ElementSearching<T> extends RecursiveTask<Integer> {

    private  final T[] array;
    private final T element;
    private final int from;
    private final int to;

    public ElementSearching(T[] array, T element, int from, int to) {
        this.array = array;
        this.element = element;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from < 10) {
            return findElement(element, from, to);
        }
        int middle = (from + to) / 2;
        ElementSearching<T> leftArray = new ElementSearching<>(array, element, from, middle);
        ElementSearching<T> rightArray = new ElementSearching<>(array, element, middle + 1, to);
        leftArray.fork();
        rightArray.fork();
        return chooseResult(leftArray.join(), rightArray.join());
    }

    public static <T> int find(T[] array, T element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ElementSearching<>(array, element, 0, array.length - 1));
    }

    private int findElement(T element, int from, int to) {
        int result = -1;
        for (int i = from; i <= to; i++) {
            if (array[i].equals(element)) {
                result = i;
            }
        }
        return result;
    }

    private static int chooseResult(int a, int b) {
        if (a != -1) {
            return a;
        } else {
            return b;
        }
    }
}
