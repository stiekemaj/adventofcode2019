package eu.stiekema.jeroen.adventofcode2019.day7;

import com.google.common.collect.Collections2;
import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntcodeComputer;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Integer> codes = FileParseUtil.getCodes("day7.txt");
        Collection<List<Integer>> phaseSettingsCombinations = Collections2.permutations(List.of(0, 1, 2, 3, 4));
        IntcodeComputer intcodeComputer = new IntcodeComputer();

        int highestOutput = 0;
        for (List<Integer> phaseSettingCombination : phaseSettingsCombinations) {
            int previousOutput = 0;
            for (int phaseSetting :  phaseSettingCombination) {
                Queue<Integer> input = new LinkedList<>(Arrays.asList(phaseSetting, previousOutput));
                previousOutput = intcodeComputer.interpretAndReturnDiagnosticCode(codes, input);
            }
            if (previousOutput > highestOutput) {
                highestOutput = previousOutput;
            }
        }

        System.out.println("Answer part 1: " + highestOutput);
    }
}
