package in.algorithm.course.part.one.week.four.bst;

import java.util.Objects;

public class BinarySearchTreeImpl<T extends Comparable> implements BinarySearchTree<T> {

    private Node<T> root;

    private BinarySearchTreeImpl() {
        root = null;
    }

    public static BinarySearchTree createNew() {
        return new BinarySearchTreeImpl();
    }

    @Override
    public void put(final T item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("NULL can not be put");
        }

        root = put(root, item);
    }

    private Node<T> put(final Node<T> currentNode, final T value) {

        if (Objects.isNull(currentNode)) {
            return Node.createNew(value);
        }

        final int comparison = value.compareTo(currentNode.getValue());
        if (toLeft(comparison)) {
            currentNode.setLeft(put(currentNode.getLeft(), value));
        } else if (toRight(comparison)) {
            currentNode.setRight(put(currentNode.getRight(), value));
        } else {
            currentNode.setValue(value);
        }

        currentNode.reCalculateCount();

        return currentNode;
    }

    private boolean toRight(final int comparison) {
        return comparison > 0;
    }

    private boolean toLeft(final int comparison) {
        return comparison < 0;
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(root);
    }

    @Override
    public T get(final T item) {
        if (Objects.isNull(item)) {
            return null;
        }

        return safeReadValueOfNode(get(root, item));
    }

    private Node<T> get(final Node<T> currentNode, final T value) {
        if (Objects.isNull(currentNode)) {
            return null;
        }
        final int comparison = value.compareTo(currentNode.getValue());
        if (toLeft(comparison)) {
            return get(currentNode.getLeft(), value);
        } else if (toRight(comparison)) {
            return get(currentNode.getRight(), value);
        }

        return currentNode;
    }

    @Override
    public int size() {
        return root == null ? 0 : root.getCount();
    }

    @Override
    public boolean contains(final T item) {
        return Objects.nonNull(get(item));
    }

    @Override
    public void remove(final T item) {
        if (Objects.isNull(item)) {
            return;
        }
        root = remove(root, item);
    }

    private Node<T> remove(final Node<T> currentNode, final T item) {
        if (Objects.isNull(currentNode)) {
            return null;
        }

        final int comparison = item.compareTo(currentNode.getValue());
        if (toLeft(comparison)) {
            currentNode.setLeft(remove(currentNode.getLeft(), item));
        } else if (toRight(comparison)) {
            currentNode.setRight(remove(currentNode.getRight(), item));
        } else {
            if ( hasNoLeftChild(currentNode) && hasNoRightChild(currentNode)) {
                return null;
            }
            else {
                if (hasNoLeftChild(currentNode)) {
                    return currentNode.getRight();
                }
                else if (hasNoRightChild(currentNode)) {
                    return currentNode.getLeft();
                } else {
                    final Node<T> nodeToReplaceCurrentNode = min(currentNode.getRight());
                    currentNode.setRight(
                            remove(currentNode.getRight(), nodeToReplaceCurrentNode.getValue())
                    );
                    currentNode.setValue(nodeToReplaceCurrentNode.getValue());
                }

            }
        }

        currentNode.reCalculateCount();

        return currentNode;
    }

    private boolean hasNoRightChild(final Node<T> currentNode) {
        return Objects.isNull(currentNode.getRight());
    }

    private boolean hasNoLeftChild(final Node<T> currentNode) {
        return Objects.isNull(currentNode.getLeft());
    }

    @Override
    public T min() {
        return safeReadValueOfNode(min(root));
    }

    private Node<T> min(final Node<T> node) {

        if (Objects.isNull(node)) {
            return null;
        }

        if (hasNoLeftChild(node)) {
            return node;
        }

        return min(node.getLeft());
    }

    @Override
    public T max() {
        return safeReadValueOfNode(max(root));
    }

    private Node<T> max(final Node<T> node) {

        if (Objects.isNull(node)) {
            return null;
        }

        if (hasNoRightChild(node)) {
            return node;
        }

        return max(node.getRight());

    }

    @Override
    public int rank(final T item) {
        return rank(root, item, noOfElementsToTheLeftOf(root));
    }

    private int rank(final Node<T> currentNode, final T value, int count) {

        if (Objects.isNull(currentNode)) {
            return count;
        }

        final int comparison = value.compareTo(currentNode.getValue());
        if (toLeft(comparison)) {
            count = rank(currentNode.getLeft(), value, count - 1 - noOfElementsToTheRightOf(currentNode.getLeft()));
        } else if (toRight(comparison)) {
            count = rank(currentNode.getRight(), value, count + 1 + noOfElementsToTheLeftOf(currentNode.getRight()));
        } else if (comparison == 0) {
            return count;
        }

        return count;
    }

    @Override
    public T floor(final T item) {
        return safeReadValueOfNode(floor(root, item, null));
    }

    private Node<T> floor(final Node<T> currentNode, final T value, final Node<T> smallest) {

        if (Objects.isNull(currentNode)) {
            return smallest;
        }

        final int comparison = value.compareTo(currentNode.getValue());
        if (toLeft(comparison)) {
            Node<T> smaller;
            if(hasNoLeftChild(currentNode)) {
                smaller = smallest;
            } else  {
               if(value.compareTo(currentNode.getLeft().getValue()) < 0) {
                   smaller = smallest;
               } else {
                   smaller = currentNode.getLeft();
               }
            }
            return floor(currentNode.getLeft(), value, smaller);
        } else if (toRight(comparison)) {
            return floor(currentNode.getRight(), value, currentNode);
        }

        return currentNode;
    }

    private T safeReadValueOfNode(final Node<T> node) {
        return Objects.isNull(node) ? null : node.getValue();
    }

    @Override
    public T ceiling(final T item) {
        return safeReadValueOfNode(ceiling(root, item, null));
    }

    private Node<T> ceiling(final Node<T> currentNode, final T value, final Node<T> largest) {

        if (Objects.isNull(currentNode)) {
            return largest;
        }

        final int comparison = value.compareTo(currentNode.getValue());
        if (toLeft(comparison)) {
            return ceiling(currentNode.getLeft(), value, currentNode);
        } else if (toRight(comparison)) {
            Node<T> larger;
            if (hasNoRightChild(currentNode)) {
                larger = largest;
            } else {
                if (value.compareTo(currentNode.getRight().getValue()) > 0) {
                    larger = largest;
                } else {
                    larger = currentNode.getRight();
                }
            }
            return ceiling(currentNode.getRight(), value, larger);
        }

        return currentNode;
    }


    @Override
    public T select(final int n) {
        return safeReadValueOfNode(select(root, noOfElementsToTheLeftOf(root), n));
    }

    private Node<T> select(final Node<T> currentNode, final int indexOfNode, final int n) {

        if (Objects.isNull(currentNode)) {
            return null;
        }

        if (n < indexOfNode) {
            return select(currentNode.getLeft(), noOfElementsToTheLeftOf(currentNode.getLeft()), n);
        } else if (n > indexOfNode) {
            return select(currentNode.getRight(), noOfElementsToTheLeftOf(currentNode.getRight()), n - indexOfNode - 1);
        }

        return currentNode;

    }

    private int noOfElementsToTheRightOf(final Node<T> node) {
        return Objects.isNull(node) ?
                0 : hasNoRightChild(node) ? 0 : node.getRight().getCount();
    }

    private int noOfElementsToTheLeftOf(final Node<T> node) {
        return Objects.isNull(node) ?
                0 : hasNoLeftChild(node) ? 0 : node.getLeft().getCount();
    }

    @Override
    public void deleteMin() {
        final Node<T> min = min(root);
        if (Objects.nonNull(min)) {
            remove(min.value);
        }
    }

    @Override
    public void deleteMax() {
        final Node<T> max = max(root);
        if (Objects.nonNull(max)) {
            remove(max.getValue());
        }
    }

    @Override
    public int size(final T min, final T max) {

        if (isEmpty()) {
            return 0;
        }

        final Node<T> minNode = ceiling(root, min, null);
        final T minValueFound = Objects.isNull(minNode) ? min() : minNode.getValue();
        final Node<T> maxNode = floor(root, max, null);
        final T maxValueFound = Objects.isNull(maxNode) ? max() : maxNode.getValue();

        final int minIndex = indexOf(root, minValueFound, noOfElementsToTheLeftOf(root));
        final int maxIndex = indexOf(root, maxValueFound, noOfElementsToTheLeftOf(root));

        return maxIndex - minIndex + 1;
    }

    private int indexOf(final Node<T> currentNode, final T value, int currentIndex) {

        if (Objects.isNull(currentNode)) {
            return -1;
        }

        final int comparison = value.compareTo(currentNode.getValue());
        if (toLeft(comparison)) {
            currentIndex = indexOf(currentNode.getLeft(), value, currentIndex - 1 - noOfElementsToTheRightOf(currentNode.getLeft()));
        } else if (toRight(comparison)) {
            currentIndex = indexOf(currentNode.getRight(), value, currentIndex + 1 + noOfElementsToTheLeftOf(currentNode.getRight()));
        } else if (comparison == 0) {
            return currentIndex;
        }

        return currentIndex;
    }


    private final static class Node<V> {

        private V value;
        private Node<V> left;
        private Node<V> right;
        private int count;

        private Node(final V value) {
            this.value = value;
            count = 1;
        }

        public static <V>  Node createNew(final V value) {
            return new Node(value);
        }

        public V getValue() {
            return value;
        }

        public void setValue(final V value) {
            this.value = value;
        }

        public Node<V> getLeft() {
            return left;
        }

        public void setLeft(final Node<V> left) {
            this.left = left;
        }

        public Node<V> getRight() {
            return right;
        }

        public void setRight(final Node<V> right) {
            this.right = right;
        }

        public int getCount() {
            return count;
        }

        public void setCount(final int count) {
            this.count = count;
        }

        public void reCalculateCount() {
            this.setCount(1 + safeCount(this.getLeft()) + safeCount(this.getRight()));
        }

        private int safeCount(final Node<V> node) {
            return Objects.isNull(node) ? 0 : node.getCount();
        }

        @Override
        public String toString() {
            final StringBuilder string = new StringBuilder(128);
            string.append(this.left);
            string.append(" - ");
            string.append("[" + this.value + "] (" + this.count +  ")");
            string.append(" - ");
            string.append(this.right);
            return string.toString();
        }
    }
}
