package eu.stiekema.jeroen.adventofcode2019.day5;

import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntcodeComputer;

import java.io.IOException;
import java.util.List;

public class Main {

    public static final int AIRCONDITIONER_UNIT_ID = 1;
    public static final int THERMAL_RADIATOR_ID = 5;

    public static void main(String[] args) throws IOException {
        List<Integer> codes = FileParseUtil.getCodes("day5.txt");

        IntcodeComputer intcodeComputer = new IntcodeComputer();

        System.out.println("Solution day 5 part 1: " + intcodeComputer.interpretAndReturnDiagnosticCode(codes, AIRCONDITIONER_UNIT_ID));

        System.out.println("Solution day 5 part 2: " + intcodeComputer.interpretAndReturnDiagnosticCode(codes, THERMAL_RADIATOR_ID));

    }
}
