package in.algorithm.course.part.one.week.five.application;

import in.algorithm.course.part.one.week.five.kdtree.Point;

public class TwoDPoint extends Point {

    private static final int X_DIMENSION = 0;
    private static final int Y_DIMENSION = 1;
    private static final int D = 2;

    private TwoDPoint(final int x, final int y) {
        super(D);
        setCoOrdinate(X_DIMENSION, x);
        setCoOrdinate(Y_DIMENSION, y);
    }

    public static TwoDPoint create(final int x, final int y) {
        return new TwoDPoint(x, y);
    }

    public int getX() {
        return getNthDimension(X_DIMENSION);
    }

    public int getY() {
        return getNthDimension(Y_DIMENSION);
    }
}
