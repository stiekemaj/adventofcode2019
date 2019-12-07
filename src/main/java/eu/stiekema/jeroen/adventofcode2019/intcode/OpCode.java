package eu.stiekema.jeroen.adventofcode2019.intcode;

import eu.stiekema.jeroen.adventofcode2019.intcode.expression.*;

enum OpCode {
    ADD("01", new AddExpressionFactory()),
    MULTIPLY("02", new MultiplyExpressionFactory()),
    INPUT("03", new InputExpressionFactory()),
    OUTPUT("04", new OutputExpressionFactory()),
    JUMP_IF_TRUE("05", new JumpIfTrueExpressionFactory()),
    JUMP_IF_FALSE("06", new JumpIfFalseExpressionFactory()),
    LESS_THAN("07", new LessThanExpressionFactory()),
    EQUALS("08", new EqualsExpressionFactory()),
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
