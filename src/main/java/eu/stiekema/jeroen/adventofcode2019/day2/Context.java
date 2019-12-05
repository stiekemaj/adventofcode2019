package eu.stiekema.jeroen.adventofcode2019.day2;

class Context {
    private final Memory memory;
    private boolean terminated;

    Context(Memory memory) {
        this.memory = memory;
    }

    Memory getMemory() {
        return memory;
    }

    boolean isTerminated() {
        return terminated;
    }

    void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }
}
