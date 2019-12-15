package eu.stiekema.jeroen.adventofcode2019.day12;

import eu.stiekema.jeroen.adventofcode2019.common.Coordinate3d;
import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;
import eu.stiekema.jeroen.adventofcode2019.common.MathUtil;
import org.apache.commons.lang3.mutable.MutableInt;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day12 {
    private static Pattern moonPattern = Pattern.compile("<x=(-?\\d*), y=(-?\\d*), z=(-?\\d*)>");

    public static void main(String[] args) {
        List<Moon> moons = parseMoons("day12.txt");

        for (int i = 0; i < 1000; i++) {
            executeTimeStep(moons);
        }

        System.out.println("Answer 1: " + calculateTotalEnergy(moons));

        // reset moons for part wo
        moons = parseMoons("day12.txt");

        long step = 0L;
        BigInteger xRepeatCount = BigInteger.valueOf(-1L);
        BigInteger yRepeatCount = BigInteger.valueOf(-1L);
        BigInteger zRepeatCount = BigInteger.valueOf(-1L);
        while (xRepeatCount.doubleValue() == -1 || yRepeatCount.doubleValue() == -1 || zRepeatCount.doubleValue() == -1) {
            executeTimeStep(moons);
            step++;
            xRepeatCount = xRepeatCount.doubleValue() == -1 ? calculateXRepeatCount(step, moons) : xRepeatCount;
            yRepeatCount = yRepeatCount.doubleValue() == -1 ? calculateYRepeatCount(step, moons) : yRepeatCount;
            zRepeatCount = zRepeatCount.doubleValue() == -1 ? calculateZRepeatCount(step, moons) : zRepeatCount;
        }

        System.out.println("Answer 2: " + MathUtil.lcm(xRepeatCount, yRepeatCount, zRepeatCount));
    }

    private static BigInteger calculateZRepeatCount(long step, List<Moon> moons) {
        if (moons.stream().allMatch(m -> m.initialCoordinate.z == m.coordinate.z && m.velocity.z == 0)) {
            return BigInteger.valueOf(step);
        } else {
            return BigInteger.valueOf(-1L);
        }
    }

    private static BigInteger calculateYRepeatCount(long step, List<Moon> moons) {
        if (moons.stream().allMatch(m -> m.initialCoordinate.y == m.coordinate.y && m.velocity.y == 0)) {
            return BigInteger.valueOf(step);
        } else {
            return BigInteger.valueOf(-1L);
        }
    }

    private static BigInteger calculateXRepeatCount(long step, List<Moon> moons) {
        if (moons.stream().allMatch(m -> m.initialCoordinate.x == m.coordinate.x && m.velocity.x == 0)) {
            return BigInteger.valueOf(step);
        } else {
            return BigInteger.valueOf(-1L);
        }
    }

    private static void executeTimeStep(List<Moon> moons) {
        Map<Moon, Coordinate3d> newVelocityMap = new HashMap<>();

        for (Moon moon : moons) {
            final MutableInt velocityX = new MutableInt(moon.getVelocity().x);
            final MutableInt velocityY = new MutableInt(moon.getVelocity().y);
            final MutableInt velocityZ = new MutableInt(moon.getVelocity().z);

            moons.stream()
                    .filter(t -> !t.equals(moon))
                    .map(Moon::getCoordinate)
                    .forEach(t -> {
                        velocityX.add(Integer.compare(t.x, moon.getCoordinate().x));
                        velocityY.add(Integer.compare(t.y, moon.getCoordinate().y));
                        velocityZ.add(Integer.compare(t.z, moon.getCoordinate().z));
                    });

            Coordinate3d velocity = new Coordinate3d(velocityX.getValue(), velocityY.getValue(), velocityZ.getValue());
            newVelocityMap.put(moon, velocity);
        }

        moons.forEach(t -> t.setVelocity(newVelocityMap.get(t)));
        moons.forEach(Moon::updateCoordinateByCurrentVelocity);
    }

    private static int calculateTotalEnergy(List<Moon> moons) {
        return moons.stream()
                .map(Moon::getTotalEnergy)
                .mapToInt(Integer::intValue).sum();
    }

    private static List<Moon> parseMoons(String file) {
        List<String> moons = FileParseUtil.readLines(Day12.class.getClassLoader().getResourceAsStream(file));
        return moons.stream()
                .map(Day12::parseMoon)
                .collect(Collectors.toList());
    }

    private static Moon parseMoon(String moon) {
        Matcher matcher = moonPattern.matcher(moon);
        if (matcher.find()) {
            return new Moon(new Coordinate3d(
                    Integer.valueOf(matcher.group(1)),
                    Integer.valueOf(matcher.group(2)),
                    Integer.valueOf(matcher.group(3))));
        } else {
            throw new IllegalArgumentException("Invalid moon string");
        }
    }

    private static void printMoons(List<Moon> moons, int step) {
        System.out.println(String.format("After %s steps:", step));
        moons.forEach(System.out::println);
        System.out.println();
    }

    private static class Moon {
        private final Coordinate3d initialCoordinate;
        private Coordinate3d coordinate;
        private Coordinate3d velocity;

        public Moon(Coordinate3d initialCoordinate) {
            this.initialCoordinate = initialCoordinate;
            this.coordinate = new Coordinate3d(initialCoordinate);
            this.velocity = new Coordinate3d(0, 0, 0);
        }

        public Coordinate3d getCoordinate() {
            return coordinate;
        }

        public Coordinate3d getInitialCoordinate() {
            return initialCoordinate;
        }

        public Coordinate3d getVelocity() {
            return velocity;
        }

        public void updateCoordinateByCurrentVelocity() {
            this.coordinate = this.coordinate.add(this.velocity);
        }

        public void setVelocity(Coordinate3d velocity) {
            this.velocity = velocity;
        }

        public int getTotalEnergy() {
            return getPotentialEnergy() * getKineticEnergy();
        }

        public int getPotentialEnergy() {
            return Math.abs(coordinate.x) + Math.abs(coordinate.y) + Math.abs(coordinate.z);
        }

        public int getKineticEnergy() {
            return Math.abs(velocity.x) + Math.abs(velocity.y) + Math.abs(velocity.z);
        }

        @Override
        public String toString() {
            return "pos=" + coordinate.toString() + ", vel=" + velocity.toString();
        }
    }
}
