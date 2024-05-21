package deque;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class timeArrayDeque {
    private static void printTimingTable(ArrayDeque<Integer> Ns, ArrayDeque<Double> times, ArrayDeque<Integer> opCounts) {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        ArrayDeque<Integer> N = new ArrayDeque<>();
        ArrayDeque<Integer> Ns = new ArrayDeque<>();
        ArrayDeque<Double> times = new ArrayDeque();
        ArrayDeque<Integer> opCounts = new ArrayDeque();
        int s;
        int M = 10000;
        for (int i = 0; i <= 7; i += 1) {
            for (int j = 0; j < (int) Math.pow(2, i) * 1000; j += 1) {
                N.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < (int) Math.pow(2, i) * 1000; j += 1) {
                N.removeLast();
            }
            times.addLast(sw.elapsedTime());
        }
        for (int q = 0; q <= 7; q += 1) {
            Ns.addLast((int) Math.pow(2, q) * 1000);
            opCounts.addLast((int) Math.pow(2, q) * 1000);
        }
        printTimingTable(Ns, times, opCounts);
    }

}
