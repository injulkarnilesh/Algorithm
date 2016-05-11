package in.algorithm.course.part.one.week.three;

import in.algorithm.course.part.one.week.two.sort.application.KnuthShuffle;
import in.algorithm.course.util.ArrayUtils;

public class QuickSort {

    public static void sort(final Comparable[] arrayToSort) {
        KnuthShuffle.shuffle(arrayToSort);
        sortRecursively(arrayToSort, 0, arrayToSort.length-1);
    }

    private static void sortRecursively(final Comparable[] array, final int low, final int high) {
        if(low >= high)
            return;
        final int partitionIndex = partition(array, low, high);
        sortRecursively(array, low, partitionIndex - 1);
        sortRecursively(array, partitionIndex + 1, high);
    }

    public static int partition(final Comparable[] arrayToPartition, final int low, final int high) {
        int i = low+1;
        int j = high;


        while (i <= j) {
            while (arrayToPartition[i].compareTo(arrayToPartition[low]) <= 0) {
                i++;
                if(i >= high)
                    break;
            }

            while(arrayToPartition[j].compareTo(arrayToPartition[low]) >= 0) {
                j--;
                if(j <= low)
                    break;
            }

            if( i < j) {
                ArrayUtils.swap(arrayToPartition, i, j);
            }
        }

        ArrayUtils.swap(arrayToPartition, j, low);
        return j;
    }

    public static void sortWith3WayPartitioning(final Comparable[] arrayToSort) {
        KnuthShuffle.shuffle(arrayToSort);
        sortRecursivelyWith3WayPartitioning(arrayToSort, 0, arrayToSort.length - 1);
    }

    private static void sortRecursivelyWith3WayPartitioning(final Comparable[] array, final int low, final int high) {
        if(low >= high)
            return;

        int i = low;
        int v = low;
        int j = high;

        while (i <= j) {
            if(array[i].compareTo(array[v]) < 0) {
                ArrayUtils.swap(array, i++, v++);
            }
            else if(array[i].compareTo(array[v]) > 0) {
                ArrayUtils.swap(array, i, j--);
            }
            else {
                i++;
            }
        }

        sortRecursivelyWith3WayPartitioning(array, low, v-1);
        sortRecursivelyWith3WayPartitioning(array, j+1, high);
    }
    
}
