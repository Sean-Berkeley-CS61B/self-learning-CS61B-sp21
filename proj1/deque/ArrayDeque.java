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
    public ArrayDeque(T x) {
        Ts = (T[]) new Object[8];
        Ts[nextFirst] = x;
        nextFirst = nextFirst - 1;
        size = 1;
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
    @Override
    public T removeFirst() {
        if (this.size() == 0) {
            return null;
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
    public class ArrayDequeIterator implements Iterator<T> {
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
    public static void main(String[] args) {
        ArrayDeque<Integer> d1 = new ArrayDeque<>(2);
        d1.addFirst(10);
        d1.addFirst(179);
        d1.addFirst(2334);
        d1.addLast(45254);
        //d1.addLast(1);
        System.out.println(d1.isEmpty());
        //d1.removeFirst();
        //d1.removeLast();
        //d1.removeLast();
//        d1.addFirst(1);
//        d1.addFirst(2);
//        d1.addLast(3);
//        System.out.println(d1.size());
//        System.out.println(d1.get(6));

        d1.printDeque();
        //d2.printDeque();
        //System.out.println(d1.removeFirst());
//        System.out.println(d1.get(1));
//        Iterator<Integer> ader = d1.iteraror();
        for (int i : d1) {
            System.out.println(i);
        }
        ArrayDeque<Integer> d2 = new ArrayDeque<>();
        d2.addFirst(1);
        d2.addFirst(179);
        d2.addFirst(2334);
        d2.addLast(45254);
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            ad1.addLast(i);
        }
        System.out.println(d1.equals(d2));
    }
}
