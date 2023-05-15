package boardgame.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardGameModel {

    private GameResults gameResult;
    public static final int BOARD_SIZE = 3;
    public ReadOnlyObjectWrapper<Square>[][] board = new ReadOnlyObjectWrapper[BOARD_SIZE][BOARD_SIZE];
    private Player currentPlayer = Player.PLAYER1;
    private boolean gameOver = false;
    private Player winner = null;

    private String player1Name;
    private String player2Name;

    private String winnerName;


    public BoardGameModel() {
        for (var i = 0; i < BOARD_SIZE; i++) {
            for (var j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new ReadOnlyObjectWrapper<Square>(Square.NONE);
            }
        }
        gameResult = new GameResults();
        gameResult.setGameStartTime(LocalDateTime.now().toString());
    }

    public ReadOnlyObjectProperty<Square> squareProperty(int i, int j) {
        return board[i][j].getReadOnlyProperty();
    }

    public Square getSquare(int i, int j) {
        return board[i][j].get();
    }

    public void move(int i, int j) {
        if (!gameOver && board[i][j].get() != Square.GREEN) {
            board[i][j].set(
                    switch (board[i][j].get()) {
                        case NONE -> Square.RED;
                        case RED -> Square.YELLOW;
                        case YELLOW -> Square.GREEN;
                        case GREEN -> Square.NONE;
                    }
            );

            if(currentPlayer == Player.PLAYER1){
                gameResult.setPlayer1Turns(gameResult.getPlayer1Turns()+1);
            }
            else{
                gameResult.setPlayer2Turns(gameResult.getPlayer2Turns()+1);
            }
            checkWinner();
            switchPlayers();}

    }
    private void switchPlayers() {
        currentPlayer = currentPlayer == Player.PLAYER1 ? Player.PLAYER2 : Player.PLAYER1;
    }

    private void checkWinner() {
        for (int i = 0; i < BoardGameModel.BOARD_SIZE; i++) {
            if (checkRow(i) || checkColumn(i)) {
            winner = currentPlayer;
            gameOver = true;
        }
    }
        if (checkDiagonal()){
            winner = currentPlayer;
            gameOver = true;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var i = 0; i < BOARD_SIZE; i++) {
            for (var j = 0; j < BOARD_SIZE; j++) {
                sb.append(board[i][j].get().ordinal()).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        var model = new BoardGameModel();
        System.out.println(model);
    }

    //Iterating the board to check for winner
    public boolean checkRow(int row) {
        Square firstSquare = getSquare(row, 0);
        if (firstSquare == Square.NONE) {
            return false;
        }
        for (int j = 1; j < BOARD_SIZE; j++) {
            if (getSquare(row, j) != firstSquare) {
                return false;
            }
        }
        return true;
    }

    public boolean checkColumn(int column) {
        Square firstSquare = getSquare(0, column);
        if (firstSquare == Square.NONE) {
            return false;
        }
        for (int i = 1; i < BOARD_SIZE; i++) {
            if (getSquare(i, column) != firstSquare) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDiagonal() {
        Square centerSquare = getSquare(1, 1);
        if (centerSquare == Square.NONE) {
            return false;
        }
        // Check the diagonal from top-left to bottom-right
        if (getSquare(0, 0) == centerSquare && getSquare(2, 2) == centerSquare) {
            return true;
        }
        // Check the diagonal from bottom-left to top-right
        if (getSquare(2, 0) == centerSquare && getSquare(0, 2) == centerSquare) {
            return true;
        }
        return false;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Player getWinner() {
        return winner;
    }

    public void resetGame(){
        if (winner!=null){saveGameResult();}
        currentPlayer=Player.PLAYER1;
        gameOver=false;
        winner=null;
        gameResult.setWinnerName(null);
        gameResult.setPlayer2Turns(0);
        gameResult.setPlayer1Turns(0);
        gameResult.setGameStartTime(LocalDateTime.now().toString());


    }
    public void setPlayerNames(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        gameResult.setPlayer1Name(player1Name);
        gameResult.setPlayer2Name(player2Name);
    }
    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
        gameResult.setWinnerName(winnerName);
    }

    private void saveGameResult() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("game_results.json");

        // Read existing game results from the file, if any
        List<GameResults> gameResultsList = new ArrayList<>();
        if (file.exists()) {
            try {
                gameResultsList = mapper.readValue(file, new TypeReference<List<GameResults>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Add the current game result to the list
        gameResultsList.add(gameResult);

        // Write the updated list back to the file
        try {
            mapper.writeValue(file, gameResultsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Long> getPlayerWins() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("game_results.json");

        List<GameResults> gameResultsList = new ArrayList<>();
        if (file.exists()) {
            try {
                gameResultsList = mapper.readValue(file, new TypeReference<List<GameResults>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return gameResultsList.stream().collect(Collectors.groupingBy(GameResults::getWinnerName, Collectors.counting()));
    }





}
