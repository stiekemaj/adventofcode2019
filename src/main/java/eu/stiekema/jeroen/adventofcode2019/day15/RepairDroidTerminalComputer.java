package eu.stiekema.jeroen.adventofcode2019.day15;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.terminal.Terminal;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntCodeComputerTerminatedException;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntcodeComputer;

import java.io.IOException;

public class RepairDroidTerminalComputer implements IntcodeComputer {

    private final IntcodeComputer intcodeComputer;
    private final Terminal terminal;

    private TerminalPosition terminalPosition;
    private MovementCommand lastMovementCommand;

    public RepairDroidTerminalComputer(IntcodeComputer intcodeComputer, Terminal terminal) {
        this.intcodeComputer = intcodeComputer;
        this.terminal = terminal;
        this.terminalPosition = new TerminalPosition(40, 13);
    }

    private RepairDroidTerminalComputer(IntcodeComputer intcodeComputer, Terminal terminal, TerminalPosition terminalPosition) {
        this.intcodeComputer = intcodeComputer;
        this.terminal = terminal;
        this.terminalPosition = terminalPosition;
    }

    @Override
    public IntcodeComputer copy() {
        return new RepairDroidTerminalComputer(this.intcodeComputer.copy(), this.terminal, this.terminalPosition);
    }

    @Override
    public void addInput(long input) {
        this.lastMovementCommand = MovementCommand.ofCode(input);
        this.intcodeComputer.addInput(input);
    }

    @Override
    public long execute() throws IntCodeComputerTerminatedException {
        try {
            long result = this.intcodeComputer.execute();
            terminal.setCursorPosition(this.terminalPosition);
            if (result == 0L) {
                terminal.putCharacter('#');
            } else if (result == 1L) {
                terminal.putCharacter('.');
                updateTerminalPosition();

            } else if (result == 2L) {
                terminal.putCharacter('O');
                updateTerminalPosition();
            }
            terminal.setCursorPosition(new TerminalPosition(0, 0));
            terminal.flush();

            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateTerminalPosition() {
        switch (this.lastMovementCommand) {
            case NORTH:
                this.terminalPosition = new TerminalPosition(this.terminalPosition.getColumn(), this.terminalPosition.getRow() - 1);
                break;
            case WEST:
                this.terminalPosition = new TerminalPosition(this.terminalPosition.getColumn() - 1, this.terminalPosition.getRow());
                break;
            case SOUTH:
                this.terminalPosition = new TerminalPosition(this.terminalPosition.getColumn(), this.terminalPosition.getRow() + 1);
                break;
            case EAST:
                this.terminalPosition = new TerminalPosition(this.terminalPosition.getColumn() + 1, this.terminalPosition.getRow());
                break;
        }
    }
}
