package eu.stiekema.jeroen.adventofcode2019.day7;

import com.google.common.collect.Collections2;
import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntCodeComputerTerminatedException;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntcodeComputer;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Long> codes = FileParseUtil.getCodes("day7.txt", ",");
        Collection<List<Integer>> phaseSettingsCombinations = Collections2.permutations(List.of(0, 1, 2, 3, 4));
        System.out.println("Answer part 1: " + getHighestOutput(codes, phaseSettingsCombinations, false));

        phaseSettingsCombinations = Collections2.permutations(List.of(5, 6, 7, 8, 9));
        System.out.println("Answer part 2: " + getHighestOutput(codes, phaseSettingsCombinations, true));


    }

    private static long getHighestOutput(List<Long> codes, Collection<List<Integer>> phaseSettingsCombinations, boolean feedbackMode) {
        long highestOutput = 0;
        for (List<Integer> phaseSettingCombination : phaseSettingsCombinations) {
            List<IntcodeComputer> intcodeComputers = phaseSettingCombination.stream()
                    .map(phase -> {
                        IntcodeComputer intcodeComputer = IntcodeComputer.newInstance(codes);
                        intcodeComputer.addInput(phase);
                        return intcodeComputer;
                    })
                    .collect(Collectors.toList());

            try {
                long previousOutput = 0;
                do {
                    for (IntcodeComputer intcodeComputer : intcodeComputers) {
                        intcodeComputer.addInput(previousOutput);
                        previousOutput = intcodeComputer.execute();
                    }
                    if (previousOutput > highestOutput) {
                        highestOutput = previousOutput;
                    }
                } while (feedbackMode && !hasTerminatedComputer(intcodeComputers));
            } catch (IntCodeComputerTerminatedException e) {
            }
        }
        return highestOutput;
    }

    private static boolean hasTerminatedComputer(List<IntcodeComputer> intcodeComputers) {
        return intcodeComputers.stream().map(IntcodeComputer::isTerminated).reduce(false, (a, b) -> a || b);
    }
}
