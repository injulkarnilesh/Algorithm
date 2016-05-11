package in.algorithm.course.part.one.week.one;

public class PercolationProblem {
	
	private static int CLOSED = 0;
	private static int OPEN = 1;
	
	private WeightedQuickUnionWithPathCompression dynamicConnectivity;
	private int [][] grid;
	private int gridSize;
	private int topMostConnectivityIndex;
	private int bottomMostConnectivityIndex;
	
	public PercolationProblem(int gridSize) {
		this.gridSize = gridSize;
		this.grid = new int[gridSize][gridSize];
		this.dynamicConnectivity = new WeightedQuickUnionWithPathCompression((gridSize * gridSize) + 2);
		topMostConnectivityIndex = 0;
		bottomMostConnectivityIndex = gridSize * gridSize + 1;
		initTopAndBottomUnion();
	}

	private void initTopAndBottomUnion() {
		for(int i = 1; i <= gridSize; i ++) {
			dynamicConnectivity.union(topMostConnectivityIndex, i);
			dynamicConnectivity.union(bottomMostConnectivityIndex, (bottomMostConnectivityIndex - i));
		}
	}

	public boolean doesPercolate() {
		return dynamicConnectivity.isConnected(topMostConnectivityIndex, bottomMostConnectivityIndex);
	}

	public void open(int row, int col) {
		if(isValidIndex(row, col)) {
			if(!isOpen(row, col)) {
				grid[row][col] = OPEN;
				connectWithNeighboringOpenIndex(row, col);
			}
		}
	}
	
	private void connectWithNeighboringOpenIndex(int row, int col) {
		unionFromSourceToDestIndexIfOpen(row, col, row-1, col);
		unionFromSourceToDestIndexIfOpen(row, col, row, col+1);
		unionFromSourceToDestIndexIfOpen(row, col, row+1, col);
		unionFromSourceToDestIndexIfOpen(row, col, row, col-1);
	}
	
	private void unionFromSourceToDestIndexIfOpen(int sourceRow, int sourceCol, int destRow, int destCol) {
		if(isValidIndex(sourceRow, sourceCol) && isValidIndex(destRow, destCol) && isOpen(destRow, destCol)) {
			dynamicConnectivity.union(mapToConnectivityIndex(sourceRow, sourceCol), mapToConnectivityIndex(destRow, destCol));
		}
	}
	
	private int mapToConnectivityIndex(int row, int col) {
		return ( row * gridSize ) + col + 1;
	}
	
	private boolean isOpen(int row, int col) {
		return grid[row][col] == OPEN;
	}
	
	private boolean isValidIndex(int row, int col) {
		return isValidIndex(row) && isValidIndex(col);
	}
	
	private boolean isValidIndex(int index) {
		return index >=0 && index <grid.length;
	}

	public String toString() {
		StringBuilder string = new StringBuilder();
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				string.append(" " + grid[i][j]);
			}
			string.append("\n");
		}
		string.append("\n");
		return string.toString();
	}

}
