package in.algorithm.course.part.one.week.one;

public class QuickUnion {

	private int [] unionIds;
	
	public QuickUnion(int size) {
		unionIds = new int [size];
		for(int i= 0; i < size; i++) {
			unionIds[i] = i;
		}
	}

	public boolean isConnected(int source, int dest) {
		return findRoot(source)  == findRoot(dest);
	}
	
	/*
	 * Recursive
	 */
	private int root(int item) {
		int parent = unionIds[item];
		if(item == parent) {
			return item;
		}
		return root(parent); 
	}
	
	/*
	 * Non Recursive
	 */
	private int findRoot(int item) {
		int parent = unionIds[item];
		while(parent != item) {
			item = parent;
			parent = unionIds[parent];
		}
		return item;
	}

	public void union(int source, int dest) {
		int rootOfSource = findRoot(source);
		int rootOfDest = findRoot(dest);
		unionIds[rootOfSource] = rootOfDest;
	}
	
	public String toString() {
		StringBuilder string = new StringBuilder();
		for(int i = 0; i < unionIds.length; i++) {
			string.append(" " + unionIds[i]);
		}
		return string.toString();
	}

}
