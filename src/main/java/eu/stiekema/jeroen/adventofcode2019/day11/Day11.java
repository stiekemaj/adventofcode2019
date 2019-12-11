package eu.stiekema.jeroen.adventofcode2019.day11;

import eu.stiekema.jeroen.adventofcode2019.common.Coordinate;
import eu.stiekema.jeroen.adventofcode2019.common.Direction;
import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntCodeComputerTerminatedException;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntcodeComputer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day11 {

    public static final int BLACK = 0;

    public static void main(String[] args) throws IOException {
        List<Long> codes = FileParseUtil.getCodes("day11.txt", ",");

        Map<Coordinate, Integer> paintedPanels = new HashMap<>();

        Direction robotDirection = Direction.UP;
        Coordinate currentPanel = new Coordinate(0, 0);
        int currentPanelColor = BLACK;

        try {
            IntcodeComputer intcodeComputer = IntcodeComputer.newInstance(codes);
            while (!intcodeComputer.isTerminated()) {
                intcodeComputer.addInput(currentPanelColor);

                int color = (int) intcodeComputer.execute();
                paintedPanels.put(currentPanel, color);

                long directionIndication = intcodeComputer.execute();
                robotDirection = getNewDirection(robotDirection, directionIndication);
                currentPanel = currentPanel.goToDirection(robotDirection);
                currentPanelColor = paintedPanels.getOrDefault(currentPanel, BLACK);
            }
        } catch (IntCodeComputerTerminatedException ignored) {}

        System.out.println("Day 11, Answer 1: " + paintedPanels.size());
    }

    private static Direction getNewDirection(Direction robotDirection, long directionIndication) {
        if (directionIndication == 0) {
            robotDirection = robotDirection.turnLeft();
        } else if (directionIndication == 1) {
            robotDirection = robotDirection.turnRight();
        } else {
            throw new IllegalStateException("Unknown direction indication: " + directionIndication);
        }
        return robotDirection;
    }
}
