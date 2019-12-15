package eu.stiekema.jeroen.adventofcode2019.day15;

import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntCodeComputerTerminatedException;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntcodeComputer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day15 {
    public static void main(String[] args) throws IOException, IntCodeComputerTerminatedException, RepairDroidHitWallException {
        List<Long> codes = FileParseUtil.getCodes("day15.txt", ",");
        IntcodeComputer intcodeComputer = IntcodeComputer.newInstance(codes);
        long fewestNumberOfMovement = findOxygenSystemAndReturnNrOfSteps(intcodeComputer, null);
        System.out.println("Answer part 1: " + fewestNumberOfMovement);
    }

    public static long findOxygenSystemAndReturnNrOfSteps(IntcodeComputer computer, MovementCommand previousCommand) throws IntCodeComputerTerminatedException, RepairDroidHitWallException {
        if (previousCommand != null) {
            long output = computer.execute();
            if (output == 0L) {
                throw new RepairDroidHitWallException();
            } else if (output == 2L) {
                return 0;
            }
        }

        List<MovementCommand> nextPossibleCommands = MovementCommand.getCommands(previousCommand);
        long fewestCommands = Long.MAX_VALUE;
        for (MovementCommand command : nextPossibleCommands) {
            IntcodeComputer clonedComputer = computer.copy();
            clonedComputer.addInput(command.getCode());
            try {
                long nrOfSteps = findOxygenSystemAndReturnNrOfSteps(clonedComputer, command);
                if (nrOfSteps < fewestCommands - 1) {
                    fewestCommands = nrOfSteps + 1;
                }
            } catch (RepairDroidHitWallException ignored) {
            }
        }

        return fewestCommands;
    }

    //north (1), south (2), west (3), and east (4).
    enum MovementCommand {
        NORTH(1L), SOUTH(2L), WEST(3L), EAST(4L);

        private long code;
        
        MovementCommand(long code) {
            this.code = code;
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
}

