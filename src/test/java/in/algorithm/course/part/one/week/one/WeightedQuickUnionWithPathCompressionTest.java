package in.algorithm.course.part.one.week.one;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class WeightedQuickUnionWithPathCompressionTest {
	
	@Test
	public void initiallyAnyNodesShouldBeNotConnected() throws Exception {
		WeightedQuickUnionWithPathCompression find = new WeightedQuickUnionWithPathCompression(10);
		Assert.assertFalse(find.isConnected(1, 5));
	}
	
	@Test
	public void shouldBeConnectedForUni() throws Exception {
		WeightedQuickUnionWithPathCompression find = new WeightedQuickUnionWithPathCompression(10);
		
		find.union(4, 3);
		find.union(3, 8);
		find.union(6, 5);
		find.union(9, 4);
		find.union(2, 1);
		
		assertTrue(find.isConnected(8, 9));
		
		assertFalse(find.isConnected(5, 4));
		
		find.union(5, 0);
		find.union(7, 2);
		find.union(6, 1);
		find.union(7, 3);
		
		System.out.println(find.toString());
	}
}
