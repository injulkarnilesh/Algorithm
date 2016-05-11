package in.algorithm.course.part.one.week.five.balancedsearchtree;

import in.algorithm.course.part.one.week.two.sort.application.KnuthShuffle;
import in.algorithm.course.util.RandomInt;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TwoThreeBalancedSearchTreeTest {

    private BalancedSearchTree<Integer> tree;

    @Before
    public void setUp() {
        tree = TwoThreeBalancedSearchTree.createNew();
    }

    @Test
    public void shouldInsertSingleElement() throws Exception {
        final Integer item = RandomInt.next();

        tree.insert(item);

        final TwoThreeBalancedSearchTree.Node<Integer> root = getRootNode();
        assertNotNull(root);
        assertTwoNodeAndValue(root, item);
    }

    private TwoThreeBalancedSearchTree.Node<Integer> getRootNode() {
        return ((TwoThreeBalancedSearchTree) tree).root();
    }


    @Test
    public void shouldInsertTwoElementsInOneThreeNode() throws Exception {
        final Integer firstItem = RandomInt.next();
        final Integer secondItem  = RandomInt.next();

        tree.insert(firstItem);
        tree.insert(secondItem);

        final TwoThreeBalancedSearchTree.Node<Integer> root = getRootNode();
        assertFalse(root.isTwoNode());
        assertThreeNodeWithValues(root, Integer.valueOf(Math.min(firstItem, secondItem)), Integer.valueOf(Math.max(firstItem, secondItem)));
    }

    @Test
    public void shouldSplitFourNodeAtRoot() throws Exception {
        final Integer firstItem = RandomInt.next();
        final Integer secondItem  = RandomInt.next();
        final Integer thirdItem = RandomInt.next();

        tree.insert(firstItem);
        tree.insert(secondItem);
        tree.insert(thirdItem);

        final TwoThreeBalancedSearchTree.Node<Integer> root = getRootNode();
        assertTrue(root.isTwoNode());
        assertFalse(root.isThreeNode());

        assertEquals(middle(firstItem, secondItem, thirdItem), root.asTwoNode().getValue());

        final TwoThreeBalancedSearchTree.Node<Integer> leftChild = root.asTwoNode().getLeft();
        final TwoThreeBalancedSearchTree.Node<Integer> rightChild = root.asTwoNode().getRight();

        assertEquals(smallest(firstItem, secondItem, thirdItem), leftChild.asTwoNode().getValue());
        assertEquals(largest(firstItem, secondItem, thirdItem), rightChild.asTwoNode().getValue());
    }

    private Integer smallest(final Integer first, final Integer second, final Integer third) {
        return Math.min(first, Math.min(second, third));
    }

    private Integer largest(final Integer first, final Integer second, final Integer third) {
        return Math.max(first, Math.max(second, third));
    }

    private Integer middle(final Integer first, final Integer second, final Integer third) {
        List<Integer> numbers = new ArrayList();
        numbers.add(first); numbers.add(second); numbers.add(third);

        numbers.remove(smallest(first, second, third));
        numbers.remove(largest(first, second, third));

        return numbers.get(0);
    }

    @Test
    public void shouldInsertLeftAndRightChild() throws Exception {
        final Integer rootItemThirty = 30;
        final Integer leftChildValueFifteen = 15;
        final Integer rightChildValueFortyFive = 45;

        tree.insert(rootItemThirty);
        tree.insert(leftChildValueFifteen);
        tree.insert(rightChildValueFortyFive);

        TwoThreeBalancedSearchTree.Node<Integer> root = getRootNode();
        assertTwoNodeAndValue(root, rootItemThirty);
        assertEquals(leftChildValueFifteen, root.asTwoNode().getLeft().asTwoNode().getValue());
        assertEquals(rightChildValueFortyFive, root.asTwoNode().getRight().asTwoNode().getValue());

        final Integer newLeftChildValueTwenty = 20;
        final Integer newRightChildValueForty = 40;

        tree.insert(newLeftChildValueTwenty);
        tree.insert(newRightChildValueForty);

        final TwoThreeBalancedSearchTree.Node<Integer> left = root.asTwoNode().getLeft();
        final TwoThreeBalancedSearchTree.Node<Integer> right = root.asTwoNode().getRight();

        assertThreeNodeWithValues(left, leftChildValueFifteen, newLeftChildValueTwenty);

        assertThreeNodeWithValues(right, newRightChildValueForty, rightChildValueFortyFive);
    }

    private void assertThreeNodeWithValues(final TwoThreeBalancedSearchTree.Node<Integer> node, final Integer leftValue, final Integer rightValue) {
        assertTrue(node.isThreeNode());
        assertEquals(leftValue, node.asThreeNode().getLeftValue());
        assertEquals(rightValue, node.asThreeNode().getRightValue());
    }


    @Test
    public void shouldSplitNonRootNonParentNodes() throws Exception {
        final Integer rootItemThirty = 30;
        final Integer leftChildValueFifteen = 15;
        final Integer rightChildValueFortyFive = 45;

        tree.insert(rootItemThirty);
        tree.insert(leftChildValueFifteen);
        tree.insert(rightChildValueFortyFive);


        final Integer newLeftChildValueTen = 10;
        final Integer newRightChildValueSixty = 60;

        tree.insert(newLeftChildValueTen);
        tree.insert(newRightChildValueSixty);

        final Integer valueToSplitRightChildFifty = 50;

        tree.insert(valueToSplitRightChildFifty);

        TwoThreeBalancedSearchTree.Node<Integer> root = getRootNode();
        assertThreeNodeWithValues(root, rootItemThirty, valueToSplitRightChildFifty);

        final TwoThreeBalancedSearchTree.Node<Integer> left = root.asThreeNode().getLeft();
        final TwoThreeBalancedSearchTree.Node<Integer> middle = root.asThreeNode().getMiddle();
        final TwoThreeBalancedSearchTree.Node<Integer> right = root.asThreeNode().getRight();

        assertThreeNodeWithValues(left, newLeftChildValueTen, leftChildValueFifteen);

        assertTwoNodeAndValue(middle, rightChildValueFortyFive);

        assertTwoNodeAndValue(right, newRightChildValueSixty);

        final Integer valueToSplitLeftChildTwenty = 20;

        tree.insert(valueToSplitLeftChildTwenty);

        final TwoThreeBalancedSearchTree.Node<Integer> newRoot = getRootNode();
        assertTrue(newRoot.isTwoNode());

        final TwoThreeBalancedSearchTree.Node<Integer> newLeftNode = newRoot.asTwoNode().getLeft();
        assertTrue(newLeftNode.isTwoNode());
        assertTwoNodeAndValue(newLeftNode, leftChildValueFifteen);
        assertTwoNodeAndValue(newLeftNode.asTwoNode().getLeft(), newLeftChildValueTen);
        assertTwoNodeAndValue(newLeftNode.asTwoNode().getRight(), valueToSplitLeftChildTwenty);

        final TwoThreeBalancedSearchTree.Node<Integer> newRightNode = newRoot.asTwoNode().getRight();
        assertTrue(newRightNode.isTwoNode());
        assertTwoNodeAndValue(newRightNode, valueToSplitRightChildFifty);
        assertTwoNodeAndValue(newRightNode.asTwoNode().getLeft(), rightChildValueFortyFive);
        assertTwoNodeAndValue(newRightNode.asTwoNode().getRight(), newRightChildValueSixty);

    }

    private void assertTwoNodeAndValue(final TwoThreeBalancedSearchTree.Node<Integer> node, final Integer value) {
        assertTrue(node.isTwoNode());
        assertEquals(value, node.asTwoNode().getValue());
    }

    @Test
    public void shouldGetAllValuesInInOrder() throws Exception {

        List<Integer> values = tree.values();
        assertTrue(values.isEmpty());

        tree.insert(100);
        tree.insert(50);
        tree.insert(20);
        tree.insert(10);
        tree.insert(140);
        tree.insert(5);
        tree.insert(75);
        tree.insert(190);
        tree.insert(130);

        values = tree.values();
        assertEquals(9, values.size());

        assertEquals(5, values.get(0).intValue());
        assertEquals(10, values.get(1).intValue());
        assertEquals(20, values.get(2).intValue());
        assertEquals(50, values.get(3).intValue());
        assertEquals(75, values.get(4).intValue());
        assertEquals(100, values.get(5).intValue());
        assertEquals(130, values.get(6).intValue());
        assertEquals(140, values.get(7).intValue());
        assertEquals(190, values.get(8).intValue());

    }

    @Test
    public void shouldInsertRandomValuesInOrder() throws Exception {
        final int size = RandomInt.next(100, 200);
        final Integer[] array = shuffledIntegersTillSize(size);

        insertIntoTree(array);

        final List<Integer> values = tree.values();

        assertEquals(size, values.size());
        for (int i = 0; i < values.size(); i++) {
            assertEquals(Integer.valueOf(i), values.get(i));
        }
    }

    private void insertIntoTree(final Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            tree.insert(array[i]);
        }
    }

    private Integer[] shuffledIntegersTillSize(final int size) {
        final Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }

        KnuthShuffle.shuffle(array);
        return array;
    }

    @Test
    public void shouldNotInsertExistingValue() throws Exception {
        final int size = RandomInt.next(100, 200);
        final Integer[] array = shuffledIntegersTillSize(size);

        insertIntoTree(array);
        insertIntoTree(array);

        assertEquals(size, tree.values().size());
    }

    @Test
    public void shouldSearchExistingValue() throws Exception {
        assertNull(tree.search(RandomInt.next()));

        final int multiple = 10;
        final int size = RandomInt.next(100, 200);

        final Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = i * multiple;
        }

        KnuthShuffle.shuffle(array);

        insertIntoTree(array);

        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], tree.search(array[i]));
        }

        for (int i = 0; i < array.length; i++) {
            assertNull(tree.search(array[i] + RandomInt.next(1, multiple)));
            assertNull(tree.search(array[i] - RandomInt.next(1, multiple)));
        }

    }
}
