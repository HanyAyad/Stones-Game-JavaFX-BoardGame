package boardgame.model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class BoardGameModel {

    public static final int BOARD_SIZE = 3;

    public ReadOnlyObjectWrapper<Square>[][] board = new ReadOnlyObjectWrapper[BOARD_SIZE][BOARD_SIZE];

    private Player currentPlayer = Player.PLAYER1;
    private boolean gameOver = false;
    private Player winner = null;


    public BoardGameModel() {
        for (var i = 0; i < BOARD_SIZE; i++) {
            for (var j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new ReadOnlyObjectWrapper<Square>(Square.NONE);
            }
        }
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
        currentPlayer=Player.PLAYER1;
        gameOver=false;
        winner=null;
    }



}
