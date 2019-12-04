package eu.stiekema.jeroen.adventofcode2019.day1;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ModuleFuelCalculatorTest {

    ModuleFuelCalculator calculator = new ModuleFuelCalculator();

    @Test
    public void calculate_shouldReturn0WhenMassIs1() {
        assertThat(calculator.calculate(1)).isEqualTo(0);
    }

    @Test
    public void calculate_shouldReturn0WhenMassIs8() {
        assertThat(calculator.calculate(8)).isEqualTo(0);
    }

    @Test
    public void calculate_shouldReturn1WhenMassIs9() {
        assertThat(calculator.calculate(9)).isEqualTo(1);
    }

    @Test
    public void calculate_shouldReturn10WhenMassIs33() {
        assertThat(calculator.calculate(33)).isEqualTo(10);
    }
}
