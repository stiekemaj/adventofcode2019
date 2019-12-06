package eu.stiekema.jeroen.adventofcode2019.intcode;

public class Context {
    private final Memory memory;
    private boolean terminated;

    public Context(Memory memory) {
        this.memory = memory;
    }

    public Memory getMemory() {
        return memory;
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }
}
