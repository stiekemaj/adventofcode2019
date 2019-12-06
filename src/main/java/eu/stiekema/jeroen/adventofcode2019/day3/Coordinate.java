package eu.stiekema.jeroen.adventofcode2019.day3;

import java.util.Objects;

class Coordinate {
    final int x;
    final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y +')';
    }

    int distanceTo(Coordinate coordinate) {
        return Math.abs(this.x - coordinate.x) + Math.abs(this.y - coordinate.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
