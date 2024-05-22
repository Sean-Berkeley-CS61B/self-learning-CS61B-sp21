package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T>{
    private class Intnode {
        public Intnode prev;
        public T T;
        public Intnode next;
        public Intnode(Intnode p, Intnode n) {
            prev = p;
            next = n;
        }
        public Intnode(Intnode p, T i, Intnode n) {
            prev = p;
            T = i;
            next = n;
        }
    }
    private Intnode sentinel_front;
    private Intnode sentinel_back;
    private int size;

    public LinkedListDeque() {
        sentinel_front = new Intnode(null,null);
        sentinel_back = new Intnode(null, null);
        //sentinel_front.next = sentinel_back;
        //sentinel_back.prev = sentinel_front;
        size = 0;
    }
//    public LinkedListDeque(T x) {
//        sentinel_front = new Intnode(null,null);
//        sentinel_back = new Intnode(null, null);
//        Intnode first = new Intnode(null, x, null);
//        sentinel_front.next = first;
//        sentinel_back.prev = first;
//        size = 1;
//    }
    @Override
    public void addFirst(T T) {
        if (this.size() == 0) {
            Intnode t = new Intnode(sentinel_front, T, sentinel_back);
            sentinel_front.next = t;
            sentinel_back.prev = t;
        }else {
            Intnode t = new Intnode(sentinel_front, T, sentinel_front.next);
            sentinel_front.next.prev = t;
            sentinel_front.next = t;
        }
        size = size + 1;
    }
    @Override
    public void addLast(T T) {
        if (this.size() == 0) {
            Intnode u = new Intnode(sentinel_front, T, sentinel_back);
            sentinel_front.next = u;
            sentinel_back.prev = u;
        }else {
            Intnode u = new Intnode(sentinel_back.prev, T, sentinel_back);
            sentinel_back.prev.next = u;
            sentinel_back.prev = u;
        }
        size = size + 1;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        Intnode p = sentinel_front;
        for (int i = 0; i < this.size(); i++) {
            System.out.print(p.next.T + " ");
            p = p.next;
        }
        System.out.println("");
    }
    @Override
    public T removeFirst() {
        Intnode q = sentinel_front.next;
        if (this.size() <= 0) {
            return null;
        }else {
            size = size - 1;
            sentinel_front.next.next.prev = sentinel_front;
            sentinel_front.next = sentinel_front.next.next;
            return q.T;
        }
    }
    @Override
    public  T removeLast() {
        Intnode r = sentinel_back.prev;
        if (this.size() <= 0) {
            return null;
        }else {
            size = size - 1;
            sentinel_back.prev = sentinel_back.prev.prev;
            sentinel_back.prev.next = sentinel_back;
            //sentinel_back.prev.prev.next = sentinel_back;
            //sentinel_back.prev = sentinel_back.prev.prev;
            return r.T;
        }
    }
    @Override
    public T get(int index) {
        Intnode s = sentinel_front;
        if (index < 0 || index >= this.size()) {
            return null;
        }else {
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
        Intnode w = this.sentinel_front.next;
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
        public LinkedListDequeIterator() {
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
