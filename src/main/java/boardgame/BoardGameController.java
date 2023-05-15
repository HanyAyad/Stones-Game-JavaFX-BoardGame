package boardgame;

import boardgame.model.BoardGameModel;
import boardgame.model.Player;
import boardgame.model.Square;
import javafx.beans.binding.ObjectBinding;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardGameController {

    @FXML
    private GridPane board;

    @FXML
    private ListView<String> leaderBoardList;


    private BoardGameModel model = new BoardGameModel();
    private boardgame.BoardGameApplication application;

    public int turn=0;


    @FXML
    private void initialize() {
        board.setDisable(true);
        for (var i = 0; i < board.getRowCount(); i++) {
            for (var j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare(i, j);
                board.add(square, j, i);
            }
        }


    }

    private StackPane createSquare(int i, int j) {
        var square = new StackPane();
        square.getStyleClass().add("square");
        var piece = new Circle(50);

        piece.fillProperty().bind(
                new ObjectBinding<Paint>() {
                    {
                        super.bind(model.squareProperty(i, j));
                    }
                    @Override
                    protected Paint computeValue() {
                        return switch (model.squareProperty(i, j).get()) {
                            case NONE -> Color.TRANSPARENT;
                            case RED -> Color.RED;
                            case YELLOW -> Color.YELLOW;
                            case GREEN -> Color.GREEN;
                        };
                    }
                }
        );
        square.getChildren().add(piece);
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

     public void checkWinner() {
         if (model.isGameOver()) {
             if (model.getWinner() != null) {
                 String winnerName = model.getWinner() == Player.PLAYER1 ? BoardGameNamesController.player1Name : BoardGameNamesController.player2Name;
                 model.setWinnerName(winnerName);
                 Logger.info("{} wins!", winnerName);

                 board.setDisable(true);
                 // System.out.println("Done"); //For Debugging
             }
         }
    }

    public void updateLeaderboard() {
        Map<String, Long> playerWins = model.getPlayerWins();
        List<Map.Entry<String, Long>> sortedEntries = new ArrayList<>(playerWins.entrySet());
        sortedEntries.sort(Map.Entry.<String, Long>comparingByValue().reversed());

        leaderBoardList.getItems().clear();

        for (int i = 0; i < Math.min(5, sortedEntries.size()); i++) {
            Map.Entry<String, Long> entry = sortedEntries.get(i);
            leaderBoardList.getItems().add(entry.getKey() + ": " + entry.getValue() + " wins");
        }
    }



    @FXML
    private void handleMouseClick(MouseEvent event) {

        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        String currPlayer= model.getCurrentPlayer()==Player.PLAYER1 ? BoardGameNamesController.player1Name : BoardGameNamesController.player2Name;
        if (model.squareProperty(row, col).get()!= Square.GREEN) {

            Logger.info("{} Click on square ({},{})", currPlayer ,row, col);

            model.move(row, col);
            turn++;
            checkWinner();
        }
        else {
            Logger.warn("Can't place other stones on top of green!");
        }


    }

    @FXML
    private void handlePlayButton(MouseEvent event) {
        if (board.isDisabled()) {
            board.setDisable(false);
            Logger.info("Game Started!");
            model.setPlayerNames(BoardGameNamesController.player1Name,BoardGameNamesController.player2Name);
        }
        updateLeaderboard();

    }

    @FXML
    private void handleResetButton(MouseEvent event) {
        for (int i = 0; i < BoardGameModel.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardGameModel.BOARD_SIZE; j++) {
                model.board[i][j].set(Square.NONE);
            }
        }
        turn=0;
        model.resetGame();
        board.setDisable(false);
        updateLeaderboard();
        Logger.info("Game Reset!");
    }



    public void setApplication(boardgame.BoardGameApplication application){
        this.application=application;
    }

}
