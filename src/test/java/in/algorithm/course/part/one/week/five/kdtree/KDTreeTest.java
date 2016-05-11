package in.algorithm.course.part.one.week.five.kdtree;

import in.algorithm.course.part.one.week.five.kdtree.KDTree.Node;
import in.algorithm.course.util.RandomInt;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class KDTreeTest {

    private KDTree tree;

    @Before
    public void setUp() throws Exception {
        tree = KDTreeImpl.createNewForDimensionsOf(2);
    }

    @Test
    public void shouldCreateKDTreeInstanceAcceptingK() throws Exception {
        assertNotNull(tree);
    }

    @Test
    public void shouldInsertOne2DPoint() throws Exception {
        final Point point = create2DPoint(10, 20);
        tree.insert(point);

        final Node root = getRoot();
        assertNotNull(root);
        assertEquals(point, root.getPoint());
    }

    private Point create2DPoint(final int x, final int y) {
        return PointBuilder.forDimensions(2).addDimension(x).addDimension(y).build();
    }

    private Node getRoot() {
        return tree.getRoot();
    }

    @Test
    public void shouldInsertPointsLeftAndRight() throws Exception {
        final Point root = create2DPoint(50, 100);
        final Point left = create2DPoint(25, 40);
        final Point right = create2DPoint(75, 70);

        insertPoints(tree,root, left, right);

        final Node actualRoot = getRoot();
        assertEquals(root, actualRoot.getPoint());
        assertEquals(left, actualRoot.getLeft().getPoint());
        assertEquals(right, actualRoot.getRight().getPoint());
    }

    @Test
    public void shouldInsertPointWithSameCurrentDimensionToRight() throws Exception {
        final Point root = create2DPoint(50, 70);
        final Point same = create2DPoint(50, 80);

        insertPoints(tree,root, same);

        final Node actualRoot = getRoot();
        assertEquals(root, actualRoot.getPoint());
        assertEquals(same, actualRoot.getRight().getPoint());
    }

    private void insertPoints(final KDTree kdTree, final Point ...points) {
        for (int i = 0; i < points.length; i++) {
            kdTree.insert(points[i]);
        }
    }

    @Test
    public void shouldCompareNextDimensionsEachLevelCase2D() throws Exception {
        final Point root = create2DPoint(50, 100);

        final Point leftSubTree = create2DPoint(30, 80);
        final Point leftInLeftSubTree = create2DPoint(40, 70);
        final Point rightInLeftSubTree = create2DPoint(10, 130);
        final Point leftLeftLeft = create2DPoint(20, 60);
        final Point leftRightRight = create2DPoint(10, 100);

        final Point rightSubTree = create2DPoint(70, 120);
        final Point leftInRightSubTree = create2DPoint(60, 80);
        final Point rightInRightSubTree = create2DPoint(55, 120);
        final Point rightLeftRight = create2DPoint(70, 100);
        final Point rightRightLeft = create2DPoint(53, 130);

        insertPoints(tree, root, leftSubTree, leftInLeftSubTree, rightSubTree,
                     rightInRightSubTree, rightInLeftSubTree, leftInRightSubTree,
                     leftLeftLeft, leftRightRight, rightLeftRight, rightRightLeft);

        final Node actualRoot = getRoot();
        assertEquals(root, actualRoot.getPoint());

        final Node actualLeft = actualRoot.getLeft();
        assertEquals(leftSubTree, actualLeft.getPoint());
        assertEquals(leftInLeftSubTree, actualLeft.getLeft().getPoint());
        assertEquals(rightInLeftSubTree, actualLeft.getRight().getPoint());
        assertEquals(leftLeftLeft, actualLeft.getLeft().getLeft().getPoint());
        assertEquals(leftRightRight, actualLeft.getRight().getRight().getPoint());

        final Node actualRight = actualRoot.getRight();
        assertEquals(rightSubTree, actualRight.getPoint());
        assertEquals(leftInRightSubTree, actualRight.getLeft().getPoint());
        assertEquals(rightInRightSubTree, actualRight.getRight().getPoint());
        assertEquals(rightLeftRight, actualRight.getLeft().getRight().getPoint());
        assertEquals(rightRightLeft, actualRight.getRight().getLeft().getPoint());
    }

    @Test
    public void shouldCompareNextDimensionsEachLevelCase3D() throws Exception {
        final KDTree _3DTree = KDTreeImpl.createNewForDimensionsOf(3);

        final Point root = create3DPoint(100, 160, 250);
        final Point left = create3DPoint(40, 150, 100);
        final Point leftLeft = create3DPoint(80, 130, 130);
        final Point leftLeftLeft = create3DPoint(70, 110, 100);
        final Point leftLeftRight = create3DPoint(5, 5, 135);
        final Point leftRight = create3DPoint(81, 180, 500);
        final Point leftRightLeft = create3DPoint(30, 190, 110);
        final Point leftRightRight = create3DPoint(5, 200, 700);
        final Point right = create3DPoint(190, 150, 500);
        final Point rightLeft = create3DPoint(120, 120, 300);
        final Point rightLeftLeft = create3DPoint(200, 100, 100);
        final Point rightLeftRight = create3DPoint(100, 140, 300);
        final Point rightRight = create3DPoint(110, 250, 250);
        final Point rightRightLeft = create3DPoint(130, 250, 200);
        final Point rightRightRight = create3DPoint(100, 150, 250);

        insertPoints(_3DTree, root, left, leftLeft, leftLeftLeft, leftLeftRight, leftRight, leftRightLeft,
                     leftRightRight, right, rightLeft, rightLeftLeft, rightLeftRight, rightRight,
                        rightRightLeft, rightRightRight);

        final Node actualRootNode = ((KDTreeImpl)_3DTree).getRoot();

        assertEquals(root, actualRootNode.getPoint());

        final Node actualLeftNode = actualRootNode.getLeft();
        assertEquals(left, actualLeftNode.getPoint());
        assertEquals(leftLeft, actualLeftNode.getLeft().getPoint());
        assertEquals(leftLeftLeft, actualLeftNode.getLeft().getLeft().getPoint());
        assertEquals(leftLeftRight, actualLeftNode.getLeft().getRight().getPoint());
        assertEquals(leftRight, actualLeftNode.getRight().getPoint());
        assertEquals(leftRightLeft, actualLeftNode.getRight().getLeft().getPoint());
        assertEquals(leftRightRight, actualLeftNode.getRight().getRight().getPoint());

        final Node actualRightNode = actualRootNode.getRight();
        assertEquals(right, actualRightNode.getPoint());
        assertEquals(rightLeft, actualRightNode.getLeft().getPoint());
        assertEquals(rightLeftLeft, actualRightNode.getLeft().getLeft().getPoint());
        assertEquals(rightLeftRight, actualRightNode.getLeft().getRight().getPoint());
        assertEquals(rightRight, actualRightNode.getRight().getPoint());
        assertEquals(rightRightLeft, actualRightNode.getRight().getLeft().getPoint());
        assertEquals(rightRightRight, actualRightNode.getRight().getRight().getPoint());
    }

    private Point create3DPoint(final int x, final int y, final int z) {
        return PointBuilder.forDimensions(3).addDimension(x).addDimension(y).addDimension(z).build();
    }

    @Test(expected = KDTree.WrongDimensionPointException.class)
    public void shouldNotAcceptWrongDimensionPoint() throws Exception {
        tree.insert(create3DPoint(RandomInt.next(), RandomInt.next(), RandomInt.next()));
    }

    @Test
    public void shouldGetPoint() throws Exception {
        assertFalse(tree.get(create2DPoint(RandomInt.next(), RandomInt.next())).isValid());
        assertFalse(tree.get(null).isValid());

        final Point root = create2DPoint(50, 100);

        final Point leftSubTree = create2DPoint(30, 80);
        final Point leftInLeftSubTree = create2DPoint(40, 70);
        final Point rightInLeftSubTree = create2DPoint(10, 130);

        final Point rightSubTree = create2DPoint(70, 120);
        final Point leftInRightSubTree = create2DPoint(60, 80);
        final Point rightInRightSubTree = create2DPoint(55, 120);

        insertPoints(tree, root, leftSubTree, leftInLeftSubTree, rightSubTree,
                rightInRightSubTree, rightInLeftSubTree, leftInRightSubTree);

        assertEquals(root, tree.get(root));

        assertEquals(leftSubTree, tree.get(leftSubTree));
        assertEquals(leftInLeftSubTree, tree.get(leftInLeftSubTree));
        assertEquals(rightInLeftSubTree, tree.get(rightInLeftSubTree));

        assertEquals(rightSubTree, tree.get(rightSubTree));
        assertEquals(leftInRightSubTree, tree.get(leftInRightSubTree));
        assertEquals(rightInRightSubTree, tree.get(rightInRightSubTree));

        assertFalse(tree.get(create2DPoint(20, 20)).isValid());
        assertFalse(tree.get(create2DPoint(30, 100)).isValid());
        assertFalse(tree.get(create2DPoint(70, 190)).isValid());
        assertFalse(tree.get(create2DPoint(55, 110)).isValid());
    }

    @Test
    public void shouldNotGetWrongDimensionPoint() throws Exception {
        final Point _2DPoint = create2DPoint(10, 20);
        insertPoints(tree, _2DPoint);
        final Point _3DPoint = create3DPoint(10, 20, 30);
        assertFalse(tree.get(_3DPoint).isValid());
    }

    @Test
    public void shouldNotInsertNullPoint() throws Exception {
        tree.insert(null);
        assertTrue(tree.isEmpty());
    }


    @Test
    public void shouldCheckIfEmpty() throws Exception {
        assertTrue(tree.isEmpty());
        insertPoints(tree, create2DPoint(RandomInt.next(), RandomInt.next()));
        assertFalse(tree.isEmpty());
    }

    @Test
    public void shouldGetRoot() throws Exception {
        assertNull(tree.getRoot());

        final Point point = create2DPoint(RandomInt.next(), RandomInt.next());

        tree.insert(point);

        assertEquals(point, tree.getRoot().getPoint());
    }
}
