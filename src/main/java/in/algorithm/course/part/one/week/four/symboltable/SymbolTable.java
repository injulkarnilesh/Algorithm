package in.algorithm.course.part.one.week.four.symboltable;

import java.util.Iterator;

public interface SymbolTable<Key, Value> {

    void put(Key key, Value value);

    Value get(Key key);

    void remove(Key key);

    boolean isEmpty();

    boolean contains(Key key);

    int size();

    Iterator<Key> keys();
}
