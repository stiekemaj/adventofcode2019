package eu.stiekema.jeroen.adventofcode2019.common;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FileParseUtil {
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

    public static List<Integer> getCodes(String file) throws IOException {
        InputStream inputStream = FileParseUtil.class.getClassLoader().getResourceAsStream(file);
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, "UTF-8");
        StringTokenizer st = new StringTokenizer(writer.toString(), ",");
        List<Integer> codes = new ArrayList<>();
        while (st.hasMoreTokens()) {
            codes.add(Integer.valueOf(st.nextToken()));
        }
        return codes;
    }
}
