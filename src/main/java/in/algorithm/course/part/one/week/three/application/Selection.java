package in.algorithm.course.part.one.week.three.application;

import in.algorithm.course.part.one.week.three.QuickSort;

import java.util.Arrays;

public class Selection {

    public static Comparable nthSmallest(final Comparable[] array, final int n) {

        if(n < 0 || n >= array.length) {
            throw new IllegalArgumentException("0 indexed n should be within array range");
        }

        final Comparable[] copy = Arrays.copyOf(array, array.length);

        int index;
        int low = 0;
        int high = array.length-1;

        do {
            index = QuickSort.partition(copy, low, high);
            if(index < n) {
                low = index + 1;
            } else if(index > n) {
                high = index - 1;
            }
        } while (index != n);

        return copy[index];
    }



}
