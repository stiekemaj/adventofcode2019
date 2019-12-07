package eu.stiekema.jeroen.adventofcode2019.intcode.expression;

import eu.stiekema.jeroen.adventofcode2019.intcode.Context;
import eu.stiekema.jeroen.adventofcode2019.intcode.DiagnosticFailureException;

public interface Expression {
    int interpret(Context context);

    static Expression onValidDiagnoseCode(Expression expression) {
        return context -> {
            if (context.getOutput() != 0) {
                throw new DiagnosticFailureException();
            }
            return expression.interpret(context);
        };
    }

    static Expression value(int number) {
        return context -> number;
    }

    static Expression valueByReference(int reference) {
        return context -> context.getMemory().get(reference);
    }

    static Expression plus(Expression left, Expression right) {
        return context -> left.interpret(context) + right.interpret(context);
    }

    static Expression multiply(Expression left, Expression right) {
        return context -> left.interpret(context) * right.interpret(context);
    }

    static Expression write(Expression expression, int address) {
        return context -> {
            int value = expression.interpret(context);
            context.getMemory().write(address, value);
            return value;
        };
    }

    static Expression output(Expression expression) {
        return context -> {
            int value = expression.interpret(context);
            context.setOutput(value);
            return value;
        };
    }

    static Expression terminate() {
        return context -> {
            context.setTerminate(true);
            return 0;
        };
    }
}
