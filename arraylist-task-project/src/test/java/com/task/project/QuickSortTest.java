package com.task.project;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    public void testSort_IntegerList() {
        // Given
        List<Integer> list = new ModernArrayList<>();
        list.add(5);
        list.add(2);
        list.add(9);
        list.add(1);
        list.add(7);
        QuickSort<Integer> quickSort = new QuickSort<>(list);

        // When
        quickSort.sort();

        // Then
        List<Integer> expected = Arrays.asList(1, 2, 5, 7, 9);
        assertEquals(expected, list);
    }

    @Test
    public void testSort_StringList() {
        // Given
        List<String> list = new ModernArrayList<>();
        list.add("a");
        list.add("c");
        list.add("b");
        list.add("e");
        list.add("d");
        Comparator<String> charComparator = Comparator.naturalOrder();
        QuickSort<String> quickSort = new QuickSort<>(list, charComparator);

        // When
        quickSort.sort();

        // Then
        List<String> expected = Arrays.asList("a", "b", "c", "d", "e");
        assertEquals(expected, list);
    }
}