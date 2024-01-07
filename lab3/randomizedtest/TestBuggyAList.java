package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> anr = new AListNoResizing<>();
        BuggyAList<Integer> ba = new BuggyAList<>();
        for (int i = 0; i < 3; i += 1) {
            anr.addLast(i);
            ba.addLast(i);
        }
        for (int j = 0; j < 3; j += 1) {
            assertEquals(anr.removeLast(), ba.removeLast());
        }
    }
    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> ba = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                ba.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size1 = L.size();
                int size2 = ba.size();
                System.out.println("size1: " + size1 + " size2: " + size2);
                assertEquals(size1, size2);
            } else if (operationNumber == 2 && L.size() > 0) {
                // getLast
                int getLast1 = L.getLast();
                int getLast2 = ba.getLast();
                System.out.println("getLast1: " + getLast1 + " getLast2: " + getLast2);
                assertEquals(getLast1, getLast2);
            } else if (operationNumber == 3 && L.size() > 0) {
                // removeLast
                int removeLast1 = L.removeLast();
                int removeLast2 = ba.removeLast();
                System.out.println("removeLast1: " + removeLast1 + " removeLast2: " + removeLast2);
                assertEquals(removeLast1, removeLast2);
            }
        }
    }
}
