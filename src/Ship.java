public class Ship {
    private final Coordinate start, end;
    private final int length;

    public Ship(Coordinate coord1, Coordinate coord2, int length) {
        this.start = coord1;
        this.end = coord2;
        this.length = length;
    }

    public Coordinate getCoord1() {
        return start;
    }

    public Coordinate getCoord2() {
        return end;
    }

    public int getLength() {
        return length;
    }
}
