package in.algorithm.course.part.one.week.one;

public class WeightedQuickUnionWithPathCompression {

	private int [] unionIds;
	private int [] sizes;
	
	public WeightedQuickUnionWithPathCompression(int size) {
		unionIds = new int [size];
		sizes = new int [size];
		for(int i= 0; i < size; i++) {
			unionIds[i] = i;
			sizes[i] = 1;
		}
		
	}

	public boolean isConnected(int source, int dest) {
		return findRootWithPathCompression(source)  == findRootWithPathCompression(dest);
	}
		
	private int findRootWithPathCompression(int item) {
		int parent = unionIds[item];
		int originalItem = item;
		while(parent != item) {
			item = parent;
			parent = unionIds[parent];
		}
		int root = parent;
		item = originalItem;
		parent = unionIds[item];
		while(parent != root) {
			unionIds[item] = root;
			item = parent;
			parent = unionIds[parent];
		}
		return root;
	}

	public void union(int source, int dest) {
		int rootOfSource = findRootWithPathCompression(source);
		int rootOfDest = findRootWithPathCompression(dest);
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
