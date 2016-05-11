package in.algorithm.course.part.one.week.five.application;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TwoDTreeRangeSearchTest {

    private TwoDTreeRangeSearch rangeSearch = TwoDTreeRangeSearch.createNew();


    @Test
    public void shouldInsertPoints() throws Exception {
        rangeSearch.insert(TwoDPoint.create(8, 8));
    }

    @Test
    public void shouldGeEmptyPointsWhenNoPointsInserted() throws Exception {
        final TwoDRectangle rectangle = TwoDRectangleBuilder.withXYCoOrdinates()
                                        .withX(1, 3).withY(3, 7).build();

        final List<TwoDPoint> pointsInRectangle = getPointsInRectangle(rectangle);

        assertTrue(pointsInRectangle.isEmpty());
    }

    @Test
    public void shouldGetPointBelongingToRectangleOnePoint() throws Exception {
        final TwoDPoint point = TwoDPoint.create(2, 4);
        rangeSearch.insert(point);

        final TwoDRectangle rectangle = TwoDRectangleBuilder.withXYCoOrdinates()
                                        .withX(1, 3).withY(3, 7).build();

        verifyPointsInRectangle(rectangle, point);
    }

    @Test
    public void shouldNotGetPointNotBelongingToRectangleOnePoint() throws Exception {
        final TwoDPoint point = TwoDPoint.create(6, 6);
        rangeSearch.insert(point);

        final TwoDRectangle rectangle = TwoDRectangleBuilder.withXYCoOrdinates()
                                        .withX(1, 3).withY(3, 7).build();

        final List<TwoDPoint> pointsInRectangle = getPointsInRectangle(rectangle);

        assertTrue(pointsInRectangle.isEmpty());
    }

    @Test
    public void shouldGetPointsByCheckingLeftSideOnXCoOrdinate() throws Exception {
        final TwoDPoint pointToRight = TwoDPoint.create(2, 5);

        rangeSearch.insert(TwoDPoint.create(5, 6));
        rangeSearch.insert(pointToRight);

        final TwoDRectangle rectangle = TwoDRectangleBuilder.withXYCoOrdinates()
                                        .withX(1, 3).withY(3, 7).build();

        verifyPointsInRectangle(rectangle, pointToRight);
    }

    @Test
    public void shouldGetPointsByCheckingRightSideOnXCoOrdinate() throws Exception {
        final TwoDPoint pointToRight = TwoDPoint.create(7, 6);

        rangeSearch.insert(TwoDPoint.create(5, 6));
        rangeSearch.insert(pointToRight);

        final TwoDRectangle rectangle = TwoDRectangleBuilder.withXYCoOrdinates()
                                        .withX(6, 9).withY(3, 7).build();
        verifyPointsInRectangle(rectangle, pointToRight);
    }

    @Test
    public void shouldGetPointsByCheckingBothSidesOnXCoOrdinatesIfRootBelongsToRectangle() throws Exception {
        final TwoDPoint rootPoint = TwoDPoint.create(5, 6);
        final TwoDPoint leftPoint = TwoDPoint.create(4, 4);
        final TwoDPoint rightPoint = TwoDPoint.create(7, 5);

        rangeSearch.insert(rootPoint);
        rangeSearch.insert(leftPoint);
        rangeSearch.insert(rightPoint);

        final TwoDRectangle rectangle = TwoDRectangleBuilder.withXYCoOrdinates()
                                        .withX(4, 9).withY(3, 7).build();

        verifyPointsInRectangle(rectangle, rootPoint, leftPoint, rightPoint);
    }

    @Test
    public void shouldGetPointsByCheckingBothSidesOnXCoOrdinatesIfRootIntersectsWithRectangle() throws Exception {
        final TwoDPoint rootPoint = TwoDPoint.create(6, 9);
        final TwoDPoint leftPoint = TwoDPoint.create(4, 4);
        final TwoDPoint rightPoint = TwoDPoint.create(7, 5);

        rangeSearch.insert(rootPoint);
        rangeSearch.insert(leftPoint);
        rangeSearch.insert(rightPoint);

        final TwoDRectangle rectangle = TwoDRectangleBuilder.withXYCoOrdinates()
                                        .withX(4, 9).withY(3, 7).build();


        verifyPointsInRectangle(rectangle, leftPoint, rightPoint);
    }

    @Test
    public void shouldGetPointsByCheckingLeftSideOnYCoOrdinate() throws Exception {
        final TwoDPoint leftLeftPoint = TwoDPoint.create(2, 5);

        rangeSearch.insert(TwoDPoint.create(5, 6));
        rangeSearch.insert(TwoDPoint.create(2, 9));
        rangeSearch.insert(leftLeftPoint);

        final TwoDRectangle rectangle = TwoDRectangleBuilder.withXYCoOrdinates()
                                        .withX(1, 3).withY(3, 7).build();

        verifyPointsInRectangle(rectangle, leftLeftPoint);
    }

    @Test
    public void shouldGetPointsByCheckingRightSideOnYCoOrdinate() throws Exception {
        final TwoDPoint leftRightPoint = TwoDPoint.create(2, 5);

        rangeSearch.insert(TwoDPoint.create(5, 6));
        rangeSearch.insert(TwoDPoint.create(2, 1));
        rangeSearch.insert(leftRightPoint);

        final TwoDRectangle rectangle = TwoDRectangleBuilder.withXYCoOrdinates()
                                        .withX(1, 3).withY(3, 7).build();

        verifyPointsInRectangle(rectangle, leftRightPoint);
    }

    @Test
    public void shouldGetPointsByCheckingBothSidesOnYCoOrdinatesIfCurrentRootIntersectsWithRectangle() throws Exception {
        final TwoDPoint leftRightPoint = TwoDPoint.create(2, 3);
        final TwoDPoint leftLeftPoint = TwoDPoint.create(3, 7);

        rangeSearch.insert(TwoDPoint.create(5, 6));
        rangeSearch.insert(TwoDPoint.create(4, 5));
        rangeSearch.insert(leftRightPoint);
        rangeSearch.insert(leftLeftPoint);

        final TwoDRectangle rectangle = TwoDRectangleBuilder.withXYCoOrdinates()
                .withX(1, 3).withY(3, 7).build();

        verifyPointsInRectangle(rectangle, leftLeftPoint, leftRightPoint);
    }

    @Test
    public void shouldGetPointsByCheckingBothSidesOnYCoOrdinatesIfCurrentRootBelongsToRectangle() throws Exception {
        final TwoDPoint leftRightPoint = TwoDPoint.create(2, 3);
        final TwoDPoint leftLeftPoint = TwoDPoint.create(3, 7);
        final TwoDPoint leftPoint = TwoDPoint.create(2, 5);

        rangeSearch.insert(TwoDPoint.create(5, 6));
        rangeSearch.insert(leftPoint);
        rangeSearch.insert(leftRightPoint);
        rangeSearch.insert(leftLeftPoint);

        final TwoDRectangle rectangle = TwoDRectangleBuilder.withXYCoOrdinates()
                .withX(1, 3).withY(3, 7).build();

        verifyPointsInRectangle(rectangle, leftPoint, leftLeftPoint, leftRightPoint);
    }

    @Test
    public void shouldGetPointsByCheckingAtDeeperLevel() throws Exception {
        final TwoDPoint point_1 = TwoDPoint.create(5, 6);
        final TwoDPoint point_2 = TwoDPoint.create(4, 7);
        final TwoDPoint point_3 = TwoDPoint.create(3, 9);
        final TwoDPoint point_4 = TwoDPoint.create(2, 7);
        final TwoDPoint point_5 = TwoDPoint.create(3, 4);
        final TwoDPoint point_6 = TwoDPoint.create(2, 6);
        final TwoDPoint point_7 = TwoDPoint.create(4, 3);
        final TwoDPoint point_8 = TwoDPoint.create(5, 4);
        final TwoDPoint point_9 = TwoDPoint.create(6, 1);
        final TwoDPoint point_10 = TwoDPoint.create(7, 7);
        final TwoDPoint point_11 = TwoDPoint.create(5, 1);
        final TwoDPoint point_12 = TwoDPoint.create(8, 3);
        final TwoDPoint point_13 = TwoDPoint.create(6, 8);
        final TwoDPoint point_14 = TwoDPoint.create(9, 8);

        rangeSearch.insert(point_1);
        rangeSearch.insert(point_2);
        rangeSearch.insert(point_3);
        rangeSearch.insert(point_4);
        rangeSearch.insert(point_5);
        rangeSearch.insert(point_6);
        rangeSearch.insert(point_7);
        rangeSearch.insert(point_8);
        rangeSearch.insert(point_9);
        rangeSearch.insert(point_10);
        rangeSearch.insert(point_11);
        rangeSearch.insert(point_12);
        rangeSearch.insert(point_13);
        rangeSearch.insert(point_14);

        final TwoDRectangle rectangle = TwoDRectangleBuilder.withXYCoOrdinates()
                                        .withX(3, 10).withY(2, 8).build();

        verifyPointsInRectangle(rectangle, point_1, point_2, point_5, point_7, point_8, point_10, point_12, point_13, point_14);
    }

    private void verifyPointsInRectangle(final TwoDRectangle rectangle, final TwoDPoint ...points) {
        final List<TwoDPoint> pointsInRectangle = getPointsInRectangle(rectangle);

        assertEquals(points.length, pointsInRectangle.size());
        Arrays.stream(points).forEach(point -> assertTrue(pointsInRectangle.contains(point)));
    }

    private List<TwoDPoint> getPointsInRectangle(final TwoDRectangle rectangle) {
        return rangeSearch.findPointsInRectangle(rectangle);
    }

}
