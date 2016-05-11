package in.algorithm.course.part.one.week.two.sort;

import in.algorithm.course.util.ArrayUtils;

public class ShellSort {

    public static void sort(final Comparable[] array) {
        final int size = array.length;
        int h = 1;

        while(h*3 < size)
            h = h*3 + 1;

        while(h > 0) {

            for(int i = h; i < size; i = i+h) {
                for(int j = i; (j-h) >= 0; j = (j-h)) {
                    if(array[j].compareTo(array[j-h]) < 0) {
                        ArrayUtils.swap(array, j, j-h);
                    }
                }
            }

            h = h/3;
        }

    }

}
