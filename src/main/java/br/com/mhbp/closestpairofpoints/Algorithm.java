package br.com.mhbp.closestpairofpoints;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithm {
    private List<Point> points;

    public Algorithm(List<Point> points) {

        this.points = points;
    }

    public double solveProblem() {
        ArrayList<Point> sortedXPoints = new ArrayList<>();
        ArrayList<Point> yPoints = new ArrayList<>();

        for (Point point : points) {
            sortedXPoints.add(point);
            yPoints.add(point);
        }

        sorteByX(sortedXPoints);
        return findClosestPoints(sortedXPoints, yPoints, sortedXPoints.size());
    }

    public double bruteForceApproach(List<Point> points) {
        double minDistance = Double.MAX_VALUE;

        for(int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); ++j) {

                if (distance(points.get(i), points.get(j)) < minDistance) {
                    minDistance = distance(points.get(i), points.get(j));
                }
            }
        }
        return minDistance;
    }

    //euclidean distance, basic triangle
    private double distance(Point p1, Point p2) {
        double xDistance = Math.abs(p1.x - p2.x);
        double yDistance = Math.abs(p1.y - p2.y);
        return Math.sqrt( xDistance * xDistance + yDistance * yDistance);
    }

    private double findClosestPoints(List<Point> pointsSortedX, List<Point> pointsY, int numOfPoints) {

        if (numOfPoints <= 3) return bruteForceApproach(pointsSortedX);
        int middleIndex = numOfPoints / 2;

        Point middlePoint = pointsSortedX.get(middleIndex);

        List<Point> leftSubPointsY = new ArrayList<>();
        List<Point> leftSubPointsX = new ArrayList<>();
        List<Point> rightSubPointsY = new ArrayList<>();
        List<Point> rightSubPointsX = new ArrayList<>();

        for ( int index = 0; index < numOfPoints; ++index) {


            if (pointsY.get(index).x <= middlePoint.x) {
                leftSubPointsY.add(pointsY.get(index));
                leftSubPointsX.add(pointsSortedX.get(index));
            } else {
                rightSubPointsY.add(pointsY.get(index));
                rightSubPointsX.add(pointsSortedX.get(index));
            }
        }

        double sigmaLeft = findClosestPoints(leftSubPointsX, leftSubPointsY, middleIndex);
        double sigmaRight = findClosestPoints(rightSubPointsX, rightSubPointsY, numOfPoints - middleIndex);
        double sigma = Math.min(sigmaLeft, sigmaRight);

        List<Point> pointsInStrip = new ArrayList<>();

        for(int index = 0; index < numOfPoints; ++index) {
            if (Math.abs(pointsY.get(index).x - middlePoint.x) < sigma) {
                pointsInStrip.add(pointsY.get(index));
            }
        }

        double minDistanceStrip = closestStrip(pointsInStrip, sigma);
        return Math.min(sigma, minDistanceStrip);
    }

    public double closestStrip(List<Point> points, double sigma) {
        double minDistance = sigma;

        for(int i = 0; i < points.size(); i++) {
            //???????????????????????????????????????????????????
            for (int j = i + 1; j < points.size() && (points.get(j).y - points.get(i).y > 0) ; ++j) {

                if (distance(points.get(i), points.get(j)) < minDistance) {
                    minDistance = distance(points.get(i), points.get(j));
                }
            }
        }
        return minDistance;
    }
    private void sorteByX(ArrayList<Point> sortedXPoints) {
        Collections.sort(points, new XSorter());
    }
}
