package in.algorithm.course.part.one.week.one;

import org.junit.Assert;
import org.junit.Test;

public class PercolationProblemTest {
	
	@Test
	public void testPerculation() throws Exception {
		PercolationProblem problem = new PercolationProblem(5);
		Assert.assertFalse(problem.doesPercolate());
		problem.open(0, 0);
		problem.open(0, 1);
		problem.open(1, 1);
		problem.open(1, 2);
		Assert.assertFalse(problem.doesPercolate());
		problem.open(1, 3);
		problem.open(2, 3);
		problem.open(3, 3);
		problem.open(3, 3);
		problem.open(3, 2);
		problem.open(4, 2);
		Assert.assertTrue(problem.doesPercolate());
		System.out.println(problem.toString());		
	}
}
