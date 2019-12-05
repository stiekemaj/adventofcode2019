package eu.stiekema.jeroen.adventofcode2019.day2;

public interface Expression {
    int interpret(Context context);

    static Expression number(int number) {
        return context -> number;
    }

    static Expression plus(Expression left, Expression right) {
        return context -> left.interpret(context) + right.interpret(context);
    }

    static Expression multiply(Expression left, Expression right) {
        return context -> left.interpret(context) * right.interpret(context);
    }

    static Expression reference(Expression expression) {
        return context -> context.getMemory().get(expression.interpret(context));
    }

    static Expression write(Expression expression, int address) {
        return context -> {
            int value = expression.interpret(context);
            context.getMemory().write(address, value);
            return value;
        };
    }

    static Expression terminate() {
        return context -> {
            context.setTerminated(true);
            return 0;
        };
    }
}
