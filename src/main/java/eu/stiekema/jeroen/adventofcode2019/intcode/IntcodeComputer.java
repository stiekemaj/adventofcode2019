package eu.stiekema.jeroen.adventofcode2019.intcode;

import eu.stiekema.jeroen.adventofcode2019.intcode.expression.AddExpressionFactory;
import eu.stiekema.jeroen.adventofcode2019.intcode.expression.IntcodeExpressionFactory;
import eu.stiekema.jeroen.adventofcode2019.intcode.expression.MultiplyExpressionFactory;
import eu.stiekema.jeroen.adventofcode2019.intcode.expression.TerminatedExpressionFactory;

import java.util.List;

public class IntcodeComputer {

    private enum OpCode {
        ADD("1", new AddExpressionFactory()),
        MULTIPLY("2", new MultiplyExpressionFactory()),
        TERMINATED("99", new TerminatedExpressionFactory());

        private String code;
        private IntcodeExpressionFactory intcodeExpressionFactory;

        OpCode(String code, IntcodeExpressionFactory intcodeExpressionFactory) {
            this.code = code;
            this.intcodeExpressionFactory = intcodeExpressionFactory;
        }

        public static OpCode findByCode(String code) {
            for (OpCode opCode : values()) {
                if (code.endsWith(opCode.code)) {
                    return opCode;
                }
            }
            throw new IllegalArgumentException("unknown code: " + code);
        }

        public IntcodeExpressionFactory getIntcodeExpressionFactory() {
            return intcodeExpressionFactory;
        }
    }

    public int interpretAndReturnIndex0(List<Integer> codes) {
        Memory memory = new Memory(codes);
        Context context = new Context(memory);

        while (!context.isTerminated()) {
            String opCodeString = Integer.toString(memory.next());
            OpCode opCode = OpCode.findByCode(opCodeString);
            opCode.getIntcodeExpressionFactory()
                    .createExpression(opCodeString, context)
                    .interpret(context);
        }

        return context.getMemory().get(0);
    }
}
