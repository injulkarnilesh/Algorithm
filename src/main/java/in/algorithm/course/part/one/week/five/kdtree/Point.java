package in.algorithm.course.part.one.week.five.kdtree;

import java.util.Arrays;

public class Point {

    public static final Point INVALID_POINT = new Point(0) {
        public boolean isValid() {
            return false;
        }
    };

    private final int d;
    private final int[] dimensions;

    protected Point(final int d) {
        this.d = d;
        this.dimensions = new int[d];
    }

    static Point createNewForDimension(final int d) {
        return new Point(d);
    }

    public int getNthDimension(final int n) {
        if (n < 0 || n > d) {
            throw new OutOfBoundDimension();
        }
        return dimensions[n];
    }

    protected void setCoOrdinate(final int dimension, final int coOrdinate) {
        this.dimensions[dimension] = coOrdinate;
    }

    public int getD() {
        return d;
    }

    public boolean isValid() {
        return true;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Point point = (Point) o;

        if (d != point.d) return false;
        return Arrays.equals(dimensions, point.dimensions);

    }

    @Override
    public int hashCode() {
        int result = d;
        result = 31 * result + Arrays.hashCode(dimensions);
        return result;
    }

    @Override
    public String toString() {
        return d + " DIMENSIONAL " + Arrays.toString(dimensions);
    }

    public static class OutOfBoundDimension extends RuntimeException {

    }

}
