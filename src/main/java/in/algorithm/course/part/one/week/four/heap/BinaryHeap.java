package in.algorithm.course.part.one.week.four.heap;

public interface BinaryHeap<T extends Comparable> {

    boolean isEmpty();

    int size();

    void insert(T item);

    T deleteMax();

    class EmptyHeap extends RuntimeException {

    }

    class FullHeap extends RuntimeException {

    }
}
