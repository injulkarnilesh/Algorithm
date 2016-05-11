package in.algorithm.course.part.one.week.four.heap.application;

import in.algorithm.course.part.one.week.four.heap.BinaryHeap;
import in.algorithm.course.part.one.week.four.heap.BinaryHeapZeroIndexedImpl;

public class HeapSort {

    public static void sort(final Comparable[] array) {
        BinaryHeap<Comparable> heap = new BinaryHeapZeroIndexedImpl(array);

        for (int i = 0; i < array.length; i++) {
            heap.insert(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            heap.deleteMax();
        }

    }
}
