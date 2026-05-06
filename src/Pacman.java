import java.util.*;

class Pacman {
    private Point pos;
    private int score = 0;
    private int lives = 3;
    public Pacman(int x, int y) { pos = new Point(x, y); }
    public Point getPos() { return pos; }
    public int getScore() { return score; }
    public int getLives() { return lives; }
    public void setPos(Point p) { pos = p; }
    public void addScore(int v) { score += v; }
    public void loseLife() { lives = Math.max(0, lives - 1); }
    public Point nextPos(char c) {
        int x = pos.getX(), y = pos.getY();
        if (c == 'w' || c == 'W') y--;
        if (c == 's' || c == 'S') y++;
        if (c == 'a' || c == 'A') x--;
        if (c == 'd' || c == 'D') x++;
        return new Point(x, y);
    }
}
