package eu.stiekema.jeroen.adventofcode2019.intcode.expression;

import eu.stiekema.jeroen.adventofcode2019.intcode.Context;

public interface IntcodeExpressionFactory {

    Expression createExpression(String opcode, Context context);
}
