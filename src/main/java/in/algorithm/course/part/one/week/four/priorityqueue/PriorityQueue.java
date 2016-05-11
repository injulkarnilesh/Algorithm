package in.algorithm.course.part.one.week.four.priorityqueue;

public interface PriorityQueue <T extends Comparable> {

    boolean isEmpty();

    void enqueue(T item);

    T dequeue();

    int size();

    class EmptyQueue extends RuntimeException {

    }

}
