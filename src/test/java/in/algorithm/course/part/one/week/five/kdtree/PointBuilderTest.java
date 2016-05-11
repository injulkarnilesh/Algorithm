package in.algorithm.course.part.one.week.five.kdtree;

import in.algorithm.course.part.one.week.five.kdtree.Point;
import in.algorithm.course.part.one.week.five.kdtree.PointBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PointBuilderTest {

    @Test
    public void shouldCreatePointOfKDimensions() throws Exception {
        final Point point = PointBuilder.forDimensions(2)
                .addDimension(10)
                .addDimension(20)
                .build();

        assertNotNull(point);
        assertEquals(2, point.getD());
        assertEquals(10, point.getNthDimension(0));
        assertEquals(20, point.getNthDimension(1));
    }

    @Test(expected = Point.OutOfBoundDimension.class)
    public void shouldNotGetOutOfBoundPointDimension() throws Exception {
        final Point point = PointBuilder.forDimensions(2).addDimension(10).addDimension(30).build();
        point.getNthDimension(3);
    }

    @Test(expected = PointBuilder.LesserCoOrdinatesThanDimensionsException.class)
    public void pointBuilderShouldNotCreatePointWithLesserCoOrdinates() throws Exception {
        PointBuilder.forDimensions(2)
                    .addDimension(10)
                    .build();
    }

    @Test(expected = PointBuilder.MoreCoOrdinatesThanDimensionsException.class)
    public void pointBuildShouldNotCreatePointWIthMoreCoOrdinates() throws Exception {
        PointBuilder.forDimensions(2)
                    .addDimension(10).addDimension(20).addDimension(30)
                    .build();
    }
}
