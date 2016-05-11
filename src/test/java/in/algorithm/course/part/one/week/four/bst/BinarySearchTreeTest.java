package in.algorithm.course.part.one.week.four.bst;

import in.algorithm.course.part.one.week.two.sort.application.KnuthShuffle;
import in.algorithm.course.util.RandomInt;
import in.algorithm.course.util.RandomString;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> bst;

    @Before
    public void setUp() {
        bst = BinarySearchTreeImpl.createNew();
    }

    @Test
    public void shouldPutAndGetValues() throws Exception {
        final int size = RandomInt.next(10, 100);
        final Integer[] values = putShuffledValuesIntoBSTTill(size);

        for (int i = 0; i < values.length; i++) {
            assertEquals(Integer.valueOf(i), bst.get(i));
        }

        assertNull(bst.get(RandomInt.next(100, 1000)));
    }

    private Integer[] putShuffledValuesIntoBSTTill(final int size) {
        final Integer[] values = integerArrayOfNumbersTill(size);

        KnuthShuffle.shuffle(values);

        putValuesIntoBst(values);

        return values;
    }

    private Integer[] integerArrayOfNumbersTill(final int size) {
        final Integer[] values = new Integer[size];
        for (int i =0; i < size; i++) {
            values[i] = i;
        }
        return values;
    }

    private void putValuesIntoBst(final Integer[] values) {
        for (int i = 0; i < values.length; i++) {
            bst.put(values[i]);
        }
    }

    @Test
    public void shouldUpdateElementOnPut() throws Exception {
        final BinarySearchTree<KeyValuePair> anotherBst = BinarySearchTreeImpl.createNew();
        final int size = RandomInt.next(10, 20);

        final KeyValuePair[] keyValues = new KeyValuePair[size];
        for (int i = 0; i < keyValues.length; i++) {
            keyValues[i] = KeyValuePair.createNew(RandomInt.next(i*100, (i+1)*100), RandomString.next());
            anotherBst.put(keyValues[i]);
        }

        for (int i = 0; i < keyValues.length; i++) {
            final String oldValue = keyValues[i].value;
            assertEquals(oldValue, anotherBst.get(keyValues[i]).value);

            final String newValue = RandomString.next();
            final KeyValuePair newPair = KeyValuePair.createNew(keyValues[i].key, newValue);
            anotherBst.put(newPair);
            assertEquals(newValue, anotherBst.get(newPair).value);
        }

    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldNotAcceptNullValue() throws Exception {
        bst.put(null);
    }

    @Test
    public void shouldGetSize() throws Exception {
        final int size = RandomInt.next(100, 200);
        for (int i = 0; i < size; i++) {
            bst.put(i);
            assertEquals(i+1, bst.size());
        }

    }

    @Test
    public void shouldCheckIfEmpty() throws Exception {
        assertTrue(bst.isEmpty());
        bst.put(RandomInt.next());
        assertFalse(bst.isEmpty());
    }

    @Test
    public void shouldCheckIfContains() throws Exception {
        assertFalse(bst.contains(RandomInt.next()));

        final int size = RandomInt.next(10, 100);
        Integer values [] = putShuffledValuesIntoBSTTill(size);

        for (int i = 0; i < values.length; i++) {
            final int value = values[i];
            assertTrue(bst.contains(value));
        }

        assertFalse(bst.contains(RandomInt.next(2000, 3000)));
    }

    @Test
    public void shouldRemoveExistingElementWithNoChildNodes() throws Exception {
        bst.put(1);
        bst.put(0);
        bst.put(2);

        assertEquals(3, bst.size());

        bst.remove(0);

        assertEquals(2, bst.size());
        assertFalse(bst.contains(0));
    }

    @Test
    public void shouldRemoveRootNodeWhenOnlyElement() throws Exception {
        final int value = RandomInt.next();
        bst.put(value);
        assertEquals(1, bst.size());

        bst.remove(value);
        assertEquals(0, bst.size());
        assertTrue(bst.isEmpty());
        assertFalse(bst.contains(value));
    }

    @Test
    public void shouldRemoveElementWithOneChild() throws Exception {
        bst.put(20);
        bst.put(10);
        bst.put(5);
        bst.put(30);
        bst.put(40);

        assertEquals(5, bst.size());

        bst.remove(10);
        assertEquals(4, bst.size());
        assertFalse(bst.contains(10));

        bst.remove(30);
        assertEquals(3, bst.size());
        assertFalse(bst.contains(30));
    }

    @Test
    public void shouldRemoveElementWithTwoChildren() throws Exception {
        bst.put(20);

        bst.put(10);
        bst.put(5);
        bst.put(15);
        bst.put(1);
        bst.put(7);
        bst.put(11);
        bst.put(17);

        bst.put(30);
        bst.put(25);
        bst.put(35);

        assertEquals(11, bst.size());

        bst.remove(5);
        assertEquals(10, bst.size());
        assertFalse(bst.contains(5));

        bst.remove(10);
        assertEquals(9, bst.size());
        assertFalse(bst.contains(10));

        bst.remove(30);
        assertEquals(8, bst.size());
        assertFalse(bst.contains(30));
    }

    @Test
    public void shouldRemove() throws Exception {
        final int size = RandomInt.next(10, 200);
        final Integer[] values = putShuffledValuesIntoBSTTill(size);

        assertEquals(size, bst.size());

        for (int i = 0; i < values.length; i++) {
            bst.remove(i);
            assertEquals(size-i-1, bst.size());
            assertFalse(bst.contains(i));
        }
    }


    @Test
    public void shouldGetMinElement() throws Exception {
        final Integer min = RandomInt.next(10, 30);
        final int size = RandomInt.next(20, 100);

        final Integer [] inputs = new Integer[size];
        for (int i = 0; i < inputs.length-1; i++) {
            inputs[i] = RandomInt.next(min+1, 1000);
        }
        inputs[size-1] = min;

        KnuthShuffle.shuffle(inputs);

        putValuesIntoBst(inputs);

        assertEquals(min, bst.min());

    }

    @Test
    public void shouldGetNullForMinIfEmpty() throws Exception {
        assertTrue(bst.isEmpty());
        assertNull(bst.min());
    }

    @Test
    public void shouldGetNullForMaxIfEmpty() throws Exception {
        assertTrue(bst.isEmpty());
        assertNull(bst.max());
    }

    @Test
    public void shouldGetMaxElement() throws Exception {
        final int size = RandomInt.next(10, 300);
        final Integer values[] = integerArrayOfNumbersTill(size);

        KnuthShuffle.shuffle(values);

        putValuesIntoBst(values);

        assertEquals(Integer.valueOf(size-1), bst.max());

    }

    @Test
    public void shouldGetNumberOfElementsSmallerThanInput() throws Exception {
        final int numberOfSmallerElements = RandomInt.next(10, 100);
        final Integer[] values = putShuffledValuesIntoBSTTill(numberOfSmallerElements);


        for (int i = 0; i < values.length; i++) {
            final int existingValue = RandomInt.next(numberOfSmallerElements);
            assertEquals(existingValue, bst.rank(existingValue));
        }

        final int largeValueNotInserted = RandomInt.next(100, 300);
        assertEquals(numberOfSmallerElements, bst.rank(largeValueNotInserted));

        final int largeValueInserted = RandomInt.next(300, 400);
        bst.put(largeValueInserted);
        assertEquals(numberOfSmallerElements, bst.rank(largeValueInserted));

        assertEquals(0, bst.rank(0));
    }


    @Test
    public void shouldGetLargestValueSmallerThanOrEqualToInputForInputLargerThanElementsOrMatching() throws Exception {
        final int numberOfSmallerElements = RandomInt.next(10, 100);
        final Integer [] values = new Integer[numberOfSmallerElements - 10 + 1];
        for (int i = 0; i <= numberOfSmallerElements-10; i++) {
            values[i] = 10 + i;
        }

        KnuthShuffle.shuffle(values);
        putValuesIntoBst(values);

        for (int i = 0; i < values.length; i++) {
            final int existingValue = values[i];
            assertEquals(existingValue, bst.floor(existingValue).intValue());
        }

        final int largeKeyNotInserted = RandomInt.next(100, 200);
        assertEquals(numberOfSmallerElements, bst.floor(largeKeyNotInserted).intValue());

        final int largeKeyInserted = RandomInt.next(200, 300);
        bst.put(largeKeyInserted);
        assertEquals(largeKeyInserted, bst.floor(largeKeyInserted).intValue());

        assertNull(bst.floor(RandomInt.next(5)));
    }

    @Test
    public void shouldGetLargestValueSmallerThanOrEqualToInputForInputInBetweenElements() throws Exception {
        final int times = RandomInt.next(10,20);
        final int size = RandomInt.next(100, 200);
        final Integer[] values = arrayOfSizeWithNTimesOfIndicesAsValues(times, size);

        for (int i = 0; i < size; i++) {
            bst.put(values[i]);
        }

        for (int i = 1; i < size-1; i++) {
            final int input = i * times - RandomInt.next(times);
            final int expectedFloor = (int) Math.floor((double) input / times) * times;

            assertEquals(Integer.valueOf(expectedFloor), bst.floor(input));
        }
    }

    @Test
    public void shouldGetSmallestValueGreaterThanOrEqualToInputForInputsAtBordersOrMatching() throws Exception {
        final int size = RandomInt.next(10, 100);

        final Integer [] values = new Integer[size];

        for (int i = 1; i <= size; i++) {
            values[i-1] = i;
        }

        KnuthShuffle.shuffle(values);
        putValuesIntoBst(values);

        assertEquals(1, bst.ceiling(0).intValue());

        for (int i = 0; i < values.length; i++) {
            final int matchingNumber = RandomInt.next(1, size);
            assertEquals(matchingNumber, bst.ceiling(matchingNumber).intValue());
        }

        assertEquals(size, bst.ceiling(size).intValue());

        assertNull(bst.ceiling(RandomInt.next(size+1, 1000)));
    }

    @Test
    public void shouldGetSmallestValueGreaterThanOrEqualToInputForInputInBetweenElements() throws Exception {
        final int times = RandomInt.next(10,20);
        final int size = RandomInt.next(100, 200);
        final Integer[] values = arrayOfSizeWithNTimesOfIndicesAsValues(times, size);

        for (int i = 0; i < size; i++) {
            bst.put(values[i]);
        }

        for (int i = 1; i < size-1; i++) {
            final int input = i * times - RandomInt.next(times);
            final int expectedFloor = (int) Math.ceil((double) input / times) * times;

            assertEquals(Integer.valueOf(expectedFloor), bst.ceiling(input));
        }
    }

    private Integer[] arrayOfSizeWithNTimesOfIndicesAsValues(final int times, final int size) {
        final Integer[] values = new Integer[size];

        for (int i = 0; i < size; i++) {
            values[i] = i * times;
        }

        KnuthShuffle.shuffle(values);
        return values;
    }

    @Test
    public void shouldFindNthSmallestValue() throws Exception {
        final int size = RandomInt.next(10, 100);
        final Integer[] values = putShuffledValuesIntoBSTTill(size);

        for (int i = 0; i < values.length; i++) {
            assertEquals(Integer.valueOf(i), bst.select(i));
        }

        assertNull(bst.select(-1));
        assertNull(bst.select(size));
    }

    @Test
    public void shouldDeleteMinValue() throws Exception {

        bst.deleteMin();

        final int size = RandomInt.next(10, 100);
        final Integer[] values = putShuffledValuesIntoBSTTill(size);

        for (int i = 0; i < values.length; i++) {
            assertTrue(bst.contains(i));
            assertEquals(size-i, bst.size());

            bst.deleteMin();

            assertFalse(bst.contains(i));
            assertEquals(size-i-1, bst.size());
        }
    }

    @Test
    public void shouldDeleteMaxValue() throws Exception {

        bst.deleteMax();

        final int size = RandomInt.next(10, 100);
        final Integer[] values = putShuffledValuesIntoBSTTill(size);

        for (int i = 0; i < values.length; i++) {
            assertTrue(bst.contains(size-i-1));
            assertEquals(size-i, bst.size());

            bst.deleteMax();

            assertFalse(bst.contains(size-i-1));
            assertEquals(size-i-1, bst.size());
        }

    }

    @Test
    public void shouldGetZeroNumberOfValuesWhenEmpty() throws Exception {
        assertEquals(0, bst.size(RandomInt.next(10, 100), RandomInt.next(100, 1000)));
    }

    @Test
    public void shouldGetSizeAsNumberOfValuesBetweenRangeCoveringAllValues() throws Exception {
        final int min = RandomInt.next(10);
        final int max = RandomInt.next(30, 50);

        for (int i = 0; i < 50; i++) {
            final int next = RandomInt.next(min + 1, max);
            bst.put(next);
        }

        assertEquals(bst.size(), bst.size(min, max));
    }

    @Test
    public void shouldGetNumberOfValuesBetweenMinAndMaxValuesProvidedValuesAreFound() throws Exception {
        final int size = RandomInt.next(10, 100);
        putShuffledValuesIntoBSTTill(size);

        final int min = RandomInt.next(10);
        final int max = RandomInt.next(10, size);

        assertEquals(max - min + 1, bst.size(min, max));
    }

    @Test
    public void shouldGetNumberOfValuesBetweenMinAndMaxValuesEvenIfValuesNotFound() throws Exception {
        final int times = RandomInt.next(1, 10);
        final int size = RandomInt.next(10, 100);

        final Integer [] values = arrayOfSizeWithNTimesOfIndicesAsValues(times, size);
        KnuthShuffle.shuffle(values);
        putValuesIntoBst(values);

        final int min = RandomInt.next(10)*times - RandomInt.next(times);
        final int max = RandomInt.next(10, size)*times + RandomInt.next(times);

        final int absMin = (int) Math.ceil((double) min / times);
        int absMax = (max / times);
        absMax = absMax > (size-1) ? (size-1) : absMax;

        assertEquals(absMax - absMin + 1, bst.size(min, max));

    }


    private static class KeyValuePair implements Comparable<KeyValuePair> {
        private Integer key;
        private String value;

        private KeyValuePair(final Integer key, final String value) {
            this.key = key;
            this.value = value;
        }

        public static KeyValuePair createNew(final Integer key, final String value) {
            return new KeyValuePair(key, value);
        }

        @Override
        public int compareTo(final KeyValuePair obj) {
            return this.key.compareTo(obj.key);
        }

        @Override
        public boolean equals(final Object obj) {
            return key.equals(obj);
        }
    }
}
