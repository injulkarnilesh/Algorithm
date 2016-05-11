package in.algorithm.course.part.one.week.two.stack;

public class ArrayStack implements Stack<String> {

	private String [] items;
	private int index;
	
	public ArrayStack(int initialSize) {
		items = new String[initialSize];
		index = 0;
	}

	@Override
	public String pop() {
		if(index == 0) { 
			throw new EmptyStack();
		}
		
		return items[--index];
	}

	@Override
	public void push(String item) {
		if(index == items.length) {
			throw new FullStack();
		}
		
		items[index++] = item;
	}

	@Override
	public boolean isEmpty() {
		return index == 0;
	}
	
}
