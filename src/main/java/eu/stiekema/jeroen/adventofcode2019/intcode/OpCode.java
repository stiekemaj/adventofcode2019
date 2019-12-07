package eu.stiekema.jeroen.adventofcode2019.intcode;

import eu.stiekema.jeroen.adventofcode2019.intcode.expression.*;

enum OpCode {
    ADD("1", new AddExpressionFactory()),
    MULTIPLY("2", new MultiplyExpressionFactory()),
    INPUT("3", new InputExpressionFactory()),
    OUTPUT("4", new OutputExpressionFactory()),
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
