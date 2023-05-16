import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController {
    private MainMenuView mainMenuView;
    private JFrame frame;

    public MainMenuController(MainMenuView mainMenuView, JFrame frame) {
        this.mainMenuView = mainMenuView;
        this.frame = frame;
    }

    public void init() {
        mainMenuView.addNewGameButtonListener(new NewGameButtonListener());
        mainMenuView.addHighScoresButtonListener(new HighScoresButtonListener());
        mainMenuView.addExitButtonListener(new ExitButtonListener());
    }

    private class NewGameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String sizeString = JOptionPane.showInputDialog(frame, "Enter board size (10-100):");
            int boardSize = Integer.parseInt(sizeString);

            if (boardSize >= 10 && boardSize <= 100) {
                frame.getContentPane().removeAll();
                GameWindow gameWindow = new GameWindow();
                gameWindow.startNewGame(boardSize);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid board size. Please enter a number between 10 and 100.");
            }
        }
    }

    private class HighScoresButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.getContentPane().removeAll();
            Ranking ranking = new Ranking();
            ranking.loadRanking();
            frame.setContentPane(ranking);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }

    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
