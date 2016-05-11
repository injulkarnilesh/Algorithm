package in.algorithm.course.part.one.week.two.stack;

public class LinkedStack <T> implements Stack <T> {

	private Node top;
	
	@Override
	public T pop() {
		if(top == null) {
			throw new EmptyStack();
		}

		T value = top.value;
		top = top.next;
		return value;
	}

	@Override
	public void push(T item) {
		Node topCopy = top;
		top = new Node(item);
		top.next = topCopy;
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}
	
	private class Node {
		private T value;
		private Node next;
		
		public Node(T value) {
			this.value = value;
		}
	}

}
