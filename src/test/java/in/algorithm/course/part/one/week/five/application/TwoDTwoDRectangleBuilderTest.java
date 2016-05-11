package in.algorithm.course.part.one.week.five.application;

import in.algorithm.course.part.one.week.five.application.TwoDRectangleBuilder.InSufficientDataException;
import in.algorithm.course.part.one.week.five.application.TwoDRectangleBuilder.InvalidHeightException;
import in.algorithm.course.part.one.week.five.application.TwoDRectangleBuilder.InvalidWidthException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TwoDTwoDRectangleBuilderTest {

    @Test
    public void shouldCreate2DRectangleWithBottomLeftPoint() throws Exception {
        final TwoDRectangleBuilder rectangleBuilder = TwoDRectangleBuilder.withBottomLeftPoint(TwoDPoint.create(1, 3))
                                    .withWidth(2)
                                    .withHeight(4);
        final TwoDRectangle rectangle = rectangleBuilder.build();
        assertNotNull(rectangle);
        assertEquals(1, rectangle.getXMin());
        assertEquals(3, rectangle.getYMin());
        assertEquals(3, rectangle.getXMax());
        assertEquals(7, rectangle.getYMax());
    }

    @Test(expected = InSufficientDataException.class)
    public void shouldNotBuildWithoutWightAndWidth() throws Exception {
        TwoDRectangleBuilder.withBottomLeftPoint(TwoDPoint.create(1, 4)).build();
    }

    @Test
    public void shouldNotBuildWithoutWidth() throws Exception {
        try {
            TwoDRectangleBuilder.withBottomLeftPoint(TwoDPoint.create(1, 4)).withHeight(12).build();
            fail("InSufficientDataException expected");
        } catch (final InSufficientDataException e) {
            assertExceptionMessageContains(e, "x", "max");
        }
    }

    private void assertExceptionMessageContains(final InSufficientDataException e, final String ...messageParts) {
        assertNotNull(e.getMessage());
        for (String messagePart : messageParts) {
            assertTrue(e.getMessage().toLowerCase().contains(messagePart));
        }
    }

    @Test
    public void shouldNotBuildWithoutHeight() throws Exception {
        try {
            TwoDRectangleBuilder.withBottomLeftPoint(TwoDPoint.create(1, 4)).withWidth(12).build();
        } catch (InSufficientDataException e) {
            assertExceptionMessageContains(e, "y", "max");
        }
    }

    @Test(expected = InvalidWidthException.class)
    public void shouldNotAcceptNegativeWidth() throws Exception {
        TwoDRectangleBuilder.withBottomLeftPoint(TwoDPoint.create(2, 5)).withWidth(-1).withHeight(101).build();
    }

    @Test(expected = InvalidWidthException.class)
    public void shouldNotAcceptZeroWidth() throws Exception {
        TwoDRectangleBuilder.withBottomLeftPoint(TwoDPoint.create(2, 5)).withWidth(0).withHeight(80).build();
    }

    @Test(expected = InvalidHeightException.class)
    public void shouldNotAcceptNegativeHeight() throws Exception {
        TwoDRectangleBuilder.withBottomLeftPoint(TwoDPoint.create(2, 5)).withWidth(1).withHeight(-101).build();
    }

    @Test(expected = InvalidHeightException.class)
    public void shouldNotAcceptZeroHeight() throws Exception {
        TwoDRectangleBuilder.withBottomLeftPoint(TwoDPoint.create(2, 5)).withWidth(10).withHeight(0).build();
    }

    @Test
    public void shouldCreate2DRectangleWithXYCoOrdinates() throws Exception {
        final int x1 = 10;final int x2 = 20;
        final int y1 = 90; final int y2 = 7;

        final TwoDRectangleBuilder builder = TwoDRectangleBuilder.withXYCoOrdinates().withX(x1, x2).withY(y1, y2);
        final TwoDRectangle rectangle = builder.build();

        assertNotNull(rectangle);
        assertEquals(x1, rectangle.getXMin());
        assertEquals(x2, rectangle.getXMax());
        assertEquals(y2, rectangle.getYMin());
        assertEquals(y1, rectangle.getYMax());
    }

    @Test(expected = InvalidWidthException.class)
    public void shouldNotAcceptSameXCoOrdinates() throws Exception {
        TwoDRectangleBuilder.withXYCoOrdinates().withX(10, 10).build();
    }

    @Test(expected = InvalidHeightException.class)
    public void shouldNotAcceptSameYCoOrdinates() throws Exception {
        TwoDRectangleBuilder.withXYCoOrdinates().withY(89, 89).build();
    }

    @Test(expected = InSufficientDataException.class)
    public void shouldNotCreateWithoutYCoOrdinates() throws Exception {
        TwoDRectangleBuilder.withXYCoOrdinates().withX(19, 79).build();
    }

    @Test(expected = InSufficientDataException.class)
    public void shouldNotCreateWithoutXCoOrdinates() throws Exception {
        TwoDRectangleBuilder.withXYCoOrdinates().withX(19, 45).build();
    }
}
