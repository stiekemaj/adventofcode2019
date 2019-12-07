package eu.stiekema.jeroen.adventofcode2019.intcode;

import eu.stiekema.jeroen.adventofcode2019.intcode.expression.Expression;
import eu.stiekema.jeroen.adventofcode2019.intcode.expression.IntcodeExpressionFactory;

import java.util.List;

public class IntcodeComputer {

    public int interpretAndReturnIndex0(List<Integer> codes, int input) {
        Context context = interpret(codes, input);
        return context.getMemory().get(0);
    }

    public int interpretAndReturnDiagnosticCode(List<Integer> codes, int input) {
        Context context = interpret(codes, input);
        return context.getOutput();
    }

    private Context interpret(List<Integer> codes, int input) {
        Memory memory = new Memory(codes);
        Context context = new Context(memory, input);

        while (!context.isTerminate()) {
            OpCodeInstruction opCodeInstruction = OpCodeInstruction.ofCode(memory.next());
            IntcodeExpressionFactory factory = opCodeInstruction.getOpCode().getIntcodeExpressionFactory();
            Expression expression = factory.createExpression(opCodeInstruction, context);
            expression.interpret(context);
        }
        return context;
    }
}
