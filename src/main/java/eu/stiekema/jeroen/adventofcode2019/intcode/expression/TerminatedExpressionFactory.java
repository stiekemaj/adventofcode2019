package eu.stiekema.jeroen.adventofcode2019.intcode.expression;

import eu.stiekema.jeroen.adventofcode2019.intcode.Context;
import eu.stiekema.jeroen.adventofcode2019.intcode.OpCodeInstruction;

public class TerminatedExpressionFactory implements IntcodeExpressionFactory {
    @Override
    public Expression createExpression(OpCodeInstruction opCodeInstruction, Context context) {
        return Expression.terminate();
    }
}
