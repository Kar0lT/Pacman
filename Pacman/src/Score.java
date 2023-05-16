import java.io.Serializable;

public class Score implements Serializable {
    private String playerName;
    private int score;

    public Score(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }
}
