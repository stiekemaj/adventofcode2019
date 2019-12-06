package eu.stiekema.jeroen.adventofcode2019.intcode.expression;

import eu.stiekema.jeroen.adventofcode2019.intcode.Context;

public class AddExpressionFactory implements IntcodeExpressionFactory {
    @Override
    public Expression createExpression(String opcode, Context context) {
        Expression argument1 = Expression.reference(Expression.number(context.getMemory().next()));
        Expression argument2 = Expression.reference(Expression.number(context.getMemory().next()));

        return Expression.write(
                Expression.plus(argument1, argument2),
                context.getMemory().next()
        );
    }
}
