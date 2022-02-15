package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private final Node sentinel;

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<T> {
        private Node iter = sentinel;

        @Override
        public boolean hasNext() {
            return iter.next != sentinel;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T returnItem = iter.next.item;
                iter = iter.next;
                return returnItem;
            }
            return null;
        }
    }

    private class Node {
        private Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
        private T item;
        private Node prev;
        private Node next;
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T item) {
        sentinel.next.prev = sentinel.next = new Node(item, sentinel, sentinel.next);
        size += 1;
    }

    @Override
    public void addLast(T item) {
        sentinel.prev.next = sentinel.prev = new Node(item, sentinel.prev, sentinel);
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node tmp = sentinel.next;
        for (int i = 0; i < size; i += 1) {
            System.out.print(tmp.item + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T tmp = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return tmp;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T tmp = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return tmp;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Node tmp = sentinel.next;
        for (int i = 0; i < index; i += 1) {
            tmp = tmp.next;
        }
        return tmp.item;
    }

    public T getRecursive(int index) {
        if (index == 0) {
            return sentinel.next.item;
        } else {
            T first = removeFirst();
            T ans = getRecursive(index - 1);
            addFirst(first);
            return ans;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> cmp = (Deque) o;
        if (cmp.size() != size()) {
            return false;
        }
        for (int i = 0; i < size(); i += 1) {
            if (!(cmp.get(i).equals(this.get(i)))) {
                return false;
            }
        }
        return true;
    }
}
