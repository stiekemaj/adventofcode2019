package eu.stiekema.jeroen.adventofcode2019.intcode.expression;

import eu.stiekema.jeroen.adventofcode2019.intcode.Context;
import eu.stiekema.jeroen.adventofcode2019.intcode.OpCodeInstruction;

public class JumpIfFalseExpressionFactory implements IntcodeExpressionFactory {

    @Override
    public Expression createExpressionForDiagnoseMode(OpCodeInstruction opCodeInstruction, Context context) {
        return Expression.onValidDiagnoseCode(
                createExpression(opCodeInstruction, context)
        );
    }

    @Override
    public Expression createExpression(OpCodeInstruction opCodeInstruction, Context context) {
        Expression firstParamExpression = opCodeInstruction.getParameterExpression(0, context.getMemory().next());
        Expression secondParamExpression = opCodeInstruction.getParameterExpression(1, context.getMemory().next());

        return Expression.ifNonZeroThenElse(
                firstParamExpression,
                Expression.noOp(),
                Expression.setPointer(secondParamExpression)
        );
    }
}
