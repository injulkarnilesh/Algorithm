package in.algorithm.course.part.one.week.five.application;

import in.algorithm.course.part.one.week.five.kdtree.Point;
import in.algorithm.course.util.RandomInt;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TwoDPointTest {

    private final int x = RandomInt.next();
    private final int y = RandomInt.next();

    private final TwoDPoint _2dPoint = TwoDPoint.create(x, y);

    @Test
    public void shouldBeGenericPoint() throws Exception {
        assertTrue(_2dPoint instanceof Point);
        final Point point = _2dPoint;
        assertTrue(point.isValid());
        assertEquals(2, point.getD());
        assertEquals(x, point.getNthDimension(0));
        assertEquals(y, point.getNthDimension(1));
    }

    @Test
    public void shouldGetXAndY() throws Exception {
        assertEquals(x, _2dPoint.getX());
        assertEquals(y, _2dPoint.getY());
    }
}
