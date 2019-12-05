package eu.stiekema.jeroen.adventofcode2019.day2;

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

        System.out.println("Result of first question: " + interpretAndReturnIndex0(codes, 12, 2));
        System.out.println("Result of second question: " + findProductOfNounAndVerbForResult(codes, 19690720));
    }

    private static int findProductOfNounAndVerbForResult(List<Integer> codes, int result) {
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                if (interpretAndReturnIndex0(codes, noun, verb) == result) {
                    return 100 * noun + verb;
                }
            }
        }

        throw new RuntimeException("no noun and verb found for result");
    }

    private static int interpretAndReturnIndex0(List<Integer> codes, int noun, int verb) {
        Memory memory = new Memory(codes);
        writeNoun(memory, noun);
        writeVerb(memory, verb);
        Context context = new Context(memory);

        while (!context.isTerminated()) {
            IntCodeExpressionFactory.readIntCodeExpression(context).interpret(context);
        }

        return context.getMemory().get(0);
    }

    private static void writeNoun(Memory memory, int value) {
        memory.write(1, value);
    }

    private static void writeVerb(Memory memory, int value) {
        memory.write(2, value);
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
