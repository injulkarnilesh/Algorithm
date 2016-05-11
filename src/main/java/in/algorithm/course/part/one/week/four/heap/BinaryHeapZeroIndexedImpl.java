package in.algorithm.course.part.one.week.four.heap;

import in.algorithm.course.util.ArrayUtils;

public class BinaryHeapZeroIndexedImpl<T extends Comparable> implements BinaryHeap<T> {

    private T[] data;
    private int N;

    public BinaryHeapZeroIndexedImpl(final int capacity) {
        data = (T[]) new Comparable[capacity];
        N = -1;
    }

    public BinaryHeapZeroIndexedImpl(final Comparable[] array) {
        data = (T[]) array;
        N = -1;
    }

    @Override
    public boolean isEmpty() {
        return N == -1;
    }

    @Override
    public int size() {
        return N+1;
    }

    @Override
    public void insert(final T newItem) {

        if (data.length - 1 == N) {
            throw new FullHeap();
        }

        N++;
        data[N] = newItem;
        swimUp(N);
    }

    private void swimUp(int n) {
        int parent;

        while ( (parent = parentIndex(n)) >= 0 ) {
            if (data[parent].compareTo(data[n]) < 0) {
                ArrayUtils.swap(data, n, parent);
            }
            n = parent;
        }

    }

    private int parentIndex(final int n) {
        return (n % 2 == 0) ? (n/2 - 1) : (n / 2);
    }

    @Override
    public T deleteMax() {

        if (isEmpty()) {
            throw new EmptyHeap();
        }

        ArrayUtils.swap(data, 0, N);
        N--;
        sinkIn(0);

        return data[N+1];
    }

    private void sinkIn(int n) {

        while (n < N) {
            int left = leftChildIndex(n);
            int right = rightChildIndex(n);

            if (left > N) {
                break;
            }

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
        return n*2 + 1;
    }

    private int rightChildIndex(final int n) {
        return n*2 + 2;
    }

    public Comparable[] getData() {
        return data;
    }

}
