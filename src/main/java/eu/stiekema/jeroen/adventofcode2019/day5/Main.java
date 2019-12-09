package eu.stiekema.jeroen.adventofcode2019.day5;

import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntcodeComputer;

import java.io.IOException;
import java.util.List;

public class Main {

    public static final int AIRCONDITIONER_UNIT_ID = 1;
    public static final int THERMAL_RADIATOR_ID = 5;

    public static void main(String[] args) throws IOException {
        List<Long> codes = FileParseUtil.getCodes("day5.txt", ",");

        IntcodeComputer intcodeComputer = IntcodeComputer.newInstance(codes);
        intcodeComputer.addInput(AIRCONDITIONER_UNIT_ID);
        System.out.println("Solution day 5 part 1: " + intcodeComputer.executeDiagnostic());


        intcodeComputer = IntcodeComputer.newInstance(codes);
        intcodeComputer.addInput(THERMAL_RADIATOR_ID);
        System.out.println("Solution day 5 part 2: " + intcodeComputer.executeDiagnostic());
    }
}
