package eu.stiekema.jeroen.adventofcode2019.day3;

import java.util.List;
import java.util.Set;

class ClosestManhattanDistanceCalculatorStrategy implements ClosestDistanceCalculatorStrategy {

    @Override
    public int getClosestDistance(Set<Coordinate> intersections, List<Line> wire1, List<Line> wire2) {
        if (intersections.isEmpty()) {
            throw new IllegalArgumentException("there should be at least one intersection");
        }

        Coordinate referenceCoordinate = new Coordinate(0, 0);
        Coordinate closestIntersection = null;

        for (Coordinate intersection : intersections) {
            if (closestIntersection == null
                    || intersection.distanceTo(referenceCoordinate) < closestIntersection.distanceTo(referenceCoordinate)) {
                closestIntersection = intersection;
            }
        }

        return closestIntersection.distanceTo(new Coordinate(0, 0));
    }
}
