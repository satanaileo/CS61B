package deque;

public interface Deque<T> {
    /**
    * Adds an item of type T to the front of the deque. You can assume that item is never null.
    */
    public void addFirst(T item);

    /**
    * Adds an item of type T to the back of the deque. You can assume that item is never null.
    */
    public void addLast(T item);

    /***
    * @Author satanaileo
    * @Description Returns true if deque is empty, false otherwise.
    * @Date 2022/2/11
    * @return boolean
    */
    default public boolean isEmpty() {
        return size() == 0;
    }

    /***
    * @Author satanaileo
    * @Description Returns the number of items in the deque.
    * @Date 2022/2/11
    * @return int
    */
    public int size();

    /***
    * @Author satanaileo
    * @Description Prints the items in the deque from first to last,
     * separated by a space. Once all the items have been printed, print out a new line.
    * @Date 2022/2/11
    */
    public void printDeque();

    /***
    * @Author satanaileo
    * @Description Removes and returns the item at the front of the deque. If no such item exists, returns null.
    * @Date 2022/2/11
    * @return T
    */
    public T removeFirst();

    /***
    * @Author satanaileo
    * @Description Removes and returns the item at the back of the deque. If no such item exists, returns null.
    * @Date 2022/2/11
    * @return T
    */
    public T removeLast();

    /***
    * @Author satanaileo
    * @Description Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
    * @Date 2022/2/11
    * @return T
    */
    public T get(int index);
}
