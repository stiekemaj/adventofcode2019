package eu.stiekema.jeroen.adventofcode2019.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InputStreamUtil {
    public static List<String> readLines(InputStream input) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        List<String> result = new ArrayList<>();
        try {
            while (reader.ready()) {
                result.add(reader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException("exception while reading input", e);
        }
        return result;
    }
}
