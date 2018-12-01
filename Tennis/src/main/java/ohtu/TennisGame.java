package ohtu;

import java.util.HashMap;
import java.util.Map;

public class TennisGame {

    private String score;
    private String playerOne;
    private String playerTwo;
    private int playerOneScore = 0;
    private int playerTwoScore = 0;

    private final Map<Integer, String> SCORES = new HashMap<Integer, String>() {
        {
            put(0, "Love");
            put(1, "Fifteen");
            put(2, "Thirty");
            put(3, "Forty");
        }
    };

    public TennisGame(String playerOne, String playerTwo) {
        this.score = "";
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public String getScore() {
        if (playerOneScore == playerTwoScore)
            tie();
        else if (playerOneScore >= 4 || playerTwoScore >= 4)
            gamePoint();
        else
            playerScores();
        return score;
    }

    public void wonPoint(String playerWhoScored) {
        if (playerWhoScored == playerOne)
            playerOneScore++;
        else
            playerTwoScore++;
    }

    public void tie() {
        if (playerOneScore < 4)
            score = SCORES.get(playerOneScore) + "-All";
        else
            score = "Deuce";
    }

    public void playerScores() {
        score += SCORES.get(playerOneScore) + "-" + SCORES.get(playerTwoScore);
    }

    public void gamePoint() {
        int scoreDifference = playerOneScore - playerTwoScore;
        if (scoreDifference == 1)
            score = "Advantage " + playerOne;
        else if (scoreDifference == -1)
            score = "Advantage " + playerTwo;
        else if (scoreDifference >= 2)
            score = "Win for " + playerOne;
        else
            score = "Win for " + playerTwo;
    }

}