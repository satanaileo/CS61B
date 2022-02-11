package deque;


public class ArrayDeque<T> implements Deque<T> {
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
        if (size() == arr.length) {
            resize(arr.length * 2);
        }
        if (start == 0) {
            if (size != 0) {
                start = arr.length - 1;
            } else {
                end = 1;
            }
        } else {
            start -= 1;
        }
        arr[start] = item;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size() == arr.length) {
            resize(arr.length * 2);
        }
        arr[end] = item;
        if (end != arr.length - 1) {
            end += 1;
        } else {
            end = 0;
        }
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
        T temp = arr[start];
        arr[start] = null;
        if (start == arr.length - 1) {
            start = 0;
        } else {
            start += 1;
        }
        size -= 1;
        return temp;
    }

    @Override
    public T removeLast() {
        T temp;
        if (end == 0) {
            temp = arr[arr.length - 1];
            arr[arr.length - 1] = null;
            end = arr.length - 1;
        } else {
            temp = arr[end - 1];
            arr[end - 1] = null;
            end -= 1;
        }
        size -= 1;
        return temp;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int tmp = start + index - arr.length;
        if (tmp < 0) {
            return arr[start + index];
        } else {
            return arr[tmp];
        }
    }
}
