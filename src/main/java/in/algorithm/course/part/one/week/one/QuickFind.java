package in.algorithm.course.part.one.week.one;

public class QuickFind {

	private int [] unionIds;
	
	public QuickFind(int size) {
		unionIds = new int [size];
		for(int i= 0; i < size; i++) {
			unionIds[i] = i;
		}
	}

	public boolean isConnected(int source, int dest) {
		return unionIds[source] == unionIds[dest];
	}

	public void union(int source, int dest) {
		int matchIdOfDest = unionIds[dest];
		int matchIdOfSource = unionIds[source];
		for(int i = 0; i < unionIds.length; i++) {
			if(unionIds[i] == matchIdOfSource) {
				unionIds[i] = matchIdOfDest;
			}
		}
	}

}
