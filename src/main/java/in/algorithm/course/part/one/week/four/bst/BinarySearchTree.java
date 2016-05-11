package in.algorithm.course.part.one.week.four.bst;

public interface BinarySearchTree<T> {

    void put(T item);

    T get(T item);

    int size();

    boolean isEmpty();

    boolean contains(T item);

    void remove(T item);

    T min();

    T max();

    int rank(T item);

    T floor(T item);

    T ceiling(T item);

    T select(int item);

    void deleteMin();

    void deleteMax();

    int size(T min, T max);
}
