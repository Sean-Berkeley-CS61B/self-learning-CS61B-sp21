package deque;

import java.util.Iterator;

public class LinkedListDeque<BleepBlorp> implements Deque<BleepBlorp>, Iterable<BleepBlorp>{
    public class Intnode {
        public Intnode prev;
        public BleepBlorp item;
        public Intnode next;
        public Intnode(Intnode p, Intnode n) {
            prev = p;
            next = n;
        }
        public Intnode(Intnode p, BleepBlorp i, Intnode n) {
            prev = p;
            item = i;
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
    public LinkedListDeque(BleepBlorp x) {
        sentinel_front = new Intnode(null,null);
        sentinel_back = new Intnode(null, null);
        Intnode first = new Intnode(null, x, null);
        sentinel_front.next = first;
        sentinel_back.prev = first;
        size = 1;
    }
    @Override
    public void addFirst(BleepBlorp item) {
        if (this.size() == 0) {
            Intnode t = new Intnode(sentinel_front, item, sentinel_back);
            sentinel_front.next = t;
            sentinel_back.prev = t;
        }else {
            Intnode t = new Intnode(sentinel_front, item, sentinel_front.next);
            sentinel_front.next.prev = t;
            sentinel_front.next = t;
        }
        size = size + 1;
    }
    @Override
    public void addLast(BleepBlorp item) {
        if (this.size() == 0) {
            Intnode u = new Intnode(sentinel_front, item, sentinel_back);
            sentinel_front.next = u;
            sentinel_back.prev = u;
        }else {
            Intnode u = new Intnode(sentinel_back.prev, item, sentinel_back);
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
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println("");
    }
    @Override
    public BleepBlorp removeFirst() {
        Intnode q = sentinel_front.next;
        if (this.size() <= 0) {
            return null;
        }else {
            size = size - 1;
            sentinel_front.next.next.prev = sentinel_front;
            sentinel_front.next = sentinel_front.next.next;
            return q.item;
        }
    }
    @Override
    public  BleepBlorp removeLast() {
        Intnode r = sentinel_back.prev;
        if (this.size() <= 0) {
            return null;
        }else {
            size = size - 1;
            sentinel_back.prev = sentinel_back.prev.prev;
            sentinel_back.prev.next = sentinel_back;
            //sentinel_back.prev.prev.next = sentinel_back;
            //sentinel_back.prev = sentinel_back.prev.prev;
            return r.item;
        }
    }
    @Override
    public BleepBlorp get(int index) {
        Intnode s = sentinel_front;
        if (index < 0 || index >= this.size()) {
            return null;
        }else {
            for (int i = index; i >= 0; i--) {
                s = s.next;
            }
            return s.item;
        }
    }
    public BleepBlorp getRecursivehelper(int index, Intnode w) {
        if (index == 0) {
            return w.item;
        } else {
            w = w.next;
            return getRecursivehelper(index - 1, w);
        }
    }
    public BleepBlorp getRecursive(int index) {
        Intnode w = this.sentinel_front.next;
        if (index < 0 || index >= this.size()) {
            return null;
        } else {
            return getRecursivehelper(index, w);
        }
    }
    @Override
    public Iterator<BleepBlorp> iterator() {
        return new LinkedListDeque.LinkedListDequeIterator();
    }
    public class LinkedListDequeIterator implements Iterator<BleepBlorp> {
        private int wizPos;
        public LinkedListDequeIterator() {
            wizPos = 0;
        }
        public boolean hasNext() {
            return wizPos < size;
        }
        public BleepBlorp next() {
            BleepBlorp returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof LinkedListDeque old) {
            // check size
            if (old.size != this.size) {
                return false;
            }
            // check all entries are the same
            for (int i = 0; i < size; i++) {
                if (!(old.get(i).equals(this.get(i)))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> d1 = new LinkedListDeque<>(1);
        d1.addFirst(10);
        d1.addFirst(179);
        d1.addFirst(2334);
        d1.addLast(45254);
        //d1.addLast(1);
        System.out.println(d1.size());
        //d1.removeFirst();
        //d1.removeLast();
        //d1.removeLast();
//        System.out.println(d1.get(2));
//        d1.printDeque();
        LinkedListDeque<Integer> d2 = null;
//        d2.addFirst(10);
//        d2.addFirst(179);
//        d2.addFirst(2334);
//        d2.addLast(45254);
//        d2.addLast(1);
        //d2.printDeque();
        //System.out.println(d1.removeFirst());
        d1.printDeque();
//        d2.printDeque();
        System.out.println(d1.equals(d2));
        for (int i : d1) {
            System.out.println(i);
        }
//        d1.printDeque();
    }
}
