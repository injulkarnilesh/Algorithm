package in.algorithm.course.part.one.week.four.priorityqueue;

class UnOrderedPriorityQueue<T extends Comparable> extends AbstractPriorityQueue<T> {

    private OrderDecider order;

    public UnOrderedPriorityQueue(final OrderDecider order) {
        super();
        this.order = order;
    }

    @Override
    public void enqueue(final T item) {
        final Node<T> newNode = new Node(item);
        newNode.setNext(getRoot());
        setRoot(newNode);
    }

    @Override
    public T dequeue() {
        if(isEmpty()) {
            throw new EmptyQueue();
        }
        Node<T> pointer = getRoot();
        Node<T> previous = null;

        Node<T> selection = pointer;
        Node<T> previousSelection = null;

        while (pointer != null) {

            if (isValidToDequeue(pointer, selection)) {
                selection = pointer;
                previousSelection = previous;
            }

            previous = pointer;
            pointer = pointer.getNext();
        }

        if(selection == getRoot()) {
            setRoot(selection.getNext());
        } else {
            previousSelection.setNext( selection.getNext() );
        }

        return selection.getValue();
    }


    protected boolean isValidToDequeue(final Node<T> pointer, final Node<T> selection) {
        return order.isValid(pointer, selection);
    }

}
