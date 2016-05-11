package in.algorithm.course.part.one.week.four.heap.application;

import in.algorithm.course.part.one.week.four.priorityqueue.PriorityQueue;
import in.algorithm.course.part.one.week.four.priorityqueue.PriorityQueueFactory;
import in.algorithm.course.part.one.week.two.sort.application.KnuthShuffle;
import in.algorithm.course.util.RandomInt;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BinaryHeapMaxPriorityQueueTest {

    @Test
    public void shouldDequeueMaxValue() throws Exception {
        final int size = RandomInt.next(100, 1000);
        final Integer[] array = getArrayWithIndexedNumbers(size);

        KnuthShuffle.shuffle(array);

        final PriorityQueue<Integer> q = PriorityQueueFactory.newBinaryHeapPriorityQueue(size);

        assertTrue(q.isEmpty());

        for (int i = 0; i < size; i++) {
            q.enqueue(array[i]);
        }

        assertFalse(q.isEmpty());
        assertEquals(size, q.size());

        for(int i = size; i > 0; i--) {
            assertEquals(i, q.dequeue().intValue());
            assertEquals(i - 1, q.size());
        }

        assertTrue(q.isEmpty());
    }

    private Integer[] getArrayWithIndexedNumbers(final int size) {
        final Integer []  array = new Integer[size];
        for(int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
        return array;
    }
}
