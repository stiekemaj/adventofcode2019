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
    private static int blockCount = 0;
    public static void main(String[] args) throws IOException {
        List<Long> codes = FileParseUtil.getCodes("day13.txt", ",");
        TerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        IntcodeComputer intcodeComputer = IntcodeComputer.newInstance(codes);
        try {
            while (!intcodeComputer.isTerminated()) {
                long x = intcodeComputer.execute();
                long y = intcodeComputer.execute();
                long tileId = intcodeComputer.execute();
                terminal.setCursorPosition(new TerminalPosition((int) x, (int) y));
                terminal.putCharacter(getTile((int) tileId));
                terminal.flush();

            }
        } catch (IntCodeComputerTerminatedException ignored) {}

        System.out.println(blockCount);

    }

    private static char getTile(int id) {
        switch (id) {
            case 0: return ' ';
            case 1: return '\u2588';
            case 2: blockCount++; return '\u2593';
            case 3: return '\u2594';
            case 4: return 'o';
            default: throw new IllegalArgumentException("" + id);
        }
    }
}
