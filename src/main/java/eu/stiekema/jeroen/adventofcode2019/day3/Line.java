package eu.stiekema.jeroen.adventofcode2019.day3;

import java.util.ArrayList;
import java.util.List;

class Line {
    enum Type {
        HORIZONTALLY, VERTICALLY
    }

    final Coordinate start;
    final Coordinate end;
    final Type type;

    public Line(Coordinate start, Coordinate end) {
        this.start = start;
        this.end = end;
        if (this.start.x == this.end.x) {
            this.type = Type.VERTICALLY;
        } else if (this.start.y == this.end.y){
            this.type = Type.HORIZONTALLY;
        } else {
            throw new IllegalArgumentException("only horizontal or vertical lines allowed");
        }
    }

    public List<Line> toSteps() {
        List<Line> steps = new ArrayList<>();
        if (this.type == Type.HORIZONTALLY) {
            int y = this.start.y;
            if (this.start.x < this.end.x) {
                for (int x = this.start.x; x < this.end.x; x++) {
                    steps.add(new Line(new Coordinate(x, y), new Coordinate(x + 1, y)));
                }
            } else {
                for (int x = this.start.x; x > this.end.x; x--) {
                    steps.add(new Line(new Coordinate(x, y), new Coordinate(x - 1, y)));
                }
            }
        } else if (this.type == Type.VERTICALLY) {
            int x = this.start.x;
            if (this.start.y < this.end.y) {
                for (int y = this.start.y; y < this.end.y; y++) {
                    steps.add(new Line(new Coordinate(x, y), new Coordinate(x, y + 1)));
                }
            } else {
                for (int y = this.start.y; y > this.end.y; y--) {
                    steps.add(new Line(new Coordinate(x, y), new Coordinate(x, y - 1)));
                }
            }
        }
        return steps;
    }

    @Override
    public String toString() {
        return "Line{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
