package eu.stiekema.jeroen.adventofcode2019.day8;

import com.google.common.base.Splitter;
import eu.stiekema.jeroen.adventofcode2019.common.FileParseUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String data = FileParseUtil.readFileAsString("day8.txt");

        ImageData imageData = new ImageData(25, 6);
        imageData.fill(data);
        String layer = imageData.findLayerWithFewest0Digits();
        long nrOf1Digits = findNrOfDigits(layer, '1');
        long nrOf2Digits = findNrOfDigits(layer, '2');
        System.out.println("Day 8 solution 1: " + nrOf1Digits * nrOf2Digits);

        System.out.println("Day 8 solution 2:");
        imageData.printImage();

    }

    private static long findNrOfDigits(String layer, char c) {
        return layer.chars().mapToObj(i -> (char) i).filter(i -> i == c).count();
    }


    static class ImageData {
        private final int width;
        private final int height;

        private static final char BLACK = '0';
        private static final char WHITE = '1';
        private static final char TRANSPARENT = '2';

        private List<String> layers = new ArrayList<>();

        public ImageData(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public void printImage() {
            String layer = mergeLayers();

            char[] charArray = layer.toCharArray();
            System.out.println();
            for (int i = 0; i < charArray.length; i++) {

                char c = charArray[i];
                printPixel(c);
                if ((i + 1) % width == 0) {
                    System.out.println();
                }

            }
            System.out.println();
        }

        private String mergeLayers() {
            Character[] result = new Character[width * height];
            for (int position = 0; position < result.length; position++) {
                for (String layer : layers) {
                    if (layer.charAt(position) == BLACK
                    || layer.charAt(position) == WHITE) {
                        result[position] = layer.charAt(position);
                        break;
                    }
                }
                if (result[position] == null) {
                    result[position] = TRANSPARENT;
                }
            }
            return new String(ArrayUtils.toPrimitive(result));
        }

        private void printPixel(char pixel) {
            if (pixel == '0') {
                System.out.print(' ');
            } else if (pixel == '1') {
                System.out.print('\u258A');
            } else {
                System.out.print('.');
            }
        }

        public void fill(String data) {
            if (data.length() % (width * height) != 0) {
                throw new IllegalArgumentException("Number of data cells not equal to any multiplication of the layer size");
            }

            Iterable<String> layerData = Splitter.fixedLength(width * height).split(data);
            for (String layer: layerData) {
                layers.add(layer);
            }
        }

        public String findLayerWithFewest0Digits() {
            String foundLayer = null;
            long foundLayerNrOf0Digits = Long.MAX_VALUE;
            for (String layer : layers) {
                long nrOf0Digits = layer.chars().mapToObj(i -> (char) i).filter(i -> i == '0').count();
                if (nrOf0Digits < foundLayerNrOf0Digits) {
                    foundLayer = layer;
                    foundLayerNrOf0Digits = nrOf0Digits;
                }
            }
            return foundLayer;
        }
    }

}