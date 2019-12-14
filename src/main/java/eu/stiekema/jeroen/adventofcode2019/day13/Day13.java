package eu.stiekema.jeroen.adventofcode2019.day13;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalFactory;
import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntCodeComputerTerminatedException;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntcodeComputer;

import java.io.IOException;
import java.util.List;

public class Day13 {
    public static void main(String[] args) throws IOException {
        List<Long> codes = FileParseUtil.getCodes("day13.txt", ",");
        IntcodeComputer intcodeComputer = IntcodeComputer.newInstance(codes);
        int blockCount = 0;
        try {
            while (!intcodeComputer.isTerminated()) {
                long x = intcodeComputer.execute();
                long y = intcodeComputer.execute();
                long tileId = intcodeComputer.execute();
                if (tileId == 2L) {
                    blockCount++;
                }
            }
        } catch (IntCodeComputerTerminatedException ignored) {}

        System.out.println("Answer 1: " + blockCount);

        intcodeComputer = IntcodeComputer.newInstance(codes);
        intcodeComputer.setAddressValue(0L, 2L);
        TerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        long paddleX = -1L;
        long ballX = -1;
        String score = null;
        try {
            while (!intcodeComputer.isTerminated()) {
                doJoystickInput(intcodeComputer, paddleX, ballX);
                long x = intcodeComputer.execute();
                long y = intcodeComputer.execute();
                if (x == -1L) {
                    score = Long.toString(intcodeComputer.execute());
                    char[] charArray = score.toCharArray();
                    for (int i = 0; i < charArray.length; i++) {
                        char c = charArray[i];
                        terminal.setCursorPosition(new TerminalPosition(i, 0));
                        terminal.putCharacter(c);
                        terminal.flush();
                    }
                } else {
                    long tileId = intcodeComputer.execute();
                    terminal.setCursorPosition(new TerminalPosition((int)x, (int)y));
                    terminal.putCharacter(getTile((int)tileId));
                    terminal.flush();
                    if (tileId == 2L) {
                        blockCount++;
                    } else if (tileId == 4L) {
                        ballX = x;
                    } else if (tileId == 3L) {
                        paddleX = x;
                    }
                }

            }
        } catch (IntCodeComputerTerminatedException ignored) { }

        System.out.println("Answer 2: " + score);

    }

    private static void doJoystickInput(IntcodeComputer computer, long paddleX, long ballX) {
        computer.clearInput();
        computer.addInput(paddleX == ballX ? 0L : paddleX < ballX ? 1L : -1L);
    }

    private static char getTile(int id) {
        switch (id) {
            case 0: return ' ';
            case 1: return '\u2588';
            case 2: return '\u2593';
            case 3: return '\u2594';
            case 4: return 'o';
            default: throw new IllegalArgumentException("" + id);
        }
    }
}
