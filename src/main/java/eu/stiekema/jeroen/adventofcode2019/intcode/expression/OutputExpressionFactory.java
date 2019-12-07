package eu.stiekema.jeroen.adventofcode2019.intcode.expression;

import eu.stiekema.jeroen.adventofcode2019.intcode.Context;

public class OutputExpressionFactory implements IntcodeExpressionFactory {
    @Override
    public Expression createExpression(String opcode, Context context) {
        int parameterValue = context.getMemory().next();
        return Expression.output(Expression.read(parameterValue));
    }
}
