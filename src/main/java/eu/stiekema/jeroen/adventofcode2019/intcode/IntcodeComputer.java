package eu.stiekema.jeroen.adventofcode2019.intcode;

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
            if (context.getOutput() != 0) {
                throw new DiagnosticFailureException();
            }
            String opCodeString = Integer.toString(memory.next());
            OpCode opCode = OpCode.findByCode(opCodeString);
            opCode.getIntcodeExpressionFactory()
                    .createExpression(opCodeString, context)
                    .interpret(context);
        }
        return context;
    }
}
