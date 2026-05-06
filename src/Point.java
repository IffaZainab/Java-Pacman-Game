import java.util.*;

class Point {
    private int x, y;
    public Point() { this.x = 0; this.y = 0; }
    public Point(int x, int y) { this.x = x; this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) return false;
        Point p = (Point) obj;
        return this.x == p.x && this.y == p.y;
    }
    public String toString() { return "[" + x + "," + y + "]"; }
}