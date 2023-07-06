package com.task.project;

import java.util.Comparator;
import java.util.List;

/**
 * Implementation of quick sort
 * @author Eugene Budnichenko
 */
public class QuickSort<T> {
    private final List<T> list;
    private final Comparator<? super T> comparator;

    public QuickSort(List<T> list) {
        this(list, null);
    }

    public QuickSort(List<T> list, Comparator<? super T> comparator) {
        this.list = list;
        this.comparator = comparator;
    }

    /**
     * Sorts the list in special order.
     */
    public void sort() {
        if (list == null || list.size() <= 1) {
            return; // Nothing to sort
        }

        quickSort(0, list.size() - 1);
    }

    /**
     * Recursively sorts the sublist from the given low index to the given high index.
     *
     * @param low  the starting index of the sublist
     * @param high the ending index of the sublist
     */
    private void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }

    /**
     * Partitions the sublist from the given low index to the given high index
     * by selecting a pivot element and rearranging the surrounding elements.
     *
     * @param low  the starting index of the sublist
     * @param high the ending index of the sublist
     * @return the final index of the pivot element after partitioning
     */
    private int partition(int low, int high) {
        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(list.get(j), pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);
        return i + 1;
    }

    /**
     * Swaps the elements at the given indices in the list.
     *
     * @param i the index of the first element
     * @param j the index of the second element
     */
    private void swap(int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * Compares two elements using the provided comparator or their natural ordering.
     * If neither a comparator is provided nor the elements implement the Comparable interface,
     * an UnsupportedOperationException is thrown.
     * @param a the first element to be compared
     * @param b the second element to be compared
     * @return a negative integer if a is less than b, zero if they are equal,
     */
    private int compare(T a, T b) {
        if (comparator != null) {
            return comparator.compare(a, b);
        } else if (a instanceof Comparable) {
            return ((Comparable<T>) a).compareTo(b);
        } else {
            throw new UnsupportedOperationException("Elements must be comparable or a comparator must be provided.");
        }
    }
}
