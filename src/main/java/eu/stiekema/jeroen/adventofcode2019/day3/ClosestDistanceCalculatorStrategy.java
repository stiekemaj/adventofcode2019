package eu.stiekema.jeroen.adventofcode2019.day3;

import java.util.List;
import java.util.Set;

public interface ClosestDistanceCalculatorStrategy {
    int getClosestDistance(Set<Coordinate> intersections, List<Line> wire1, List<Line> wire2);
}
