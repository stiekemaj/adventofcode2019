package eu.stiekema.jeroen.adventofcode2019.intcode;

import java.util.Queue;

public class Context {
    private final Memory memory;
    private boolean terminate;
    private final Queue<Long> input;
    private long output = 0L;
    private long relativeBase = 0;

    public Context(Memory memory, Queue<Long> input) {
        this.memory = memory;
        this.input = input;
    }

    public Memory getMemory() {
        return memory;
    }

    public boolean isTerminated() {
        return terminate;
    }

    public void addInput(long input) {
        this.input.add(input);
    }

    public long getRelativeBase() {
        return relativeBase;
    }

    public void setRelativeBase(long relativeBase) {
        this.relativeBase = relativeBase;
    }

    public long getInput() {
        if (input.size() == 0) {
            throw new IllegalStateException("No more input values available");
        }
        return input.remove();
    }

    public long getOutput() {
        return output;
    }

    public void setOutput(long output) {
        this.output = output;
    }

    public void setTerminate(boolean terminate) {
        this.terminate = terminate;
    }
}
