package boardgame.model;


import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

import java.time.LocalDateTime;

/**
 * Represents the board game model.
 * The model maintains the state of the game, including the board, players, and game results.
 */
public class BoardGameModel {

    /**
     * The result record of the game.
     */
    public GameResults gameResult;

    /**
     * The manager for game results.
     */
    public GameResultsManager gameResultsManager;

    /**
     * The size of the board.
     */
    public static final int BOARD_SIZE = 3;

    /**
     * The board of squares.
     */
    public ReadOnlyObjectWrapper<Square>[][] board = new ReadOnlyObjectWrapper[BOARD_SIZE][BOARD_SIZE];

    /**
     * The current player.
     */
    public Player currentPlayer = Player.PLAYER1;

    /**
     * Flag indicating if the game is over.
     */
    public boolean gameOver = false;

    /**
     * The winner of the game.
     */
    public Player winner = null;

    /**
     * The name of player 1.
     */
    public String player1Name;

    /**
     * The name of player 2.
     */
    public String player2Name;

    /**
     * The name of the winner.
     */
    public String winnerName;



    /**
     * Constructs a new BoardGameModel object.
     * Initializes the board with empty squares and sets the game start time.
     */
    public BoardGameModel() {
        for (var i = 0; i < BOARD_SIZE; i++) {
            for (var j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new ReadOnlyObjectWrapper<Square>(Square.NONE);
            }
        }
        gameResult = new GameResults();
        gameResultsManager = new GameResultsManager();
        gameResult.setGameStartTime(LocalDateTime.now().toString());
    }



    /**
     * @param i the row index
     * @param j the column index
     * {@return the read-only property of the specified square on the board.}
     */
    public ReadOnlyObjectProperty<Square> squareProperty(int i, int j) {

        return board[i][j].getReadOnlyProperty();
    }



    /**
     * @param i the row index
     * @param j the column index
     * {@return the value of the specified square on the board.}
     */
    public Square getSquare(int i, int j) {

        return board[i][j].get();
    }



    /**
     * Method performs a move on the board at the specified position.
     * If the game is not over and the square is not GREEN, the square's value is changed according to the current player.
     * Player can't make a move to a square that is GREEN
     * Also updates the player turns and checks for a winner.
     *
     * @param i the row index
     * @param j the column index
     */
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



    /**
     * Method switches the current player.
     */
    private void switchPlayers() {

        currentPlayer = currentPlayer == Player.PLAYER1 ? Player.PLAYER2 : Player.PLAYER1;
    }



    /**
     * Method checks for a winner by iterating over the board.
     * If a row or column has all the same non-empty squares, the current player is declared the winner and the game is over.
     * If either of the two diagonals has all the same non-empty squares, the current player is declared the winner and the game is over.
     */
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



    /**
     * {@return a string representation of the board.}
     */
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



    /**
     * The main function of the program.
     * Creates a new BoardGameModel object and prints its string representation.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        var model = new BoardGameModel();
        System.out.println(model);
    }



    /**
     * @param row the row index
     * {@return true if the row has all the same non-empty squares, false otherwise}
     */
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



    /**
     * @param column the column index
     *{@return true if the column has all the same non-empty squares, false otherwise}
     */
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



    /**
     * {@return true if either diagonal has all the same non-empty squares, false otherwise}
     */
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



    /**
     * {@return the current player}
     */
    public Player getCurrentPlayer() {

        return currentPlayer;
    }



    /**
     * {@return true if the game is over, false otherwise}
     */
    public boolean isGameOver() {
        return gameOver;
    }



    /**
     * {@return the winner of the game}
     */
    public Player getWinner() {

        return winner;
    }



    /**
     * Method resets the game state.
     * If there is a winner, the game result is saved.
     * The current player is set to PLAYER1, game over flag is set to false, winner is set to null.
     * Player names, winner name, and game start time are reset.
     */
    public void resetGame(){
        if (winner!=null){ gameResultsManager.saveGameResult(gameResult);}
        currentPlayer=Player.PLAYER1;
        gameOver=false;
        winner=null;
        gameResult.setWinnerName(null);
        gameResult.setPlayer2Turns(0);
        gameResult.setPlayer1Turns(0);
        gameResult.setGameStartTime(LocalDateTime.now().toString());


    }



    /**
     * Method Sets the names of the players.
     *
     * @param player1Name the name of player 1
     * @param player2Name the name of player 2
     */
    public void setPlayerNames(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        gameResult.setPlayer1Name(player1Name);
        gameResult.setPlayer2Name(player2Name);
    }



    /**
     * Method Sets the name of the winner.
     *
     * @param winnerName the name of the winner
     */
    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
        gameResult.setWinnerName(winnerName);
    }





}
