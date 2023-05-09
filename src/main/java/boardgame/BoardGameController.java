package boardgame;

import boardgame.model.BoardGameModel;
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

public class BoardGameController {

    @FXML
    private GridPane board;

    @FXML
    private ListView<String> leaderBoardList;


    private BoardGameModel model = new BoardGameModel();
    private boardgame.BoardGameApplication application;

    public int turn=0;

    //Unmaintainable values
    public int player1Wins=0;
    public int player2Wins=0;

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
        for (int i = 0; i < BoardGameModel.BOARD_SIZE; i++) {
            if (model.checkRow(i) || model.checkColumn(i)) {
                if(turn%2!=0) {
                        System.out.printf("%s wins!%n",BoardGameNamesController.player1Name);
                        leaderBoardList.getItems().add(BoardGameNamesController.player1Name+" wins");
                        player1Wins++;

                    }
                if(turn%2==0) {
                    System.out.printf("%s wins!%n",BoardGameNamesController.player2Name);
                    leaderBoardList.getItems().add(BoardGameNamesController.player2Name+" wins");
                    player2Wins++;
                }
                    board.setDisable(true);

                System.out.printf("%s has %d wins %n",BoardGameNamesController.player1Name, player1Wins);
                System.out.printf("%s has %d wins %n",BoardGameNamesController.player2Name, player2Wins);
                    return;

            }
        }
        if (model.checkDiagonal()) {
            if(turn%2!=0) {
                System.out.printf("%s wins!%n",BoardGameNamesController.player1Name);
                leaderBoardList.getItems().add(BoardGameNamesController.player1Name+" wins");
                player1Wins++;
            }
            if(turn%2==0) {
                System.out.printf("%s wins!%n",BoardGameNamesController.player2Name);
                leaderBoardList.getItems().add(BoardGameNamesController.player2Name+" wins");
                player2Wins++;
            }
            System.out.printf("%s has %d wins %n",BoardGameNamesController.player1Name, player1Wins);
            System.out.printf("%s has %d wins %n",BoardGameNamesController.player2Name, player2Wins);
            board.setDisable(true);
            return;
        }
        // System.out.println("Done"); //For Debugging
    }



    @FXML
    private void handleMouseClick(MouseEvent event) {

        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        if (model.squareProperty(row, col).get()!= Square.GREEN) {
            if(turn%2==0) {
                System.out.printf("%s clicked on square (%d,%d)%n", BoardGameNamesController.player1Name,row, col);
            }
            if(turn%2!=0){
                System.out.printf("%s clicked on square (%d,%d)%n", BoardGameNamesController.player2Name,row, col);
            }

            model.move(row, col);
            turn++;
        }
        else {
            System.out.println("Can't place other stones on top of green!");
        }
        checkWinner();

    }

    @FXML
    private void handlePlayButton(MouseEvent event) {
        if (board.isDisabled()) {
            board.setDisable(false);
            System.out.println("Game Started!");
        }
    }

    @FXML
    private void handleResetButton(MouseEvent event) {
        for (int i = 0; i < BoardGameModel.BOARD_SIZE; i++) {
            for (int j = 0; j < BoardGameModel.BOARD_SIZE; j++) {
                model.board[i][j].set(Square.NONE);
            }
        }
        board.setDisable(false);
        turn=0;
        System.out.println("Game Reset!");
    }



    public void setApplication(boardgame.BoardGameApplication application){
        this.application=application;
    }

}
