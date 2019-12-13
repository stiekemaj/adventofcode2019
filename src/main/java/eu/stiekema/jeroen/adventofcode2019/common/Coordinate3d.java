package eu.stiekema.jeroen.adventofcode2019.common;

public class Coordinate3d {
    public final int x, y, z;

    public Coordinate3d(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coordinate3d add(Coordinate3d addedCoordinate) {
        return new Coordinate3d(this.x + addedCoordinate.x, this.y + addedCoordinate.y, this.z + addedCoordinate.z);
    }

    @Override
    public String toString() {
        return String.format("(%s,%s,%s)", x, y, z);
    }
}
