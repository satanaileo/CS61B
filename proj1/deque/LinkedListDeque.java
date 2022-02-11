package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private int size;
    private final Node sentinel;

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
}
