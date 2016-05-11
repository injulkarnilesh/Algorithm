package in.algorithm.course.part.one.week.five.balancedsearchtree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RedBlackBalancedSearchTree<T extends Comparable> implements BalancedSearchTree<T> {

    private Node<T> root;

    private RedBlackBalancedSearchTree() {
        root = null;
    }

    public static BalancedSearchTree createNew() {
        return new RedBlackBalancedSearchTree();
    }

    @Override
    public void insert(final T item) {
        if (isEmpty()) {
            root = Node.createNew(item);
            return;
        }

        root = insert(root, item);
    }

    private Node<T> insert(Node<T> currentNode, final T item) {

        if (Objects.isNull(currentNode)) {
            return null;
        }

        if (currentNode.valueShouldBeToLeft(item)) {
            if (currentNode.hasLeftChild()) {
                final Node<T> leftNode = insert(currentNode.getLeft(), item);
                currentNode.setLeft(leftNode);
            } else {
                final Node<T> leftNode = Node.createNew(item);
                currentNode.setLeft(leftNode, LinkType.RED);
            }
        } else if (currentNode.valueShouldBeToRight(item)) {
            if (currentNode.haRightChild()) {
                final Node<T> rightNode =  insert(currentNode.getRight(), item);
                currentNode.setRight(rightNode);
            } else {
                final Node<T> rightNode = Node.createNew(item);
                currentNode.setRight(rightNode, LinkType.RED);
            }
        }

        if (hasRedLinkedLeftChild(currentNode)) {
            final Node<T> leftNode = currentNode.getLeft();
            if (hasRedLinkedLeftChild(leftNode)) {
                currentNode = rotateRight(currentNode);
            }
        }

        if (hadRedLinkedRightChild(currentNode)) {
            if (hasRedLinkedLeftChild(currentNode)) {
                return flipColors(currentNode);
            } else {
                return rotateLeft(currentNode);
            }
        }


        return currentNode;
    }

    private boolean hadRedLinkedRightChild(final Node<T> currentNode) {
        return currentNode.haRightChild() && currentNode.getRight().getLinkType().isRed();
    }

    private boolean hasRedLinkedLeftChild(final Node<T> currentNode) {
        return currentNode.hasLeftChild() && currentNode.getLeft().getLinkType().isRed();
    }

    private boolean isEmpty() {
        return Objects.isNull(root);
    }

    @Override
    public List<T> values() {
        final List<T> values = new ArrayList();
        traverseTree(root, values);
        return values;
    }

    public void traverseTree(final Node<T> currentNode, final List<T> values) {
        if (Objects.isNull(currentNode)) {
            return;
        }
        traverseTree(currentNode.getLeft(), values);
        values.add(currentNode.getValue());
        traverseTree(currentNode.getRight(), values);
    }

    @Override
    public T search(final T item) {
        return search(root, item);
    }

    public T search(final Node<T> currentNode, final T item) {
        if (Objects.isNull(currentNode)) {
            return null;
        }

        if (currentNode.hasValue(item)) {
            return currentNode.getValue();
        } else if (currentNode.valueShouldBeToLeft(item)) {
            return search(currentNode.getLeft(), item);
        } else {
            return search(currentNode.getRight(), item);
        }

    }


    public Node<T> rotateLeft(final Node<T> currentNode) {
        final Node<T> oldRight = currentNode.getRight();
        currentNode.setRight(oldRight.getLeft());
        oldRight.setLinkType(currentNode.getLinkType());
        oldRight.setLeft(currentNode, LinkType.RED);
        return oldRight;
    }

    public Node<T> rotateRight(final Node<T> node) {
        final Node<T> oldLeft = node.getLeft();
        node.setLeft(oldLeft.getRight());
        oldLeft.setLinkType(node.getLinkType());
        oldLeft.setRight(node, LinkType.RED);
        return oldLeft;
    }

    public Node<T> flipColors(final Node<T> node) {
        if (node.hasLeftChild())
            node.getLeft().setLinkType(LinkType.BLACK);
        if (node.haRightChild())
            node.getRight().setLinkType(LinkType.BLACK);
        node.setLinkType(LinkType.RED);
        return node;
    }

    public Node<T> getRoot() {
        return root;
    }

    public enum LinkType {
        RED,
        BLACK;

        boolean isRed() {
            return this.equals(RED);
        }
        boolean isBlack() {
            return this.equals(BLACK);
        }
    }

    public static class Node<E extends Comparable> {
        private final E value;
        private Node<E> left;
        private Node<E> right;
        private LinkType linkType = LinkType.BLACK;

        private Node(final E value) {
            this.value = value;
        }

        public static <E extends  Comparable> Node createNew(final E value) {
            return new Node(value);
        }

        public E getValue() {
            return value;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(final Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(final Node<E> right) {
            this.right = right;
        }

        public LinkType getLinkType() {
            return linkType;
        }

        public void setLinkType(final LinkType linkType) {
            this.linkType = linkType;
        }

        public void setLeft(final Node<E> left, final LinkType linkType) {
            this.left = left;
            this.left.setLinkType(linkType);
        }

        public void setRight(final Node<E> right, final LinkType linkType) {
            this.right = right;
            this.right.setLinkType(linkType);
        }

        public boolean hasLeftChild() {
            return Objects.nonNull(this.left);
        }

        public boolean haRightChild() {
            return Objects.nonNull(this.right);
        }

        public boolean valueShouldBeToLeft(final E item) {
            return this.value.compareTo(item) > 0;
        }

        public boolean valueShouldBeToRight(final E item) {
            return this.value.compareTo(item) < 0;
        }

        public boolean hasValue(final E item) {
            return this.value.compareTo(item) == 0;
        }
    }

}
