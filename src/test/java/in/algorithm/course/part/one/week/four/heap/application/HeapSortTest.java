package in.algorithm.course.part.one.week.four.heap.application;

import in.algorithm.course.util.Check;
import in.algorithm.course.util.RandomInt;
import org.junit.Assert;
import org.junit.Test;

public class HeapSortTest {

    @Test
    public void shouldSortArrayOfRandomIntegers() throws Exception {
        final int size = RandomInt.next(100, 1000);
        final Integer [] array = new Integer[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = RandomInt.next(1000);
        }

        HeapSort.sort(array);

        Assert.assertTrue(Check.isSorted(array));
    }

    @Test
    public void shouldSortKnownStrings() throws Exception {
        final String[] array = new String[] {
                "N", "I", "L", "E", "S", "H", "I", "N", "J", "U", "L", "K", "A", "R"
        };

        HeapSort.sort(array);

        Assert.assertTrue(Check.isSorted(array));
    }
}
