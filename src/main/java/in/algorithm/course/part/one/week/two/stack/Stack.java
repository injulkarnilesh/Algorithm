package in.algorithm.course.part.one.week.two.stack;

public interface Stack<T> {
	
	T pop();
	
	void push(T item);
	
	boolean isEmpty();
	
	class EmptyStack extends RuntimeException {
		
	} 
	
	class FullStack extends RuntimeException {
		
	}
	
}
