package in.algorithm.course.part.one.week.four.heap;

import in.algorithm.course.util.ArrayUtils;

public class BinaryHeapImpl<T extends Comparable> implements BinaryHeap<T> {

    private T[] data;
    private int N;

    public BinaryHeapImpl(final int capacity) {
        data = (T[]) new Comparable[capacity+1];
        N = 0;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public void insert(final T newItem) {

        if (data.length == N+1) {
            throw new FullHeap();
        }

        N++;
        data[N] = newItem;
        swimUp(N);
    }

    private void swimUp(int n) {
        int parent;

        while ( (parent = parentIndex(n)) >= 1 ) {
            if (data[parent].compareTo(data[n]) < 0) {
                ArrayUtils.swap(data, n, parent);
            }
            n = parent;
        }

    }

    private int parentIndex(final int n) {
        return n/2;
    }

    @Override
    public T deleteMax() {

        if (isEmpty()) {
            throw new EmptyHeap();
        }

        ArrayUtils.swap(data, 1, N);
        N--;
        sinkIn(1);

        return data[N+1];
    }

    private void sinkIn(int n) {

        while (n <= N/2) {
            int left = leftChildIndex(n);
            int right = rightChildIndex(n);

            final int child = chooseWithinRangeLargerChildren(left, right);

            if(data[n].compareTo(data[child]) < 0) {
                ArrayUtils.swap(data, n, child);
                n = child;
            } else {
                break;
            }

        }

    }

    private int chooseWithinRangeLargerChildren(final int left, final int right) {
        if(right > N) {
            return left;
        }
        return data[left].compareTo(data[right]) < 0 ? right : left;
    }

    private int leftChildIndex(final int n) {
        return n*2;
    }

    private int rightChildIndex(final int n) {
        return n*2 + 1;
    }

    public Comparable[] getData() {
        return data;
    }

}
