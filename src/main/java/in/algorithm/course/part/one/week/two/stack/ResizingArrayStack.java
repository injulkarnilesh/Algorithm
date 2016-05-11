package in.algorithm.course.part.one.week.two.stack;

public class ResizingArrayStack implements Stack<String> {

	private int index;
	private String [] items;
	
	public ResizingArrayStack() {
		index = 0;
		items = new String[1];
	}
	
	@Override
	public String pop() {
		if(index == 0) {
			throw new EmptyStack();
		}
		String poppedItem = items[--index];
		if(index != 0 && index == (items.length / 4)) {
			setArraySize(items.length / 2);
		}	
		return poppedItem;
	}

	@Override
	public void push(String item) {
		if(index == items.length) {
			setArraySize(items.length * 2);
		}
		items[index++] = item;
	}

	private void setArraySize(int newSize) {
		String [] newLargerArray = new String[newSize];
		for(int i = 0; i < items.length; i++) {
			if(i < newSize) {
				newLargerArray[i] = items[i];
			}
		}
		items = newLargerArray;
	}

	@Override
	public boolean isEmpty() {
		return index == 0;
	}
	
	public int getArraySize() {
		return items.length;
	}

}
