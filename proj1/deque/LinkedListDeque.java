package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Intnode {
        private Intnode prev;
        private T T;
        private Intnode next;
        private Intnode(Intnode p, Intnode n) {
            prev = p;
            next = n;
        }
        private Intnode(Intnode p, T i, Intnode n) {
            prev = p;
            T = i;
            next = n;
        }
    }
    private Intnode sentinelFront;
    private Intnode sentinelBack;
    private int size;

    public LinkedListDeque() {
        sentinelFront = new Intnode(null, null);
        sentinelBack = new Intnode(null, null);
        //sentinelFront.next = sentinelBack;
        //sentinelBack.prev = sentinelFront;
        size = 0;
    }
//    public LinkedListDeque(T x) {
//        sentinelFront = new Intnode(null,null);
//        sentinelBack = new Intnode(null, null);
//        Intnode first = new Intnode(null, x, null);
//        sentinelFront.next = first;
//        sentinelBack.prev = first;
//        size = 1;
//    }
    @Override
    public void addFirst(T T) {
        if (this.size() == 0) {
            Intnode t = new Intnode(sentinelFront, T, sentinelBack);
            sentinelFront.next = t;
            sentinelBack.prev = t;
        } else {
            Intnode t = new Intnode(sentinelFront, T, sentinelFront.next);
            sentinelFront.next.prev = t;
            sentinelFront.next = t;
        }
        size = size + 1;
    }
    @Override
    public void addLast(T T) {
        if (this.size() == 0) {
            Intnode u = new Intnode(sentinelFront, T, sentinelBack);
            sentinelFront.next = u;
            sentinelBack.prev = u;
        } else {
            Intnode u = new Intnode(sentinelBack.prev, T, sentinelBack);
            sentinelBack.prev.next = u;
            sentinelBack.prev = u;
        }
        size = size + 1;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        Intnode p = sentinelFront;
        for (int i = 0; i < this.size(); i++) {
            System.out.print(p.next.T + " ");
            p = p.next;
        }
        System.out.println("");
    }
    @Override
    public T removeFirst() {
        Intnode q = sentinelFront.next;
        if (this.size() <= 0) {
            return null;
        } else {
            size = size - 1;
            sentinelFront.next.next.prev = sentinelFront;
            sentinelFront.next = sentinelFront.next.next;
            return q.T;
        }
    }
    @Override
    public  T removeLast() {
        Intnode r = sentinelBack.prev;
        if (this.size() <= 0) {
            return null;
        } else {
            size = size - 1;
            sentinelBack.prev = sentinelBack.prev.prev;
            sentinelBack.prev.next = sentinelBack;
            //sentinelBack.prev.prev.next = sentinelBack;
            //sentinelBack.prev = sentinelBack.prev.prev;
            return r.T;
        }
    }
    @Override
    public T get(int index) {
        Intnode s = sentinelFront;
        if (index < 0 || index >= this.size()) {
            return null;
        } else {
            for (int i = index; i >= 0; i--) {
                s = s.next;
            }
            return s.T;
        }
    }
    private T getRecursivehelper(int index, Intnode w) {
        if (index == 0) {
            return w.T;
        } else {
            w = w.next;
            return getRecursivehelper(index - 1, w);
        }
    }
    public T getRecursive(int index) {
        Intnode w = this.sentinelFront.next;
        if (index < 0 || index >= this.size()) {
            return null;
        } else {
            return getRecursivehelper(index, w);
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDeque.LinkedListDequeIterator();
    }
    private class LinkedListDequeIterator implements Iterator<T> {
        private int wizPos;
        private LinkedListDequeIterator() {
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
        Deque<T> ol = (Deque<T>) o;
        if (ol.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (!(ol.get(i).equals(this.get(i)))) {
                return false;
            }
        }
        return true;
    }
}
