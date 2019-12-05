package eu.stiekema.jeroen.adventofcode2019.day2;

import java.util.ArrayList;
import java.util.List;

public class Memory {

    private final List<Integer> addresses = new ArrayList<>();

    private int pointer = 0;

    Memory(List<Integer> addresses) {
        this.addresses.addAll(addresses);
    }

    int next() {
        return addresses.get(pointer++);
    }

    void write(int index, int value) {
        addresses.remove(index);
        addresses.add(index, value);
    }

    int getPointer() {
        return pointer;
    }

    int get(int index) {
        return addresses.get(index);
    }

    @Override
    public String toString() {
        return addresses.toString();
    }
}
