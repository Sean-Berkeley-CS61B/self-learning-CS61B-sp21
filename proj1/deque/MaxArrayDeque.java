package deque;
import java.util.Comparator;
public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        cmp = c;
    }
    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        T maxie = this.get(0);
        for (T i : this) {
            if (cmp.compare(i, maxie) > 0) {
                maxie = i;
            }
        }
        return maxie;
    }
    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        T maxie = this.get(0);
        for (T i : this) {
            if (c.compare(i, maxie) > 0) {
                maxie = i;
            }
        }
        return maxie;
    }
    private static class IntComparator implements Comparator<Integer> {
        public int compare(Integer a,  Integer b) {
            return a - b;
        }
    }
    private static class ReverseIntComparator implements Comparator<Integer> {
        public int compare(Integer a,  Integer b) {
            return b - a;
        }
    }
}
