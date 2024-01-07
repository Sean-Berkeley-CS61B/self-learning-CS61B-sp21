package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
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
        SLList<Integer> N = new SLList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int s;
        int M = 10000;
        for (int i = 0; i <= 7; i += 1) {
            for (int j = 0; j < (int) Math.pow(2, i) * 1000; j += 1) {
                N.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            for (int k = 0; k < M; k += 1) {
                s = N.getLast();
            }
            times.addLast(sw.elapsedTime());
        }
        for (int q = 0; q <= 7; q += 1) {
            Ns.addLast((int) Math.pow(2, q) * 1000);
            opCounts.addLast(M);
        }
        printTimingTable(Ns, times, opCounts);
    }

}
