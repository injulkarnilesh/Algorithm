package in.algorithm.course.part.one.week.three.application;

import in.algorithm.course.part.one.week.two.sort.application.KnuthShuffle;
import in.algorithm.course.util.RandomInt;
import org.junit.Assert;
import org.junit.Test;

public class SelectionTest {

    @Test
    public void shouldSelectSmallestNumberFor0th() throws Exception {
        final int size = RandomInt.next(10, 100);
        final Integer[] array = new Integer[size];
        final int smallestNumber = RandomInt.next(100, 500);

        for (int i = 0; i < array.length; i++) {
            array[i] = RandomInt.next(smallestNumber+1, 1000);
        }
        array[RandomInt.next(size)] = smallestNumber;

        final Integer nthSmallestNumber = (Integer)Selection.nthSmallest(array, 0);

        Assert.assertEquals(smallestNumber, nthSmallestNumber.intValue());
    }

    @Test
    public void shouldSelectLargestNumberForLast() throws Exception {
        final int size = RandomInt.next(10, 100);
        final Integer[] array = new Integer[size];
        final int largestNumber = RandomInt.next(100, 500);

        for (int i = 0; i < array.length; i++) {
            array[i] = RandomInt.next(0, largestNumber);
        }
        array[RandomInt.next(size)] = largestNumber;

        final Integer nthSmallestNumber = (Integer)Selection.nthSmallest(array, array.length - 1);

        Assert.assertEquals(largestNumber, nthSmallestNumber.intValue());
    }

    @Test
    public void shouldSelectNthSmallestNumber() throws Exception {
        final int size = RandomInt.next(10, 100);
        final Integer[] array = new Integer[size];
        final int expectedNth = RandomInt.next(100, 500);
        final int expectedNthIndex = RandomInt.next(size);

        for (int i = 0; i < expectedNthIndex; i++) {
            array[i] = RandomInt.next(expectedNth);
        }
        for(int i = expectedNthIndex+1; i<array.length; i++) {
            array[i] = RandomInt.next(expectedNth, 1000);
        }
        array[expectedNthIndex] = expectedNth;
        KnuthShuffle.shuffle(array);

        final Integer nthSmallestNumber = (Integer)Selection.nthSmallest(array, expectedNthIndex);

        Assert.assertEquals(expectedNth, nthSmallestNumber.intValue());
    }
}
