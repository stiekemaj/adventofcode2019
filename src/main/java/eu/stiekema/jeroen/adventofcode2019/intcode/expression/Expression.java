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
