package eu.stiekema.jeroen.adventofcode2019.intcode;

public interface IntcodeComputer {
    IntcodeComputer copy();

    void addInput(long input);

    long execute() throws IntCodeComputerTerminatedException;
}
