import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController implements KeyListener {
    private GameModel gameModel;
    private GameView gameView;
    private JFrame frame;
    private Timer timer;
    private List<PowerUp> powerUps;
    private int powerUpSpawnInterval;
    private int powerUpSpawnProbability;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public GameController(GameModel gameModel, GameView gameView, JFrame frame) {
        this.gameModel = gameModel;
        this.gameView = gameView;
        this.frame = frame;
        gameView.addKeyListener(this);

        powerUps = new ArrayList<>();
        powerUpSpawnInterval = 5 * 1000; // 5 seconds
        powerUpSpawnProbability = 25; // 25%

        timer = new Timer(1000 / 60, new GameLoop());
        timer.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            gameModel.movePlayer(Direction.UP);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            gameModel.movePlayer(Direction.DOWN);
        } else if (keyCode == KeyEvent.VK_LEFT) {
            gameModel.movePlayer(Direction.LEFT);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            gameModel.movePlayer(Direction.RIGHT);
        } else if (e.isControlDown() && e.isShiftDown() && keyCode == KeyEvent.VK_Q) {
            // Złożony skrót klawiszowy - powrót do menu głównego
            returnToMainMenu();
        }

        gameView.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public void startGame() {
        frame.setContentPane(gameView);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void returnToMainMenu() {
        timer.stop();
        frame.getContentPane().removeAll();
        MainMenuView mainMenuView = new MainMenuView();
        MainMenuController mainMenuController = new MainMenuController(mainMenuView, frame);
        mainMenuController.init();
        frame.setContentPane(mainMenuView);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void spawnPowerUp() {
        if (Math.random() * 100 < powerUpSpawnProbability) {
            Random rand = new Random();
            int x = rand.nextInt(gameModel.getBoardSize());
            int y = rand.nextInt(gameModel.getBoardSize());
            PowerUp powerUp = new PowerUp(x, y, PowerUpType.getRandomPowerUpType());
            powerUps.add(powerUp);
        }
    }

    private void checkPowerUpCollision() {
        for (int i = 0; i < powerUps.size(); i++) {
            PowerUp powerUp = powerUps.get(i);
            if (powerUp.getX() == gameModel.getPlayerX() && powerUp.getY() == gameModel.getPlayerY()) {
                gameModel.collectPowerUp(powerUp);
                powerUps.remove(i);
                i--;
            }
        }
    }

    private class GameLoop implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameModel.update();
            checkPowerUpCollision();
            spawnPowerUp();

            // Sprawdzenie warunku zakończenia gry
            if (gameModel.isGameOver()) {
                // Wyświetlenie okna z zapisem do rankingu
                String playerName = JOptionPane.showInputDialog(frame, "Podaj swoje imię:");
                Ranking.addScore(playerName, gameModel.getScore());

                // Powrót do menu głównego
                returnToMainMenu();
            }

            gameView.repaint();
        }
    }
}

