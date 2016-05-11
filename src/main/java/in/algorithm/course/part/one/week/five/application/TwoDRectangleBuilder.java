package in.algorithm.course.part.one.week.five.application;

import java.util.Objects;

public class TwoDRectangleBuilder {

    protected Integer xMin;
    protected Integer xMax;
    protected Integer yMin;
    protected Integer yMax;

    private TwoDRectangleBuilder() {

    }

    public static TwoDRectangleBuilderWithBottomLeftPoint withBottomLeftPoint(final TwoDPoint twoDPoint) {
        return TwoDRectangleBuilderWithBottomLeftPoint.create(twoDPoint);
    }

    public static TwoDRectangleBuilderWithXYCoOrdinates withXYCoOrdinates() {
        return TwoDRectangleBuilderWithXYCoOrdinates.createNew();
    }

    public TwoDRectangle build() {
        validateDimensions();
        return TwoDRectangle.create(xMin, xMax, yMin, yMax);
    }

    private void validateDimensions() {
        if (Objects.isNull(xMax)) {
            throw InSufficientDataException.forXMax();
        } else if (Objects.isNull(yMax)) {
            throw InSufficientDataException.forYMax();
        } else if (Objects.isNull(xMin)) {
            throw InSufficientDataException.forXMin();
        } else if (Objects.isNull(yMin)) {
            throw InSufficientDataException.forYMin();
        }
    }

    public static class TwoDRectangleBuilderWithXYCoOrdinates extends TwoDRectangleBuilder {

        private TwoDRectangleBuilderWithXYCoOrdinates() { }

        public static TwoDRectangleBuilderWithXYCoOrdinates createNew() {
            return new TwoDRectangleBuilderWithXYCoOrdinates();
        }

        public TwoDRectangleBuilderWithXYCoOrdinates withX(final int x1, final int x2) {
            if (x1 < x2) {
                this.xMin = x1;
                this.xMax = x2;
            } else if (x2 < x1){
                this.xMin = x2;
                this.xMax = x1;
            } else {
                throw new InvalidWidthException(0);
            }

            return this;
        }

        public TwoDRectangleBuilder withY(final int y1, final int y2) {
            if (y1 < y2) {
                this.yMin = y1;
                this.yMax = y2;
            } else if (y2 < y1) {
                this.yMin = y2;
                this.yMax = y1;
            } else {
                throw new InvalidHeightException(0);
            }
            return this;
        }
    }

    public static class TwoDRectangleBuilderWithBottomLeftPoint extends TwoDRectangleBuilder {

        private TwoDRectangleBuilderWithBottomLeftPoint(final TwoDPoint bottomLeftPoint) {
            this.xMin = bottomLeftPoint.getX();
            this.yMin = bottomLeftPoint.getY();
        }

        public static TwoDRectangleBuilderWithBottomLeftPoint create(final TwoDPoint bottomLeftPoint) {
            return new TwoDRectangleBuilderWithBottomLeftPoint(bottomLeftPoint);
        }

        public TwoDRectangleBuilderWithBottomLeftPoint withWidth(final int width) {
            if (width <= 0) {
                throw new InvalidWidthException(width);
            }
            this.xMax = this.xMin + width;
            return this;
        }

        public TwoDRectangleBuilderWithBottomLeftPoint withHeight(final int height) {
            if (height <= 0) {
                throw new InvalidHeightException(height);
            }
            this.yMax = this.yMin + height;
            return this;
        }

    }

    public static class InvalidHeightException extends RuntimeException {
        private InvalidHeightException(final int inputValue) {
            super("Height should be positive, found value : " +inputValue);
        }
    }

    public static class InvalidWidthException extends RuntimeException {
        private InvalidWidthException(final int inputValue) {
            super("Width should be positive, found value : " + inputValue);
        }
    }

    public static class InSufficientDataException extends RuntimeException {

        private InSufficientDataException(final String dimension) {
            super(dimension + " not provided.");
        }

        private static InSufficientDataException forXMax() {
            return new InSufficientDataException("X Max");
        }

        private static InSufficientDataException forYMax() {
            return new InSufficientDataException("Y Max");
        }

        private static InSufficientDataException forXMin() {
            return new InSufficientDataException("X Min");
        }

        private static InSufficientDataException forYMin() {
            return new InSufficientDataException("Y Min");
        }

    }

}
