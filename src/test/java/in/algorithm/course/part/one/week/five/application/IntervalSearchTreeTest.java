package in.algorithm.course.part.one.week.five.application;

import in.algorithm.course.part.one.week.five.application.IntervalSearchTree.Interval;
import in.algorithm.course.util.RandomInt;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IntervalSearchTreeTest {

    private IntervalSearchTree intervalTree;

    @Before
    public void setUp() {
        intervalTree = IntervalSearchTree.createNew();
    }

    @Test
    public void shouldInsertSingleInterval() throws Exception {
        intervalTree.insert(Interval.of(10, 20));

        final IntervalSearchTree.Node root = intervalTree.getRoot();
        final Interval actualInterval = root.getInterval();
        assertEquals(Interval.of(10, 20), actualInterval);
    }

    @Test
    public void shouldCreateIntervalWithMinMaxValue() throws Exception {
        final Interval interval = Interval.of(20, 10);

        assertEquals(Interval.of(10, 20), interval);
        assertEquals(10, interval.getMin());
        assertEquals(20, interval.getMax());
    }

    @Test
    public void shouldInsertToLeftIfMinSmaller() throws Exception {
        final Interval rootInterval = Interval.of(20, 25);
        final Interval leftInterval = Interval.of(17, 23);

        intervalTree.insert(rootInterval);
        intervalTree.insert(leftInterval);

        final IntervalSearchTree.Node root = intervalTree.getRoot();
        assertEquals(rootInterval, root.getInterval());

        final IntervalSearchTree.Node leftNode = root.getLeft();
        assertEquals(leftInterval, leftNode.getInterval());
    }

    @Test
    public void shouldInsertToRightIfMinLarger() throws Exception {
        final Interval rootInterval = Interval.of(20, 25);
        final Interval rightInterval = Interval.of(21, 23);

        intervalTree.insert(rootInterval)
                    .insert(rightInterval);

        final IntervalSearchTree.Node root = intervalTree.getRoot();
        assertEquals(rootInterval, root.getInterval());

        final IntervalSearchTree.Node leftNode = root.getRight();
        assertEquals(rightInterval, leftNode.getInterval());
    }

    @Test
    public void shouldInsertIntervalToRightIfMinEqual() throws Exception {
        final Interval rootInterval = Interval.of(20, 25);
        final Interval rightInterval = Interval.of(20, 23);

        intervalTree.insert(rootInterval)
                    .insert(rightInterval);

        final IntervalSearchTree.Node root = intervalTree.getRoot();
        assertEquals(rootInterval, root.getInterval());

        final IntervalSearchTree.Node leftNode = root.getRight();
        assertEquals(rightInterval, leftNode.getInterval());

    }

    @Test
    public void shouldInsertAtDeeperLevels() throws Exception {
        final Interval rootInterval = Interval.of(20, 30);

        final Interval leftInterval = Interval.of(10, 13);
        final Interval rightInterval = Interval.of(25, 43);

        final Interval leftLeftInterval = Interval.of(5, 12);
        final Interval leftRightInterval = Interval.of(14, 17);

        final Interval rightLeftInterval = Interval.of(23, 29);
        final Interval rightRightInterval = Interval.of(25, 30);

        intervalTree.insert(rootInterval)
                    .insert(leftInterval).insert(rightInterval)
                    .insert(leftLeftInterval).insert(rightRightInterval)
                    .insert(leftRightInterval).insert(rightLeftInterval);

        final IntervalSearchTree.Node root = intervalTree.getRoot();
        final IntervalSearchTree.Node left = root.getLeft();
        final IntervalSearchTree.Node right = root.getRight();

        assertEquals(rootInterval, root.getInterval());
        assertEquals(leftInterval, left.getInterval());
        assertEquals(rightInterval, right.getInterval());

        assertEquals(leftLeftInterval, left.getLeft().getInterval());
        assertEquals(leftRightInterval, left.getRight().getInterval());
        assertEquals(rightLeftInterval, right.getLeft().getInterval());
        assertEquals(rightRightInterval, right.getRight().getInterval());
    }

    @Test
    public void shouldKeepTrackOfMaxOfIntervalsAtEachSubTree() throws Exception {
        final Interval rootInterval = Interval.of(20, 30);

        intervalTree.insert(rootInterval);

        final IntervalSearchTree.Node root = intervalTree.getRoot();
        assertEquals(rootInterval.getMax(), root.getMaxInSubTree());

        final Interval leftInterval = Interval.of(10, 12);
        intervalTree.insert(leftInterval);
        assertEquals(leftInterval.getMax(), intervalTree.getRoot().getLeft().getMaxInSubTree());
        assertEquals(rootInterval.getMax(), intervalTree.getRoot().getMaxInSubTree());

        final Interval leftRightInterval = Interval.of(15, 21);
        intervalTree.insert(leftRightInterval);
        assertEquals(leftRightInterval.getMax(), intervalTree.getRoot().getLeft().getRight().getMaxInSubTree());
        assertEquals(leftRightInterval.getMax(), intervalTree.getRoot().getLeft().getMaxInSubTree());
        assertEquals(rootInterval.getMax(), intervalTree.getRoot().getMaxInSubTree());

        final Interval rightInterval = Interval.of(25, 46);
        intervalTree.insert(rightInterval);
        assertEquals(rightInterval.getMax(), intervalTree.getRoot().getRight().getMaxInSubTree());
        assertEquals(rightInterval.getMax(), intervalTree.getRoot().getMaxInSubTree());

        final Interval rightLeftInterval = Interval.of(22, 23);
        intervalTree.insert(rightLeftInterval);
        assertEquals(rightLeftInterval.getMax(), intervalTree.getRoot().getRight().getLeft().getMaxInSubTree());
        assertEquals(rightInterval.getMax(), intervalTree.getRoot().getRight().getMaxInSubTree());
        assertEquals(rightInterval.getMax(), intervalTree.getRoot().getMaxInSubTree());
    }

    @Test
    public void shouldKeepTrackOfMinOfIntervalsAtEachSubTree() throws Exception {
        final Interval rootInterval = Interval.of(20, 30);

        intervalTree.insert(rootInterval);

        final IntervalSearchTree.Node root = intervalTree.getRoot();
        assertEquals(rootInterval.getMin(), root.getMinInSubTree());

        final Interval leftInterval = Interval.of(10, 12);
        intervalTree.insert(leftInterval);
        assertEquals(leftInterval.getMin(), intervalTree.getRoot().getLeft().getMinInSubTree());
        assertEquals(leftInterval.getMin(), intervalTree.getRoot().getMinInSubTree());

        final Interval leftLeftInterval = Interval.of(7, 10);
        intervalTree.insert(leftLeftInterval);
        assertEquals(leftLeftInterval.getMin(), intervalTree.getRoot().getLeft().getLeft().getMinInSubTree());
        assertEquals(leftLeftInterval.getMin(), intervalTree.getRoot().getLeft().getMinInSubTree());
        assertEquals(leftLeftInterval.getMin(), intervalTree.getRoot().getMinInSubTree());

        final Interval rightInterval = Interval.of(25, 46);
        intervalTree.insert(rightInterval);
        assertEquals(leftLeftInterval.getMin(), intervalTree.getRoot().getMinInSubTree());
        assertEquals(rightInterval.getMin(), intervalTree.getRoot().getRight().getMinInSubTree());

        final Interval rightLeftInterval = Interval.of(22, 23);
        intervalTree.insert(rightLeftInterval);
        assertEquals(leftLeftInterval.getMin(), intervalTree.getRoot().getMinInSubTree());
        assertEquals(rightLeftInterval.getMin(), intervalTree.getRoot().getRight().getMinInSubTree());
        assertEquals(rightLeftInterval.getMin(), intervalTree.getRoot().getRight().getLeft().getMinInSubTree());
    }

    @Test
    public void shouldGetIntersectsForSingleIntervalIfIntersects() throws Exception {

        intervalTree.insert(Interval.of(20, 30));

        final List<Interval> intersectingIntervals = intervalTree.intersects(Interval.of(25, 80));

        assertEquals(1, intersectingIntervals.size());
        assertTrue(intersectingIntervals.contains(Interval.of(20, 30)));
    }

    @Test
    public void shouldNotGetIntersectingIntervalForNonIntersectingSingleInterval() throws Exception {
        intervalTree.insert(Interval.of(20, 30));

        final List<Interval> intersectingIntervals = intervalTree.intersects(Interval.of(35, 80));

        assertTrue(intersectingIntervals.isEmpty());
    }

    @Test
    public void shouldNotGetIntersectingIntervalForBorderIntervalsMin() throws Exception {
        intervalTree.insert(Interval.of(20, 30));

        final List<Interval> intersectingIntervals = intervalTree.intersects(Interval.of(30, 80));

        assertTrue(intersectingIntervals.isEmpty());
    }

    @Test
    public void shouldNotGetIntersectingIntervalForBorderIntervalsMax() throws Exception {
        intervalTree.insert(Interval.of(20, 30));

        final List<Interval> intersectingIntervals = intervalTree.intersects(Interval.of(15, 20));

        assertTrue(intersectingIntervals.isEmpty());
    }

    @Test
    public void shouldNotGetIntersectingIntervalsForNoInsertedIntervals() throws Exception {
        final List<Interval> intersectingIntervals = intervalTree.intersects(Interval.of(RandomInt.next(), RandomInt.next()));

        assertTrue(intersectingIntervals.isEmpty());
    }

    @Test
    public void shouldGetIntersectingIntervalFromLeftIntervals() throws Exception {
        final Interval leftIntersectingInterval = Interval.of(10, 15);

        intervalTree.insert(Interval.of(20, 30))
                    .insert(leftIntersectingInterval)
                    .insert(Interval.of(32, 40));

        final List<Interval> intersectingIntervals = intervalTree.intersects(Interval.of(11, 18));

        verifyIntersectingIntervals(intersectingIntervals, leftIntersectingInterval);
    }

    @Test
    public void shouldGetIntersectingIntervalFromRightIntervals() throws Exception {
        final Interval rightIntersectingInterval = Interval.of(32, 40);

        intervalTree.insert(Interval.of(20, 30))
                    .insert(Interval.of(10, 15))
                    .insert(rightIntersectingInterval);

        final List<Interval> intersectingIntervals = intervalTree.intersects(Interval.of(38, 88));

        verifyIntersectingIntervals(intersectingIntervals, rightIntersectingInterval);
    }

    @Test
    public void shouldGetIntersectingIntervalsFromLeftIntervalsCaseExclusive() throws Exception {
        final Interval leftInterval = Interval.of(10, 16);
        final Interval leftRightInterval = Interval.of(15, 18);

        intervalTree.insert(Interval.of(20, 30))
                    .insert(leftInterval)
                    .insert(Interval.of(5, 8))
                    .insert(leftRightInterval);

        final List<Interval> intersectingIntervals = intervalTree.intersects(Interval.of(14, 17));

        verifyIntersectingIntervals(intersectingIntervals, leftInterval, leftRightInterval);
    }

    @Test
    public void shouldGetIntersectingIntervalsFromRightIntervalsCaseExclusive() throws Exception {
        final Interval rootInterval = Interval.of(20, 30);
        final Interval rightInterval = Interval.of(35, 40);
        final Interval rightLeftInterval = Interval.of(32, 37);

        intervalTree.insert(rootInterval)
                    .insert(rightInterval)
                    .insert(Interval.of(44, 88))
                    .insert(rightLeftInterval);

        final List<Interval> intersectingIntervals = intervalTree.intersects(Interval.of(28, 39));

        verifyIntersectingIntervals(intersectingIntervals, rightInterval, rightInterval, rightLeftInterval);
    }

    @Test
    public void shouldGetIntersectingIntervalsWithBothLeftRightAndCurrent() throws Exception {
        final Interval rootInterval = Interval.of(20, 30);
        final Interval rightInterval = Interval.of(35, 40);
        final Interval leftInterval = Interval.of(5, 12);

        intervalTree.insert(rootInterval)
                    .insert(rightInterval)
                    .insert(leftInterval);

        final List<Interval> intersectingIntervals = intervalTree.intersects(Interval.of(2, 39));

        verifyIntersectingIntervals(intersectingIntervals, rootInterval, leftInterval, rightInterval);
    }

    private void verifyIntersectingIntervals(final List<Interval> actualIntersectingIntervals, final Interval ...expectedIntervals) {
        assertEquals(expectedIntervals.length, actualIntersectingIntervals.size());
        Arrays.stream(expectedIntervals).forEach(expectedInterval ->
            assertTrue(actualIntersectingIntervals.contains(expectedInterval))
        );
    }
}
