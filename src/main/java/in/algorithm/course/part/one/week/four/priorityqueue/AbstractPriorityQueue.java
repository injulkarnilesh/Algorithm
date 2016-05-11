package in.algorithm.course.part.one.week.four.priorityqueue;

abstract class AbstractPriorityQueue<T extends Comparable> implements PriorityQueue<T> {

    private Node<T> root;

    public AbstractPriorityQueue() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return null == root;
    }

    @Override
    public int size() {
        int size = 0;
        Node<T> pointer = root;
        while (pointer != null) {
            size++;
            pointer = pointer.getNext();
        }
        return size;
    }

    protected Node<T> getRoot() {
        return root;
    }

    protected void setRoot(final Node<T> root) {
        this.root = root;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(512);
        Node<T> pointer = root;
        while (pointer != null) {
            builder.append(pointer);
            builder.append(" => ");
            pointer = pointer.getNext();
        }
        return builder.toString();
    }
}
