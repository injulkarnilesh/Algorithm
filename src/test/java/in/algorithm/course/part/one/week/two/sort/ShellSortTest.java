package in.algorithm.course.part.one.week.two.sort;

import in.algorithm.course.util.Check;
import in.algorithm.course.util.RandomInt;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ShellSortTest {

    @Test
    public void shouldSortArrayOfRandomIntegers() throws Exception {
        final int size = RandomInt.next(100, 1000);
        final Integer [] arrayToSort = new Integer[size];

        for (int i = 0; i < size; i++ ) {
            arrayToSort[i] = RandomInt.next();
        }

        ShellSort.sort(arrayToSort);

        assertTrue(Check.isSorted(arrayToSort));
    }

    @Test
    public void shouldSortArrayOfKnownStrings() {
        final String [] arrayToSort = new String[] {
                "N", "I", "L", "E", "S", "H"
        };

        ShellSort.sort(arrayToSort);

        assertTrue(Check.isSorted(arrayToSort));
    }

}
