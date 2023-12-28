package deque;

public class LinkedListDeque<BleepBlorp> {
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
    /**public LinkedListDeque(BleepBlorp x) {
        sentinel = new Intnode(null, 63, null);
        Intnode first = new Intnode(null, x, null);
        sentinel.next = first;
        sentinel.prev = first;
        size = 1;
    }*/
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

    public int size() {
        return size;
    }
    public boolean isEmpty() {
        if (this.size() == 0) {
            return true;
        }
        return false;
    }
    public void printDeque() {
        Intnode p = sentinel_front;
        for (int i = 0; i < this.size(); i++) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println("");
    }
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        LinkedListDeque<BleepBlorp> ol = (LinkedListDeque<BleepBlorp>) o;
        if (ol.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!(ol.get(i).equals(this.get(i)))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> d1 = new LinkedListDeque<>();
        d1.addFirst(10);
        d1.addFirst(179);
        d1.addFirst(2334);
        d1.addLast(45254);
        //d1.addLast(1);
        System.out.println(d1.isEmpty());
        //d1.removeFirst();
        //d1.removeLast();
        //d1.removeLast();
        System.out.println(d1.get(2));
        d1.printDeque();
        LinkedListDeque<Integer> d2 = new LinkedListDeque<>();
        d2.addFirst(10);
        d2.addFirst(179);
        d2.addFirst(2334);
        d2.addLast(45254);
        d2.addLast(1);
        //d2.printDeque();
        //System.out.println(d1.removeFirst());
        //System.out.println(d1.size());
        System.out.println(d1.equals(d2));
    }
}
