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
        RolColSum.Sums[] sums = RolColSum.sum(array);
        assertThat(sums[0].getColSum()).isEqualTo(4);
        assertThat(sums[1].getRowSum()).isEqualTo(4);
    }

    @Test
    void whenAsyncVersion() {
        int[][] array = new int[4][4];
        for (int col = 0; col < array.length; col++) {
            Arrays.fill(array[col], 1);
        }
        RolColSum.Sums[] sums = RolColSum.asyncSum(array);
        assertThat(sums[0].getColSum()).isEqualTo(4);
        assertThat(sums[0].getRowSum()).isEqualTo(4);
        assertThat(sums[1].getColSum()).isEqualTo(4);
        assertThat(sums[1].getRowSum()).isEqualTo(4);
        assertThat(sums[2].getColSum()).isEqualTo(4);
        assertThat(sums[2].getRowSum()).isEqualTo(4);
        assertThat(sums[3].getColSum()).isEqualTo(4);
        assertThat(sums[3].getRowSum()).isEqualTo(4);
    }
}