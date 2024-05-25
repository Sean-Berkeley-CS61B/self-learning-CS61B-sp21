package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private int j;
    private int k;
    private int nextFirst = 3;
    private int nextLast = 4;
    private int sizecpy;
    private T[] Ts;
    public ArrayDeque() {
        Ts = (T[]) new Object[8];
        size = 0;
    }
    private void expandsize() {
        T[] a = (T[]) new Object[Ts.length * 2];
        if (nextLast == 0) {
            System.arraycopy(Ts, 0, a, a.length - size, size);
            nextFirst = a.length - size - 1;
            Ts = a;
        } else {
            System.arraycopy(Ts, 0, a, 0, nextLast);
            System.arraycopy(Ts, nextLast, a, a.length - (size - nextFirst) + 1, size - nextLast);
            nextFirst = a.length - (size - nextFirst);
            Ts = a;
        }
    }
    @Override
    public void addFirst(T x) {
        if (size == Ts.length) {
            expandsize();
        }
        Ts[nextFirst] = x;
        size = size + 1;
        if (nextFirst == 0) {
            nextFirst = Ts.length - 1;
        } else {
            nextFirst = nextFirst - 1;
        }
    }
    @Override
    public void addLast(T x) {
        if (size == Ts.length) {
            expandsize();
        }
        Ts[nextLast] = x;
        size = size + 1;
        if (nextLast == Ts.length - 1) {
            nextLast = 0;
        } else {
            nextLast = nextLast + 1;
        }
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        sizecpy = this.size();
        if (nextFirst == Ts.length - 1) {
            for (int i = 0; i < sizecpy; i++) {
                System.out.print(Ts[i] + " ");
            }
        } else {
            j = nextFirst + 1;
            while (j < Ts.length && Ts[j] != null) {
                System.out.print(Ts[j] + " ");
                j = j + 1;
            }
            k = 0;
            while (Ts[k] != null && k <= this.size() - (Ts.length - nextFirst)) {
                System.out.print(Ts[k] + " ");
                k = k + 1;
            }
            }
        System.out.println("");
    }
    private void reducelength() {
        T[] a = (T[]) new Object[(int) Math.round(Ts.length * 0.5)];
        if (nextLast != 0 && nextLast <= nextFirst) {
            System.arraycopy(Ts, 0, a, 0, nextLast);
            System.arraycopy(Ts, nextFirst + 1, a, a.length - (Ts.length - (nextFirst + 1)), Ts.length - (nextFirst + 1));
            nextFirst = a.length - (Ts.length - (nextFirst + 1)) + 1;
            Ts = a;
        } else {
            System.arraycopy(Ts, nextFirst + 1, a, a.length - size, size);
            nextFirst = a.length - size - 1;
            nextLast = 0;
            Ts = a;
        }
    }
    @Override
    public T removeFirst() {
        if (this.size() == 0) {
            return null;
        }
        if (this.size() <= Math.round(0.3 * Ts.length)) {
            this.reducelength();
        }
        size = size - 1;
        if (nextFirst == Ts.length - 1) {
            nextFirst = 0;
            return Ts[0];
        } else {
            nextFirst = nextFirst + 1;
            return Ts[nextFirst];
        }
    }
    @Override
    public T removeLast() {
        if (this.size() == 0) {
            return null;
        }
        if (this.size() <= Math.round(0.3 * Ts.length)) {
            this.reducelength();
        }
        size = size - 1;
        if (nextLast == 0) {
            nextLast = Ts.length - 1;
            return Ts[Ts.length - 1];
        } else {
            nextLast = nextLast - 1;
            return Ts[nextLast];
        }
    }
    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size()) {
            return null;
        }
        if (index < Ts.length - nextFirst - 1) {
            return Ts[nextFirst + index + 1];
        } else {
            return Ts[index - (Ts.length - nextFirst - 1)];
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;
        public ArrayDequeIterator() {
            wizPos = 0;
        }
        public boolean hasNext() {
            return wizPos < size;
        }
        public T next() {
            T returnT = get(wizPos);
            wizPos += 1;
            return returnT;
        }
    }
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        if (o == null) {
            return false;
        }
        Deque<T> oa = (Deque<T>) o;
        if (oa.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (!(oa.get(i).equals(this.get(i)))) {
                return false;
            }
        }
        return true;
    }
//    public static void main(String[] arg) {
//        ArrayDeque<Integer> ad = new ArrayDeque<>();
//        for (int i = 1; i < 10; i++) {
//            ad.addLast(i);
//        }
//        Iterator<Integer> aditer = ad.iterator();
//        while (aditer.hasNext()) {
//            System.out.println(aditer.next());
//        }
//    }
}
