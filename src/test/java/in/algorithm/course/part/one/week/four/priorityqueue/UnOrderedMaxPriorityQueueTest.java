package in.algorithm.course.part.one.week.four.priorityqueue;

import in.algorithm.course.util.RandomInt;
import org.junit.Assert;
import org.junit.Test;

public class UnOrderedMaxPriorityQueueTest {

    @Test
    public void shouldDequeueMaxValue() throws Exception {
        PriorityQueue<Integer> q = PriorityQueueFactory.newUnOrderedMaxPriorityQueue();
        Assert.assertTrue(q.isEmpty());

        final Integer max = RandomInt.next(0, 200);
        int i = 1, j =1;

        for(; i < RandomInt.next(50, 100); i++) {
            q.enqueue(RandomInt.next(max));
            Assert.assertEquals(i, q.size());
        }

        q.enqueue(max);

        for(; j < RandomInt.next(50, 100); j++) {
            q.enqueue(RandomInt.next(max));
            Assert.assertEquals(i+j, q.size());
        }

        Assert.assertEquals(max, q.dequeue());
        Assert.assertEquals(i + j - 2, q.size());
    }
}
