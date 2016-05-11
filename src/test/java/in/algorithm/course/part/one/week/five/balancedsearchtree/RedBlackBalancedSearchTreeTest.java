package in.algorithm.course.part.one.week.five.balancedsearchtree;

import in.algorithm.course.part.one.week.five.balancedsearchtree.RedBlackBalancedSearchTree.LinkType;
import in.algorithm.course.part.one.week.five.balancedsearchtree.RedBlackBalancedSearchTree.Node;
import in.algorithm.course.part.one.week.two.sort.application.KnuthShuffle;
import in.algorithm.course.util.RandomBoolean;
import in.algorithm.course.util.RandomInt;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RedBlackBalancedSearchTreeTest {


    public static final Integer ROOT_VALUE_FORTY = 40;
    public static final Integer LEFT_SUB_TREE_TWENTY = 20;
    public static final Integer LEFT_IN_LEFT_SUB_TREE_TEN = 10;
    public static final Integer RIGHT_IN_LEFT_SUB_TREE_THIRTY = 30;
    public static final Integer RIGHT_SUB_TREE_SIXTY = 60;
    public static final Integer LEFT_IN_RIGHT_SUB_TREE_FIFTY = 50;
    public static final Integer RIGHT_IN_RIGHT_SUB_TREE_SEVENTY = 70;

    private BalancedSearchTree<Integer> tree;
    private RedBlackBalancedSearchTree<Integer> redBlackTree;

    @Before
    public void setUp() {
        tree = RedBlackBalancedSearchTree.createNew();
        redBlackTree = (RedBlackBalancedSearchTree) tree;
    }

    @Test
    public void shouldRotateLeft() throws Exception {
        final LinkType rootLinkType = randomLinkType();
        final Node<Integer> rootNode = createTreeToRotate(rootLinkType);
        rootNode.getRight().setLinkType(LinkType.RED);

        final Node<Integer> newRoot = redBlackTree.rotateLeft(rootNode);

        assertEquals(RIGHT_SUB_TREE_SIXTY, newRoot.getValue());

        final Node<Integer> newRight = newRoot.getRight();
        assertEquals(RIGHT_IN_RIGHT_SUB_TREE_SEVENTY, newRight.getValue());

        final Node<Integer> newLeft = newRoot.getLeft();
        assertEquals(LinkType.RED, newLeft.getLinkType());

        assertEquals(LEFT_SUB_TREE_TWENTY, newLeft.getLeft().getValue());
        assertEquals(LEFT_IN_RIGHT_SUB_TREE_FIFTY, newLeft.getRight().getValue());
        assertEquals(rootLinkType, newRoot.getLinkType());
    }

    private Node<Integer> createTreeToRotate(final LinkType rootLinkType) {
        final Node<Integer> rootNode = Node.createNew(ROOT_VALUE_FORTY);

        final Node<Integer> leftSubTreeRoot = Node.createNew(LEFT_SUB_TREE_TWENTY);
        final Node<Integer> leftInLeftSubTree = Node.createNew(LEFT_IN_LEFT_SUB_TREE_TEN);
        final Node<Integer> rightInLeftSubTree = Node.createNew(RIGHT_IN_LEFT_SUB_TREE_THIRTY);
        leftSubTreeRoot.setLeft(leftInLeftSubTree);
        leftSubTreeRoot.setRight(rightInLeftSubTree);

        final Node<Integer> rightSubTreeRoot = Node.createNew(RIGHT_SUB_TREE_SIXTY);
        final Node<Integer> leftInRightSubTree = Node.createNew(LEFT_IN_RIGHT_SUB_TREE_FIFTY);
        final Node<Integer> rightInRightSubTree = Node.createNew(RIGHT_IN_RIGHT_SUB_TREE_SEVENTY);
        rightSubTreeRoot.setLeft(leftInRightSubTree);
        rightSubTreeRoot.setRight(rightInRightSubTree);

        rootNode.setLeft(leftSubTreeRoot);
        rootNode.setRight(rightSubTreeRoot);
        rootNode.setLinkType(rootLinkType);

        return rootNode;
    }

    @Test
    public void shouldRotateRight() throws Exception {
        final LinkType rootLinkType = randomLinkType();
        final Node<Integer> rootNode = createTreeToRotate(rootLinkType);

        rootNode.getLeft().setLinkType(LinkType.RED);

        final Node<Integer> newRoot = redBlackTree.rotateRight(rootNode);

        assertEquals(LEFT_SUB_TREE_TWENTY, newRoot.getValue());
        assertEquals(LEFT_IN_LEFT_SUB_TREE_TEN, newRoot.getLeft().getValue());

        final Node<Integer> newRight = newRoot.getRight();
        assertEquals(ROOT_VALUE_FORTY, newRight.getValue());
        assertEquals(LinkType.RED, newRight.getLinkType());
        assertEquals(RIGHT_IN_LEFT_SUB_TREE_THIRTY, newRight.getLeft().getValue());
        assertEquals(RIGHT_SUB_TREE_SIXTY, newRight.getRight().getValue());
    }

    private LinkType randomLinkType() {
        return RandomBoolean.next() ? LinkType.BLACK : LinkType.RED;
    }

    @Test
    public void shouldFlipColors() throws Exception {
        final Node<Integer> rootNode = createTreeToRotate(randomLinkType());
        rootNode.getLeft().setLinkType(LinkType.RED);
        rootNode.getRight().setLinkType(LinkType.RED);

        final Node<Integer> newRootNode = redBlackTree.flipColors(rootNode);

        assertAllBlackThreeNodesTree(rootNode, ROOT_VALUE_FORTY, LEFT_SUB_TREE_TWENTY, RIGHT_SUB_TREE_SIXTY);
        assertEquals(LinkType.RED, newRootNode.getLinkType());
    }

    @Test
    public void shouldInsertOneValue() throws Exception {
        tree.insert(ROOT_VALUE_FORTY);

        final Node<Integer> root = getRoot();

        assertNotNull(root);
        assertEquals(ROOT_VALUE_FORTY, root.getValue());
    }

    private Node<Integer> getRoot() {
        return redBlackTree.getRoot();
    }

    @Test
    public void shouldInsertToLeft() throws Exception {
        insertValues(ROOT_VALUE_FORTY, LEFT_SUB_TREE_TWENTY);

        final Node<Integer> root = getRoot();

        assertLeftLeaningNodeWithValues(root, LEFT_SUB_TREE_TWENTY, ROOT_VALUE_FORTY);
    }

    private void insertValues(final Integer ... values) {
        for (int i = 0; i < values.length; i++) {
            tree.insert(values[i]);
        }
    }

    @Test
    public void shouldInsertToRight() throws Exception {
        insertValues(ROOT_VALUE_FORTY, RIGHT_SUB_TREE_SIXTY);

        final Node<Integer> root = getRoot();

        assertLeftLeaningNodeWithValues(root, ROOT_VALUE_FORTY, RIGHT_SUB_TREE_SIXTY);
    }

    private void assertLeftLeaningNodeWithValues(final Node<Integer> node,
                                                 final Integer leftValue, final Integer rightValue) {
        assertEquals(rightValue, node.getValue());
        assertEquals(leftValue, node.getLeft().getValue());
        assertEquals(LinkType.RED, node.getLeft().getLinkType());
    }

    @Test
    public void shouldInsertToLeftAndRight() throws Exception {
        insertValues(ROOT_VALUE_FORTY, LEFT_SUB_TREE_TWENTY, RIGHT_SUB_TREE_SIXTY);

        final Node<Integer> root = getRoot();

        assertAllBlackThreeNodesTree(root, ROOT_VALUE_FORTY, LEFT_SUB_TREE_TWENTY, RIGHT_SUB_TREE_SIXTY);
    }

    @Test
    public void shouldInsertToLeftAtBottom() throws Exception {
        insertValues(ROOT_VALUE_FORTY, LEFT_SUB_TREE_TWENTY, RIGHT_SUB_TREE_SIXTY,
                     LEFT_IN_LEFT_SUB_TREE_TEN, LEFT_IN_RIGHT_SUB_TREE_FIFTY);

        final Node<Integer> root = getRoot();

        assertAllBlackThreeNodesTree(root, ROOT_VALUE_FORTY, LEFT_SUB_TREE_TWENTY, RIGHT_SUB_TREE_SIXTY);

        assertLeftLeaningNodeWithValues(root.getLeft(), LEFT_IN_LEFT_SUB_TREE_TEN, LEFT_SUB_TREE_TWENTY);
        assertLeftLeaningNodeWithValues(root.getRight(), LEFT_IN_RIGHT_SUB_TREE_FIFTY, RIGHT_SUB_TREE_SIXTY);
    }


    @Test
    public void shouldInsertToRightAtBottom() throws Exception {
        insertValues(ROOT_VALUE_FORTY, LEFT_SUB_TREE_TWENTY, RIGHT_SUB_TREE_SIXTY,
                RIGHT_IN_LEFT_SUB_TREE_THIRTY, RIGHT_IN_RIGHT_SUB_TREE_SEVENTY);

        final Node<Integer> root = getRoot();

        assertAllBlackThreeNodesTree(root, ROOT_VALUE_FORTY, RIGHT_IN_LEFT_SUB_TREE_THIRTY, RIGHT_IN_RIGHT_SUB_TREE_SEVENTY);

        assertLeftLeaningNodeWithValues(root.getLeft(), LEFT_SUB_TREE_TWENTY, RIGHT_IN_LEFT_SUB_TREE_THIRTY);
        assertLeftLeaningNodeWithValues(root.getRight(), RIGHT_SUB_TREE_SIXTY, RIGHT_IN_RIGHT_SUB_TREE_SEVENTY);
    }

    private void assertAllBlackThreeNodesTree(final Node<Integer> node, final Integer rootValue,
                                              final Integer leftValue, final Integer rightValue) {
        assertEquals(rootValue, node.getValue());
        assertEquals(leftValue, node.getLeft().getValue());
        assertEquals(rightValue, node.getRight().getValue());
        assertEquals(LinkType.BLACK, node.getLeft().getLinkType());
        assertEquals(LinkType.BLACK, node.getRight().getLinkType());
    }

    @Test
    public void shouldRotateRightOnDoubleLeftREDLinks() throws Exception {
        insertValues(ROOT_VALUE_FORTY, LEFT_SUB_TREE_TWENTY, LEFT_IN_LEFT_SUB_TREE_TEN);

        final Node<Integer> node = getRoot();

        assertAllBlackThreeNodesTree(node, LEFT_SUB_TREE_TWENTY, LEFT_IN_LEFT_SUB_TREE_TEN, ROOT_VALUE_FORTY);
    }

    @Test
    public void shouldInsertMiddleValue() throws Exception {
        insertValues(ROOT_VALUE_FORTY, LEFT_SUB_TREE_TWENTY, RIGHT_IN_LEFT_SUB_TREE_THIRTY);

        final Node<Integer> node = getRoot();

        assertAllBlackThreeNodesTree(node, RIGHT_IN_LEFT_SUB_TREE_THIRTY, LEFT_SUB_TREE_TWENTY, ROOT_VALUE_FORTY);
    }

    @Test
    public void shouldInsertIntoThreeNodeAtBottomLeft() throws Exception {
        insertValues(ROOT_VALUE_FORTY, LEFT_SUB_TREE_TWENTY, RIGHT_SUB_TREE_SIXTY,
                     LEFT_IN_LEFT_SUB_TREE_TEN, RIGHT_IN_LEFT_SUB_TREE_THIRTY);

        final Node<Integer> node = getRoot();

        assertLeftLeaningNodeWithValues(node, LEFT_SUB_TREE_TWENTY, ROOT_VALUE_FORTY);
        assertAllBlackThreeNodesTree(node.getLeft(), LEFT_SUB_TREE_TWENTY, LEFT_IN_LEFT_SUB_TREE_TEN, RIGHT_IN_LEFT_SUB_TREE_THIRTY);

    }

    @Test
    public void shouldInsertIntoThreeNodeAtBottomRight() throws Exception {
        insertValues(ROOT_VALUE_FORTY, LEFT_SUB_TREE_TWENTY, RIGHT_SUB_TREE_SIXTY,
                     RIGHT_IN_RIGHT_SUB_TREE_SEVENTY, LEFT_IN_RIGHT_SUB_TREE_FIFTY);

        final Node<Integer> node = getRoot();

        assertLeftLeaningNodeWithValues(node, ROOT_VALUE_FORTY, RIGHT_SUB_TREE_SIXTY);
        assertAllBlackThreeNodesTree(node.getLeft(), ROOT_VALUE_FORTY, LEFT_SUB_TREE_TWENTY, LEFT_IN_RIGHT_SUB_TREE_FIFTY);
    }

    @Test
    public void shouldPassRedLinksUpTheTree() throws Exception {
        insertValues(40, 30, 20, 10, 50, 60, 70, 35, 45);

        final Node<Integer> node = getRoot();

        assertAllBlackThreeNodesTree(node, 40, 30, 50);
        assertAllBlackThreeNodesTree(node.getLeft(), 30, 20, 35);
        assertAllBlackThreeNodesTree(node.getRight(), 50, 45, 70);
        assertLeftLeaningNodeWithValues(node.getLeft().getLeft(), 10, 20);
        assertLeftLeaningNodeWithValues(node.getRight().getRight(), 60, 70);
    }

    @Test
    public void shouldSearchValuesInserted() throws Exception {
        assertNull(tree.search(RandomInt.next()));

        insertValues(ROOT_VALUE_FORTY, LEFT_SUB_TREE_TWENTY,
                    RIGHT_SUB_TREE_SIXTY, LEFT_IN_LEFT_SUB_TREE_TEN,
                    RIGHT_IN_LEFT_SUB_TREE_THIRTY, LEFT_IN_RIGHT_SUB_TREE_FIFTY,
                    RIGHT_IN_RIGHT_SUB_TREE_SEVENTY);

        assertEquals(ROOT_VALUE_FORTY, tree.search(ROOT_VALUE_FORTY));

        assertEquals(LEFT_SUB_TREE_TWENTY, tree.search(LEFT_SUB_TREE_TWENTY));
        assertEquals(RIGHT_SUB_TREE_SIXTY, tree.search(RIGHT_SUB_TREE_SIXTY));

        assertEquals(LEFT_IN_LEFT_SUB_TREE_TEN, tree.search(LEFT_IN_LEFT_SUB_TREE_TEN));
        assertEquals(RIGHT_IN_LEFT_SUB_TREE_THIRTY, tree.search(RIGHT_IN_LEFT_SUB_TREE_THIRTY));

        assertEquals(LEFT_IN_RIGHT_SUB_TREE_FIFTY, tree.search(LEFT_IN_RIGHT_SUB_TREE_FIFTY));
        assertEquals(RIGHT_IN_RIGHT_SUB_TREE_SEVENTY, tree.search(RIGHT_IN_RIGHT_SUB_TREE_SEVENTY));

        assertNearByValueNotFound(ROOT_VALUE_FORTY);

        assertNearByValueNotFound(LEFT_SUB_TREE_TWENTY);
        assertNearByValueNotFound(RIGHT_SUB_TREE_SIXTY);

        assertNearByValueNotFound(LEFT_IN_LEFT_SUB_TREE_TEN);
        assertNearByValueNotFound(RIGHT_IN_LEFT_SUB_TREE_THIRTY);

        assertNearByValueNotFound(LEFT_IN_RIGHT_SUB_TREE_FIFTY);
        assertNearByValueNotFound(RIGHT_IN_RIGHT_SUB_TREE_SEVENTY);
    }

    private void assertNearByValueNotFound(final Integer value) {
        assertNull(tree.search(value + RandomInt.next(1, 10)));
        assertNull(tree.search(value - RandomInt.next(1, 10)));
    }

    @Test
    public void shouldSearchRandomValues() throws Exception {
        final Integer[] values = insertValuesInRandomOrder();

        for (int i = 0; i < values.length; i++) {
            assertEquals(values[i], tree.search(values[i]));
        }

    }

    private Integer[] insertValuesInRandomOrder() {
        final int size = RandomInt.next(100, 200);
        final Integer [] values = new Integer[size];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }

        KnuthShuffle.shuffle(values);

        insertValues(values);
        return values;
    }

    @Test
    public void shouldGetAllValues() throws Exception {
        insertValues(ROOT_VALUE_FORTY, LEFT_SUB_TREE_TWENTY,
                RIGHT_SUB_TREE_SIXTY, LEFT_IN_LEFT_SUB_TREE_TEN,
                RIGHT_IN_LEFT_SUB_TREE_THIRTY, LEFT_IN_RIGHT_SUB_TREE_FIFTY,
                RIGHT_IN_RIGHT_SUB_TREE_SEVENTY);

        final List<Integer> values = tree.values();
        assertEquals(7, values.size());

        assertEquals(LEFT_IN_LEFT_SUB_TREE_TEN, values.get(0));
        assertEquals(LEFT_SUB_TREE_TWENTY, values.get(1));
        assertEquals(RIGHT_IN_LEFT_SUB_TREE_THIRTY, values.get(2));
        assertEquals(ROOT_VALUE_FORTY, values.get(3));
        assertEquals(LEFT_IN_RIGHT_SUB_TREE_FIFTY, values.get(4));
        assertEquals(RIGHT_SUB_TREE_SIXTY, values.get(5));
        assertEquals(RIGHT_IN_RIGHT_SUB_TREE_SEVENTY, values.get(6));
    }

    @Test
    public void shouldGetAllValuesForRandomInsertedValues() throws Exception {
        final Integer [] insertedValues = insertValuesInRandomOrder();

        final List<Integer> values = tree.values();

        assertEquals(insertedValues.length, values.size());
        for (int i = 0; i < insertedValues.length; i++) {
            assertEquals(Integer.valueOf(i), values.get(i));
        }
    }

    @Test
    public void shouldNotReInsertExistingValues() throws Exception {
        final Integer [] insertedValues = insertValuesInRandomOrder();
        insertValues(insertedValues);

        final List<Integer> values = tree.values();

        assertEquals(insertedValues.length, values.size());
    }
}
