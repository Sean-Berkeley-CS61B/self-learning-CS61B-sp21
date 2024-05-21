package deque;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class timeLinkedListDeque {
    private static void printTimingTable(LinkedListDeque<Integer> Ns, LinkedListDeque<Double> times, LinkedListDeque<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeLinkedListDequeConstruction();
    }

//    public static void timeAListConstruction() {
//        // TODO: YOUR CODE HERE
//        AList<Integer> N = new AList<>();
//        AList<Integer> Ns = new AList<>();
//        AList<Double> times = new AList<>();
//        AList<Integer> opCounts = new AList<>();
//        for (int i = 0; i <= 10; i += 1) {
//            Stopwatch sw = new Stopwatch();
//            for (int j = 0; j <(int) Math.pow(2, i) * 1000; j += 1) {
//                N.addLast(j);
//            }
//            times.addLast(sw.elapsedTime());
//        }
//        for (int k = 0; k <= 10; k += 1) {
//            Ns.addLast((int) Math.pow(2, k) * 1000);
//            opCounts.addLast((int) Math.pow(2, k) * 1000);
//        }
//        printTimingTable(Ns, times, opCounts);
//    }
public static void timeLinkedListDequeConstruction() {
    // TODO: YOUR CODE HERE
    LinkedListDeque<Integer> N = new LinkedListDeque<>();
    LinkedListDeque<Integer> Ns = new LinkedListDeque<>();
    LinkedListDeque<Double> times = new LinkedListDeque<>();
    LinkedListDeque<Integer> opCounts = new LinkedListDeque<>();
    for (int i = 0; i <= 10; i += 1) {
        for (int j = 0; j <(int) Math.pow(2, i) * 1000; j += 1) {
            N.addFirst(j);
        }
        Stopwatch sw = new Stopwatch();
        N.removeLast();
        times.addLast(sw.elapsedTime());
    }
    for (int k = 0; k <= 10; k += 1) {
        Ns.addLast((int) Math.pow(2, k) * 1000);
        opCounts.addLast((int) Math.pow(2, k) * 1000);
    }
    printTimingTable(Ns, times, opCounts);
}

}
