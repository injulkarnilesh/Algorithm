package in.algorithm.course.part.one.week.five.application;


public class TwoDRectangle {

    private final int xMin;
    private final int xMax;
    private final int yMin;
    private final int yMax;

    private TwoDRectangle(final int xMin, final int xMax, final int yMin, final int yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public static TwoDRectangle create(final int xMin, final int xMax, final int yMin, final int yMax) {
        return new TwoDRectangle(xMin, xMax, yMin, yMax);
    }

    public int getXMin() {
        return xMin;
    }

    public int getXMax() {
        return xMax;
    }

    public int getYMin() {
        return yMin;
    }

    public int getYMax() {
        return yMax;
    }
}
