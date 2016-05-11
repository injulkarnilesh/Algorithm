package in.algorithm.course.part.one.week.six;

import in.algorithm.course.part.one.week.two.sort.application.KnuthShuffle;
import in.algorithm.course.util.RandomInt;
import in.algorithm.course.util.RandomString;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SymbolTableWithLinearProbingTest {
    private static final int SIZE = 100;

    private SymbolTable<Integer, String> symbolTable;

    @Before
    public void setUp() throws Exception {
        symbolTable = SymbolTableWithLinearProbing.createNew(SIZE);
    }

    @Test
    public void shouldPutKeyValue() throws Exception {
        final Integer key = RandomInt.next(200);
        final String value = RandomString.next(10);

        symbolTable.put(key, value);

        assertEquals(value, symbolTable.get(key));
    }

    @Test
    public void shouldPutMultipleKeysAndValues() throws Exception {
        final Integer keyOne = 30;
        final String valueOne = RandomString.next(20);

        final Integer keyTwo = 50;
        final String valueTwo = RandomString.next(10);

        symbolTable.put(keyOne, valueOne);
        symbolTable.put(keyTwo, valueTwo);

        assertEquals(valueOne, symbolTable.get(keyOne));
        assertEquals(valueTwo, symbolTable.get(keyTwo));
    }

    @Test
    public void shouldUpdateValueOnInsertingSameKey() throws Exception {
        final Integer key = RandomInt.next(200);
        final String value = RandomString.next(10);
        final String newValue = RandomString.next(10);

        symbolTable.put(key, value);
        assertEquals(value, symbolTable.get(key));

        symbolTable.put(key, newValue);
        assertEquals(newValue, symbolTable.get(key));
    }

    @Test
    public void shouldInsertTwoKeysWithCollision() throws Exception {
        final Integer keyOne = 20;
        final String valueOne = RandomString.next(10);
        final Integer keyTwo = 120;
        final String valueTwo = RandomString.next(10);

        symbolTable.put(keyOne, valueOne);
        symbolTable.put(keyTwo, valueTwo);

        assertEquals(valueOne, symbolTable.get(keyOne));
        assertEquals(valueTwo, symbolTable.get(keyTwo));
    }

    @Test
    public void shouldUpdateValueOfKeyWithCollision() throws Exception {
        final Integer keyOne = 20;
        final String valueOne = RandomString.next(10);
        final String valueOneNew = RandomString.next(10);
        final Integer keyTwo = 120;
        final String valueTwo = RandomString.next(10);

        symbolTable.put(keyOne, valueOne);
        symbolTable.put(keyTwo, valueTwo);
        symbolTable.put(keyOne, valueOneNew);

        assertEquals(valueOneNew, symbolTable.get(keyOne));
        assertEquals(valueTwo, symbolTable.get(keyTwo));
    }

    @Test
    public void shouldPutGetAndUpdateRandomData() throws Exception {
        final Integer[] keys = new Integer[SIZE/2];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = i;
        }

        final String value = RandomString.next(5);
        final int multiplier = RandomInt.next(1, 6);

        KnuthShuffle.shuffle(keys);

        for (Integer key : keys) {
            symbolTable.put(key, value + key);
            final Integer keyTwo = getCollidingKey(key, multiplier);
            symbolTable.put(keyTwo, value + keyTwo);
        }

        for (Integer key : keys) {
            assertEquals(value + key, symbolTable.get(key));
            final Integer keyTwo = getCollidingKey(key, multiplier);
            assertEquals(value + keyTwo, symbolTable.get(keyTwo));
        }

        final String newValue = RandomString.next(10);

        for (Integer key : keys) {
            symbolTable.put(key, newValue + key);
            final Integer keyTwo = getCollidingKey(key, multiplier);
            symbolTable.put(keyTwo, newValue + keyTwo);
        }

        for (Integer key : keys) {
            assertEquals(newValue + key, symbolTable.get(key));
            final Integer keyTwo = getCollidingKey(key, multiplier);
            assertEquals(newValue + keyTwo, symbolTable.get(keyTwo));
        }
    }

    private int getCollidingKey(final Integer key, final int multiplier) {
        return key + (SIZE * multiplier);
    }

    @Test
    public void shouldGetNullForNonExistingKeys() throws Exception {
        assertNull(symbolTable.get(RandomInt.next()));
        final int key = RandomInt.next(SIZE);
        symbolTable.put(key, RandomString.next(10));

        assertNull(symbolTable.get(getCollidingKey(key, RandomInt.next(1, 9))));
    }

    @Test
    public void shouldGetNullForNoMatchAfterAllSearched() throws Exception {
        final int key = RandomInt.next(1, SIZE);
        for (int  i = 0; i < SIZE; i++) {
            symbolTable.put(key + (i * SIZE), RandomString.next(10));
        }

        assertNull(symbolTable.get(SIZE));
    }

    @Test( expected = SymbolTableWithLinearProbing.SymbolTableOutOfSizeException.class )
    public void shouldThrowExceptionOnKeysReachSize() throws Exception {
        for (int i = 0; i < SIZE + 10; i++) {
            symbolTable.put(i, RandomString.next(10));
        }
    }

    @Test( expected = SymbolTable.NullNotSupportedException.class )
    public void shouldNotAcceptNullKey() throws Exception {
        symbolTable.put(null, RandomString.next());
    }
}
