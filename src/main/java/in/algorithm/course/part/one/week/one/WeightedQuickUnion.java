package in.algorithm.course.part.one.week.one;

public class WeightedQuickUnion {

	private int [] unionIds;
	private int [] sizes;
	
	public WeightedQuickUnion(int size) {
		unionIds = new int [size];
		sizes = new int [size];
		for(int i= 0; i < size; i++) {
			unionIds[i] = i;
			sizes[i] = 1;
		}
		
	}

	public boolean isConnected(int source, int dest) {
		return findRoot(source)  == findRoot(dest);
	}
		
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
		if(sizes[rootOfSource] < sizes[rootOfDest]) {
			attachTreeFromTo(rootOfSource, rootOfDest);
		} else {
			attachTreeFromTo(rootOfDest, rootOfSource);
		}
		
	}

	private void attachTreeFromTo(int source, int target) {
		unionIds[source] = target;
		sizes[target] += sizes[source];
	}
	
	public String toString() {
		StringBuilder string = new StringBuilder();
		for(int i = 0; i < unionIds.length; i++) {
			string.append(" " + unionIds[i]);
		}
		return string.toString();
	}

}
