package deque;


import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] arr;
    private int start, end;
    private int size;
    private final int L = 8;

    public ArrayDeque() {
        arr = (T[]) new Object[L];
        start = 0;
        end = 0;
        size = 0;
    }

    private void resize(int len) {
        T[] newArr = (T[]) new Object[len];
        if (end > start) {
            System.arraycopy(arr, start, newArr, 0, size());
        } else {
            System.arraycopy(arr, start, newArr, 0, arr.length - start);
            System.arraycopy(arr, 0, newArr, arr.length - start, end);
        }
        start = 0;
        end = arr.length;
        arr = newArr;
    }

    @Override
    public void addFirst(T item) {
        if (size == arr.length) {
            resize(arr.length * 2);
        }
        start = ((start - 1) + arr.length) % arr.length;
        arr[start] = item;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size == arr.length) {
            resize(arr.length * 2);
        }
        arr[end] = item;
        end = (end + 1) % arr.length;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (end > start) {
            for (int i = start; i < end; i += 1) {
                System.out.print(arr[i] + " ");
            }
        } else {
            for (int i = start; i < arr.length; i += 1) {
                System.out.print(arr[i] + " ");
            }
            for (int i = 0; i < end; i += 1) {
                System.out.print(arr[i] + " ");
            }
        }

        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (arr.length >= 16 && size == arr.length / 4) {
            resize(arr.length / 2);
        }
        T temp = arr[start];
        arr[start] = null;
        start = (start + 1) % arr.length;
        size -= 1;
        return temp;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (arr.length >= 16 && size == arr.length / 4) {
            resize(arr.length / 2);
        }
        end = (end - 1 + arr.length) % arr.length;
        T temp = arr[end];
        arr[end] = null;
        size -= 1;
        return temp;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return arr[(start + index) % arr.length];
    }

    /***
    * @Author satanaileo
    * @Description Returns the length of the using array.
    * @Date 2022/2/12
    * @return arr.length.
    */
    public int arrLength() {
        return arr.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int iter;

        public ArrayIterator() {
            iter = 0;
        }

        @Override
        public boolean hasNext() {
            return iter < size();
        }

        @Override
        public T next() {
            return arr[iter++];
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<T> cmp = (ArrayDeque) o;
        if (cmp.size() != size()) {
            return false;
        }
        for (int i = 0; i < size(); i += 1) {
            if (cmp.get(i) != this.get(i)) {
                return false;
            }
        }
        return true;
    }
}
