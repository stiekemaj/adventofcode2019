package eu.stiekema.jeroen.adventofcode2019.intcode;

import java.util.Queue;

public class Context {
    private final Memory memory;
    private boolean terminate;
    private final Queue<Integer> input;
    private int output = 0;

    public Context(Memory memory, Queue<Integer> input) {
        this.memory = memory;
        this.input = input;
    }

    public Memory getMemory() {
        return memory;
    }

    public boolean isTerminate() {
        return terminate;
    }

    public int getInput() {
        if (input.size() == 0) {
            throw new IllegalStateException("No more input values available");
        }
        return input.remove();
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public void setTerminate(boolean terminate) {
        this.terminate = terminate;
    }
}
