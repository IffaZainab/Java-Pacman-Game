import java.util.*;

public class Main {
    public static void printHeader() {
        System.out.println("==================================================");
        System.out.println("                    PACMAN GAME");
        System.out.println("                CONSOLE VERSION");
        System.out.println("==================================================");
        System.out.println(" Controls:");
        System.out.println("   W  - Move Up");
        System.out.println("   A  - Move Left");
        System.out.println("   S  - Move Down");
        System.out.println("   D  - Move Right");
        System.out.println("   Q  - Quit Game");
        System.out.println("==================================================\n");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printHeader();
        World w = new World(15, 31);
        w.placePacman(15, 7);
        w.addGhost(new Ghost("R", 3, 3, "R"));
        w.addGhost(new Ghost("B", 27, 12, "L"));
        w.draw();
        while (true) {
            System.out.print("Enter Move (W/A/S/D): ");
            String s = sc.nextLine();
            if (s.length() == 0) continue;

            char c = s.charAt(0);
            if (c == 'q' || c == 'Q') break;

            Point intended = w.getPacman().nextPos(c);
            if (!w.movePacman(intended))
                System.out.println("Hit a wall!");
            w.updateGhosts();

            if (w.collision()) {
                System.out.println("\n💀 GHOST KILLED PACMAN! 💀");
                w.getPacman().loseLife();
                w.draw();
                System.out.println("Lives left: " + w.getPacman().getLives());
                System.out.println("Press Enter to continue...");
                sc.nextLine();

                if (w.getPacman().getLives() == 0) {
                    System.out.println("\nGAME OVER!");
                    break;
                }
                w.placePacman(15, 7);
                w.draw();
                continue;
            }
            if (w.allFoodGone()) {
                System.out.println("WIN! All food eaten!");
                w.draw();
                break;
            }
            w.draw();
        }
        System.out.println("Final Score: " + w.getPacman().getScore());
    }
}