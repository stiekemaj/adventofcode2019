package eu.stiekema.jeroen.adventofcode2019.day3;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SmallestWireDistanceCalculatorStrategy implements ClosestDistanceCalculatorStrategy {
    @Override
    public int getClosestDistance(Set<Coordinate> intersections, List<Line> wire1, List<Line> wire2) {
        if (intersections.isEmpty()) {
            throw new IllegalArgumentException("there should be at least one intersection");
        }

        int smallestWireDistance = Integer.MAX_VALUE;

        for (Coordinate intersection : intersections) {
            int wire1IntersectionLength = calculateLength(wire1, intersection);
            int wire2IntersectionLength = calculateLength(wire2, intersection);
            if (wire1IntersectionLength + wire2IntersectionLength < smallestWireDistance) {
                smallestWireDistance = wire1IntersectionLength + wire2IntersectionLength;
            }
        }

        return smallestWireDistance;
    }

    private int calculateLength(List<Line> wire, Coordinate intersection) {
        List<Coordinate> wireInSteps = wire.stream()
                .map(Line::toSteps)
                .flatMap(Collection::stream)
                .map(t -> t.end)
                .collect(Collectors.toList());

        return wireInSteps.indexOf(intersection) + 1;
    }
}
