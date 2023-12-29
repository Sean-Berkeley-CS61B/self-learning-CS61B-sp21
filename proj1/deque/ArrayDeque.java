package deque;

public class ArrayDeque<item> {
    private int size;
    private int j;
    private int k;
    private int nextFirst = 3;
    private int nextLast = 4;
    private int sizecpy;
    private item[] items;
    public ArrayDeque() {
        items = (item[]) new Object[8];
        size = 0;
    }
    private void expandsize() {
        item[] a = (item[]) new Object[items.length * 2];
        if (nextLast == 0) {
            System.arraycopy(items, 0, a, a.length - size, size);
            nextFirst = a.length - size - 1;
            items = a;
        } else {
            System.arraycopy(items, 0, a, 0, nextLast);
            System.arraycopy(items, nextLast, a, a.length - (size - nextFirst) + 1, size - nextLast);
            nextFirst = a.length - (size - nextFirst);
            items = a;
        }
    }
    public void addFirst(item x) {
        if (size == items.length) {
            expandsize();
        }
        items[nextFirst] = x;
        size = size + 1;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst = nextFirst - 1;
        }
    }
    public void addLast(item x) {
        if (size == items.length) {
            expandsize();
        }
        items[nextLast] = x;
        size = size + 1;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast = nextLast + 1;
        }
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
        sizecpy = this.size();
        if (nextFirst == items.length - 1) {
            for (int i = 0; i < sizecpy; i++) {
                System.out.print(items[i] + " ");
            }
        } else {
            j = nextFirst + 1;
            while (j < items.length && items[j] != null) {
                System.out.print(items[j] + " ");
                j = j + 1;
            }
            k = 0;
            while (items[k] != null && k <= this.size() - (items.length - nextFirst)) {
                System.out.print(items[k] + " ");
                k = k + 1;
            }
            }
        System.out.println("");
    }
    public item removeFirst() {
        if (this.size() == 0) {
            return null;
        }
        size = size - 1;
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
            return items[0];
        } else {
            nextFirst = nextFirst + 1;
            return items[nextFirst];
        }
    }
    public item removeLast() {
        if (this.size() == 0) {
            return null;
        }
        size = size - 1;
        if (nextLast == 0) {
            nextLast = items.length - 1;
            return items[items.length - 1];
        } else {
            nextLast = nextLast - 1;
            return items[nextLast];
        }
    }
    public item get(int index) {
        if (index < 0 || index >= this.size()) {
            return null;
        }
        if (index < items.length - nextFirst - 1) {
            return items[nextFirst + index + 1];
        } else {
            return items[index - (items.length - nextFirst - 1)];
        }
    }
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<item> ol = (ArrayDeque<item>) o;
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
        ArrayDeque<Integer> d1 = new ArrayDeque<>();
        d1.addFirst(10);
        d1.addFirst(179);
        d1.addFirst(2334);
        d1.addLast(45254);
        //d1.addLast(1);
        System.out.println(d1.isEmpty());
        //d1.removeFirst();
        //d1.removeLast();
        //d1.removeLast();
        d1.addFirst(1);
        d1.addFirst(2);
        d1.addLast(3);
        System.out.println(d1.size());
        System.out.println(d1.get(6));

        d1.printDeque();
        //d2.printDeque();
        //System.out.println(d1.removeFirst());
        //System.out.println(d1.size());
    }
}
