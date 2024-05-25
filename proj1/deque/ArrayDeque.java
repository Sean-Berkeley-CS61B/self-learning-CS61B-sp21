package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private int j;
    private int k;
    private int nextFirst = 3;
    private int nextLast = 4;
    private int sizecpy;
    private T[] iniDeque;
    public ArrayDeque() {
        iniDeque = (T[]) new Object[8];
        size = 0;
    }
    private void expandsize() {
        T[] a = (T[]) new Object[iniDeque.length * 2];
        if (nextLast == 0) {
            System.arraycopy(iniDeque, 0, a, a.length - size, size);
            nextFirst = a.length - size - 1;
            iniDeque = a;
        } else {
            System.arraycopy(iniDeque, 0, a, 0, nextLast);
            System.arraycopy(iniDeque, nextLast, a, a.length - (size - nextFirst) + 1, size - nextLast);
            nextFirst = a.length - (size - nextFirst);
            iniDeque = a;
        }
    }
    @Override
    public void addFirst(T x) {
        if (size == iniDeque.length) {
            expandsize();
        }
        iniDeque[nextFirst] = x;
        size = size + 1;
        if (nextFirst == 0) {
            nextFirst = iniDeque.length - 1;
        } else {
            nextFirst = nextFirst - 1;
        }
    }
    @Override
    public void addLast(T x) {
        if (size == iniDeque.length) {
            expandsize();
        }
        iniDeque[nextLast] = x;
        size = size + 1;
        if (nextLast == iniDeque.length - 1) {
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
        if (nextFirst == iniDeque.length - 1) {
            for (int i = 0; i < sizecpy; i++) {
                System.out.print(iniDeque[i] + " ");
            }
        } else {
            j = nextFirst + 1;
            while (j < iniDeque.length && iniDeque[j] != null) {
                System.out.print(iniDeque[j] + " ");
                j = j + 1;
            }
            k = 0;
            while (iniDeque[k] != null && k <= this.size() - (iniDeque.length - nextFirst)) {
                System.out.print(iniDeque[k] + " ");
                k = k + 1;
            }
        }
        System.out.println("");
    }
    private void reducelength() {
        T[] a = (T[]) new Object[(int) Math.ceil(iniDeque.length * 0.25)];
        if (nextLast != 0 && nextLast <= nextFirst) {
            System.arraycopy(iniDeque, 0, a, 0, nextLast);
            int fstHf = size - nextLast;
            System.arraycopy(iniDeque, nextFirst + 1, a, a.length - (iniDeque.length - fstHf), fstHf);
            nextFirst = a.length - (iniDeque.length - fstHf) - 1;
            iniDeque = a;
        } else {
            System.arraycopy(iniDeque, nextFirst + 1, a, a.length - size, size);
            nextFirst = a.length - size - 1;
            nextLast = 0;
            iniDeque = a;
        }
    }
    @Override
    public T removeFirst() {
        if (this.size() == 0) {
            return null;
        }
        if (this.size() <= Math.ceil(0.25 * iniDeque.length)) {
            this.reducelength();
        }
        size = size - 1;
        if (nextFirst == iniDeque.length - 1) {
            nextFirst = 0;
            return iniDeque[0];
        } else {
            nextFirst = nextFirst + 1;
            return iniDeque[nextFirst];
        }
    }
    @Override
    public T removeLast() {
        if (this.size() == 0) {
            return null;
        }
        if (this.size() <= Math.ceil(0.25 * iniDeque.length)) {
            this.reducelength();
        }
        size = size - 1;
        if (nextLast == 0) {
            nextLast = iniDeque.length - 1;
            return iniDeque[iniDeque.length - 1];
        } else {
            nextLast = nextLast - 1;
            return iniDeque[nextLast];
        }
    }
    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size()) {
            return null;
        }
        if (index < iniDeque.length - nextFirst - 1) {
            return iniDeque[nextFirst + index + 1];
        } else {
            return iniDeque[index - (iniDeque.length - nextFirst - 1)];
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
