package in.algorithm.course.part.one.week.five.balancedsearchtree;

import in.algorithm.course.part.one.week.two.sort.InsertionSort;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TwoThreeBalancedSearchTree<T extends Comparable> implements BalancedSearchTree<T> {

    private Node<T> root;

    private TwoThreeBalancedSearchTree() {
        root = null;
    }

    public static BalancedSearchTree createNew() {
        return new TwoThreeBalancedSearchTree();
    }

    @Override
    public void insert(final T item) {
        if (isEmpty()) {
            root = Node.createNew(item);
            return;
        }

        final Node<T> node = insert(root, item);
        if (node.isFourNode()) {
            root = splitNode(node.asFourNode());
        } else {
            root = node;
        }

    }

    private Node<T> insert(final Node<T> currentNode, final T item) {

        if (currentNode.hasNoChildren()) {

            if (currentNode.isTwoNode()) {
                final T currentValue = currentNode.asTwoNode().getValue();
                if (firstSmallerThanSecond(item, currentValue)) {
                    return Node.createNew(item, currentValue);
                } else if (firstSmallerThanSecond(currentValue, item)) {
                    return Node.createNew(currentValue, item);
                }
            } else if (currentNode.isThreeNode()) {
                final ThreeNode<T> currentThreeNode = currentNode.asThreeNode();
                if (areNotEqual(currentThreeNode.getLeftValue(), item) && areNotEqual(currentThreeNode.getRightValue(), item)) {
                    final T [] sortedValues = sorted(currentThreeNode.getLeftValue(),
                                                     currentThreeNode.getRightValue(), item);
                    final Node<T> fourNode = Node.createNew(sortedValues[0], sortedValues[1], sortedValues[2]);
                    return fourNode;
                }
            }

        } else {

            if (currentNode.isTwoNode()) {
                final TwoNode<T> currentTwoNode = currentNode.asTwoNode();

                if (firstSmallerThanSecond(item, currentTwoNode.getValue())) {
                    final Node<T> leftNode = insert(currentTwoNode.getLeft(), item);
                    if (leftNode.isFourNode()) {
                        final TwoNode<T> leftSplitNode = splitNode(leftNode.asFourNode());

                        final ThreeNode<T> newCurrentNode = Node.createNew(leftSplitNode.getValue(), currentTwoNode.getValue());
                        newCurrentNode.setLeft(leftSplitNode.getLeft());
                        newCurrentNode.setMiddle(leftSplitNode.getRight());
                        newCurrentNode.setRight(currentTwoNode.getRight());
                        return newCurrentNode;
                    } else {
                        currentTwoNode.setLeft(leftNode);
                    }
                } else if (firstSmallerThanSecond(currentTwoNode.getValue(), item)) {
                    final Node<T> rightNode = insert(currentTwoNode.getRight(), item);
                    if (rightNode.isFourNode()) {
                        final TwoNode<T> rightSplitNode = splitNode(rightNode.asFourNode());

                        final ThreeNode<T> newCurrentNode = Node.createNew(currentTwoNode.getValue(), rightSplitNode.getValue());
                        newCurrentNode.setLeft(currentTwoNode.getLeft());
                        newCurrentNode.setMiddle(rightSplitNode.getLeft());
                        newCurrentNode.setRight(rightSplitNode.getRight());
                        return newCurrentNode;
                    } else {
                       currentTwoNode.setRight(rightNode);
                    }
                }

                return currentNode;

            } else if (currentNode.isThreeNode()) {
                final ThreeNode<T> currentThreeNode = currentNode.asThreeNode();
                if (firstSmallerThanSecond(item, currentThreeNode.getLeftValue())) {
                    final Node<T> leftNode = insert(currentThreeNode.getLeft(), item);
                    if (leftNode.isFourNode()) {
                        final TwoNode<T> leftSplitNode = splitNode(leftNode.asFourNode());
                        final FourNode<T> newCurrentNode = Node.createNew(leftSplitNode.getValue(), currentThreeNode.getLeftValue(), currentThreeNode.getRightValue());
                        newCurrentNode.setLeftMost(leftSplitNode.getLeft());
                        newCurrentNode.setMiddleLeft(leftSplitNode.getRight());
                        newCurrentNode.setMiddleRight(currentThreeNode.getMiddle());
                        newCurrentNode.setRightMost(currentThreeNode.getRight());
                        return newCurrentNode;
                    } else {
                        currentThreeNode.setLeft(leftNode);
                    }
                } else if (firstSmallerThanSecond(currentThreeNode.getRightValue(), item)) {
                    final Node<T> rightNode = insert(currentThreeNode.getRight(), item);
                    if (rightNode.isFourNode()) {
                        final TwoNode<T> rightSplitNode = splitNode(rightNode.asFourNode());
                        final FourNode<T> newCurrentNode = Node.createNew(currentThreeNode.getLeftValue(), currentThreeNode.getRightValue(), rightSplitNode.getValue());
                        newCurrentNode.setLeftMost(currentThreeNode.getLeft());
                        newCurrentNode.setMiddleLeft(currentThreeNode.getMiddle());
                        newCurrentNode.setMiddleRight(rightSplitNode.getLeft());
                        newCurrentNode.setRightMost(rightSplitNode.getRight());
                        return newCurrentNode;
                    } else {
                        currentThreeNode.setRight(rightNode);
                    }
                } else if (firstSmallerThanSecond(currentThreeNode.getLeftValue(), item) && firstSmallerThanSecond(item, currentThreeNode.getRightValue())) {
                    final Node<T> middleNode = insert(currentThreeNode.getMiddle(), item);
                    if (middleNode.isFourNode()) {
                        final TwoNode<T> middleSplitNode = splitNode(middleNode.asFourNode());
                        final FourNode<T> newCurrentNode = Node.createNew(currentThreeNode.getLeftValue(), middleSplitNode.getValue(), currentThreeNode.getRightValue());
                        newCurrentNode.setLeftMost(currentThreeNode.getLeft());
                        newCurrentNode.setMiddleLeft(middleSplitNode.getLeft());
                        newCurrentNode.setMiddleRight(middleSplitNode.getRight());
                        newCurrentNode.setRightMost(currentThreeNode.getRight());
                        return newCurrentNode;
                    } else {
                        currentThreeNode.setMiddle(middleNode);
                    }
                }

                return currentThreeNode;

            }

        }

        return currentNode;
    }

    private T[] sorted(final T first, final T second, final T third) {
        final Comparable [] array = new Comparable[3];
        array[0]= first; array[1] = second; array[2] = third;
        InsertionSort.sort(array);
        return (T[]) array;
    }

    private boolean isEmpty() {
        return Objects.isNull(root);
    }

    private TwoNode<T> splitNode(final FourNode<T> currentFourNode) {

        final TwoNode<T> newLeftChild = Node.createNew(currentFourNode.getLeftValue());
        newLeftChild.setLeft(currentFourNode.getLeftMost());
        newLeftChild.setRight(currentFourNode.getMiddleLeft());

        final TwoNode<T> newRightChild = Node.createNew(currentFourNode.getRightValue());
        newRightChild.setLeft(currentFourNode.getMiddleRight());
        newRightChild.setRight(currentFourNode.getRightMost());

        final Node<T> newParent = Node.createNew(currentFourNode.getMiddleValue());

        newParent.asTwoNode().setLeft(newLeftChild);
        newParent.asTwoNode().setRight(newRightChild);

        return newParent.asTwoNode();
    }


    private boolean firstSmallerThanSecond(final T first, final T second) {
        return first.compareTo(second) < 0;
    }

    private boolean areNotEqual(final T first, final T second) {
        return !areEqual(first, second);
    }

    private boolean areEqual(final T first, final T second) {
        return first.compareTo(second) == 0;
    }

    public Node<T> root() {
        return root;
    }

    @Override
    public List<T> values() {
        List<T> values = new ArrayList();
        inOrder(root, values);
        return values;
    }

    private void inOrder(final Node<T> currentNode, final List<T> values) {
        if (Objects.isNull(currentNode)) {
            return;
        }
        if (currentNode.isThreeNode()) {
            final ThreeNode<T> currentThreeNode = currentNode.asThreeNode();
            inOrder(currentThreeNode.getLeft(), values);
            values.add(currentThreeNode.getLeftValue());
            inOrder(currentThreeNode.getMiddle(), values);
            values.add(currentThreeNode.getRightValue());
            inOrder(currentThreeNode.getRight(), values);
        } else if (currentNode.isTwoNode()) {
            final TwoNode<T> currentTwoNode = currentNode.asTwoNode();
            inOrder(currentTwoNode.getLeft(), values);
            values.add(currentTwoNode.getValue());
            inOrder(currentTwoNode.getRight(), values);
        }
    }

    @Override
    public T search(final T item) {
        return search(root, item);
    }

    private T search(final Node<T> currentNode, final T item) {
        if (Objects.isNull(currentNode)) {
            return null;
        }

        if (currentNode.isThreeNode()) {
            final ThreeNode<T> currentThreeNode = currentNode.asThreeNode();
            if (firstSmallerThanSecond(item, currentThreeNode.getLeftValue())) {
                return search(currentThreeNode.getLeft(), item);
            } else if (firstSmallerThanSecond(currentThreeNode.getRightValue(), item)) {
                return search(currentThreeNode.getRight(), item);
            } else if (areEqual(currentThreeNode.getLeftValue(), item)) {
                return currentThreeNode.getLeftValue();
            } else if (areEqual(currentThreeNode.getRightValue(), item)) {
                return currentThreeNode.getRightValue();
            } else {
                return search(currentThreeNode.getMiddle(), item);
            }

        } else if (currentNode.isTwoNode()) {
            final TwoNode<T> currentTwoNode = currentNode.asTwoNode();
            if (firstSmallerThanSecond(item, currentTwoNode.getValue())) {
                return search(currentTwoNode.getLeft(), item);
            } else if (firstSmallerThanSecond(currentTwoNode.getValue(), item)) {
                return search(currentTwoNode.getRight(), item);
            } else {
                return currentTwoNode.getValue();
            }
        }

        return null;
    }

    public enum NodeType {
        TWO_NODE,
        THREE_NODE,
        FOUR_NODE
    }

    public abstract static class Node<E> {

        public static <E> TwoNode<E> createNew(final E value) {
            return TwoNode.createNew(value);
        }

        public static <E> ThreeNode<E> createNew(final E leftValue, final E rightValue) {
            return ThreeNode.createNew(leftValue, rightValue);
        }

        public static <E> FourNode<E> createNew(final E leftValue, final E middleValue, final E rightValue) {
            return FourNode.createNew(leftValue, middleValue, rightValue);
        }

        public TwoNode<E> asTwoNode() {
            return (TwoNode<E>) this;
        }

        public ThreeNode<E> asThreeNode() {
            return (ThreeNode<E>) this;
        }

        public FourNode<E> asFourNode() {
            return (FourNode<E>) this;
        }

        public final boolean isTwoNode() {
            return NodeType.TWO_NODE.equals(getNodeType());
        }

        public final boolean isThreeNode() {
            return NodeType.THREE_NODE.equals(getNodeType());
        }

        public final boolean isFourNode() {
            return NodeType.FOUR_NODE.equals(getNodeType());
        }

        public abstract boolean hasNoChildren();

        protected abstract NodeType getNodeType();

    }

    public static class TwoNode<E> extends Node<E> {

        private final E value;
        private Node<E> left;
        private Node<E> right;

        private TwoNode(final E value) {
            this.value = value;
        }

        public static <E> TwoNode createNew(final E value) {
            return new TwoNode(value);
        }

        public E getValue() {
            return value;
        }

        @Override
        protected NodeType getNodeType() {
            return NodeType.TWO_NODE;
        }

        @Override
        public boolean hasNoChildren() {
            return Objects.isNull(left) && Objects.isNull(right);
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }


        public void setLeft(final Node<E> left) {
            this.left = left;
        }

        public void setRight(final Node<E> right) {
            this.right = right;
        }
    }

    public static class ThreeNode<E> extends Node<E> {
        private final E leftValue;
        private final E rightValue;

        private Node<E> left;
        private Node<E> middle;
        private Node<E> right;

        private ThreeNode(final E leftValue, final E rightValue) {
            this.leftValue = leftValue;
            this.rightValue = rightValue;
        }

        public static <E> ThreeNode<E> createNew(final E leftValue, final E rightValue) {
            return new ThreeNode(leftValue, rightValue);
        }

        @Override
        protected NodeType getNodeType() {
            return NodeType.THREE_NODE;
        }

        @Override
        public boolean hasNoChildren() {
            return Objects.isNull(left) && Objects.isNull(middle) && Objects.isNull(right);
        }

        public E getLeftValue() {
            return leftValue;
        }

        public E getRightValue() {
            return rightValue;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getMiddle() {
            return middle;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setLeft(final Node<E> left) {
            this.left = left;
        }

        public void setMiddle(final Node<E> middle) {
            this.middle = middle;
        }

        public void setRight(final Node<E> right) {
            this.right = right;
        }
    }

    public static class FourNode<E> extends Node<E> {

        private final E leftValue;
        private final E middleValue;
        private final E rightValue;

        private Node<E> leftMost;
        private Node<E> middleLeft;
        private Node<E> middleRight;
        private Node<E> rightMost;

        private FourNode(final E leftValue, final E middleValue, final E rightValue) {
            this.leftValue = leftValue;
            this.middleValue = middleValue;
            this.rightValue = rightValue;
        }

        public static <E> FourNode<E> createNew(final E leftValue, final E middleValue, final E rightValue) {
            return new FourNode<>(leftValue, middleValue, rightValue);
        }

        @Override
        public boolean hasNoChildren() {
            return Objects.isNull(leftMost) && Objects.isNull(middleLeft)
                    && Objects.isNull(middleRight) && Objects.isNull(rightMost);
        }

        @Override
        protected NodeType getNodeType() {
            return NodeType.FOUR_NODE;
        }

        public E getLeftValue() {
            return leftValue;
        }

        public E getRightValue() {
            return rightValue;
        }

        public E getMiddleValue() {
            return middleValue;
        }

        public Node<E> getLeftMost() {
            return leftMost;
        }

        public Node<E> getMiddleLeft() {
            return middleLeft;
        }

        public Node<E> getMiddleRight() {
            return middleRight;
        }

        public Node<E> getRightMost() {
            return rightMost;
        }

        public void setLeftMost(final Node<E> leftMost) {
            this.leftMost = leftMost;
        }

        public void setMiddleLeft(final Node<E> middleLeft) {
            this.middleLeft = middleLeft;
        }

        public void setMiddleRight(final Node<E> middleRight) {
            this.middleRight = middleRight;
        }

        public void setRightMost(final Node<E> rightMost) {
            this.rightMost = rightMost;
        }
    }

}
