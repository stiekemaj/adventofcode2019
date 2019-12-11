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

    private static final Integer BLACK = 0;
    private static final Integer WHITE = 1;

    public static void main(String[] args) throws IOException {
        List<Long> codes = FileParseUtil.getCodes("day11.txt", ",");
        Map<Coordinate, Integer> paintedPanels = computePaintedPanels(codes, new Coordinate(0, 0), BLACK, Direction.UP);
        System.out.println("Day 11, Answer 1: " + paintedPanels.size());

        System.out.println("Answer 2:");
        printPaintedPanels(computePaintedPanels(codes, new Coordinate(0, 0), WHITE, Direction.UP));


    }

    private static void printPaintedPanels(Map<Coordinate, Integer> paintedPanels) {
        int minX = paintedPanels.keySet().stream().map(c -> c.x).min(Integer::compareTo).get();
        int minY = paintedPanels.keySet().stream().map(c -> c.y).min(Integer::compareTo).get();
        int maxX = paintedPanels.keySet().stream().map(c -> c.x).max(Integer::compareTo).get();
        int maxY = paintedPanels.keySet().stream().map(c -> c.y).max(Integer::compareTo).get();

        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                Integer color = paintedPanels.get(new Coordinate(x, y));
                System.out.print(WHITE.equals(color) ? '\u258A' : ' ');
            }
            System.out.println();
        }
    }

    private static Map<Coordinate, Integer> computePaintedPanels(List<Long> codes, Coordinate currentPanel, int currentPanelColor, Direction robotDirection) {
        Map<Coordinate, Integer> paintedPanels = new HashMap<>();
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
        return paintedPanels;
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
