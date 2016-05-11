package in.algorithm.course.part.one.week.three;

import in.algorithm.course.part.one.week.two.sort.SelectionSort;
import in.algorithm.course.util.Check;
import in.algorithm.course.util.RandomInt;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class MergeSortTest {

    @Test
    public void shouldSortArrayOfRandomIntegersRecursively() throws Exception {
        final int size = RandomInt.next(100, 1000);
        final Integer[] arrayToSort = getRandomArrayOfSize(size);

        MergeSort.sortRecursively(arrayToSort);

        assertTrue(Check.isSorted(arrayToSort));
    }

    private Integer[] getRandomArrayOfSize(final int size) {
        final Integer [] array = new Integer[size];
        for(int i = 0; i < array.length; i++) {
            array[i] = RandomInt.next();
        }
        return array;
    }

    @Test
    public void shouldSortArrayOfKnownStringsRecursively() {
        final String [] arrayToSort = new String[] {
                "N", "I", "L", "E", "S", "H"
        };

        MergeSort.sortRecursively(arrayToSort);

        assertTrue(Check.isSorted(arrayToSort));
    }

    @Test
    public void shouldSortArrayOfRandomIntegersBottomUp() throws Exception {
        final int size = RandomInt.next(100, 1000);
        final Integer[] arrayToSort = getRandomArrayOfSize(size);

        MergeSort.sortBottomUp(arrayToSort);

        assertTrue(Check.isSorted(arrayToSort));
    }

    @Test
    public void shouldMergeSortedArrays() throws Exception {
        final int size = RandomInt.next(100);
        final Integer[] firstHalf = getRandomArrayOfSize(size);
        final Integer[] secondHalf = getRandomArrayOfSize(size);

        SelectionSort.sort(firstHalf);
        SelectionSort.sort(secondHalf);

        final Integer[] array = new Integer[size*2];
        for (int i = 0; i < size; i++) {
            array[i] = firstHalf[i];
        }
        for(int i = size, j = 0; i < 2*size && j < size; i++,j++ ){
            array[i] = secondHalf[j];
        }

        MergeSort.merge(array, Arrays.copyOf(array, array.length), 0, size-1, size * 2 - 1);

        Assert.assertTrue(Check.isSorted(array));
    }

}
