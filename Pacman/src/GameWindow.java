import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private GameController gameController;

    public GameWindow() {
        super("Pacman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
    }

    public void init() {
        MainMenuView mainMenuView = new MainMenuView();
        MainMenuController mainMenuController = new MainMenuController(mainMenuView, this);
        mainMenuController.init();
        setContentPane(mainMenuView);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void startNewGame(int boardSize) {
        GameModel gameModel = new GameModel(boardSize);
        GameView gameView = new GameView(gameModel);
        GameController gameController = new GameController(gameModel, gameView, this);
        this.gameController = gameController;

        setContentPane(gameView); // Dodanie GameView do kontenera okna
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

