package eu.stiekema.jeroen.adventofcode2019.intcode;

import eu.stiekema.jeroen.adventofcode2019.common.StringParseUtil;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class IntcodeComputerTest {

    @Test
    public void interpretAndReturnDiagnosticCode() {
        String program = "3,3,1107,-1,8,3,4,3,99";

        assertThat(executeWithInput(program, 1)).isEqualTo(1);
        assertThat(executeWithInput(program, 2)).isEqualTo(1);
        assertThat(executeWithInput(program, 3)).isEqualTo(1);
        assertThat(executeWithInput(program, 4)).isEqualTo(1);
        assertThat(executeWithInput(program, 5)).isEqualTo(1);
        assertThat(executeWithInput(program, 6)).isEqualTo(1);
        assertThat(executeWithInput(program, 7)).isEqualTo(1);
        assertThat(executeWithInput(program, 8)).isEqualTo(0);

    }

    private int executeWithInput(String program, int input) {
        IntcodeComputer intcodeComputer = IntcodeComputer.newInstance(StringParseUtil.getCodes(program, ","));
        intcodeComputer.addInput(input);
        return intcodeComputer.executeDiagnostic();
    }
}