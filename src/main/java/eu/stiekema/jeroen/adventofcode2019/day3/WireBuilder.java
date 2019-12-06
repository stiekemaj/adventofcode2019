package eu.stiekema.jeroen.adventofcode2019.day3;

import java.util.ArrayList;
import java.util.List;

public class WireBuilder {

    List<Line> wire = new ArrayList<>();
    private Coordinate currentCoordinate = new Coordinate(0, 0);

    WireBuilder stepTo(Step step) {
        Coordinate endCoordinate;
        switch (step.direction) {
            case UP:
                endCoordinate = new Coordinate(currentCoordinate.x, currentCoordinate.y + step.distance);
                break;
            case DOWN:
                endCoordinate = new Coordinate(currentCoordinate.x, currentCoordinate.y - step.distance);
                break;
            case LEFT:
                endCoordinate = new Coordinate(currentCoordinate.x - step.distance, currentCoordinate.y);
                break;
            case RIGHT:
                endCoordinate = new Coordinate(currentCoordinate.x + step.distance, currentCoordinate.y);
                break;
            default:
                throw new IllegalArgumentException("unknown direction: " + step.direction);
        }

        wire.add(new Line(currentCoordinate, endCoordinate));
        currentCoordinate = endCoordinate;
        return this;
    }

    List<Line> build() {
        return wire;
    }
}
