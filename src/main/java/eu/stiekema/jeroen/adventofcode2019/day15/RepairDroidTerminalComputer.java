package eu.stiekema.jeroen.adventofcode2019.day15;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntCodeComputerTerminatedException;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntcodeComputer;

import java.io.IOException;

public class RepairDroidTerminalComputer implements IntcodeComputer {

    private final IntcodeComputer intcodeComputer;
    private final Terminal terminal;

    private TerminalPosition terminalPosition;
    private TerminalPosition droidPosition;
    private MovementCommand lastMovementCommand;
    private boolean firstStep = true;

    public RepairDroidTerminalComputer(IntcodeComputer intcodeComputer, Terminal terminal) {
        this.intcodeComputer = intcodeComputer;
        this.terminal = terminal;
        this.terminalPosition = new TerminalPosition(40, 21);
        this.droidPosition = this.terminalPosition;
    }

    private RepairDroidTerminalComputer(IntcodeComputer intcodeComputer, Terminal terminal, TerminalPosition terminalPosition, TerminalPosition droidPosition, boolean firstStep) {
        this.intcodeComputer = intcodeComputer;
        this.terminal = terminal;
        this.terminalPosition = terminalPosition;
        this.droidPosition = droidPosition;
        this.firstStep = firstStep;
    }

    @Override
    public IntcodeComputer copy() {
        return new RepairDroidTerminalComputer(this.intcodeComputer.copy(), this.terminal, this.terminalPosition, this.droidPosition, this.firstStep);
    }

    @Override
    public void addInput(long input) {
        this.lastMovementCommand = MovementCommand.ofCode(input);
        this.intcodeComputer.addInput(input);
    }

    @Override
    public long execute() throws IntCodeComputerTerminatedException {
        try {
            if (firstStep) {
                terminal.setBackgroundColor(new TextColor.RGB(255, 0, 0));
            } else {
                terminal.setBackgroundColor(new TextColor.RGB(0, 255, 0));
            }

            long result = this.intcodeComputer.execute();
            terminal.setCursorPosition(this.terminalPosition);
            if (result == 0L) {
                updateTerminalPosition();
                terminal.setCursorPosition(this.terminalPosition);
                terminal.putCharacter('#');
            } else if (result == 1L) {
                terminal.setCursorPosition(this.terminalPosition);
                terminal.putCharacter('.');
                updateDroidPosition();
            } else if (result == 2L) {
                terminal.setCursorPosition(this.terminalPosition);
                terminal.putCharacter('O');
                updateDroidPosition();
            }
            this.terminalPosition = this.droidPosition;
            terminal.setCursorPosition(new TerminalPosition(0, 0));
            terminal.flush();
            firstStep = false;
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateTerminalPosition() {
        this.terminalPosition = getNextPosition(this.terminalPosition);
    }

    private void updateDroidPosition() {
        this.droidPosition = getNextPosition(this.droidPosition);
    }

    private TerminalPosition getNextPosition(TerminalPosition position) {
        switch (this.lastMovementCommand) {
            case NORTH:
                return new TerminalPosition(position.getColumn(), position.getRow() - 1);
            case WEST:
                return new TerminalPosition(position.getColumn() - 1, position.getRow());
            case SOUTH:
                return new TerminalPosition(position.getColumn(), position.getRow() + 1);
            case EAST:
                return new TerminalPosition(position.getColumn() + 1, position.getRow());
            default:
                throw new IllegalArgumentException();
        }
    }
}
