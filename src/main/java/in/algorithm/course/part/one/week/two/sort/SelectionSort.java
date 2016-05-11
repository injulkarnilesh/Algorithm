package in.algorithm.course.part.one.week.two.sort;

import in.algorithm.course.util.ArrayUtils;

public class SelectionSort {
    public static void sort(final Comparable [] arrayToSort) {

        for (int i = 0; i < arrayToSort.length - 1; i++) {

            int min = i;
            for (int j = i + 1 ; j < arrayToSort.length; j++) {
                if (arrayToSort[j].compareTo(arrayToSort[min]) < 0) {
                    min = j;
                }
            }

            ArrayUtils.swap(arrayToSort, i, min);
        }

    }


}
