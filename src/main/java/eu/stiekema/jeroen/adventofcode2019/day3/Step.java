package eu.stiekema.jeroen.adventofcode2019.day3;

public class Step {

    enum Direction {
        LEFT, RIGHT, UP, DOWN;

        public static Direction ofString(String str) {
            switch (str) {
                case "L": return LEFT;
                case "R": return RIGHT;
                case "U": return UP;
                case "D": return DOWN;
                default: throw new IllegalArgumentException("unknown direction value: " + str);
            }
        }
    }

    final Direction direction;
    final int distance;

    public Step(Direction direction, int distance) {
        this.direction = direction;
        this.distance = distance;
    }
}
