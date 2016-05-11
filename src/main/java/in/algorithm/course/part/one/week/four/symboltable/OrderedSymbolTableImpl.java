package in.algorithm.course.part.one.week.four.symboltable;


import java.util.Iterator;

public class OrderedSymbolTableImpl<Key extends Comparable, Value> implements OrderedSymbolTable<Key, Value> {

    final private Comparable[] keys;
    final private Value[] values;

    final private int capacity;

    private int size;

    private OrderedSymbolTableImpl(final int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.keys = new Comparable[capacity];
        this.values = (Value[]) new Object[capacity];
    }

    public static OrderedSymbolTable createNew(final int capacity) {
        return new OrderedSymbolTableImpl(capacity);
    }

    @Override
    public void put(final Key key, final Value value) {

        if(size >= capacity) {
            throw new Overflow();
        }

        int index = findIndex(key);

        if (keyExists(index)) {
            values[index] = value;
        } else {
            index = getIndexWhereKeyShouldHaveBeen(index);
            moveElementsForwardByOnePositionFrom(index);
            keys[index] = key;
            values[index] = value;
            size++;
        }
    }

    private void moveElementsForwardByOnePositionFrom(final int index) {
        for(int i = size-1; i >= index; i--) {
            keys[i+1] = keys[i];
            values[i+1] = values[i];
        }
    }

    private int getIndexWhereKeyShouldHaveBeen(final int index) {
        return Math.abs(index) - 1;
    }

    private boolean keyExists(final int index) {
        return index >= 0;
    }

    @Override
    public Value get(final Key key) {
        final int index = findIndex(key);

        if(keyExists(index)) {
            return values[index];
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove(final Key key) {
        int index = findIndex(key);

        if (keyExists(index)) {
            moveElementsBackwardByOnePositionFrom(index);
            size--;
        }

    }

    private void moveElementsBackwardByOnePositionFrom(final int index) {
        for(int i = index+1; i < size; i++) {
            keys[i-1] = keys[i];
            values[i-1] = values[i];
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(final Key key) {
        if (keyExists(findIndex(key))) {
            return true;
        }
        return false;
    }

    @Override
    public int rank(final Key key) {
        final int index = findIndex(key);
        if (keyExists(index)) {
            return index;
        }
        return getIndexWhereKeyShouldHaveBeen(index);
    }

    private int findIndex(final Key key) {
        int low = 0;
        int high = size-1;

        while (low <= high) {
            int mid = low + (high - low)/2;
            int comparison = keys[mid].compareTo(key);
            if(comparison < 0) {
                low = mid + 1;
            } else if (comparison > 0) {
                high = mid - 1;
            }  else if(comparison == 0) {
                return mid;
            }
        }

        return negativeOfWhereKeyShouldHaveBeenMinusOne(low);
    }

    private int negativeOfWhereKeyShouldHaveBeenMinusOne(final int low) {
        return -(low + 1);
    }

    @Override
    public Key floor(final Key key) {

        int index = findIndex(key);
        if (!keyExists(index)) {
            index = getIndexWhereKeyShouldHaveBeen(index) - 1 ;
        }

        if(index < 0) {
            return null;
        }

        return getKeyAt(index);
    }

    private Key getKeyAt(final int index) {
        return (Key) keys[index];
    }

    @Override
    public Key ceiling(final Key key) {
        int index = findIndex(key);
        if (!keyExists(index)) {
            index = getIndexWhereKeyShouldHaveBeen(index);
        }

        if(index >= size) {
            return null;
        }

        return getKeyAt(index);
    }

    @Override
    public Key select(final int n) {
        if(n < 0 || n >= size) {
            return null;
        }
        return getKeyAt(n);
    }

    @Override
    public void deleteMin() {
        remove(getKeyAt(0));
    }

    @Override
    public void deleteMax() {
        if(!isEmpty()) {
            remove(getKeyAt(size-1));
        }
    }

    @Override
    public int size(final Key min, final Key max) {

        if(isEmpty()) {
            return 0;
        }

        Key smallest = ceiling(min);
        smallest = (smallest == null) ? getKeyAt(0) : smallest;

        Key largest = floor(max);
        largest = (largest == null) ? getKeyAt(size-1) : largest;

        final int maxIndex = findIndex(largest);
        final int minIndex = findIndex(smallest);

        return maxIndex - minIndex + 1;
    }

    @Override
    public Iterator<Key> keys() {
        return new KeysIterator();
    }

    private final class KeysIterator implements Iterator<Key> {

        private int index;

        private KeysIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return (index < size);
        }

        @Override
        public Key next() {
            return getKeyAt(index++);
        }
    }
}
