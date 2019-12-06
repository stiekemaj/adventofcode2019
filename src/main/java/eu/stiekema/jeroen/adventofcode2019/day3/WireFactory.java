package eu.stiekema.jeroen.adventofcode2019.day3;

import java.util.List;
import java.util.StringTokenizer;

class WireFactory {
    List<Line> createWire(String wireString) {
        WireBuilder wireBuilder = new WireBuilder();
        StringTokenizer st = new StringTokenizer(wireString, ",");
        while (st.hasMoreTokens()) {
            wireBuilder.stepTo(createStep(st.nextToken()));
        }
        return wireBuilder.build();
    }

    private Step createStep(String str) {
        Step.Direction direction = Step.Direction.ofString(str.substring(0, 1));
        return new Step(direction, Integer.parseInt(str.substring(1)));
    }
}
