package in.algorithm.course.part.one.week.five.kdtree;

public class PointBuilder {

    private final Point point;
    private final int d;

    private int countOfDimensionsAdded;

    private PointBuilder(final int d) {
        this.d = d;
        this.countOfDimensionsAdded = 0;
        this.point = Point.createNewForDimension(d);
    }
    
    public static PointBuilder forDimensions(final int d) {
        return new PointBuilder(d);
    }

    public PointBuilder addDimension(final int coOrdinate) {
        if (this.countOfDimensionsAdded < this.d) {
            point.setCoOrdinate(countOfDimensionsAdded, coOrdinate);
        }
        countOfDimensionsAdded++;
        return this;
    }

    public Point build() {
        if (this.countOfDimensionsAdded < d) {
            throw new LesserCoOrdinatesThanDimensionsException();
        } else if (this.countOfDimensionsAdded > d) {
            throw new MoreCoOrdinatesThanDimensionsException();
        }
        return point;
    }

    public static class LesserCoOrdinatesThanDimensionsException extends RuntimeException {

    }

    public static class MoreCoOrdinatesThanDimensionsException extends RuntimeException {

    }
}
