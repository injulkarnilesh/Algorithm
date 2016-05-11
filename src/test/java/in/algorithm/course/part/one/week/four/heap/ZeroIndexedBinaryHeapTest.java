package in.algorithm.course.part.one.week.four.heap;

import in.algorithm.course.part.one.week.two.sort.application.KnuthShuffle;
import in.algorithm.course.util.RandomInt;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ZeroIndexedBinaryHeapTest {

    @Test
    public void shouldHaveParentNoSmallerThanChildrenOnInsert() throws Exception {
        final BinaryHeap<Integer> heap = new BinaryHeapZeroIndexedImpl<>(10);
        int itemFive = 5;
        heap.insert(itemFive);
        Integer[] heapData = heapData(heap);
        //5
        assertEquals(Integer.valueOf(itemFive), heapData[0]);

        int itemFour = 4;
        heap.insert(itemFour);
        heapData = heapData(heap);
        //5, 4
        assertEquals(Integer.valueOf(itemFour), heapData[1]);

        int itemSeven = 7;
        heap.insert(itemSeven);
        heapData = heapData(heap);
        //7, 4, 5
        assertEquals(Integer.valueOf(itemSeven), heapData[0]);
        assertEquals(Integer.valueOf(itemFour), heapData[1]);
        assertEquals(Integer.valueOf(itemFive), heapData[2]);

        int itemTen = 10;
        heap.insert(itemTen);
        heapData = heapData(heap);
        //10, 7, 5, 4
        assertEquals(Integer.valueOf(itemTen), heapData[0]);
        assertEquals(Integer.valueOf(itemSeven), heapData[1]);
        assertEquals(Integer.valueOf(itemFive), heapData[2]);
        assertEquals(Integer.valueOf(itemFour), heapData[3]);
    }

    @Test
    public void shouldHaveLargestElementAtTopAlways() throws Exception {
        final int size = RandomInt.next(100, 1000);
        final Integer[] array = getArrayWithIndexedNumbers(size);

        final BinaryHeap<Integer> heap = new BinaryHeapZeroIndexedImpl(size);

        for (int i = 0; i < size; i++) {
            heap.insert(array[i]);
            assertEquals(i + 1, heapData(heap)[0].intValue());
        }
    }

    private Integer[] getArrayWithIndexedNumbers(final int size) {
        final Integer []  array = new Integer[size];
        for(int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    @Test
    public void shouldHaveMaxElementAtTopOnRemovingMaxFromTop() throws Exception {
        final BinaryHeap<Integer> heap = new BinaryHeapZeroIndexedImpl(10);
        int itemFive = 5;
        heap.insert(itemFive);
        int itemFour = 4;
        heap.insert(itemFour);
        int itemSeven = 7;
        heap.insert(itemSeven);
        int itemTen = 10;
        heap.insert(itemTen);
        int itemThree = 3;
        heap.insert(itemThree);
        int itemTwo = 2;
        heap.insert(itemTwo);

        //10, 7, 5, 4, 3, 2
        int max = heap.deleteMax();
        assertEquals(itemTen, max);

        //7, 4, 5, 2, 3
        Integer[] heapData = heapData(heap);
        assertEquals(itemSeven, heapData[0].intValue());
        assertEquals(itemFour, heapData[1].intValue());
        assertEquals(itemFive, heapData[2].intValue());
        assertEquals(itemTwo, heapData[3].intValue());
        assertEquals(itemThree, heapData[4].intValue());

        max = heap.deleteMax();
        assertEquals(itemSeven, max);

        //5, 4, 3, 2
        heapData = heapData(heap);
        assertEquals(itemFive, heapData[0].intValue());
        assertEquals(itemFour, heapData[1].intValue());
        assertEquals(itemThree, heapData[2].intValue());
        assertEquals(itemTwo, heapData[3].intValue());
    }

    @Test
    public void shouldMaintainMaxAtTopOnDeleteMax() throws Exception {
        final int size = RandomInt.next(100, 1000);
        final Integer[] array = getArrayWithIndexedNumbers(size);

        KnuthShuffle.shuffle(array);

        final BinaryHeap<Integer> heap = new BinaryHeapZeroIndexedImpl(size);

        for (int i = 0; i < size; i++) {
            heap.insert(array[i]);
        }

        for(int i = size; i > 0; i--) {
            assertEquals(i, heap.deleteMax().intValue());
        }

    }

    private Integer[] heapData(final BinaryHeap<Integer> heap) {
        final Comparable[] array = ((BinaryHeapZeroIndexedImpl)heap).getData();
        final Integer[] intArray = new Integer[array.length];
        for (int i = 0; i < array.length; i++)
            intArray[i] = (Integer)array[i];
        return intArray;
    }


    @Test (expected = BinaryHeap.EmptyHeap.class)
    public void shouldThrowEmptyHeapExceptionOnDeleteWhenEmptyHeap() throws Exception {
        final BinaryHeap<String> heap = new BinaryHeapZeroIndexedImpl(5);
        assertTrue(heap.isEmpty());
        heap.insert("WHAT");
        assertFalse(heap.isEmpty());
        heap.deleteMax();

        heap.deleteMax();
    }

    @Test (expected = BinaryHeap.FullHeap.class)
    public void shouldThrowFullHeapExceptionOnInsertingIntoFullHeap() throws Exception {
        final BinaryHeap<String> heap = new BinaryHeapZeroIndexedImpl(0);
        heap.insert("WHERE");
    }
}
