package eu.stiekema.jeroen.adventofcode2019.intcode;

import eu.stiekema.jeroen.adventofcode2019.intcode.expression.Expression;

import java.util.ArrayList;
import java.util.List;

public class OpCodeInstruction {
    private OpCode opCode;
    private List<ParameterMode> parameterModes;

    private OpCodeInstruction(OpCode opCode, List<ParameterMode> parameterModes) {
        this.opCode = opCode;
        this.parameterModes = parameterModes;
    }

    static OpCodeInstruction ofCode(long code) {
        String fullCodeString = String.format("%05d", code);
        OpCode opCode = OpCode.findByCode(fullCodeString.substring(fullCodeString.length() - 2));

        List<ParameterMode> parameterModes = new ArrayList<>();
        String parameterCodes = fullCodeString.substring(0, fullCodeString.length() - 2);
        for (char parameterCode : parameterCodes.toCharArray()) {
            parameterModes.add(0, ParameterMode.byCode(String.valueOf(parameterCode)));
        }

        return new OpCodeInstruction(opCode, parameterModes);
    }

    public OpCode getOpCode() {
        return this.opCode;
    }

    public Expression getParameterExpression(int paramIndex, long parameterValue) {
        ParameterMode parameterMode = parameterModes.get(paramIndex);
        if (parameterMode == ParameterMode.IMMEDIATE) {
            return Expression.value(parameterValue);
        } else if (parameterMode == ParameterMode.POSITION) {
            return Expression.valueByReference(parameterValue);
        } else if (parameterMode == ParameterMode.RELATIVE) {
            return Expression.valueByRelative(parameterValue);
        } else {
            throw new IllegalArgumentException("unknown parameter mode: " + parameterMode);
        }
    }

    public Expression getWriteParameterExpression(int paramIndex, long parameterValue) {
        ParameterMode parameterMode = parameterModes.get(paramIndex);
        if (parameterMode == ParameterMode.IMMEDIATE) {
            throw new IllegalArgumentException("Mode of write parameter can't be IMMEDIATE");
        } else if (parameterMode == ParameterMode.POSITION) {
            return Expression.value(parameterValue);
        } else if (parameterMode == ParameterMode.RELATIVE) {
            return context -> parameterValue + context.getRelativeBase();
        } else {
            throw new IllegalArgumentException("unknown parameter mode: " + parameterMode);
        }
    }

}
