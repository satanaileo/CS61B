package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

/**
 * satanaileo
 * 2022/2/15 21:34
 */
public class TestArrayDequeEC {

    @Test
    public void testArray() {
        StringBuilder sb = new StringBuilder("\n");
        StudentArrayDeque<Integer> st = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        for (int i = 0; i < 100; i += 1) {
            double randNum = StdRandom.uniform();
            if (randNum < 0.25) {
                st.addFirst(i);
                solution.addFirst(i);
                sb.append("addFirst(" + i + ")\n");
                Integer expected = solution.get(0);
                Integer actual = st.get(0);
                assertEquals(sb.toString(), expected, actual);
            } else if (randNum < 0.5) {
                st.addLast(i);
                solution.addLast(i);
                sb.append("addLast(" + i + ")\n");
                Integer expected = solution.get(solution.size() - 1);
                Integer actual = st.get(st.size() - 1);
                assertEquals(sb.toString(), expected, actual);
            } else if (randNum < 0.75) {
                sb.append("removeFirst()\n");
                assertEquals(sb.toString(), solution.size(), st.size());
                if (solution.size() > 0) {
                    Integer actual = st.removeFirst();
                    Integer expected = solution.removeFirst();
                    assertEquals(sb.toString(), expected, actual);
                }
            } else {
                sb.append("removeFirst()\n");
                assertEquals(sb.toString(), solution.size(), st.size());
                if (solution.size() > 0) {
                    Integer actual = st.removeLast();
                    Integer expected = solution.removeLast();
                    assertEquals(sb.toString(), expected, actual);
                }
            }

        }
    }
}
