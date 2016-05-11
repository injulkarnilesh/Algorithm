package in.algorithm.course.part.one.week.six;

public interface SymbolTable<Key, Value> {

    void put(Key key, Value value);

    Value get(Key key);

    public class NullNotSupportedException extends RuntimeException {

    }

}
