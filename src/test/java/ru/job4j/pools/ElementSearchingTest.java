package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ElementSearchingTest {
    @Test
    void whenThereIsNoSuchElement() {
        String[] strings = new String[]{"One", "Two", "Three"};
        String element = "four";
        assertThat(ElementSearching.find(strings, element)).isEqualTo(-1);
    }

    @Test
    void whenIntegerArrayAndElementExists() {
        Integer[] elements = IntStream.range(-20, 100).boxed().toArray(Integer[]::new);
        int element = 47;
        assertThat(ElementSearching.find(elements, element)).isEqualTo(67);
    }
}