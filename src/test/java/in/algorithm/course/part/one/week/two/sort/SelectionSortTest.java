package in.algorithm.course.part.one.week.two.sort;

import in.algorithm.course.util.Check;
import in.algorithm.course.util.RandomInt;
import org.junit.Test;


import static org.junit.Assert.assertTrue;

public class SelectionSortTest {

    @Test
    public void shouldSortArrayOfRandomIntegers() throws Exception {
        final Integer [] arrayToSort = new Integer[] {
                RandomInt.next(), RandomInt.next(), RandomInt.next(), RandomInt.next(), RandomInt.next()
        };

        SelectionSort.sort(arrayToSort);

        assertTrue(Check.isSorted(arrayToSort));
    }

    @Test
    public void shouldSortArrayOfKnownStrings() {
        final String [] arrayToSort = new String[] {
                "N", "I", "L", "E", "S", "H"
        };

        SelectionSort.sort(arrayToSort);

        assertTrue(Check.isSorted(arrayToSort));
    }

}
