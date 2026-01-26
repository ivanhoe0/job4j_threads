package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class RolColSumTest {
    @Test
    void whenSequentialVersion() {
        int[][] array = new int[4][4];
        for (int col = 0; col < array.length; col++) {
            Arrays.fill(array[col], 1);
        }
        Sums[] sums = RolColSum.sum(array);
        Sums[] expected = new Sums[4];
        Arrays.fill(expected, new Sums(4, 4));
        assertThat(sums).isEqualTo(expected);
    }

    @Test
    void whenAsyncVersion() {
        int[][] array = new int[4][4];
        for (int col = 0; col < array.length; col++) {
            Arrays.fill(array[col], 1);
        }
        Sums[] sums = RolColSum.asyncSum(array);
        Sums[] expected = new Sums[4];
        Arrays.fill(expected, new Sums(4, 4));
        assertThat(sums).isEqualTo(expected);
    }
}