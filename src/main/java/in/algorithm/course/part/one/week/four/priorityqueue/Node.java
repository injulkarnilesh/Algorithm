package in.algorithm.course.part.one.week.four.priorityqueue;

class Node<T> {

    final private T value;
    private Node<T> next;

    public Node(final T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(final Node<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node[ " + value + "]";
    }
}
