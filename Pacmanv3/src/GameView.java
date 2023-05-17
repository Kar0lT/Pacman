import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameView extends JPanel {
    private GameModel gameModel;
    private BufferedImage pacmanImage;
    private BufferedImage ghostImage;
    private BufferedImage powerUpImage;
    private JLabel scoreLabel;
    private JLabel livesLabel;
    private JLabel timeLabel;

    public GameView(GameModel gameModel) {
        this.gameModel = gameModel;
        loadImages();
        setPreferredSize(new Dimension(1200, 1000));
        setFocusable(true);
        scoreLabel = new JLabel();
        livesLabel = new JLabel();
        timeLabel = new JLabel();
        add(scoreLabel);
        add(livesLabel);
        add(timeLabel);
    }

    private void loadImages() {
        try {
            pacmanImage = ImageIO.read(new File("resources/pacman.png"));
            ghostImage = ImageIO.read(new File("resources/ghost.png"));
            powerUpImage = ImageIO.read(new File("resources/powerup.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawPacman(g);
        drawGhosts(g);
        drawPowerUps(g);
        drawScore(g);
        drawLives(g);
        drawTime(g);
    }

    private void drawBoard(Graphics g) {
        boolean[][] board = gameModel.getBoard();
        int tileSize = 20;
        int numRows = board.length;
        int numCols = board[0].length;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int tileX = col * tileSize;
                int tileY = row * tileSize;
                boolean tileValue = board[row][col];

                if (!tileValue) {
                    g.setColor(Color.BLACK);
                    g.fillRect(tileX, tileY, tileSize, tileSize);
                }
            }
        }
    }

    private void drawPacman(Graphics g) {
        int pacmanX = gameModel.getPlayerX();
        int pacmanY = gameModel.getPlayerY();
        int tileSize = 20;

        g.drawImage(pacmanImage, pacmanX * tileSize, pacmanY * tileSize, tileSize, tileSize, null);
    }

    private void drawGhosts(Graphics g) {
        int[][] ghostPositions = gameModel.getGhostPositions();
        int tileSize = 20;

        for (int[] ghostPosition : ghostPositions) {
            int ghostX = ghostPosition[0];
            int ghostY = ghostPosition[1];

            g.drawImage(ghostImage, ghostX * tileSize, ghostY * tileSize, tileSize, tileSize, null);
        }
    }

    private void drawPowerUps(Graphics g) {
        int[][] powerUpPositions = gameModel.getPowerUpPositions();
        int tileSize = 20;

        for (int[] powerUpPosition : powerUpPositions) {
            int powerUpX = powerUpPosition[0];
            int powerUpY = powerUpPosition[1];

            g.drawImage(powerUpImage, powerUpX * tileSize, powerUpY * tileSize, tileSize, tileSize, null);
        }
    }

    private void drawScore(Graphics g) {
        int score = gameModel.getScore();
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Score: " + score, 10, 20);
    }

    private void drawLives(Graphics g) {
        int lives = gameModel.getLives();
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Lives: " + lives, 10, 40);
    }

    private void drawTime(Graphics g) {
        int secondsPassed = gameModel.getSecondsPassed();
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Time: " + secondsPassed + "s", 10, 60);
}
}
