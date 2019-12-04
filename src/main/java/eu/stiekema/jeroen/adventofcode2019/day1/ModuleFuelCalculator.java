package eu.stiekema.jeroen.adventofcode2019.day1;

class ModuleFuelCalculator {
    int simpleCalculate(int mass) {
        return calculate(mass, false);
    }

    int calculate(int mass) {
        return calculate(mass, true);
    }

    private int calculate(int mass, boolean includeFuelMass) {
        int fuel = (mass / 3) - 2;

        if (fuel <= 0) {
            return 0;
        }

        if (!includeFuelMass) {
            return fuel;
        }

        return fuel + calculate(fuel, true);
    }
}
