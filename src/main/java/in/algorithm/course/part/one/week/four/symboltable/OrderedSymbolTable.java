package in.algorithm.course.part.one.week.four.symboltable;


public interface OrderedSymbolTable<Key extends Comparable, Value> extends SymbolTable<Key, Value> {

    int rank(Key key);

    Key floor(Key key);

    Key ceiling(Key key);

    Key select(int n);

    void deleteMin();

    void deleteMax();

    int size(Key min, Key max);

    class Overflow extends RuntimeException {

    }
}
