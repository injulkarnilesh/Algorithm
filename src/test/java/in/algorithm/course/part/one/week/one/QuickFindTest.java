package in.algorithm.course.part.one.week.one;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class QuickFindTest {
	
	@Test
	public void initiallyAnyNodesShouldBeNotConnected() throws Exception {
		QuickFind find = new QuickFind(10);
		Assert.assertFalse(find.isConnected(1, 5));
	}
	
	@Test
	public void shouldBeConnectedForUni() throws Exception {
		QuickFind find = new QuickFind(10);
		
		find.union(1, 4);
		
		Assert.assertTrue(find.isConnected(1, 4));
		Assert.assertFalse(find.isConnected(1, 3));
	}
}
