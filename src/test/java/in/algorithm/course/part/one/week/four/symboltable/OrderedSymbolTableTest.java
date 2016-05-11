package in.algorithm.course.part.one.week.four.symboltable;

import in.algorithm.course.part.one.week.two.sort.application.KnuthShuffle;
import in.algorithm.course.util.RandomInt;
import in.algorithm.course.util.RandomString;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class OrderedSymbolTableTest {

    public static final int CAPACITY_MIN = 100;
    public static final int CAPACITY_MAX = 500;

    private int capacity;
    private OrderedSymbolTable<Integer, String> symbolTable;

    @Before
    public void setUp() throws Exception {
        capacity = RandomInt.next(CAPACITY_MIN, CAPACITY_MAX);
        symbolTable = OrderedSymbolTableImpl.createNew(capacity);
    }

    @Test
    public void shouldGetElementsAlreadyPut() throws Exception {
        final String firstValue = RandomString.next();
        final int firstKey = RandomInt.next(100);

        final String secondValue = RandomString.next();
        final int secondKey = RandomInt.next(100, 500);

        symbolTable.put(firstKey, firstValue);
        assertEquals(firstValue, symbolTable.get(firstKey));

        symbolTable.put(secondKey, secondValue);
        assertEquals(secondValue, symbolTable.get(secondKey));
    }

    @Test
    public void shouldPutElementOrUpdate() throws Exception {
        final int key = RandomInt.next();
        final String originalValue = RandomString.next();
        final String newValue = RandomString.next();

        symbolTable.put(key, originalValue);
        assertEquals(originalValue, symbolTable.get(key));
        assertEquals(1, symbolTable.size());

        symbolTable.put(key, newValue);
        assertEquals(1, symbolTable.size());
        assertEquals(newValue, symbolTable.get(key));
    }

    @Test
    public void shouldUpdateExistingOnPut() throws Exception {
        final int size = RandomInt.next(capacity);
        final Integer [] keys = new Integer[size];
        for(int i = 0; i < size; i++) {
            keys[i] = i;
        }

        KnuthShuffle.shuffle(keys);

        for(int i = 0; i < size; i++) {
            symbolTable.put(keys[i], RandomString.next());
        }

        assertEquals(size, symbolTable.size());

        for(int i = 0; i < size; i++) {
            final int randomKey = keys[RandomInt.next(size)];
            final String randomNewValue = RandomString.next();

            symbolTable.put(randomKey, randomNewValue);

            assertEquals(randomNewValue, symbolTable.get(randomKey));
            assertEquals(size, symbolTable.size());
        }
    }

    @Test
    public void shouldGetSize() throws Exception {
        assertEquals(0, symbolTable.size());

        symbolTable.put(RandomInt.next(), RandomString.next());
        assertEquals(1, symbolTable.size());

        symbolTable.put(RandomInt.next(), RandomString.next());
        symbolTable.put(RandomInt.next(), RandomString.next());
        assertEquals(3, symbolTable.size());
    }

    @Test
    public void shouldRemoveElements() throws Exception {
        final int keyToCheck = RandomInt.next(100);
        final int keyToCheckTwo = RandomInt.next(100, 300);
        final int keyToCheckThree = RandomInt.next(300, 500);

        symbolTable.put(keyToCheck, RandomString.next());
        symbolTable.put(RandomInt.next(500, 700), RandomString.next());
        symbolTable.put(keyToCheckTwo, RandomString.next());
        symbolTable.put(RandomInt.next(700, 1000), RandomString.next());
        symbolTable.put(keyToCheckThree, RandomString.next());

        assertEquals(5, symbolTable.size());

        symbolTable.remove(keyToCheck);
        symbolTable.remove(keyToCheckTwo);
        symbolTable.remove(keyToCheckThree);
        symbolTable.remove(RandomInt.next(1000, 1500));

        assertEquals(2, symbolTable.size());
        assertNull(symbolTable.get(keyToCheck));
        assertNull(symbolTable.get(keyToCheckTwo));
        assertNull(symbolTable.get(keyToCheckThree));
    }

    @Test
    public void shouldGetIsEmpty() throws Exception {
        assertTrue(symbolTable.isEmpty());

        symbolTable.put(RandomInt.next(), RandomString.next());

        assertFalse(symbolTable.isEmpty());
    }

    @Test
    public void shouldCheckIfContains() throws Exception {
        final int key = RandomInt.next(100);
        assertFalse(symbolTable.contains(key));

        symbolTable.put(key, RandomString.next());

        assertTrue(symbolTable.contains(key));
    }

    @Test (expected = OrderedSymbolTable.Overflow.class)
    public void shouldNotExceedCapacity() throws Exception {

        for(int i = 0; i < capacity + 1 ; i++) {
            symbolTable.put(i, RandomString.next());
        }

    }

    @Test
    public void shouldGetNumberOfElementsSmallerThanInput() throws Exception {
        final int numberOfSmallerElements = RandomInt.next(CAPACITY_MIN - 10);
        for (int i = 0; i < numberOfSmallerElements; i++) {
            symbolTable.put(i, RandomString.next());
        }

        final int keyToCheck = RandomInt.next(numberOfSmallerElements);
        assertEquals(keyToCheck, symbolTable.rank(keyToCheck));

        final int largeKeyNotInserted = RandomInt.next(numberOfSmallerElements + 1, capacity);
        assertEquals(numberOfSmallerElements, symbolTable.rank(largeKeyNotInserted));

        final int largeKeyInserted = RandomInt.next(numberOfSmallerElements, CAPACITY_MAX);
        symbolTable.put(largeKeyInserted, RandomString.next());
        assertEquals(numberOfSmallerElements, symbolTable.rank(largeKeyInserted));

        assertEquals(0, symbolTable.rank(0));
    }

    @Test
    public void shouldGetLargestKeySmallerThanOrEqualToInput() throws Exception {
        final int numberOfSmallerElements = RandomInt.next(10, CAPACITY_MIN);
        for (int i = 10; i <= numberOfSmallerElements; i++) {
            symbolTable.put(i, RandomString.next());
        }

        final int existingKey = RandomInt.next(10, numberOfSmallerElements);
        assertEquals(existingKey, symbolTable.floor(existingKey).intValue());

        final int largeKeyNotInserted = RandomInt.next(CAPACITY_MIN, capacity);
        assertEquals(numberOfSmallerElements, symbolTable.floor(largeKeyNotInserted).intValue());

        final int largeKeyInserted = RandomInt.next(CAPACITY_MIN, capacity);
        symbolTable.put(largeKeyInserted, RandomString.next());
        assertEquals(largeKeyInserted, symbolTable.floor(largeKeyInserted).intValue());

        final int largerKeyInserted = largeKeyInserted + 1;
        symbolTable.put(largerKeyInserted, RandomString.next());
        assertEquals(largeKeyInserted, symbolTable.floor(largeKeyInserted).intValue());

        assertNull(symbolTable.floor(RandomInt.next(9)));
    }

    @Test
    public void shouldGetLargestKeySmallerThanOrEqualToInputForInputInBetweenElements() throws Exception {
        final int times = RandomInt.next(10,20);
        final int size = RandomInt.next(100, 200);
        final Integer[] values = new Integer[size];
        for (int i = 0; i < size; i++) {
            values[i] = i * times;
        }

        KnuthShuffle.shuffle(values);

        for (int i = 0; i < size; i++) {
            symbolTable.put(values[i], RandomString.next());
        }

        for (int i = 1; i < size-1; i++) {
            final int input = i * times - RandomInt.next(times);
            final int expectedFloor = (int) Math.floor((double) input / times) * times;

            assertEquals(Integer.valueOf(expectedFloor), symbolTable.floor(input));
        }
    }

    @Test
    public void shouldGetSmallestKeyGreaterThanOrEqualToInput() throws Exception {
        final int size = RandomInt.next(10, CAPACITY_MIN);
        for (int i = 1; i <= size; i++) {
            symbolTable.put(i, RandomString.next());
        }

        assertEquals(1, symbolTable.ceiling(0).intValue());

        final int matchingNumber = RandomInt.next(1, size);
        assertEquals(matchingNumber, symbolTable.ceiling(matchingNumber).intValue());

        assertEquals(size, symbolTable.ceiling(size).intValue());

        assertNull(symbolTable.ceiling(RandomInt.next(size+1, CAPACITY_MAX)));
    }

    @Test
    public void shouldGetSmallestKeyGreaterThanOrEqualToInputForInputInBetweenElements() throws Exception {
        final int times = RandomInt.next(10,20);
        final int size = RandomInt.next(100, 200);
        final Integer[] values = new Integer[size];
        for (int i = 0; i < size; i++) {
            values[i] = i * times;

        }

        KnuthShuffle.shuffle(values);

        for (int i = 0; i < size; i++) {
            symbolTable.put(values[i], RandomString.next());
        }

        for (int i = 1; i < size-1; i++) {
            final int input = i * times - RandomInt.next(times);
            final int expectedFloor = (int) Math.ceil((double) input / times) * times;

            assertEquals(Integer.valueOf(expectedFloor), symbolTable.ceiling(input));
        }
    }

    @Test
    public void shouldFindNthSmallestKey() throws Exception {
        final int size = RandomInt.next(10, CAPACITY_MIN);
        for (int i = 0; i < size; i++) {
            symbolTable.put(i, RandomString.next());
        }

        for (int i =0; i< size; i++) {
            assertEquals(Integer.valueOf(i), symbolTable.select(i));
        }

        assertNull(symbolTable.select(-1));
        assertNull(symbolTable.select(size));
    }

    @Test
    public void shouldDeleteMinKey() throws Exception {

        symbolTable.deleteMin();

        final int size = RandomInt.next(10, CAPACITY_MIN);
        for (int i = 0; i < size; i++) {
            symbolTable.put(i, RandomString.next());
        }

        assertTrue(symbolTable.contains(0));
        assertEquals(size, symbolTable.size());

        symbolTable.deleteMin();

        assertFalse(symbolTable.contains(0));
        assertEquals(size-1, symbolTable.size());
    }

    @Test
    public void shouldDeleteMaxKey() throws Exception {

        symbolTable.deleteMax();

        final int size = RandomInt.next(10, CAPACITY_MIN);
        for (int i = 0; i < size; i++) {
            symbolTable.put(i, RandomString.next());
        }

        assertTrue(symbolTable.contains(size-1));
        assertEquals(size, symbolTable.size());

        symbolTable.deleteMax();

        assertFalse(symbolTable.contains(size-1));
        assertEquals(size-1, symbolTable.size());
    }

    @Test
    public void shouldGetNumberOfKeysBetweenMinAndMaxKeysProvidedKeysAreFound() throws Exception {

        final int size = RandomInt.next(10, CAPACITY_MIN);
        for (int i = 0; i < size; i++) {
            symbolTable.put(i, RandomString.next());
        }
        final int min = RandomInt.next(10);
        final int max = RandomInt.next(10, size);
        assertEquals(max - min + 1, symbolTable.size(min, max));

    }

    @Test
    public void shouldGetNumberOfKeysBetweenMinAndMaxKeysEvenIfKeysNotFound() throws Exception {
        final int times = RandomInt.next(1, 10);
        final int size = RandomInt.next(10, CAPACITY_MIN);

        for (int i = 0; i < size; i++) {
            symbolTable.put(i*times, RandomString.next());
        }

        final int min = RandomInt.next(10)*times - RandomInt.next(times);
        final int max = RandomInt.next(10, size)*times + RandomInt.next(times);

        final int absMin = (int) Math.ceil((double) min / times);
        int absMax = (max / times);
        absMax = absMax > (size-1) ? (size-1) : absMax;

        assertEquals(absMax - absMin + 1, symbolTable.size(min, max));

    }

    @Test
    public void shouldGetZeroNumberOfKeysWhenEmpty() throws Exception {
        assertEquals(0, symbolTable.size(RandomInt.next(10, 100), RandomInt.next(100, 1000)));
    }

    @Test
    public void shouldGetSizeAsNumberOfKeysBetweenRangeCoveringAllKeys() throws Exception {
        final int min = RandomInt.next(10);
        final int max = RandomInt.next(10, 50);

        for (int i = 0; i < 20; i++) {
            symbolTable.put(RandomInt.next(min+1, max), RandomString.next());
        }

        assertEquals(symbolTable.size(), symbolTable.size(min, max));
    }

    @Test
    public void shouldGetIteratorForKeys() throws Exception {
        final int size = RandomInt.next(capacity);
        List<Integer> keysInserted = new ArrayList();
        for(int i = 0; i < size; i++) {
            symbolTable.put(i, RandomString.next());
            keysInserted.add(i);
        }

        List<Integer> keysIterated = new ArrayList();
        Iterator<Integer> keys = symbolTable.keys();
        while (keys.hasNext()) {
            keysIterated.add(keys.next());
        }

        assertEquals(keysInserted.size(), keysIterated.size());
        assertTrue(keysInserted.containsAll(keysIterated));
        assertTrue(keysIterated.containsAll(keysInserted));

    }
}
