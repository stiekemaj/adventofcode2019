package eu.stiekema.jeroen.adventofcode2019.day1;

import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;

import java.io.InputStream;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        ModuleFuelCalculator moduleFuelCalculator = new ModuleFuelCalculator();

        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("day1.txt");
        List<String> modules = FileParseUtil.readLines(inputStream);

        System.out.println("Total amount of fuel (simple calculation): " +
                calculateTotalAmountOfFuel(modules, moduleFuelCalculator::simpleCalculate));

        System.out.println("Total amount of fuel (also considering fuel mass): " +
                calculateTotalAmountOfFuel(modules, moduleFuelCalculator::calculate));
    }

    private static int calculateTotalAmountOfFuel(List<String> modules, Function<Integer, Integer> fuelCalculator) {
        return modules.stream()
                .map(Integer::valueOf)
                .map(fuelCalculator)
                .mapToInt(Integer::intValue).sum();
    }
}
