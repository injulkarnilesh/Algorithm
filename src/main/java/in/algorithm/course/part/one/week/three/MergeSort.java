package in.algorithm.course.part.one.week.three;

import java.util.Arrays;

public class MergeSort {

    public static void sortRecursively(final Comparable[] arrayToSort) {
        final Comparable[] auxiliaryArray = Arrays.copyOf(arrayToSort, arrayToSort.length);
        recursiveSort(arrayToSort, auxiliaryArray, 0, arrayToSort.length - 1);
    }

    private static void recursiveSort(final Comparable[] array, final Comparable[] aux, final int low, final  int high) {
        final int mid = low + (high - low)/2;

        if(isRangeOfSizeOneElement(low, high))
            return;

        recursiveSort(array, aux, low, mid);
        recursiveSort(array, aux, mid + 1, high);

        if(isMergeRequired(array, mid)){
            merge(array, array, low, mid(low, high), high);
        }
    }

    private static boolean isRangeOfSizeOneElement(final int low, final int high) {
        return low == high;
    }

    private static boolean isMergeRequired(final Comparable[] array, final int mid) {
        return array[mid].compareTo(array[mid+1]) > 0;
    }

    private static int mid(final int low, final int high) {
        return low + (high - low)/2;
    }

    public static void sortBottomUp(final Comparable[] arrayToSort) {
        for(int bucketSize = 1; bucketSize < arrayToSort.length; bucketSize *= 2) {
            final Comparable[] auxiliaryArray = Arrays.copyOf(arrayToSort, arrayToSort.length);
            for(int low = 0; low < arrayToSort.length; low += 2*bucketSize) {
                merge(arrayToSort, auxiliaryArray, low, (low + bucketSize - 1), Math.min((low + 2*bucketSize - 1), (arrayToSort.length - 1)));
            }
        }
    }

    public static void merge(final Comparable[] array, final Comparable[] aux, final int low, final int mid, final int high) {
        int firstPartIndex = low;
        int secondPartIndex = mid+1;

        for(int index = low; index<=high; index++) {
            if(firstPartIndex > mid)
                array[index] = aux[secondPartIndex++];
            else if(secondPartIndex > high)
                array[index] = aux[firstPartIndex++];
            else if(aux[firstPartIndex].compareTo(aux[secondPartIndex]) < 0)
                array[index] = aux[firstPartIndex++];
            else
                array[index] = aux[secondPartIndex++];
        }
    }

}
