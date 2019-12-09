package eu.stiekema.jeroen.adventofcode2019.intcode;

import java.util.ArrayList;
import java.util.List;

public class Memory {

    private final List<Long> addresses = new ArrayList<>();

    private int pointer = 0;

    Memory(List<Long> addresses) {
        this.addresses.addAll(addresses);
    }

    public long next() {
        return addresses.get(pointer++);
    }

    public long peek() {
        return addresses.get(pointer);
    }

    public void write(long index, long value) {
        if (index > Integer.MAX_VALUE) {
            throw new RuntimeException("does not support arrays bigger than " + Integer.MAX_VALUE + ", but was " + index);
        }

        if (index >= addresses.size()) {
            for (int i = addresses.size(); i <= index; i++) {
                addresses.add(i, 0L);
            }
        }

        addresses.remove((int) index);
        addresses.add((int) index, value);
    }

    int getPointer() {
        return pointer;
    }

    public long get(long index) {
        if (index > Integer.MAX_VALUE) {
            throw new RuntimeException("does not support arrays bigger than " + index);
        }

        if (index >= addresses.size()) {
            return 0;
        }

        return addresses.get((int) index);
    }

    @Override
    public String toString() {
        return addresses.toString();
    }

    public void setPointer(long pointer) {
        if (pointer > Integer.MAX_VALUE) {
            throw new RuntimeException("does not support arrays bigger than " + pointer);
        }
        this.pointer = (int) pointer;
    }
}
