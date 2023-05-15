package boardgame.model;

import java.time.LocalDateTime;

public class GameResults {

    private LocalDateTime gameStartTime;
    private String player1Name;
    private String player2Name;
    private int player1Turns;
    private int player2Turns;
    private String winnerName;

    public LocalDateTime getGameStartTime() {
        return gameStartTime;
    }

    public void setGameStartTime(LocalDateTime gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public int getPlayer1Turns() {
        return player1Turns;
    }

    public void setPlayer1Turns(int player1Turns) {
        this.player1Turns = player1Turns;
    }

    public int getPlayer2Turns() {
        return player2Turns;
    }

    public void setPlayer2Turns(int player2Turns) {
        this.player2Turns = player2Turns;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

}
