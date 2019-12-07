package eu.stiekema.jeroen.adventofcode2019.intcode.expression;

import eu.stiekema.jeroen.adventofcode2019.intcode.Context;
import eu.stiekema.jeroen.adventofcode2019.intcode.OpCodeInstruction;

public class InputExpressionFactory implements IntcodeExpressionFactory {
    @Override
    public Expression createExpression(OpCodeInstruction opCodeInstruction, Context context) {
        return Expression.onValidDiagnoseCode(
                Expression.write(Expression.value(context.getInput()), context.getMemory().next())
        );
    }
}
