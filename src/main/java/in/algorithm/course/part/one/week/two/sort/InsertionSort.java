package in.algorithm.course.part.one.week.two.sort;

import in.algorithm.course.util.ArrayUtils;

public class InsertionSort {

    public static void sort(final Comparable[] array) {
        for(int i = 1; i <  array.length; i++) {
            for(int j = i; j > 0; j--) {
                if(array[j].compareTo(array[j-1]) < 0) {
                    ArrayUtils.swap(array, j, j-1);
                } else {
                    break;
                }

            }
        }
    }
}
