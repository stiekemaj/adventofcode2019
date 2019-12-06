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
        Coordinate closestIntersection = panel.getClosestIntersection();
        if (closestIntersection == null) {
            System.out.println("No intersection found");
        } else {
            System.out.println(closestIntersection.distanceTo(new Coordinate(0, 0)));
        }
    }
}
