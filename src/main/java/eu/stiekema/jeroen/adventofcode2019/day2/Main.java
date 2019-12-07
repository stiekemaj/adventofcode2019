package eu.stiekema.jeroen.adventofcode2019.day2;

import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntcodeComputer;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Integer> codes = FileParseUtil.getCodes("day2.txt");

        writeNoun(codes, 12);
        writeVerb(codes, 2);
        IntcodeComputer intcodeComputer = new IntcodeComputer();
        System.out.println("Result of first question: " + intcodeComputer.interpretAndReturnIndex0(codes, 0));
        System.out.println("Result of second question: " + findProductOfNounAndVerbForResult(codes, 19690720));
    }

    private static int findProductOfNounAndVerbForResult(List<Integer> codes, int result) {
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                writeNoun(codes, noun);
                writeVerb(codes, verb);
                IntcodeComputer intcodeComputer = new IntcodeComputer();
                if (intcodeComputer.interpretAndReturnIndex0(codes, 0) == result) {
                    return 100 * noun + verb;
                }
            }
        }

        throw new RuntimeException("no noun and verb found for result");
    }

    private static void writeNoun(List<Integer> codes, int value) {
        codes.remove(1);
        codes.add(1, value);
    }

    private static void writeVerb(List<Integer> codes, int value) {
        codes.remove(2);
        codes.add(2, value);
    }
}
