package in.algorithm.course.util;

public class ArrayUtils {

    public static void swap(final Comparable[] array, final int i, final int j) {
        final Comparable toSwap = array[i];
        array[i] = array[j];
        array[j] = toSwap;
    }

}
