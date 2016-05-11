package in.algorithm.course.part.one.week.two.stack;

import static org.junit.Assert.*;
import in.algorithm.course.part.one.week.two.stack.Stack.EmptyStack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResizingArrayStackTest {

	private Stack<String> stack;
	
	@Before
	public void setUp() {
		stack = new ResizingArrayStack();
	}
	
	@Test
	public void shouldBeEmptyInitially() throws Exception {
		Assert.assertTrue(stack.isEmpty());
	}
	
	@Test
	public void shouldNotBeEmptyOnPush() throws Exception {
		stack.push("anything");
		Assert.assertFalse(stack.isEmpty());
	}
	
	@Test
	public void shouldPopLastPusedItem() throws Exception {
		String item = "item";
		stack.push(item);
		
		String popedItem = stack.pop();
				
		Assert.assertEquals(item, popedItem);
		Assert.assertTrue(stack.isEmpty());
	}
	
	@Test(expected = EmptyStack.class)
	public void shouldThrowEmptyStackWhenPopedFromEmptyStack() throws Exception {
		stack.pop();
	}
	
	@Test
	public void shouldDoubleArraySizeWhenStackArrayFull() throws Exception {
		stack.push("whatever");
		stack.push("whichever");
		Assert.assertEquals(2, getArraySize());
		stack.push("whenever");
		Assert.assertEquals(4, getArraySize());
		stack.push("another");
		stack.push("yet another");
		Assert.assertEquals(8, getArraySize());
	}
	
	@Test
	public void shouldHalveArraySizeWhenQuaterSizeIsReached() throws Exception {
		for(int i = 0; i < 5; i++) {
			stack.push("random");
		}
		
		stack.pop();
		Assert.assertEquals(8, getArraySize());
		stack.pop();
		Assert.assertEquals(8, getArraySize());
		stack.pop();
		Assert.assertEquals(4, getArraySize());
		stack.pop();
		Assert.assertEquals(2, getArraySize());
	}
		
	private int getArraySize() {
		return ((ResizingArrayStack)stack).getArraySize();
	}
}
