package in.algorithm.course.part.one.week.four.priorityqueue;

public interface OrderDecider<T extends Comparable> {

    boolean isValid(Node<T> first, Node<T> second);

}
