package eu.stiekema.jeroen.adventofcode2019.day15;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.TerminalFactory;
import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntCodeComputerTerminatedException;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntcodeComputer;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntcodeComputerImpl;

import java.io.IOException;
import java.util.List;

public class Day15 {
    public static void main(String[] args) throws IOException, IntCodeComputerTerminatedException, RepairDroidHitWallException {
        List<Long> codes = FileParseUtil.getCodes("day15.txt", ",");
        TerminalFactory terminalFactory = new DefaultTerminalFactory();
        IntcodeComputer intcodeComputer = new RepairDroidTerminalComputer(IntcodeComputerImpl.newInstance(codes), terminalFactory.createTerminal());

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

}

