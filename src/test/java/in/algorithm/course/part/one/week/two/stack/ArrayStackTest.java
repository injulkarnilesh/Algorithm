package in.algorithm.course.part.one.week.two.stack;

import static org.junit.Assert.*;
import in.algorithm.course.part.one.week.two.stack.Stack.EmptyStack;
import in.algorithm.course.part.one.week.two.stack.Stack.FullStack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayStackTest {
	
	private Stack<String> stack;
	private int initSize = 4;
	
	@Before
	public void setUp() {
		stack = new ArrayStack(initSize);
	}
	
	@Test
	public void shouldInitializeEmptyStack() throws Exception {
		Assert.assertTrue(stack.isEmpty());
	}
	
	@Test
	public void shouldNotBeEmptyIfItemIsPushed() throws Exception {
		stack.push("item");
		Assert.assertFalse(stack.isEmpty());
	}
	
	
	@Test
	public void shouldPopLastPushedItem() throws Exception {
		String item = "MNOP";
		stack.push(item);
		String popedItem = stack.pop();
		Assert.assertEquals(item, popedItem);
		Assert.assertTrue(stack.isEmpty());
	}
	
	@Test(expected = EmptyStack.class)
	public void shouldThrowStackEmptyWhenPopedFromEmptyStack() throws Exception {
		stack.pop();
	}
	
	@Test(expected = FullStack.class)
	public void shouldThrowStackFullWhenPushedBeyondInitSize() throws Exception {
		for(int i = 0; i< initSize; i++)
			stack.push("whatever");
		stack.push("thelastonetoexlode");
	}

}
