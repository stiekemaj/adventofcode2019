package eu.stiekema.jeroen.adventofcode2019.day3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Panel {
    private final List<Line> wire1;
    private final List<Line> wire2;

    Panel(List<Line> wire1, List<Line> wire2) {
        this.wire1 = wire1;
        this.wire2 = wire2;
    }

    Coordinate getClosestIntersection() {
        Set<Coordinate> intersections = getIntersections();
        Coordinate referenceCoordinate = new Coordinate(0, 0);
        Coordinate closestIntersection = null;

        for (Coordinate intersection : intersections) {
            if (closestIntersection == null
                    || intersection.distanceTo(referenceCoordinate) < closestIntersection.distanceTo(referenceCoordinate)) {
                closestIntersection = intersection;
            }
        }

        return closestIntersection;
    }

    private Set<Coordinate> getIntersections() {
        Set<Coordinate> intersections = new HashSet<>();
        for (Line wire1Line : wire1) {
            for (Line wire2Line : wire2) {
                intersections.addAll(findLineIntersections(wire1Line, wire2Line));
            }
        }

        // exclude (0,0)
        intersections.remove(new Coordinate(0, 0));

        return intersections;
    }

    private Set<Coordinate> findLineIntersections(Line line1, Line line2) {
        Set<Coordinate> intersections = new HashSet<>();

        if (line1.type == Line.Type.VERTICALLY && line2.type == Line.Type.VERTICALLY) {
            if (line1.start.x == line2.start.x) {
                for (int y = Math.min(line1.start.y, line1.end.y); y <= Math.max(line2.start.y, line2.end.y); y++) {
                    if (y >= Math.min(line2.start.y, line2.end.y) && y <= Math.max(line2.start.y, line2.end.y)) {
                        intersections.add(new Coordinate(line1.start.x, y));
                    }
                }
            }
        } else if (line1.type == Line.Type.HORIZONTALLY && line2.type == Line.Type.HORIZONTALLY) {
            if (line1.start.y == line2.start.y) {
                for (int x = Math.min(line1.start.x, line1.end.x); x <= Math.max(line2.start.x, line2.end.x); x++) {
                    if (x >= Math.min(line2.start.x, line2.end.x) && x <= Math.max(line2.start.x, line2.end.x)) {
                        intersections.add(new Coordinate(x, line1.start.y));
                    }
                }
            }
        } else if (Math.min(line1.start.x, line1.end.x) <= Math.max(line2.start.x, line2.end.x)
                && Math.max(line1.start.x, line1.end.x) >= Math.min(line2.start.x, line2.end.x)
                && Math.min(line2.start.y, line2.end.y) <= Math.max(line1.start.y, line1.end.y)
                && Math.max(line2.start.y, line2.end.y) >= Math.min(line1.start.y, line1.end.y)
        ) {
            intersections.add(new Coordinate(
                    line1.type == Line.Type.HORIZONTALLY ? line2.start.x : line1.start.x,
                    line1.type == Line.Type.HORIZONTALLY ? line1.start.y : line2.start.y)
            );
        }

        return intersections;
    }
}

