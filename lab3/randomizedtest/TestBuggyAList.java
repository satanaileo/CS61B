package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    public static void assertMyEquals(AListNoResizing<Integer> alstNo, BuggyAList<Integer> buggy) {
        int[] expected = new int[alstNo.size()];
        int[] actual = new int[buggy.size()];
        for (int j = 0; j < expected.length; j += 1) {
            expected[j] = alstNo.get(j);
        }
        for (int j = 0; j < actual.length; j += 1) {
            actual[j] = buggy.get(j);
        }
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> alstNo = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();
        for (int i = 4; i <= 6; i += 1) {
            alstNo.addLast(i);
            buggy.addLast(i);
            assertMyEquals(alstNo, buggy);
        }
        for (int i = 0; i < 3; i += 1) {
            alstNo.removeLast();
            buggy.removeLast();
            assertMyEquals(alstNo, buggy);
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();
        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int sizeOfL = L.size();
                int sizeOfB = B.size();
                assertEquals(sizeOfL, sizeOfB);
            } else if (operationNumber == 2) {
                int Lval = 200, Bval = 201;
                if (L.size() > 0) {
                    Lval = L.getLast();
                }
                if (B.size() > 0) {
                    Bval = B.getLast();
                }
                if (L.size() > 0 || B.size() > 0) {
                    assertEquals(Lval, Bval);
                }
            } else if (operationNumber == 3) {
                int Lval = 200, Bval = 201;
                if (L.size() > 0) {
                    Lval = L.removeLast();
                }
                if (B.size() > 0) {
                    Bval = B.removeLast();
                }
                if (L.size() > 0 || B.size() > 0) {
                    assertEquals(Lval, Bval);
                }
            }
        }
    }
}
