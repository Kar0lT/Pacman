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
        this.ghostPositions = new int[0][2];
        this.powerUpPositions = new int[0][2];
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
    }
    public void collectPowerUp(PowerUp powerUp) {
        // Implement the logic for collecting a power-up
    }
    public boolean isGameOver() {
        // Implement your game over condition
        return false;
    }
    public void generatePowerUps() {
        Random random = new Random();

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (!powerUps[row][col] && random.nextDouble() < 0.25) {
                    powerUps[row][col] = true;
                }
            }
        }
    }
}
