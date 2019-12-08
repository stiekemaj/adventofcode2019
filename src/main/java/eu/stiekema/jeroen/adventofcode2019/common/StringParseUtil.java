package eu.stiekema.jeroen.adventofcode2019.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class StringParseUtil {
    public static List<Integer> getCodes(String commaSeparatedValues, String delimiter) {
        StringTokenizer st = new StringTokenizer(commaSeparatedValues, delimiter);
        List<Integer> codes = new ArrayList<>();
        while (st.hasMoreTokens()) {
            codes.add(Integer.valueOf(st.nextToken()));
        }
        return Collections.unmodifiableList(codes);
    }
}
