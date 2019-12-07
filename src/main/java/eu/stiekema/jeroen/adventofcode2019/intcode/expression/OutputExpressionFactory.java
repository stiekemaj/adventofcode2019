package eu.stiekema.jeroen.adventofcode2019.intcode.expression;

import eu.stiekema.jeroen.adventofcode2019.intcode.Context;
import eu.stiekema.jeroen.adventofcode2019.intcode.OpCodeInstruction;

public class OutputExpressionFactory implements IntcodeExpressionFactory {
    @Override
    public Expression createExpression(OpCodeInstruction opCodeInstruction, Context context) {
        Expression parameterExpression = opCodeInstruction.getParameterExpression(0, context.getMemory().next());
        return Expression.onValidDiagnoseCode(Expression.output(parameterExpression));
    }
}
