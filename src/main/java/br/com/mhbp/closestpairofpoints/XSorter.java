package br.com.mhbp.closestpairofpoints;

import java.util.Comparator;

public class XSorter implements Comparator<Point> {

    @Override
    public int compare(Point point, Point otherPoint) {
        return Double.compare(point.x, otherPoint.x);
    }
}
