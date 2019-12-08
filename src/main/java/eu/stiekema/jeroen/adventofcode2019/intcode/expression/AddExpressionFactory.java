package eu.stiekema.jeroen.adventofcode2019.intcode.expression;

import eu.stiekema.jeroen.adventofcode2019.intcode.Context;
import eu.stiekema.jeroen.adventofcode2019.intcode.OpCodeInstruction;

public class AddExpressionFactory implements IntcodeExpressionFactory {

    @Override
    public Expression createExpressionForDiagnoseMode(OpCodeInstruction opCodeInstruction, Context context) {
        return Expression.onValidDiagnoseCode(
                createExpression(opCodeInstruction, context)
        );
    }

    @Override
    public Expression createExpression(OpCodeInstruction opCodeInstruction, Context context) {
        Expression argument1 = opCodeInstruction.getParameterExpression(0, context.getMemory().next());
        Expression argument2 = opCodeInstruction.getParameterExpression(1, context.getMemory().next());

        return Expression.write(
                Expression.plus(argument1, argument2),
                context.getMemory().next()
        );
    }
}
