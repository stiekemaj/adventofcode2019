package eu.stiekema.jeroen.adventofcode2019.intcode;

public class Context {
    private final Memory memory;
    private boolean terminate;
    private final int input;
    private int output = 0;

    public Context(Memory memory, int input) {
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
        return input;
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
