package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testSize() {
        Deque<Integer> test = new ArrayDeque<>();
        for (int i = 0; i < 8; i += 1) {
            test.addLast(i);
        }
        for (int i = 0; i < 8; i += 1) {
            test.addFirst(i + 8);
        }
        assertEquals(16, test.size());
    }

    @Test
    public void testGet() {
        Deque<Integer> test = new ArrayDeque<>();
        for (int i = 0; i < 32; i += 1) {
            test.addLast(i);
        }
        for (int i = 0; i < 32; i += 1) {
            assertEquals(i, (long) test.get(i));
        }
        for (int i = 0; i < 32; i += 1) {
            test.removeLast();
        }
        for (int i = 0; i < 32; i += 1) {
            test.addFirst(i);
        }
        for (int i = 0; i < 32; i += 1) {
            assertEquals(31 - i, (long) test.get(i));
        }
    }

    @Test
    public void testRemoveFirst() {
        Deque<Integer> test = new ArrayDeque<>();
        for (int i = 0; i < 8; i += 1) {
            test.addLast(i);
        }
        test.removeFirst();
        assertEquals(1, (long) test.get(0));
        assertEquals(7, test.size());
    }

    @Test
    public void testRemoveLast() {
        Deque<Integer> test = new ArrayDeque<>();
        for (int i = 0; i < 8; i += 1) {
            test.addLast(i);
        }
        test.removeLast();
        assertEquals(6, (long) test.get(test.size() - 1));
        assertEquals(7, test.size());
    }

    @Test
    public void testAddRemove() {
        Deque<Integer> test = new ArrayDeque<>();
        for (int i = 0; i < 8; i += 1) {
            test.addFirst(i);
        }
        for (int i = 0; i < 8; i += 1) {
            test.removeLast();
        }
        assertEquals(test.size(), 0);
    }

    @Test
    public void testIterator() {
        ArrayDeque<String> lld1 = new ArrayDeque<>();
        lld1.addFirst("abc");
        lld1.addFirst("b");
        lld1.addFirst("c");
        int i = 0;
        for (String item : lld1) {
            assertEquals(item, lld1.get(i));
            i += 1;
        }
    }
}
