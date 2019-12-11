package eu.stiekema.jeroen.adventofcode2019.day10;

import com.google.common.math.IntMath;
import eu.stiekema.jeroen.adventofcode2019.common.Coordinate;
import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class Day10 {
    public static void main(String[] args) {
        List<Coordinate> asteroids = getAsteroidCoordinates("day10.txt");

        Pair<Coordinate, List<Coordinate>> mostVisibleAsteroids = null;

        for (Coordinate asteroid : asteroids) {
            List<Coordinate> otherAsteroids = new ArrayList<>(asteroids);
            otherAsteroids.remove(asteroid);

            List<Coordinate> visibleAsteroids = findVisibleAsteroids(asteroid, otherAsteroids);
            if (mostVisibleAsteroids == null || visibleAsteroids.size() > mostVisibleAsteroids.getValue().size()) {
                mostVisibleAsteroids = new ImmutablePair<>(asteroid, visibleAsteroids);
            }
        }

        System.out.println("Answer 1: " + mostVisibleAsteroids.getValue().size());

        Coordinate vaporizedAsteroid = vaporizeAndReturnVaporizedAsteroid(asteroids, mostVisibleAsteroids, 200);
        System.out.println("Answer 2: " + (vaporizedAsteroid.x * 100 + vaporizedAsteroid.y));

    }

    private static Coordinate vaporizeAndReturnVaporizedAsteroid(List<Coordinate> asteroids, Pair<Coordinate, List<Coordinate>> mostVisibleAsteroids, int returnVaporizedNr) {
        List<Coordinate> unvaporizedAsteroids = new ArrayList<>(asteroids);
        Coordinate asteroidWithBaseStation = mostVisibleAsteroids.getKey();
        List<Coordinate> visibleAsteroids = new ArrayList<>(mostVisibleAsteroids.getValue());

        int vaporizedAsteroidCount = 0;

        while (!unvaporizedAsteroids.isEmpty()) {
            visibleAsteroids.sort((o1, o2) -> {
                Double angle1 = asteroidWithBaseStation.getAngle(o1);
                Double angle2 = asteroidWithBaseStation.getAngle(o2);
                return angle1.compareTo(angle2);
            });

            for (Coordinate visibleAsteroid : visibleAsteroids) {
                unvaporizedAsteroids.remove(visibleAsteroid);
                vaporizedAsteroidCount++;

                if (vaporizedAsteroidCount == returnVaporizedNr) {
                    return visibleAsteroid;
                }
            }

            visibleAsteroids = findVisibleAsteroids(asteroidWithBaseStation, unvaporizedAsteroids);
        }

        return null;
    }

    private static List<Coordinate> findVisibleAsteroids(Coordinate asteroid, List<Coordinate> otherAsteroids) {
        List<Coordinate> visibleAsteroids = new ArrayList<>();
        for (Coordinate otherAsteroid : otherAsteroids) {
            List<Coordinate> coordinatesInPath = getCoordinatesInPath(asteroid, otherAsteroid);
            if (!hasAstroidOnAnyCoordinate(coordinatesInPath, otherAsteroids)) {
                visibleAsteroids.add(otherAsteroid);
            }
        }
        return visibleAsteroids;
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



    private static List<Coordinate> getAsteroidCoordinates(String file) {
        List<Coordinate> coordinates = new ArrayList<>();

        List<String> lines = FileParseUtil.readLines(Day10.class.getClassLoader().getResourceAsStream(file));
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

}
