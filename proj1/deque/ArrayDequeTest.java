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
        for (int i = 0; i < 15; i += 1) {
            test.addLast(i);
        }
        assertEquals(14, (long) test.get(14));
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
}
