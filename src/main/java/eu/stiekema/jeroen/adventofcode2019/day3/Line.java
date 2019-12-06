package eu.stiekema.jeroen.adventofcode2019.day3;

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

    @Override
    public String toString() {
        return "Line{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
