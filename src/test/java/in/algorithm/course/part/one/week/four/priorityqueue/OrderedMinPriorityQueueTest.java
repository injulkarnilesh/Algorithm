package in.algorithm.course.part.one.week.four.priorityqueue;

import in.algorithm.course.util.RandomInt;
import org.junit.Assert;
import org.junit.Test;

public class OrderedMinPriorityQueueTest {

    @Test
    public void shouldDequeueMinValue() throws Exception {
        PriorityQueue<Integer> q = PriorityQueueFactory.newOrderedMinPriorityQueue();

        Assert.assertTrue(q.isEmpty());

        final Integer min = RandomInt.next(0, 200);
        int i = 1, j =1;

        for(; i < RandomInt.next(50, 100); i++) {
            q.enqueue(RandomInt.next(min, 1000));
            Assert.assertEquals(i, q.size());
        }

        q.enqueue(min);

        for(; j < RandomInt.next(50, 100); j++) {
            q.enqueue(RandomInt.next(min, 1000));
            Assert.assertEquals(i+j, q.size());
        }

        Assert.assertEquals(min, q.dequeue());
        Assert.assertEquals(i + j - 2, q.size());
    }
}
