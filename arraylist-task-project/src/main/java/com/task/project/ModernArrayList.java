package com.task.project;

import java.lang.reflect.Array;
import java.util.*;

public class ModernArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private T[] elements;
    private int size;

    public ModernArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ModernArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Invalid initial capacity: " + initialCapacity);
        }
        elements = (T[]) new Object[initialCapacity];
        size = 0;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if this list is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * @param o the element to be checked for containment in this list.
     * @return true if this list contains the specified element, false otherwise.
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * @return an iterator over the elements in this list.
     */
    @Override
    public Iterator<T> iterator() {
        return new ModernListIterator();
    }

    /**
     * @return an array containing all the elements in this list
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    /**
     * @param a the array into which the elements of this list are to be stored,
     *          if it is big enough; otherwise, a new array of the same runtime type is allocated
     * @param <E> the type of the elements in the returned array
     * @return an array containing all the elements in this list
     */
    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size) {
            return (E[]) copyElements(size, a.getClass());
        }
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    /**
     * @param newSize the size of the new array
     * @param newType the component type of the new array
     * @return a new array containing the copied elements
     */
    private Object[] copyElements(int newSize, Class<?> newType) {
        Object[] newArray = (Object[]) Array.newInstance(newType.getComponentType(), newSize);
        System.arraycopy(elements, 0, newArray, 0, Math.min(size, newSize));
        return newArray;
    }

    /**
     * @param element the element to be added to this list
     * @return true (as specified by Collection.add(E))
     */
    @Override
    public boolean add(T element) {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
        elements[size++] = element;
        return true;
    }

    /**
     * @param o the element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * @param collection the collection to be checked for containment in this list
     * @return true if this list contains all the elements of the specified collection, false otherwise
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object obj : collection) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param collection the collection containing elements to be added to this list
     * @return true if this list changed as a result of the call, false otherwise
     */
    @Override
    public boolean addAll(Collection<? extends T> collection) {
        for (T element : collection) {
            add(element);
        }
        return !collection.isEmpty();
    }

    /**
     * @param index the index at which to insert the first element from the specified collection
     * @param collection the collection containing elements to be added to this list
     * @return true if this list changed as a result of the method request
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        int i = index;
        for (T element : collection) {
            add(i++, element);
        }
        return !collection.isEmpty();
    }

    /**
     * @param collection the collection containing elements to be removed from this list
     * @return true if this list changed as a result of the call, false otherwise
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        Objects.requireNonNull(collection);
        boolean modified = false;
        Iterator<?> iterator = iterator();
        while (iterator.hasNext()) {
            if (collection.contains(iterator.next())) {
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * @param collection the collection containing elements to be retained in this list
     * @return true if this list changed as a result of the call, false otherwise
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        Objects.requireNonNull(collection);
        boolean modified = false;
        Iterator<?> iterator = iterator();
        while (iterator.hasNext()) {
            if (!collection.contains(iterator.next())) {
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes all the elements from this list.
     */
    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    /**
     * Retrieves the element at the specified index in this list.
     *
     * @param index the index of the element to retrieve.
     * @return the element at the specified index in this list.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size).
     */
    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }

    /**
     * @param index   the index of the element to replace.
     * @param element the new element to be stored at the specified index.
     * @return the element at the specified index.
     */
    @Override
    public T set(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        T oldValue = elements[index];
        elements[index] = element;
        return oldValue;
    }

    /**
     * @param index   the index at which to insert the specified element.
     * @param element the element to insert.
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * @param index the index of the element to remove.
     * @return the element that was removed from the list.
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        T storedElement = elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return storedElement;
    }

    /**
     * @param o - the element of list.
     * @return the value of index.
     */
    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * @param o - the element of list.
     * @return the value of index.
     */
    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * @return a list iterator
     */
    @Override
    public ListIterator<T> listIterator() {
        return new ModernListIterator();
    }

    /**
     * @param index the index to start the iterator from
     * @return a list iterator
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return new ModernListIterator(index);
    }

    /**
     * @param fromIndex the starting index (inclusive) of the subList
     * @param toIndex the ending index (exclusive) of the subList
     * @return a subList view of this list
     */
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        int subListSize = toIndex - fromIndex;
        ModernArrayList<T> subList = new ModernArrayList<>(subListSize);
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(get(i));
        }
        return subList;
    }

    /**
     * An iterator over the elements in the list.
     */
    private class ModernListIterator implements ListIterator<T> {
        private int cursor;
        private int lastRet = -1;

        public ModernListIterator() {
            this(0);
        }

        public ModernListIterator(int index) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
            }
            cursor = index;
        }

        /**
         * @return true if the list iterator has more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        /**
         * @return the next element in the list
         * @throws NoSuchElementException if there are no more elements in the list
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastRet = cursor;
            return elements[cursor++];
        }

        /**
         * @return true if the list iterator has more elements, false otherwise
         */
        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        /**
         * @return the previous element in the list
         */
        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            lastRet = --cursor;
            return elements[cursor];
        }

        /**
         * @return the index of the next element
         */
        @Override
        public int nextIndex() {
            return cursor;
        }

        /**
         * @return the index of the previous element
         */
        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        /**
         * Removes the last element returned by next() or previous() from the list.
         */
        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            ModernArrayList.this.remove(lastRet);
            if (lastRet < cursor) {
                cursor--;
            }
            lastRet = -1;
        }

        /**
         * @param element the element to set
         */
        @Override
        public void set(T element) {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            ModernArrayList.this.set(lastRet, element);
        }

        /**
         * @param element the element to insert
         */
        @Override
        public void add(T element) {
            ModernArrayList.this.add(cursor++, element);
            lastRet = -1;
        }
    }
}