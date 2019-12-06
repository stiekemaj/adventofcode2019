package eu.stiekema.jeroen.adventofcode2019.day2;

import eu.stiekema.jeroen.adventofcode2019.intcode.IntcodeComputer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = eu.stiekema.jeroen.adventofcode2019.day1.Main.class.getClassLoader().getResourceAsStream("day2.txt");
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, "UTF-8");
        List<Integer> codes = getCodes(writer.toString());

        writeNoun(codes, 12);
        writeVerb(codes, 2);
        IntcodeComputer intcodeComputer = new IntcodeComputer();
        System.out.println("Result of first question: " + intcodeComputer.interpretAndReturnIndex0(codes));
        System.out.println("Result of second question: " + findProductOfNounAndVerbForResult(codes, 19690720));
    }

    private static int findProductOfNounAndVerbForResult(List<Integer> codes, int result) {
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                writeNoun(codes, noun);
                writeVerb(codes, verb);
                IntcodeComputer intcodeComputer = new IntcodeComputer();
                if (intcodeComputer.interpretAndReturnIndex0(codes) == result) {
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

    private static List<Integer> getCodes(String line) {
        StringTokenizer st = new StringTokenizer(line, ",");
        List<Integer> codes = new ArrayList<>();
        while (st.hasMoreTokens()) {
            codes.add(Integer.valueOf(st.nextToken()));
        }
        return codes;
    }
}
