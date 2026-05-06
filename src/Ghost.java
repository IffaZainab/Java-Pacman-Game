import java.util.*;

class Ghost {
    private String color;
    private Point pos;
    private String dir;
    public Ghost(String color, int x, int y, String dir) {
        this.color = color;
        this.pos = new Point(x, y);
        this.dir = dir;
    }
    public Ghost(int x, int y) {
        this("G", x, y, "R");
    }
    public Point getPos() { return pos; }
    public void moveTo(Point p) { pos = p; }
}