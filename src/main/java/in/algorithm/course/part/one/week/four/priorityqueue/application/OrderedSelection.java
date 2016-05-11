package in.algorithm.course.part.one.week.four.priorityqueue.application;

import in.algorithm.course.part.one.week.four.priorityqueue.PriorityQueue;
import in.algorithm.course.part.one.week.four.priorityqueue.PriorityQueueFactory;

public class OrderedSelection {

    public static Comparable[] bottom(final Comparable[] array, final int n) {
        final PriorityQueue<Comparable> q = PriorityQueueFactory.newUnOrderedMaxPriorityQueue();
        return usePriorityQueueToCaptureNElements(array, n, q);
    }

    public static Comparable[] top(final Comparable[] array, final int n) {
        final PriorityQueue<Comparable> q = PriorityQueueFactory.newOrderedMinPriorityQueue();
        return usePriorityQueueToCaptureNElements(array, n, q);
    }

    private static Comparable[] usePriorityQueueToCaptureNElements(final Comparable[] array, final int n, final PriorityQueue<Comparable> q) {

        for(int i = 0; i < array.length; i++) {
            q.enqueue(array[i]);
            if(q.size() > n) {
                q.dequeue();
            }
        }

        final Comparable[] remaining = new Comparable[n];
        for(int i = 0; i < n; i++) {
            remaining[i] = q.dequeue();
        }

        return remaining;
    }
}
