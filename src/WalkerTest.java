import java.util.Random;
class Walker {
    protected int x, y;
    public Walker(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void moveRandom() {
        Random r = new Random();
        int choice = r.nextInt(4);
        if (choice == 0) x++;
        if (choice == 1) x--;
        if (choice == 2) y++;
        if (choice == 3) y--;
    }
    public void moveDirected(char c) {
        if (c == 'w') y--;
        if (c == 's') y++;
        if (c == 'a') x--;
        if (c == 'd') x++;
    }
    public String getPos() {
        return "(" + x + "," + y + ")";
    }
}
public class WalkerTest {
    public static void main(String[] args) {
        Walker ghost1 = new Walker(5, 5);
        Walker ghost2 = new Walker(10, 10);
        Walker pacman = new Walker(0, 0);
        System.out.println("Testing Walkers for 20 Steps:");
        System.out.println("--------------------------------");
        for (int i = 1; i <= 20; i++) {
            ghost1.moveRandom();
            ghost2.moveRandom();
            pacman.moveDirected('d');
            System.out.println("Step " + i + ":");
            System.out.println("Ghost1: " + ghost1.getPos());
            System.out.println("Ghost2: " + ghost2.getPos());
            System.out.println("Pacman: " + pacman.getPos());
            System.out.println();
        }
    }
}
