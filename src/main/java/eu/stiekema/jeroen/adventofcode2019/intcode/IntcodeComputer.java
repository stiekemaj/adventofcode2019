package eu.stiekema.jeroen.adventofcode2019.intcode;

import eu.stiekema.jeroen.adventofcode2019.intcode.expression.Expression;
import eu.stiekema.jeroen.adventofcode2019.intcode.expression.IntcodeExpressionFactory;

import java.util.*;

public class IntcodeComputer {

    private Context context;

    private IntcodeComputer(List<Long> codes, Queue<Long> input) {
        this.context = new Context(new Memory(codes), input);
    }

    public static IntcodeComputer newInstance(List<Long> codes) {
        return new IntcodeComputer(codes, new LinkedList<>());
    }

    public void addInput(long input) {
        this.context.addInput(input);
    }

    public void clearInput() {
        this.context.clearInput();
    }

    public void setAddressValue(long address, long value) {
        context.getMemory().write(address, value);
    }

    /**
     * Execute code until output and terminate opcode has been processed subsequently. The output will be returned.
     * If an output opcode will be followed by any opcode other than terminate, a DiagnosticFailureException will
     * be thrown if the output is other than 0.
     * @return
     */
    public long executeDiagnostic() {
        while (!context.isTerminated()) {
            OpCodeInstruction opCodeInstruction = OpCodeInstruction.ofCode(this.context.getMemory().next());
            IntcodeExpressionFactory factory = opCodeInstruction.getOpCode().getIntcodeExpressionFactory();
            Expression expression = factory.createExpressionForDiagnoseMode(opCodeInstruction, context);
            expression.interpret(context);
        }
        return context.getOutput();
    }
    
    public long getIndex0Value() {
        return context.getMemory().get(0);
    }
    
    public boolean isTerminated() {
        return this.context.isTerminated();
    }

    public long execute() throws IntCodeComputerTerminatedException {
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
