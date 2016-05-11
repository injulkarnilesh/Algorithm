package in.algorithm.course.part.one.week.four.priorityqueue;

class OrderedPriorityQueue <T extends Comparable> extends AbstractPriorityQueue<T> {

    private OrderDecider order;

    public OrderedPriorityQueue(final OrderDecider order) {
        super();
        this.order = order;
    }

    @Override
    public void enqueue(final T item) {
        final Node<T> newNode = new Node(item);

        if(isEmpty()) {
            setRoot(newNode);
            return;
        }

        Node<T> pointer = getRoot();
        Node<T> previous = null;

        while (pointer != null) {
            if (isValidToEnqueue(pointer, newNode)) {
                break;
            }
            previous = pointer;
            pointer = pointer.getNext();
        }

        if (previous == null) {
            newNode.setNext(getRoot());
            setRoot(newNode);
            return;
        }

        newNode.setNext(pointer);
        previous.setNext(newNode);
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyQueue();
        }
        final T value = getRoot().getValue();
        setRoot(getRoot().getNext());
        return value;
    }

    protected boolean isValidToEnqueue(final Node<T> pointer, final Node<T> newNode) {
        return order.isValid(pointer, newNode);
    }

}
