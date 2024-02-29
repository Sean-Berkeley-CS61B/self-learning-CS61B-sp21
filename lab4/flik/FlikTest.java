package flik;

import static org.junit.Assert.*;
import org.junit.Test;
public class FlikTest {
    /** Performs a few arbitrary tests to see if Flik is
     * correct */
    @Test
    public void test_greater_than_280() {
//        test if the Flik class performs correctly when
//        the number is not less than 128
        assertEquals(true, Flik.isSameNumber(500, 500));
    }
}
