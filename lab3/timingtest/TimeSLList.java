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
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        timeGetLast(Ns, times, opCounts);
        printTimingTable(Ns, times, opCounts);
    }

    public static void timeGetLast(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        // TODO: YOUR CODE HERE
        int i = 1000;
        for (int k = 0; k < 8; k += 1) {
            Ns.addLast(i);
            opCounts.addLast(10000);
            SLList<Integer> tmp = new SLList<>();
            for (int j = 0; j < i; j += 1) {
                tmp.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            for (int l = 0; l < 10000; l += 1) {
                tmp.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
            i *= 2;
        }
    }

}
