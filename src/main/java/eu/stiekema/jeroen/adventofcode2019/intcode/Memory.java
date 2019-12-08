package eu.stiekema.jeroen.adventofcode2019.intcode;

import java.util.ArrayList;
import java.util.List;

public class Memory {

    private final List<Integer> addresses = new ArrayList<>();

    private int pointer = 0;

    Memory(List<Integer> addresses) {
        this.addresses.addAll(addresses);
    }

    public int next() {
        return addresses.get(pointer++);
    }

    public int peek() {
        return addresses.get(pointer);
    }

    public void write(int index, int value) {
        addresses.remove(index);
        addresses.add(index, value);
    }

    int getPointer() {
        return pointer;
    }

    public int get(int index) {
        return addresses.get(index);
    }

    @Override
    public String toString() {
        return addresses.toString();
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }
}
