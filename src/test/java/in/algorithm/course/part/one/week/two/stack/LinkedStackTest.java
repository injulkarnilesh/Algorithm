package in.algorithm.course.part.one.week.two.stack;

import static org.junit.Assert.*;
import in.algorithm.course.part.one.week.two.stack.Stack.EmptyStack;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedStackTest {
	
	private Stack<String> stack;
	
	@Before
	public void setUp() {
		stack = new LinkedStack<String>();
	}
	
	@Test
	public void shouldInitialiseEmptyStack() throws Exception {
		Assert.assertTrue(stack.isEmpty());
	}
	
	@Test
	public void shouldNotBeEmptyOnPush() throws Exception {
		stack.push("item");
		Assert.assertFalse(stack.isEmpty());
	}
	
	@Test
	public void shouldPopLastPushedItem() throws Exception {
		String item = "ABC";
		stack.push(item);
		String popedItem = stack.pop();
		
		Assert.assertEquals(item, popedItem);
	}
	
	
	@Test(expected = EmptyStack.class)
	public void emptyPopShouldThrowStackEmptyException() throws Exception {
		stack.pop();
	}
		

}
