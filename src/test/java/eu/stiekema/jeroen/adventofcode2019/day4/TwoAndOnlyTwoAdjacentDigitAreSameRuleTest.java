package eu.stiekema.jeroen.adventofcode2019.day4;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TwoAndOnlyTwoAdjacentDigitAreSameRuleTest {
    private Main.TwoAndOnlyTwoAdjacentDigitAreSameRule rule = new Main.TwoAndOnlyTwoAdjacentDigitAreSameRule();

    @Test
    public void isValid_shouldReturnFalse() {
        assertThat(rule.isValid("123444")).isFalse();
    }

    @Test
    public void isValid_shouldReturnTrue() {
        assertThat(rule.isValid("112233")).isTrue();
        assertThat(rule.isValid("111122")).isTrue();
    }

}
