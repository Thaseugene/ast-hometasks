package com.task.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModernArrayListTest {
    private ModernArrayList<Integer> modernList;

    @BeforeEach
    public void setup() {
        modernList = new ModernArrayList<>();
        modernList.add(1);
        modernList.add(2);
        modernList.add(3);
    }

    @Test
    public void testSize() {
        // Given
        // The list is initialized in setup() method

        // When
        int size = modernList.size();

        // Then
        assertEquals(3, size);
    }

    @Test
    public void testIsEmpty() {
        // Given
        // The list is initialized in setup() method

        // When
        boolean isEmpty = modernList.isEmpty();

        // Then
        assertFalse(isEmpty);
    }

    @Test
    public void testContains() {
        // Given
        // The list is initialized in setup() method

        // When
        boolean contains2 = modernList.contains(2);
        boolean contains4 = modernList.contains(4);

        // Then
        assertTrue(contains2);
        assertFalse(contains4);
    }

    @Test
    public void testAdd() {
        // Given
        // The list is initialized in setup() method

        // When
        boolean added = modernList.add(4);
        int size = modernList.size();

        // Then
        assertTrue(added);
        assertEquals(4, size);
    }

    @Test
    public void testRemove() {
        // Given
        // The list is initialized in setup() method

        // When
        boolean removed = modernList.remove(Integer.valueOf(2));
        boolean contains2 = modernList.contains(2);
        int size = modernList.size();

        // Then
        assertTrue(removed);
        assertFalse(contains2);
        assertEquals(2, size);
    }

    @Test
    public void testGet() {
        // Given
        // The list is initialized in setup() method

        // When
        int element = modernList.get(1);

        // Then
        assertEquals(2, element);
    }

    @Test
    public void testSet() {
        // Given
        // The list is initialized in setup() method

        // When
        int previousElement = modernList.set(1, 10);
        int newElement = modernList.get(1);

        // Then
        assertEquals(2, previousElement);
        assertEquals(10, newElement);
    }

    @Test
    public void testAddAtIndex() {
        // Given
        // The list is initialized in setup() method

        // When
        modernList.add(1, 5);
        int addedElement = modernList.get(1);
        int size = modernList.size();

        // Then
        assertEquals(5, addedElement);
        assertEquals(4, size);
    }

    @Test
    public void testRemoveAtIndex() {
        // Given
        // The list is initialized in setup() method

        // When
        int removedElement = modernList.remove(1);
        int size = modernList.size();

        // Then
        assertEquals(2, removedElement);
        assertEquals(2, size);
    }

    @Test
    public void testIndexOf() {
        // Given
        // The list is initialized in setup() method

        // When
        int index2 = modernList.indexOf(2);
        int index4 = modernList.indexOf(4);

        // Then
        assertEquals(1, index2);
        assertEquals(-1, index4);
    }

    @Test
    public void testLastIndexOf() {
        // Given
        // The list is initialized in setup() method

        // When
        modernList.add(2);
        int lastIndex2 = modernList.lastIndexOf(2);

        // Then
        assertEquals(3, lastIndex2);
    }

    @Test
    public void testToArray() {
        // Given
        // The list is initialized in setup() method

        // When
        Object[] expectedArray = {1, 2, 3};
        Object[] array = modernList.toArray();

        // Then
        assertArrayEquals(expectedArray, array);
    }

    @Test
    public void testToArrayWithArgument() {
        // Given
        // The list is initialized in setup() method

        // When
        Integer[] expectedArray = {1, 2, 3};
        Integer[] actualArray = new Integer[3];
        Integer[] array = modernList.toArray(actualArray);

        // Then
        assertArrayEquals(expectedArray, array);
    }

    @Test
    public void testSubList() {
        // Given
        // The list is initialized in setup() method

        // When
        List<Integer> subList = modernList.subList(1, 3);
        List<Integer> expectedSubList = Arrays.asList(2, 3);

        // Then
        assertEquals(expectedSubList, subList);
    }

    @Test
    public void testRetainAll() {
        // Given
        List<Integer> retainList = new ModernArrayList<>();
        retainList.add(2);
        retainList.add(3);

        // When

        boolean retained = modernList.retainAll(retainList);
        int size = modernList.size();
        int firstElement = modernList.get(0);
        int secondElement = modernList.get(1);
        // Then
        assertTrue(retained);
        assertEquals(2, size);
        assertEquals(2, firstElement);
        assertEquals(3, secondElement);
    }

    @Test
    public void testRemoveAll() {
        // Given
        List<Integer> removeList = new ModernArrayList<>();
        removeList.add(2);
        removeList.add(3);

        // When
        boolean removed = modernList.removeAll(removeList);
        int size = modernList.size();
        int firstElement = modernList.get(0);

        // Then
        assertTrue(removed);
        assertEquals(1, size);
        assertEquals(1, firstElement);
    }

    @Test
    public void testAddAll() {
        // Given
        List<Integer> addList = new ModernArrayList<>();
        addList.add(4);
        addList.add(5);

        // When
        boolean added = modernList.addAll(addList);
        int size = modernList.size();
        int fourthElement = modernList.get(3);
        int fifthElement = modernList.get(4);

        // Then
        assertTrue(added);
        assertEquals(5, size);
        assertEquals(4, fourthElement);
        assertEquals(5, fifthElement);
    }

    @Test
    public void testAddAllAtIndex() {
        // Given
        List<Integer> addList = new ModernArrayList<>();
        addList.add(4);
        addList.add(5);

        // When
        boolean added = modernList.addAll(1, addList);
        int size = modernList.size();
        int firstElement = modernList.get(1);
        int secondElement = modernList.get(2);

        // Then
        assertTrue(added);
        assertEquals(5, size);
        assertEquals(4, firstElement);
        assertEquals(5, secondElement);
    }

    @Test
    public void testClear() {
        // Given
        // The list is initialized in setup() method

        // When
        modernList.clear();
        boolean isEmpty = modernList.isEmpty();

        // Then
        assertTrue(isEmpty);
    }
}