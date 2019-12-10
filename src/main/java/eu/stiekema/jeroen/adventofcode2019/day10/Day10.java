package eu.stiekema.jeroen.adventofcode2019.day10;

import com.google.common.math.IntMath;
import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;

import java.util.*;

public class Day10 {
    public static void main(String[] args) {
        List<Coordinate> asteroids = getAsteroidCoordinates();


        int coordinateWithMostVisibleAsteroids = 0;

        for (Coordinate asteroid : asteroids) {
            int visibleAsteroids = 0;
            List<Coordinate> otherAsteroids = new ArrayList<>(asteroids);
            otherAsteroids.remove(asteroid);

            for (Coordinate otherAsteroid : otherAsteroids) {
                List<Coordinate> coordinatesInPath = getCoordinatesInPath(asteroid, otherAsteroid);
                if (!hasAstroidOnAnyCoordinate(coordinatesInPath, asteroids)) {
                    visibleAsteroids++;
                }
            }

            if (coordinateWithMostVisibleAsteroids < visibleAsteroids) {
                coordinateWithMostVisibleAsteroids = visibleAsteroids;
            }
        }

        System.out.println("Answer 1: " + coordinateWithMostVisibleAsteroids);

    }

    private static boolean hasAstroidOnAnyCoordinate(List<Coordinate> coordinates, List<Coordinate> asteroids) {
        for (Coordinate coordinate : coordinates) {
            if (asteroids.contains(coordinate)) {
                return true;
            }
        }

        return false;
    }

    private static List<Coordinate> getCoordinatesInPath(Coordinate startCoordinate, Coordinate endCoordinate) {
        List<Coordinate> coordinates = new ArrayList<>();
        Coordinate closestCoordinateInPath = getClosestCoordinateInPath(startCoordinate, endCoordinate);
        if (closestCoordinateInPath.equals(endCoordinate)) {
            return Collections.emptyList();
        }

        int xDistance  = closestCoordinateInPath.x - startCoordinate.x;
        int yDistance = closestCoordinateInPath.y - startCoordinate.y;

        Coordinate nextCoordinateInPath = closestCoordinateInPath;
        while (!nextCoordinateInPath.equals(endCoordinate)) {
            coordinates.add(nextCoordinateInPath);
            nextCoordinateInPath = new Coordinate(nextCoordinateInPath.x + xDistance, nextCoordinateInPath.y + yDistance);
        }

        return coordinates;
    }

    private static Coordinate getClosestCoordinateInPath(Coordinate startCoordinate, Coordinate endCoordinate) {

        if (startCoordinate.equals(endCoordinate)) {
            return endCoordinate;
        } else if (startCoordinate.x == endCoordinate.x) {
            return new Coordinate(startCoordinate.x, startCoordinate.y < endCoordinate.y ? startCoordinate.y + 1 : startCoordinate.y - 1);
        } else if (startCoordinate.y == endCoordinate.y) {
            return new Coordinate(startCoordinate.x < endCoordinate.x ? startCoordinate.x + 1 : startCoordinate.x - 1, startCoordinate.y);
        } else {
            int xDistance = endCoordinate.x - startCoordinate.x;
            int yDistance = endCoordinate.y - startCoordinate.y;

            for (int i = 2; i <= Math.abs(xDistance); i++) {
                if (IntMath.isPrime(i) && xDistance % i == 0 && yDistance % i == 0) {
                    Coordinate newCoordinate = new Coordinate(startCoordinate.x + xDistance / i, startCoordinate.y + yDistance / i);
                    return getClosestCoordinateInPath(startCoordinate, newCoordinate);
                }
            }

            return endCoordinate;
        }
    }



    private static List<Coordinate> getAsteroidCoordinates() {
        List<Coordinate> coordinates = new ArrayList<>();

        List<String> lines = FileParseUtil.readLines(Day10.class.getClassLoader().getResourceAsStream("day10.txt"));
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            char[] charArray = line.toCharArray();
            for (int x = 0; x < charArray.length; x++) {
                char c = charArray[x];
                if (c == '#') {
                    coordinates.add(new Coordinate(x, y));
                }
            }
        }
        return coordinates;
    }

    static class Coordinate {
        final int x;
        final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
}