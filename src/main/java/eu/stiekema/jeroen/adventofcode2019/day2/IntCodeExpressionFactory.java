package eu.stiekema.jeroen.adventofcode2019.day2;

class IntCodeExpressionFactory {

    private static final int ADD = 1;
    private static final int MULTIPLY = 2;
    private static final int TERMINATED = 99;

    static Expression readIntCodeExpression(Context context) {
        Memory memory = context.getMemory();
        if (memory.getPointer() % 4 != 0) {
            throw new IllegalStateException("Illegal memory pointer for retrieving expression");
        }

        int opcode = memory.next();

        if (opcode == TERMINATED) {
            return Expression.terminate();
        }

        Expression argument1 = Expression.reference(Expression.number(memory.next()));
        Expression argument2 = Expression.reference(Expression.number(memory.next()));

        Expression calculate;

        switch (opcode) {
            case ADD:
                calculate = Expression.plus(argument1, argument2);
                break;
            case MULTIPLY:
                calculate = Expression.multiply(argument1, argument2);
                break;
            default:
                throw new IllegalArgumentException("Illegal opcode: " + opcode);
        }

        return Expression.write(calculate, memory.next());
    }
}
