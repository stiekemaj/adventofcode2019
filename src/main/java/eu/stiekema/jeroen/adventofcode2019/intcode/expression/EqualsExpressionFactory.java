package eu.stiekema.jeroen.adventofcode2019.intcode.expression;

import eu.stiekema.jeroen.adventofcode2019.intcode.Context;
import eu.stiekema.jeroen.adventofcode2019.intcode.OpCodeInstruction;

public class EqualsExpressionFactory implements IntcodeExpressionFactory {

    @Override
    public Expression createExpressionForDiagnoseMode(OpCodeInstruction opCodeInstruction, Context context) {
        return Expression.onValidDiagnoseCode(
                createExpression(opCodeInstruction, context)
        );
    }

    @Override
    public Expression createExpression(OpCodeInstruction opCodeInstruction, Context context) {
        Expression firstParameterExpression = opCodeInstruction.getParameterExpression(0, context.getMemory().next());
        Expression secondParameterExpression = opCodeInstruction.getParameterExpression(1, context.getMemory().next());
        Expression writePointer = opCodeInstruction.getWriteParameterExpression(2, context.getMemory().next());

        return ctx -> {
            if (firstParameterExpression.interpret(ctx) == secondParameterExpression.interpret(ctx)) {
                Expression.write(Expression.value(1), writePointer).interpret(ctx);
            } else {
                Expression.write(Expression.value(0), writePointer).interpret(ctx);
            }
            return 0;
        };
    }
}
