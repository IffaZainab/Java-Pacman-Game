import java.util.*;

class World {
    private char[][] board;
    private int rows, cols;
    private Pacman pacman;
    private ArrayList<Ghost> ghosts = new ArrayList<>();
    private int foodLeft = 0;
    public World(int r, int c) {
        rows = r;
        cols = c;
        board = new char[rows][cols];
        createBaseBoard();
        addWalls();
        countFood();
        addFruit();
    }

    private void createBaseBoard() {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                board[i][j] = '.';
    }

    private void addWalls() {
        for (int i = 0; i < cols; i++) {
            board[0][i] = '■';
            board[rows - 1][i] = '■';
        }
        for (int i = 0; i < rows; i++) {
            board[i][0] = '■';
            board[i][cols - 1] = '■';
        }

        for (int j = 2; j < cols - 2; j += 4)
            for (int i = 2; i < rows - 2; i++)
                board[i][j] = '■';

        board[rows / 2][2] = '.';
        board[rows/2][14] = '.';
        board[rows / 2][26] = '.';
    }

    private void countFood() {
        foodLeft = 0;
        for (int i = 1; i < rows - 1; i++)
            for (int j = 1; j < cols - 1; j++)
                if (board[i][j] == '.')
                    foodLeft++;
    }

    private void addFruit() {
        Random r = new Random();
        while (true) {
            int fx = r.nextInt(cols);
            int fy = r.nextInt(rows);
            if (board[fy][fx] == '.') {
                board[fy][fx] = '♦';
                break;
            }
        }
    }

    public void placePacman(int x, int y) {
        if (pacman == null)
            pacman = new Pacman(x, y);
        else
            pacman.setPos(new Point(x, y));
    }

    public void addGhost(Ghost g) { ghosts.add(g); }

    public boolean isWall(Point p) {
        int x = p.getX(), y = p.getY();
        if (x < 0 || x >= cols || y < 0 || y >= rows) return true;
        return board[y][x] == '■';
    }

    public boolean movePacman(Point next) {
        if (isWall(next)) return false;

        pacman.setPos(next);
        char tile = board[next.getY()][next.getX()];

        if (tile == '.') {
            pacman.addScore(10);
            board[next.getY()][next.getX()] = ' ';
            foodLeft--;
        } else if (tile == '♦') {
            pacman.addScore(50);
            board[next.getY()][next.getX()] = ' ';
            System.out.println("♦ BONUS FRUIT EATEN! +50 POINTS!");
        }
        return true;
    }

    public void updateGhosts() {
        Point pac = pacman.getPos();
        for (Ghost g : ghosts) {
            Point gp = g.getPos();
            int gx = gp.getX(), gy = gp.getY();
            int px = pac.getX(), py = pac.getY();

            int nx = gx, ny = gy;

            if (px > gx) nx++;
            else if (px < gx) nx--;

            if (py > gy) ny++;
            else if (py < gy) ny--;

            Point next = new Point(nx, ny);

            if (isWall(next)) {
                if (px > gx) next = new Point(gx + 1, gy);
                else if (px < gx) next = new Point(gx - 1, gy);

                if (isWall(next)) {
                    if (py > gy) next = new Point(gx, gy + 1);
                    else if (py < gy) next = new Point(gx, gy - 1);
                }
            }
            boolean occupied = false;
            for (Ghost other : ghosts) {
                if (other != g && other.getPos().equals(next)) {
                    occupied = true;
                    break;
                }
            }
            if (!occupied && !isWall(next))
                g.moveTo(next);
        }
    }

    public boolean collision() {
        Point p = pacman.getPos();
        for (Ghost g : ghosts)
            if (g.getPos().equals(p)) return true;
        return false;
    }

    public boolean allFoodGone() { return foodLeft <= 0; }
    public Pacman getPacman() { return pacman; }

    public void draw() {
        char[][] temp = new char[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                temp[i][j] = board[i][j];
        temp[pacman.getPos().getY()][pacman.getPos().getX()] = 'P';
        for (Ghost g : ghosts) {
            Point gp = g.getPos();
            if (gp.equals(pacman.getPos()))
                temp[gp.getY()][gp.getX()] = 'X';
            else
                temp[gp.getY()][gp.getX()] = 'G';
        }
        for (char[] row : temp) {
            for (char c : row) System.out.print(c + " ");
            System.out.println();
        }
        System.out.println("----------------------------------------------");
        System.out.println("Score: " + pacman.getScore() +
                "   Lives: " + pacman.getLives() +
                "   Food Left: " + foodLeft);
        System.out.println("----------------------------------------------");
    }
}
