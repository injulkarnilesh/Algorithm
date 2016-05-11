package in.algorithm.course.part.one.week.five.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IntervalSearchTree {

    private Node root;

    private IntervalSearchTree() {
        root = null;
    }

    public static IntervalSearchTree createNew() {
        return new IntervalSearchTree();
    }

    public IntervalSearchTree insert(final Interval interval) {

        if (Objects.isNull(interval)) {
            return this;
        }

        if (isEmpty()) {
            root = createNewNodeWithInterval(interval);
            return this;
        }

        root = insert(root, interval);

        return this;
    }

    private boolean isEmpty() {
        return root == null;
    }

    private Node insert(final Node currentNode, final Interval interval) {
        if (Objects.isNull(currentNode)) {
            final Node newNode = createNewNodeWithInterval(interval);
            return newNode;
        }

        final int comparison = interval.compareTo(currentNode.getInterval());
        final Node newChild;

        if (comparison < 0) {
            newChild = insert(currentNode.getLeft(), interval);
            currentNode.setLeft(newChild);
        } else {
            newChild = insert(currentNode.getRight(), interval);
            currentNode.setRight(newChild);
        }

        setMaxInCurrentNodeSubTree(currentNode, newChild);
        setMinInCurrentNodeSubTree(currentNode, newChild);

        return currentNode;
    }

    private Node createNewNodeWithInterval(final Interval interval) {
        final Node newNode = Node.createNew(interval);
        newNode.setMaxInSubTree(interval.getMax());
        newNode.setMinInSubTree(interval.getMin());
        return newNode;
    }

    private void setMinInCurrentNodeSubTree(final Node currentNode, final Node newChildNode) {
        final int minInNewLeftNode = Math.min(currentNode.getMinInSubTree(), newChildNode.getMinInSubTree());
        currentNode.setMinInSubTree(minInNewLeftNode);
    }

    private void setMaxInCurrentNodeSubTree(final Node currentNode, final Node newChildNode) {
        final int maxInNewLeftNode = Math.max(currentNode.getMaxInSubTree(), newChildNode.getMaxInSubTree());
        currentNode.setMaxInSubTree(maxInNewLeftNode);
    }

    Node getRoot() {
        return root;
    }

    public List<Interval> intersects(final Interval interval) {
        final List<Interval> intersectingIntervals = new ArrayList<>();

        if (isEmpty() || Objects.isNull(interval)) {
            return intersectingIntervals;
        }

        intersects(getRoot(), intersectingIntervals, interval);
        
        return intersectingIntervals;
    }

    private void intersects(final Node currentNode, final List<Interval> intersectingIntervals, final Interval interval) {

        if (Objects.isNull(currentNode)) {
            return;
        }

        if (currentNode.getInterval().intersects(interval)) {
            intersectingIntervals.add(currentNode.getInterval());
        }

        if (canGoLeft(currentNode, interval)) {
            intersects(currentNode.getLeft(), intersectingIntervals, interval);
        }

        if (canGoRight(currentNode, interval)) {
            intersects(currentNode.getRight(), intersectingIntervals, interval);
        }
    }

    private boolean canGoLeft(final Node currentNode, final Interval interval) {
        return currentNode.hasLeftChild() && interval.getMin() < currentNode.getLeft().getMaxInSubTree();
    }

    private boolean canGoRight(final Node currentNode, final Interval interval) {
        return currentNode.hasRightChild() && interval.getMax() > currentNode.getRight().getMinInSubTree();
    }

    public static class Interval implements Comparable<Interval> {

        private final int min;
        private final int max;

        private Interval(final int min, final int max) {
            this.min = min;
            this.max = max;
        }

        public static Interval of(final int valueOne, final int valueTwo) {
            final int min = Math.min(valueOne, valueTwo);
            final int max = Math.max(valueOne, valueTwo);
            return new Interval(min, max);
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final Interval interval = (Interval) o;

            if (min != interval.min) return false;
            return max == interval.max;

        }

        @Override
        public int hashCode() {
            int result = min;
            result = 31 * result + max;
            return result;
        }

        @Override
        public String toString() {
            return "Interval{" + "min=" + min +  ", max=" + max + '}';
        }

        @Override
        public int compareTo(final Interval other) {
            return Integer.valueOf(this.min).compareTo(Integer.valueOf(other.getMin()));
        }

        public boolean intersects(final Interval other) {
            if (Objects.isNull(other)) {
                return false;
            }
            return (this.getMin() < other.getMin() && other.getMin() < this.getMax())
                    || (this.getMin() < other.getMax() && other.getMax() < this.getMax())
                    || (other.getMin() < this.getMin() && this.getMin() < other.getMax())
                    || (other.getMin() < this.getMax() && this.getMax() < other.getMax());
        }
    }

    public static class Node {

        private final Interval interval;
        private int maxInSubTree;
        private int minInSubTree;

        private Node left;
        private Node right;

        private Node(final Interval interval) {
            this.interval = interval;
        }

        public static Node createNew(final Interval interval) {
            return new Node(interval);
        }

        public Interval getInterval() {
            return interval;
        }

        public void setLeft(final Node left) {
            this.left = left;
        }

        public Node getLeft() {
            return left;
        }

        public int getMaxInSubTree() {
            return maxInSubTree;
        }

        public void setMaxInSubTree(final int maxInSubTree) {
            this.maxInSubTree = maxInSubTree;
        }

        public int getMinInSubTree() {
            return minInSubTree;
        }

        public void setMinInSubTree(final int minInSubTree) {
            this.minInSubTree = minInSubTree;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(final Node right) {
            this.right = right;
        }

        public boolean hasLeftChild() {
            return Objects.nonNull(left);
        }

        public boolean hasRightChild() {
            return Objects.nonNull(right);
        }
    }
}
