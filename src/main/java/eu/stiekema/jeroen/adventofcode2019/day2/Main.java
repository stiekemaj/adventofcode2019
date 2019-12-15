package eu.stiekema.jeroen.adventofcode2019.day2;

import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntcodeComputerImpl;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Long> codes = FileParseUtil.getCodes("day2.txt", ",");

        writeNoun(codes, 12);
        writeVerb(codes, 2);
        IntcodeComputerImpl intcodeComputer = IntcodeComputerImpl.newInstance(codes);
        intcodeComputer.addInput(0);
        intcodeComputer.executeDiagnostic();
        System.out.println("Result of first question: " + intcodeComputer.getIndex0Value());
        System.out.println("Result of second question: " + findProductOfNounAndVerbForResult(codes, 19690720));
    }

    private static int findProductOfNounAndVerbForResult(List<Long> codes, long result) {
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                writeNoun(codes, noun);
                writeVerb(codes, verb);
                IntcodeComputerImpl intcodeComputer = IntcodeComputerImpl.newInstance(codes);
                intcodeComputer.addInput(0);
                intcodeComputer.executeDiagnostic();
                if (intcodeComputer.getIndex0Value() == result) {
                    return 100 * noun + verb;
                }
            }
        }

        throw new RuntimeException("no noun and verb found for result");
    }

    private static void writeNoun(List<Long> codes, long value) {
        codes.remove(1);
        codes.add(1, value);
    }

    private static void writeVerb(List<Long> codes, long value) {
        codes.remove(2);
        codes.add(2, value);
    }
}
