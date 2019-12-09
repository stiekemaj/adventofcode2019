package eu.stiekema.jeroen.adventofcode2019.intcode.expression;

import eu.stiekema.jeroen.adventofcode2019.intcode.Context;
import eu.stiekema.jeroen.adventofcode2019.intcode.DiagnosticFailureException;

public interface Expression {
    long interpret(Context context);

    static Expression onValidDiagnoseCode(Expression expression) {
        return context -> {
            if (context.getOutput() != 0) {
                throw new DiagnosticFailureException();
            }
            return expression.interpret(context);
        };
    }

    static Expression value(long number) {
        return context -> number;
    }

    static Expression valueByReference(long reference) {
        return context -> context.getMemory().get(reference);
    }

    static Expression valueByRelative(long relative) {
        return context -> context.getMemory().get(relative + context.getRelativeBase());
    }

    static Expression plus(Expression left, Expression right) {
        return context -> left.interpret(context) + right.interpret(context);
    }

    static Expression multiply(Expression left, Expression right) {
        return context -> left.interpret(context) * right.interpret(context);
    }

    static Expression write(Expression expression, Expression address) {
        return context -> {
            long value = expression.interpret(context);
            context.getMemory().write(address.interpret(context), value);
            return value;
        };
    }

    static Expression increaseRelativeBase(Expression expression) {
        return context -> {
            context.setRelativeBase(context.getRelativeBase() + expression.interpret(context));
            return 0;
        };
    }

    static Expression output(Expression expression) {
        return context -> {
            long value = expression.interpret(context);
            context.setOutput(value);
            return value;
        };
    }

    static Expression ifNonZeroThenElse(Expression conditionExpression, Expression resultExpression, Expression elseExpression) {
        return context -> {
            if (conditionExpression.interpret(context) != 0) {
                return resultExpression.interpret(context);
            } else {
                return elseExpression.interpret(context);
            }
        };
    }

    static Expression setPointer(Expression expression) {
        return context -> {
            context.getMemory().setPointer(expression.interpret(context));
            return 0;
        };
    }

    static Expression noOp() {
        return context -> 0;
    }

    static Expression terminate() {
        return context -> {
            context.setTerminate(true);
            return 0;
        };
    }
}
