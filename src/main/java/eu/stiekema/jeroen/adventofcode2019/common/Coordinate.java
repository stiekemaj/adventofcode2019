package eu.stiekema.jeroen.adventofcode2019.common;

import java.util.Objects;

public class Coordinate {

    public final int x;
    public final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getAngle(Coordinate target) {
        return -1 * Math.toDegrees(Math.atan2(target.x - x, target.y - y)) + 180;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }

    public Coordinate goToDirection(Direction direction) {
        switch (direction) {
            case UP: return new Coordinate(x, y-1);
            case DOWN: return new Coordinate(x, y+1);
            case LEFT: return new Coordinate(x-1, y);
            case RIGHT: return new Coordinate(x+1, y);
            default: throw new IllegalArgumentException("Unknown direction: " + direction);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
