package in.algorithm.course.part.one.week.four.symboltable;

import java.util.Iterator;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public final class UnOrderedSymbolTable<Key, Value> implements SymbolTable<Key, Value> {

    private Pair<Key, Value> root;

    private UnOrderedSymbolTable() {
        root = null;
    }

    public static SymbolTable createNew() {
        return new UnOrderedSymbolTable();
    }

    @Override
    public void put(final Key key, final Value value) {

        if (isNull(key)) {
            throw new IllegalArgumentException("Key can not be null");
        }

        final Pair<Key, Value> newPair = Pair.createNew(key, value);
        if (isEmpty()) {
            root = newPair;
            return;
        }

        final Pair<Key, Value> matchingPair = find(key);
        if(nonNull(matchingPair)) {
            matchingPair.setValue(value);
            return;
        }

        newPair.setNext(root);
        root = newPair;
    }

    @Override
    public boolean isEmpty() {
        return isNull(root);
    }

    private Pair<Key, Value> find(final Key key) {
        Pair<Key, Value> pointer = root;
        while (nonNull(pointer)) {
            if (pointer.getKey().equals(key)) {
                return pointer;
            }
            pointer = pointer.getNext();
        }
        return null;
    }

    @Override
    public Value get(final Key key) {
        final Pair<Key, Value> matchingPair = find(key);
        return isNull(matchingPair) ? null : matchingPair.getValue();
    }

    @Override
    public void remove(final Key key) {
        if (isEmpty()) {
            return;
        }

        Pair<Key, Value> pointer = root;
        Pair<Key, Value> previous = null;
        while (nonNull(pointer)) {
            if (pointer.getKey().equals(key)) {
                break;
            }
            previous = pointer;
            pointer = pointer.getNext();
        }
        if (pointer == root) {
            root = null;
            return;
        }

        previous.setNext(pointer.getNext());
    }

    @Override
    public boolean contains(final Key key) {
        return nonNull(find(key));
    }

    @Override
    public int size() {
        int count = 0;
        Pair<Key, Value> pointer = root;
        while (nonNull(pointer)) {
            count++;
            pointer = pointer.getNext();
        }
        return count;
    }

    @Override
    public Iterator<Key> keys() {
        return new KeysIterator();
    }


    private final class KeysIterator implements Iterator<Key> {

        private Pair<Key, Value> pointer;

        private KeysIterator() {
            pointer = root;
        }

        @Override
        public Key next() {
            final Key nextKey = pointer.getKey();
            pointer = pointer.getNext();
            return nextKey;
        }

        @Override
        public boolean hasNext() {
            return nonNull(pointer);
        }
    }



    private final static class Pair<Key, Value> {
        private Key key;
        private Value value;
        private Pair<Key, Value> next;

        private Pair(final Key key, final Value value) {
            this.key = key;
            this.value = value;
        }

        public static <Key, Value> Pair<Key, Value> createNew(final Key key, final Value value) {
            return new Pair(key, value);
        }

        public Key getKey() {
            return key;
        }

        public Value getValue() {
            return value;
        }

        public Pair<Key, Value> getNext() {
            return next;
        }

        public void setValue(final Value value) {
            this.value = value;
        }

        public void setNext(final Pair<Key,Value> next) {
            this.next = next;
        }
    }
}
