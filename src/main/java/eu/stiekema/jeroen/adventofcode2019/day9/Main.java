package eu.stiekema.jeroen.adventofcode2019.day9;

import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntCodeComputerTerminatedException;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntcodeComputer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, IntCodeComputerTerminatedException {
        List<Long> codes = FileParseUtil.getCodes("day9.txt", ",");

        System.out.println("Answer part 1: " + process(codes, 1L));
        System.out.println("Answer part 2: " + process(codes, 2L));
    }

    private static List<Long> process(List<Long> codes, long input) throws IntCodeComputerTerminatedException {
        IntcodeComputer intcodeComputer = IntcodeComputer.newInstance(codes);
        intcodeComputer.addInput(input);
        List<Long> output = new ArrayList<>();
        while (!intcodeComputer.isTerminated()) {
            output.add(intcodeComputer.execute());
        }
        return output;
    }
}
