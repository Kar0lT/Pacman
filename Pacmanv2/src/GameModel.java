import java.util.Random;

public class GameModel {
    private int boardSize;
    private boolean[][] board;
    private int playerX;
    private int playerY;
    private int score;
    private int lives;
    private boolean[][] powerUps;
    private int[][] ghostPositions;
    private int[][] powerUpPositions;

    public GameModel(int boardSize) {
        this.boardSize = boardSize;
        this.board = new boolean[boardSize][boardSize];
        this.playerX = boardSize / 2;
        this.playerY = boardSize / 2;
        this.score = 0;
        this.lives = 3;
        this.powerUps = new boolean[boardSize][boardSize];
        this.ghostPositions = new int[boardSize][2];
        this.powerUpPositions = new int[boardSize][2];
        generatePowerUps();
        generateGhosts();
    }

    public int getBoardSize() {
        return boardSize;
    }

    public boolean[][] getBoard() {
        return board;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public boolean[][] getPowerUps() {
        return powerUps;
    }

    public int[][] getGhostPositions() {
        return ghostPositions;
    }

    public int[][] getPowerUpPositions() {
        return powerUpPositions;
    }

    public boolean hasPowerUp(int row, int col) {
        return powerUps[row][col];
    }

    public void movePlayer(GameController.Direction direction) {
        int newX = playerX;
        int newY = playerY;

        if (direction == GameController.Direction.UP && playerY > 0) {
            newY--;
        } else if (direction == GameController.Direction.DOWN && playerY < boardSize - 1) {
            newY++;
        } else if (direction == GameController.Direction.LEFT && playerX > 0) {
            newX--;
        } else if (direction == GameController.Direction.RIGHT && playerX < boardSize - 1) {
            newX++;
        }

        if (!board[newY][newX]) {
            playerX = newX;
            playerY = newY;

            if (powerUps[newY][newX]) {
                score += 10;
                powerUps[newY][newX] = false;
            }
        } else {
            lives--;

            if (lives == 0) {
                gameOver();
            }
        }
    }

    private void gameOver() {
        // Logika zakończenia gry
    }

    public void update() {
        // Implement your game update logic
        moveGhosts();
    }

    private void moveGhosts() {
        Random random = new Random();

        for (int i = 0; i < ghostPositions.length; i++) {
            int[] ghostPosition = ghostPositions[i];
            int ghostX = ghostPosition[0];
            int ghostY = ghostPosition[1];

            int direction = random.nextInt(4);

            if (direction == 0 && ghostY > 0) {
                ghostY--;
            } else if (direction == 1 && ghostY < boardSize - 1) {
                ghostY++;
            } else if (direction == 2 && ghostX > 0) {
                ghostX--;
            } else if (direction == 3 && ghostX < boardSize - 1) {
                ghostX++;
            }
            ghostPositions[i][0] = ghostX;
            ghostPositions[i][1] = ghostY;
        }
    }

    private void generatePowerUps() {
        Random random = new Random();

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (!powerUps[row][col] && random.nextDouble() < 0.25) {
                    powerUps[row][col] = true;
                    powerUpPositions[row][0] = col;
                    powerUpPositions[row][1] = row;
                }
            }
        }
    }

    private void generateGhosts() {
        Random random = new Random();

        for (int i = 0; i < ghostPositions.length; i++) {
            int ghostX = random.nextInt(boardSize);
            int ghostY = random.nextInt(boardSize);

            ghostPositions[i][0] = ghostX;
            ghostPositions[i][1] = ghostY;
        }
    }

    public void collectPowerUp(PowerUp powerUp) {
        // Implement the logic for collecting a power-up
    }

    public boolean isGameOver() {
        // Implement your game over condition
        return false;
    }
}

