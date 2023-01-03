package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSqarePrimes1() {
        IntList lst1 = IntList.of(11, 3, 17, 8, 12);
        boolean changed = IntListExercises.squarePrimes(lst1);
        assertEquals("121 -> 9 -> 289 -> 8 -> 12", lst1.toString());
        assertTrue(changed);
    }
}
