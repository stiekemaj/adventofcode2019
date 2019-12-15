package eu.stiekema.jeroen.adventofcode2019.day15;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

enum MovementCommand {
    NORTH(1L), SOUTH(2L), WEST(3L), EAST(4L);

    private long code;

    MovementCommand(long code) {
        this.code = code;
    }

    public static MovementCommand ofCode(long code) {
        return Arrays.stream(values())
                .filter(t -> t.getCode() == code)
                .findFirst()
                .orElseThrow();
    }

    MovementCommand opposite() {
        switch (this) {
            case NORTH: return SOUTH;
            case SOUTH: return NORTH;
            case WEST: return EAST;
            case EAST: return WEST;
            default: throw new IllegalArgumentException("unknown moment command: " + this);
        }
    }

    public long getCode() {
        return code;
    }

    static List<MovementCommand> getCommands(MovementCommand previousCommand) {
        MovementCommand oppositeMovementCommand = previousCommand == null ? null : previousCommand.opposite();
        return Arrays.stream(values()).filter(t -> t != oppositeMovementCommand).collect(Collectors.toList());
    }
}
