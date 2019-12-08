package eu.stiekema.jeroen.adventofcode2019.intcode;

import eu.stiekema.jeroen.adventofcode2019.intcode.expression.Expression;
import eu.stiekema.jeroen.adventofcode2019.intcode.expression.IntcodeExpressionFactory;

import java.util.*;

public class IntcodeComputer {

    private Context context;

    private IntcodeComputer(List<Integer> codes, Queue<Integer> input) {
        this.context = new Context(new Memory(codes), input);
    }

    public static IntcodeComputer newInstance(List<Integer> codes) {
        return new IntcodeComputer(codes, new LinkedList<>());
    }

    public void addInput(int input) {
        this.context.addInput(input);
    }

    /**
     * Execute code until output and terminate opcode has been processed subsequently. The output will be returned.
     * If an output opcode will be followed by any opcode other than terminate, a DiagnosticFailureException will
     * be thrown if the output is other than 0.
     * @return
     */
    public int executeDiagnostic() {
        while (!context.isTerminated()) {
            OpCodeInstruction opCodeInstruction = OpCodeInstruction.ofCode(this.context.getMemory().next());
            IntcodeExpressionFactory factory = opCodeInstruction.getOpCode().getIntcodeExpressionFactory();
            Expression expression = factory.createExpressionForDiagnoseMode(opCodeInstruction, context);
            expression.interpret(context);
        }
        return context.getOutput();
    }
    
    public int getIndex0Value() {
        return context.getMemory().get(0);
    }
    
    public boolean isTerminated() {
        return this.context.isTerminated();
    }

    public int execute() throws IntCodeComputerTerminatedException {
        while (!context.isTerminated()) {
            OpCodeInstruction opCodeInstruction = OpCodeInstruction.ofCode(this.context.getMemory().next());
            IntcodeExpressionFactory factory = opCodeInstruction.getOpCode().getIntcodeExpressionFactory();
            Expression expression = factory.createExpression(opCodeInstruction, context);
            expression.interpret(context);
            if (opCodeInstruction.getOpCode() == OpCode.OUTPUT) {
                // check if next code is exit code
                if (OpCodeInstruction.ofCode(this.context.getMemory().peek()).getOpCode() == OpCode.TERMINATED) {
                    context.setTerminate(true);
                }
                return context.getOutput();
            }
        }
        throw new IntCodeComputerTerminatedException();
    }

}
