package in.algorithm.course.part.one.week.two.queue;

public class CircularResizingQueue implements Queue<String> {

	private static final int DEFAULT_INIT_ARRAY_SIZE = 16;

	private String[] items;
	private int count;
	private int head;
	private int tail;
	
	public CircularResizingQueue() {
		items = new String[DEFAULT_INIT_ARRAY_SIZE];
		initHeadAndTailAndCount();
	}
	
	private void initHeadAndTailAndCount() {
		head = 0;
		tail = 0;
		count = 0;
	}
	
	public CircularResizingQueue(int initSize) {
		items = new String[initSize];
		initHeadAndTailAndCount();
	}
	
	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public void enqueue(String item) {
		if(isFull()) {
			setArraySizeTo(items.length * 2);
		}
		items[tail] = item;
		count++;
		tail = nextCircularIndex(tail);
	}
	
	private boolean isFull() {
		return count == items.length;
	}
	
	private void setArraySizeTo(int newSize) {
		String [] newItems = new String[newSize]; 

		for(int newIndex = 0, oldIndex = head; newIndex < count; oldIndex = nextCircularIndex(oldIndex), newIndex++) {
			newItems[newIndex] = items[oldIndex];
		}
		items = newItems;
		head = 0;
		tail = count;
	}

	@Override
	public String dequeue() {
		if(isEmpty()) {
			throw new EmptyQueue();
		}
		String item = items[head];
		count--;
		head = nextCircularIndex(head);
		if(isArrayQuarterFull()) {
			setArraySizeTo(items.length / 2);
		}
		return item;
	}
	
	private boolean isArrayQuarterFull() {
		return count == (items.length / 4);
	}
	
	private int nextCircularIndex(int currentIndex) {
		return ((++currentIndex) == items.length) ? 0 : currentIndex;
	}
	
	@Override
	public int size() {
		return count;
	}
	
	int getArraySize() {
		return items.length;
	}

}
