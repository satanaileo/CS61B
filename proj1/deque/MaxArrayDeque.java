package deque;

import java.util.Comparator;

/**
 * satanaileo
 * 2022/2/15 17:08
 */
public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c) {
        cmp = c;
    }

    public T max() {
        if (size() == 0) {
            return null;
        }
        T maxT = get(0);
        for (int i = 1; i < size(); i += 1) {
            T getI = get(i);
            if (cmp.compare(maxT, getI) < 0) {
                maxT = getI;
            }
        }
        return maxT;
    }

    public T max(Comparator<T> c) {
        cmp = c;
        return max();
    }
}
