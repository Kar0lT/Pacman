import java.awt.BorderLayout;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Ranking extends JPanel {
    private static final String RANKING_FILE = "resources/ranking.dat";

    private DefaultListModel<Score> rankingModel;
    private JList<Score> rankingList;

    public Ranking() {
        setLayout(new BorderLayout());

        rankingModel = new DefaultListModel<>();
        rankingList = new JList<>(rankingModel);

        JScrollPane scrollPane = new JScrollPane(rankingList);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadRanking() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RANKING_FILE))) {
            List<Score> scores = (List<Score>) ois.readObject();
            for (Score score : scores) {
                rankingModel.addElement(score);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveRanking() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RANKING_FILE))) {
            List<Score> scores = new ArrayList<>();
            for (int i = 0; i < rankingModel.getSize(); i++) {
                scores.add(rankingModel.get(i));
            }
            oos.writeObject(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addScore(String playerName, int score) {
        Score newScore = new Score(playerName, score);
        Ranking ranking = new Ranking();
        ranking.rankingModel.addElement(newScore);
        ranking.sortRanking();
        ranking.saveRanking();
    }

    private void sortRanking() {
        List<Score> scores = new ArrayList<>();
        for (int i = 0; i < rankingModel.getSize(); i++) {
            scores.add(rankingModel.get(i));
        }
        Collections.sort(scores, (s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()));
        rankingModel.clear();
        for (Score score : scores) {
            rankingModel.addElement(score);
        }
    }
}
