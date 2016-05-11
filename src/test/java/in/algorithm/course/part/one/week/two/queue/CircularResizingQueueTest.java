package in.algorithm.course.part.one.week.two.queue;

import static org.junit.Assert.*;
import in.algorithm.course.part.one.week.two.queue.Queue.EmptyQueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CircularResizingQueueTest {

	private Queue<String> queue;
	
	@Before
	public void setUp() {
		queue = new CircularResizingQueue(); 	
	}
	
	@Test
	public void shouldBeEmptyInitialy() throws Exception {
		assertTrue(queue.isEmpty());
		assertEquals(0, queue.size());
	}
	
	@Test
	public void shouldDeueueFirstEnqueuedItem() throws Exception {
		String firstItem = "It's your life and it's ending a minute a time";
		String secondItem = "Yippie-ki-yay";
		queue.enqueue(firstItem);
		queue.enqueue(secondItem);
		
		assertEquals(firstItem, queue.dequeue());
		assertEquals(secondItem, queue.dequeue());
	}
	
	@Test
	public void shouldBeEmptyWhenAllItem() throws Exception {
		queue.enqueue("Let's put a smile on that face");
		assertFalse(queue.isEmpty());
		queue.dequeue();
		assertTrue(queue.isEmpty());
	}
	
	@Test
	public void shouldHaveSizeOnceEnqueued() throws Exception {
		assertEquals(0, queue.size());
		queue.enqueue("This city deserves a better class of criminal. And I'm gonna give it to them.");
		assertEquals(1, queue.size());
		queue.dequeue();
		assertEquals(0, queue.size());
	}
	
	@Test(expected = EmptyQueue.class)
	public void shouldThrowExceptionOnDequeueOnEmptyQueue() throws Exception {
		queue.dequeue();
	}
	
	
	
	@Test
	public void shouldQueueItemsCircularly() throws Exception {
		queue = new CircularResizingQueue(4);
		
		queue.enqueue("One");
		queue.enqueue("Two");
		queue.enqueue("Three");
		queue.enqueue("Four");
		assertEquals(4, queue.size());
		
		queue.dequeue();
		assertEquals(3, queue.size());
		
		queue.enqueue("Five");
		assertEquals(4, queue.size());
		assertEquals("Two", queue.dequeue());
	}
	
	@Test
	public void shouldDoubleQueueArraySizeWhenFull() throws Exception {
		String firstItem = "One",
				secondItem = "Two",
				thirdItem = "Three",
				fourthItem = "Four",
				fifthItem = "Five";
		queue = new CircularResizingQueue(4);
		queue.enqueue(firstItem);
		queue.enqueue(secondItem);
		queue.enqueue(thirdItem);
		queue.enqueue(fourthItem);
		assertEquals(4, queue.size());
		assertEquals(4, getQueueArraySize());
		queue.enqueue("Five");
		assertEquals(5, queue.size());
		assertEquals(8, getQueueArraySize());
		assertEquals(firstItem, queue.dequeue());
		assertEquals(secondItem, queue.dequeue());
		assertEquals(thirdItem, queue.dequeue());
		assertEquals(fourthItem, queue.dequeue());
		assertEquals(fifthItem, queue.dequeue());
		assertTrue(queue.isEmpty());
	}
	
	@Test
	public void shouldHalveArraySizeWhenQuaterSizeIsReached() throws Exception {
		queue = new CircularResizingQueue(2);
		for(int i = 0; i < 5; i++) {
			queue.enqueue("Random " + i);
		}
		assertEquals(8, getQueueArraySize());
		queue.dequeue();
		assertEquals(8, getQueueArraySize());
		queue.dequeue();
		assertEquals(8, getQueueArraySize());
		queue.dequeue();
		assertEquals(4, getQueueArraySize());
		queue.dequeue();
		assertEquals(2, getQueueArraySize());
	}
	
	private int getQueueArraySize() {
		return ((CircularResizingQueue)queue).getArraySize();
	}
	
}
