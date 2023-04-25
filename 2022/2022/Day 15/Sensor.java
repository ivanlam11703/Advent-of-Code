public class Sensor {
    public long x, y, bx, by, manhattanDist;

    Sensor(long x, long y, long bx, long by) {
        this.x =x;
        this.y = y;
        this.bx = bx;
        this.by = by;
        this.manhattanDist = Math.abs(x-bx) + Math.abs(y-by);
    }

    void show() {
        System.out.println("Sensor at x=" + x + ", y=" + y + ": closest beacon is at x=" + bx + ", y=" + by);
    }
}