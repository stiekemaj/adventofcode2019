package eu.stiekema.jeroen.adventofcode2019.intcode;

import eu.stiekema.jeroen.adventofcode2019.common.StringParseUtil;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class IntcodeComputerTest {

    private IntcodeComputer intcodeComputer = new IntcodeComputer();

    @Test
    public void interpretAndReturnDiagnosticCode() {
        String program = "3,3,1107,-1,8,3,4,3,99";

        assertThat(intcodeComputer.interpretAndReturnDiagnosticCode(StringParseUtil.getCodes(program, ","), 1)).isEqualTo(1);
        assertThat(intcodeComputer.interpretAndReturnDiagnosticCode(StringParseUtil.getCodes(program, ","), 2)).isEqualTo(1);
        assertThat(intcodeComputer.interpretAndReturnDiagnosticCode(StringParseUtil.getCodes(program, ","), 3)).isEqualTo(1);
        assertThat(intcodeComputer.interpretAndReturnDiagnosticCode(StringParseUtil.getCodes(program, ","), 4)).isEqualTo(1);
        assertThat(intcodeComputer.interpretAndReturnDiagnosticCode(StringParseUtil.getCodes(program, ","), 5)).isEqualTo(1);
        assertThat(intcodeComputer.interpretAndReturnDiagnosticCode(StringParseUtil.getCodes(program, ","), 6)).isEqualTo(1);
        assertThat(intcodeComputer.interpretAndReturnDiagnosticCode(StringParseUtil.getCodes(program, ","), 7)).isEqualTo(1);
        assertThat(intcodeComputer.interpretAndReturnDiagnosticCode(StringParseUtil.getCodes(program, ","), 8)).isEqualTo(0);

    }
}