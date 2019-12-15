package eu.stiekema.jeroen.adventofcode2019.day14;

import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Day14 {


    public static void main(String[] args) {
        Map<String, Reaction> outputReactionMap = FileParseUtil.readLines("day14.txt")
                .stream()
                .map(Reaction::parse)
                .collect(Collectors.toMap(r -> r.outputChemicalQuantity.getName(), r -> r));

        ChemicalQuantity targetChemicalQuantity = new ChemicalQuantity(1, "FUEL");

        long nrOfOre = findNrOfOre(targetChemicalQuantity, new Context(outputReactionMap));
        System.out.println("Answer part 1: " + nrOfOre);


        // part 2

        long fuelIncrementor = 500_000L;
        long nrOfProducedFuel = 0L;
        long usedOre = 0L;
        long maxOreToUse = 1_000_000_000_000L;
        Context context = new Context(outputReactionMap);

        while (true) {
            long totalNrOfOre = findNrOfOre(new ChemicalQuantity(nrOfProducedFuel + fuelIncrementor, "FUEL"), context);
            if (totalNrOfOre > maxOreToUse) {
                break;
            }

            usedOre = totalNrOfOre;
            nrOfProducedFuel += fuelIncrementor;
            context = new Context(outputReactionMap);
        }

        while (true) {
            usedOre += findNrOfOre(new ChemicalQuantity(1L, "FUEL"), context);

            if (usedOre > maxOreToUse) {
                break;
            }

            nrOfProducedFuel++;
        }

        System.out.println("Answer part 2: " + nrOfProducedFuel);

    }

    private static long findNrOfOre(ChemicalQuantity targetChemicalQuantity, Context context) {
        if (targetChemicalQuantity.getQuantity() <= 0) {
            return 0;
        }

        if (targetChemicalQuantity.getName().equals("ORE")) {
            return targetChemicalQuantity.getQuantity();
        }

        Reaction reaction = context.getReactionForTargetChemical(targetChemicalQuantity);

        long nrOfOre = 0;

        long nrOfNeededReactions = (long) Math.ceil((double) targetChemicalQuantity.getQuantity() / (double) reaction.getOutputChemicalQuantity().getQuantity());

        List<ChemicalQuantity> inputChemicalQuantities = reaction.getInputChemicalQuantities()
                .stream()
                .map(t -> new ChemicalQuantity(t.quantity * nrOfNeededReactions, t.name))
                .collect(Collectors.toList());
        for (ChemicalQuantity inputChemicalQuantity : inputChemicalQuantities) {
            ChemicalQuantity chemicalQuantityLeftover = context.removeAndGetChemicalQuantityLeftover(inputChemicalQuantity);
            nrOfOre += findNrOfOre(inputChemicalQuantity.substract(chemicalQuantityLeftover), context);
        }

        long producedQuantity = nrOfNeededReactions * reaction.getOutputChemicalQuantity().getQuantity();
        context.addLeftover(targetChemicalQuantity.getName(), producedQuantity - targetChemicalQuantity.getQuantity());

        return nrOfOre;
    }


    static class Context {
        private final Map<String, Reaction> outputReactionMap;
        private final Map<String, Long> chemicalLeftovers = new HashMap<>();

        public Context(Map<String, Reaction> outputReactionMap) {
            this.outputReactionMap = outputReactionMap;
        }

        public Reaction getReactionForTargetChemical(ChemicalQuantity chemicalQuantity) {
            return outputReactionMap.get(chemicalQuantity.getName());
        }

        public ChemicalQuantity removeAndGetChemicalQuantityLeftover(ChemicalQuantity neededChemicalQuantity) {
            if (!chemicalLeftovers.containsKey(neededChemicalQuantity.getName())) {
                return new ChemicalQuantity(0L, neededChemicalQuantity.getName());
            }

            long currentLeftoverQuantity = chemicalLeftovers.get(neededChemicalQuantity.getName());
            long takenQuantity = Math.min(currentLeftoverQuantity, neededChemicalQuantity.getQuantity());
            chemicalLeftovers.put(neededChemicalQuantity.getName(), currentLeftoverQuantity - takenQuantity);
            return new ChemicalQuantity(takenQuantity, neededChemicalQuantity.getName());
        }

        public void addLeftover(String chemicalQuantityName, long quantity) {
            long currentLeftover = chemicalLeftovers.getOrDefault(chemicalQuantityName, 0L);
            chemicalLeftovers.put(chemicalQuantityName, currentLeftover + quantity);
        }

        public boolean hasNoLeftover() {
            return chemicalLeftovers.values().stream().allMatch(t -> t == 0);
        }
    }

    static class Reaction {
        private final List<ChemicalQuantity> inputChemicalQuantities;
        private final ChemicalQuantity outputChemicalQuantity;

        public Reaction(List<ChemicalQuantity> inputChemicalQuantities, ChemicalQuantity outputChemicalQuantity) {
            this.inputChemicalQuantities = inputChemicalQuantities;
            this.outputChemicalQuantity = outputChemicalQuantity;
        }

        public static Reaction parse(String str) {
            String[] splitLeftAndRight = str.split(" => ");
            List<ChemicalQuantity> inputChemicalQuantities = new ArrayList<>();
            String[] reactions = splitLeftAndRight[0].split(", ");
            for (String reaction : reactions) {
                inputChemicalQuantities.add(new ChemicalQuantity(Long.valueOf(reaction.split(" ")[0]), reaction.split(" ")[1]));
            }
            return new Reaction(
                    inputChemicalQuantities,
                    new ChemicalQuantity(
                            Integer.valueOf(splitLeftAndRight[1].split(" ")[0]),
                            splitLeftAndRight[1].split(" ")[1])
                    );
        }

        public List<ChemicalQuantity> getInputChemicalQuantities() {
            return inputChemicalQuantities;
        }

        public ChemicalQuantity getOutputChemicalQuantity() {
            return outputChemicalQuantity;
        }

        @Override
        public String toString() {
            return inputChemicalQuantities.toString() + " => " + outputChemicalQuantity;
        }
    }

    static class ChemicalQuantity {
        private final long quantity;
        private final String name;

        public ChemicalQuantity(long quantity, String name) {
            this.quantity = quantity;
            this.name = name;
        }

        public long getQuantity() {
            return quantity;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return quantity + " " + name;
        }

        public ChemicalQuantity substract(ChemicalQuantity chemicalQuantity) {
            return new ChemicalQuantity(this.quantity - chemicalQuantity.getQuantity(), this.name);
        }
    }
}
