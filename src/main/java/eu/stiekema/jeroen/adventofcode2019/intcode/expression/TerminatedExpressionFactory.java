package eu.stiekema.jeroen.adventofcode2019.intcode.expression;

import eu.stiekema.jeroen.adventofcode2019.intcode.Context;

public class TerminatedExpressionFactory implements IntcodeExpressionFactory {
    @Override
    public Expression createExpression(String opcode, Context context) {
        return Expression.terminate();
    }
}
