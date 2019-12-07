package eu.stiekema.jeroen.adventofcode2019.intcode.expression;

import eu.stiekema.jeroen.adventofcode2019.intcode.Context;

public class InputExpressionFactory implements IntcodeExpressionFactory {
    @Override
    public Expression createExpression(String opcode, Context context) {
        return Expression.write(Expression.number(context.getInput()), context.getMemory().next());
    }
}
