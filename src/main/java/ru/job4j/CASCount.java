package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        int curentVal;
        int newVal;
        do {
            curentVal = count.get();
            newVal = curentVal + 1;
        } while (!count.compareAndSet(curentVal, newVal));
    }

    public int get() {
        return count.get();
    }
}