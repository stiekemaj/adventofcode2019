package eu.stiekema.jeroen.adventofcode2019.common;

import java.math.BigInteger;
import java.util.Arrays;

public final class MathUtil {
    private MathUtil() {}

    public static BigInteger lcm(BigInteger number1, BigInteger number2) {
        BigInteger gcd = number1.gcd(number2);
        BigInteger absProduct = number1.multiply(number2).abs();
        return absProduct.divide(gcd);
    }

    public static BigInteger lcm(BigInteger ... numbers) {
        return Arrays.stream(numbers)
                .reduce(BigInteger.valueOf(1L), MathUtil::lcm);
    }
}
