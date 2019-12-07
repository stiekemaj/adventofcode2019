package eu.stiekema.jeroen.adventofcode2019.day5;

import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;
import eu.stiekema.jeroen.adventofcode2019.intcode.IntcodeComputer;

import java.io.IOException;
import java.util.List;

public class Main {

    public static final int AIRCONDITIONER_UNIT_ID = 1;

    public static void main(String[] args) throws IOException {
        List<Integer> codes = FileParseUtil.getCodes("day5.txt");

        IntcodeComputer intcodeComputer = new IntcodeComputer();

        intcodeComputer.interpretAndReturnDiagnosticCode(codes, AIRCONDITIONER_UNIT_ID);

    }
}
