package in.algorithm.course.part.one.week.five.balancedsearchtree;

import java.util.List;

public interface BalancedSearchTree<T extends Comparable> {

    void insert(T item);

    List<T> values();

    T search(T item);
}
