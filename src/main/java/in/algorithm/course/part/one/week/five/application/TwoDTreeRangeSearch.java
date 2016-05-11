package in.algorithm.course.part.one.week.five.application;

import in.algorithm.course.part.one.week.five.kdtree.KDTree;
import in.algorithm.course.part.one.week.five.kdtree.KDTreeImpl;
import in.algorithm.course.part.one.week.five.kdtree.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TwoDTreeRangeSearch {

    private final KDTree tree;

    private TwoDTreeRangeSearch() {
        tree = KDTreeImpl.createNewForDimensionsOf(2);
    }

    public static TwoDTreeRangeSearch createNew() {
        return new TwoDTreeRangeSearch();
    }

    public void insert(final TwoDPoint point) {
        tree.insert(point);
    }

    public List<TwoDPoint> findPointsInRectangle(final TwoDRectangle rectangle) {
        final List<TwoDPoint> pointsInRectangle = new ArrayList<>();

        if (tree.isEmpty()) {
            return pointsInRectangle;
        }

        findPointsInRectangle(getRoot(), rectangle, pointsInRectangle, TwoDCoOrdinate.initial());

        return pointsInRectangle;
    }

    private void findPointsInRectangle(final KDTree.Node currentNode, final TwoDRectangle rectangle,
                                        final List<TwoDPoint> pointsInRectangle, final TwoDCoOrdinate coOrdinate) {
        if (Objects.isNull(currentNode)) {
            return;
        }

        final TwoDPoint currentPoint = asTwoDPoint(currentNode.getPoint());

        if (pointBelongToRectangle(rectangle, currentPoint)) {
            pointsInRectangle.add(currentPoint);
            findPointsInBothChildren(currentNode, rectangle, pointsInRectangle, coOrdinate);
        } else if (pointIntersectsWithRectangle(rectangle, currentPoint, coOrdinate)) {
            findPointsInBothChildren(currentNode, rectangle, pointsInRectangle, coOrdinate);
        }

        if (rectangleLiesInLeftTreeOfPoint(rectangle, currentPoint, coOrdinate)) {
            if (currentNode.hasLeftChild()) {
                findPointsInRectangle(currentNode.getLeft(), rectangle, pointsInRectangle, coOrdinate.next());
            }
        }

        if (rectangleLiesInRightTreeOfPoint(rectangle, currentPoint, coOrdinate)) {
            if (currentNode.hasRightChild()) {
                findPointsInRectangle(currentNode.getRight(), rectangle, pointsInRectangle, coOrdinate.next());
            }
        }

    }

    private void findPointsInBothChildren(final KDTree.Node currentNode, final TwoDRectangle rectangle, final List<TwoDPoint> pointsInRectangle, final TwoDCoOrdinate coOrdinate) {
        if (currentNode.hasLeftChild()) {
            findPointsInRectangle(currentNode.getLeft(), rectangle, pointsInRectangle, coOrdinate.next());
        }
        if (currentNode.hasRightChild()) {
            findPointsInRectangle(currentNode.getRight(), rectangle, pointsInRectangle, coOrdinate.next());
        }
    }

    private boolean pointIntersectsWithRectangle(final TwoDRectangle rectangle, final TwoDPoint point, final TwoDCoOrdinate coOrdinate) {
        if (coOrdinate.isX()) {
            return xCoOrdinateLiesInRectangle(rectangle, point)
                    && !yCoOrdinateLiesInRectangle(rectangle, point);

        } else if (coOrdinate.isY()) {
            return yCoOrdinateLiesInRectangle(rectangle, point)
                    && !xCoOrdinateLiesInRectangle(rectangle, point);
        }
        return false;
    }

    private boolean yCoOrdinateLiesInRectangle(final TwoDRectangle rectangle, final TwoDPoint point) {
        return rectangle.getYMin() <= point.getY() && point.getY() <= rectangle.getYMax();
    }

    private boolean xCoOrdinateLiesInRectangle(final TwoDRectangle rectangle, final TwoDPoint point) {
        return rectangle.getXMin() <= point.getX() && point.getX() <= rectangle.getXMax();
    }

    private boolean rectangleLiesInRightTreeOfPoint(final TwoDRectangle rectangle, final TwoDPoint currentPoint, final TwoDCoOrdinate coOrdinate) {
        if (coOrdinate.isX()) {
            return rectangle.getXMin() > currentPoint.getX();
        } else if (coOrdinate.isY()) {
            return rectangle.getYMin() > currentPoint.getY();
        }
        return false;
    }

    private boolean rectangleLiesInLeftTreeOfPoint(final TwoDRectangle rectangle, final TwoDPoint currentPoint, final TwoDCoOrdinate coOrdinate) {
        if (coOrdinate.isX()) {
            return rectangle.getXMax() < currentPoint.getX();
        } else if (coOrdinate.isY()) {
            return rectangle.getYMax() < currentPoint.getY();
        }
        return false;
    }

    private boolean pointBelongToRectangle(final TwoDRectangle rectangle, final TwoDPoint point) {
        return xCoOrdinateLiesInRectangle(rectangle, point) && yCoOrdinateLiesInRectangle(rectangle, point);
    }

    private TwoDPoint asTwoDPoint(final Point point) {
        return (TwoDPoint) point;
    }

    private KDTree.Node getRoot() {
        return tree.getRoot();
    }


    private enum TwoDCoOrdinate {
        X,
        Y;

        public static TwoDCoOrdinate initial() {
            return X;
        }

        public TwoDCoOrdinate next() {
            return X.equals(this) ? Y : X;
        }

        public boolean isX() {
            return X.equals(this);
        }

        public boolean isY() {
            return Y.equals(this);
        }
    }
}
