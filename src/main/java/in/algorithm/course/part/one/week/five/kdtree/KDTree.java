package in.algorithm.course.part.one.week.five.kdtree;

import java.util.Objects;

public interface KDTree {

    void insert(Point point);

    Point get(Point point);

    boolean isEmpty();

    Node getRoot();

    class WrongDimensionPointException extends RuntimeException {

    }

     class Node {
        private final Point point;
        private Node left;
        private Node right;

        private Node(final Point point) {
            this.point = point;
        }

        public static Node createNew(final Point point) {
            return new Node(point);
        }

        public Point getPoint() {
            return point;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(final Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(final Node right) {
            this.right = right;
        }

        public boolean hasLeftChild() {
            return Objects.nonNull(this.left);
        }

        public boolean hasRightChild() {
            return Objects.nonNull(this.right);
        }
    }
}
