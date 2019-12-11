package eu.stiekema.jeroen.adventofcode2019.common;

public enum Direction {
    UP, RIGHT, DOWN, LEFT;

    public Direction turnRight() {
        return values()[Math.floorMod(this.ordinal() + 1, values().length)];
    }

    public Direction turnLeft() {
        return values()[Math.floorMod(this.ordinal() - 1, values().length)];
    }
}
