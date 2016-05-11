package in.algorithm.course.part.one.week.four.priorityqueue.application;


import in.algorithm.course.part.one.week.two.sort.application.KnuthShuffle;
import in.algorithm.course.util.RandomInt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

public class OrderedSelectionTest {

    private int largerArraySize;
    private int smallerArraySize;
    private Integer[] largerArray;
    private Integer[] smallerArray;
    private Integer[] input;

    @Before
    public void setUp() throws Exception {
        largerArraySize = RandomInt.next(10, 20);
        smallerArraySize = RandomInt.next(10, 20);
        final int min = RandomInt.next(50);

        largerArray = new Integer[largerArraySize];
        for(int i = 0; i < largerArraySize; i++) {
            largerArray[i] = RandomInt.next(min, 1000);
        }

        smallerArray = new Integer[smallerArraySize];
        for(int i = 0; i < smallerArraySize; i++) {
            smallerArray[i] = RandomInt.next(min);
        }

        input = Stream.concat(Arrays.stream(largerArray), Arrays.stream(smallerArray))
                .toArray(Integer[]::new);
        KnuthShuffle.shuffle(input);
    }

    @Test
    public void shouldFindSmallestElementsFromArray() throws Exception {

        final Comparable[] bottom = OrderedSelection.bottom(input, smallerArraySize);

        verifyArrayAreEqual(smallerArray, bottom);
    }

    private void verifyArrayAreEqual(final Comparable[] expected, final Comparable[] actual) {
        Arrays.sort(expected);
        Arrays.sort(actual);

        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindLargestElementsFromArray() throws Exception {

        final Comparable[] bottom = OrderedSelection.top(input, largerArraySize);

        verifyArrayAreEqual(largerArray, bottom);
    }

}
