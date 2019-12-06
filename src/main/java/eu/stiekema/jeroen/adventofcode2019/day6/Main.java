package eu.stiekema.jeroen.adventofcode2019.day6;

import eu.stiekema.jeroen.adventofcode2019.common.InputStreamUtil;

import java.io.InputStream;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = eu.stiekema.jeroen.adventofcode2019.day1.Main.class.getClassLoader().getResourceAsStream("day5.txt");
        List<String> items = InputStreamUtil.readLines(inputStream);

        Map<String, SpaceObject> spaceObjects = new HashMap<>();

        for (String item : items) {
            String parentName = item.substring(0,item.indexOf(')'));
            String childName = item.substring(item.indexOf(')') + 1);

            addSpaceObjectToMap(spaceObjects, parentName);
            addSpaceObjectToMap(spaceObjects, childName);

            SpaceObject childSpaceObject = spaceObjects.get(childName);

            if (childSpaceObject.getParent() != null) {
                throw new IllegalStateException("spaceobject with name %s can only orbit one space object");
            }

            childSpaceObject.setParent(spaceObjects.get(parentName));
        }

        System.out.println("Answer part 1: " + calculateNumberOfDirectAndIndirectOrbits(spaceObjects));

        System.out.println("Answer part 2: " + calculateMinimalOrbitalTransfers(spaceObjects.get("YOU"), spaceObjects.get("SAN")));
    }

    private static int calculateMinimalOrbitalTransfers(SpaceObject you, SpaceObject san) {
        try {
            return findTargetSpaceObject(null, you, san.getParent());
        } catch (TargetSpaceObjectNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static int findTargetSpaceObject(SpaceObject previousSpaceObject, SpaceObject currentSpaceObject, SpaceObject targetSpaceObject) throws TargetSpaceObjectNotFoundException {
        if (currentSpaceObject.getChildren().contains(targetSpaceObject)) {
            return 0;
        }

        for (SpaceObject neighbourObject : currentSpaceObject.getNeighbourObjects()) {
            try {
                if (!Objects.equals(neighbourObject, previousSpaceObject)) {
                    return 1 + findTargetSpaceObject(currentSpaceObject, neighbourObject, targetSpaceObject);
                }
            } catch (TargetSpaceObjectNotFoundException e) {
                continue;
            }
        }

        throw new TargetSpaceObjectNotFoundException();
    }

    private static int calculateNumberOfDirectAndIndirectOrbits(Map<String, SpaceObject> spaceObjects) {
        return spaceObjects.values().stream()
                .map(SpaceObject::getNumberOfOrbits)
                .mapToInt(Integer::intValue).sum();
    }

    private static void addSpaceObjectToMap(Map<String, SpaceObject> spaceObjects, String spaceObjectName) {
        if (spaceObjects.get(spaceObjectName) == null) {
            SpaceObject parentSpaceObject = new SpaceObject(spaceObjectName);
            spaceObjects.put(spaceObjectName, parentSpaceObject);
        }
    }

    static class SpaceObject {
        private final String name;
        private SpaceObject parent;
        private List<SpaceObject> children = new ArrayList<>();
        private List<SpaceObject> neighbourObjects = new ArrayList<>();

        public SpaceObject(String name) {
            this.name = name;
        }

        public void setParent(SpaceObject parent) {
            this.parent = parent;
            this.neighbourObjects.add(parent);
            parent.addChild(this);
        }

        private void addChild(SpaceObject childSpaceObject) {
            this.children.add(childSpaceObject);
            this.neighbourObjects.add(childSpaceObject);
        }

        public SpaceObject getParent() {
            return parent;
        }

        public List<SpaceObject> getNeighbourObjects() {
            return this.neighbourObjects;
        }

        public List<SpaceObject> getChildren() {
            return children;
        }

        public int getNumberOfOrbits() {
            if (parent == null) {
                return 0;
            }

            return 1 + parent.getNumberOfOrbits();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SpaceObject that = (SpaceObject) o;
            return name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "SpaceObject{" +
                    "name='" + name + '\'' +
                    ", children=" + children +
                    '}';
        }
    }
}
