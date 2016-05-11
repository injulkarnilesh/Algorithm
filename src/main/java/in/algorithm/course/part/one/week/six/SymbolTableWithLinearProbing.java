package in.algorithm.course.part.one.week.six;

public class SymbolTableWithLinearProbing<Key, Value> implements SymbolTable<Key, Value> {

    private final int size;
    private final Key[] keys;
    private final Value[] values;

    private SymbolTableWithLinearProbing(final int size) {
        this.size = size;
        keys = (Key []) new Object[size];
        values = (Value[]) new Object[size];
    }

    public static SymbolTableWithLinearProbing createNew(final int size) {
        return new SymbolTableWithLinearProbing(size);
    }

    @Override
    public void put(final Key key, final Value value) {

        if (null == key) {
            throw new NullNotSupportedException();
        }

        final int originalKeyHashIndex = getKeyHashIndex(key);
        int keyHashIndex = originalKeyHashIndex;
        do {
            if (keys[keyHashIndex] == null) {
                keys[keyHashIndex] = key;
                values[keyHashIndex] = value;
                return;
            } else if (keys[keyHashIndex].equals(key)) {
                values[keyHashIndex] = value;
                return;
            }
            keyHashIndex = nextKeyHashIndex(keyHashIndex);
        } while (keyHashIndex != originalKeyHashIndex);

        throw new SymbolTableOutOfSizeException(size);
    }

    private int nextKeyHashIndex(final int currentKeyHashIndex) {
        return (currentKeyHashIndex + 1) % size;
    }

    private int getKeyHashIndex(final Key key) {
        return key.hashCode() % size;
    }

    @Override
    public Value get(final Key key) {
        final int originalKeyHashIndex = getKeyHashIndex(key);
        int keyHashIndex = originalKeyHashIndex;
        do {
            if (keys[keyHashIndex] == null) {
                return null;
            }
            if (keys[keyHashIndex].equals(key)) {
                return values[keyHashIndex];
            }
            keyHashIndex = nextKeyHashIndex(keyHashIndex);
        } while (keyHashIndex != originalKeyHashIndex);

        return null;
    }

    public static class SymbolTableOutOfSizeException extends RuntimeException {
        private SymbolTableOutOfSizeException(final int size) {
            super("Symbol table ran out of space init size was " + size);
        }
    }
}
