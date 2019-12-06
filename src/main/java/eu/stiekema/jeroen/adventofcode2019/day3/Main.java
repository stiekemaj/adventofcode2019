package eu.stiekema.jeroen.adventofcode2019.day3;

import eu.stiekema.jeroen.adventofcode2019.common.InputStreamUtil;

import java.io.InputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        InputStream inputStream = eu.stiekema.jeroen.adventofcode2019.day1.Main.class.getClassLoader().getResourceAsStream("day3.txt");
        List<String> lines = InputStreamUtil.readLines(inputStream);

        String firstWireString = lines.get(0);
        String secondWireString = lines.get(1);

        WireFactory wireFactory = new WireFactory();
        List<Line> firstWire = wireFactory.createWire(firstWireString);
        List<Line> secondWire = wireFactory.createWire(secondWireString);
        Panel panel = new Panel(firstWire, secondWire);

        try {
            System.out.println("Answer part 1: " + panel.getClosestIntersectionDistance(new ClosestManhattanDistanceCalculatorStrategy()));
            System.out.println("Answer part 2: " + panel.getClosestIntersectionDistance(new SmallestWireDistanceCalculatorStrategy()));
        } catch (NoIntersectionFoundException e) {
            System.out.println("No intersection found");
        }
    }
}
