package boardgame;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Objects;

public class BoardGameNamesController {
    private boardgame.BoardGameApplication application;
    public static String player1Name;
    public static String player2Name;

    @FXML
    private TextField player1NameField;

    @FXML
    private TextField player2NameField;

    public void StartGameButton(javafx.event.ActionEvent event){
        // Retrieve the text entered by the user in the text fields
        player1Name = player1NameField.getText();
        player2Name = player2NameField.getText();
        //If both players entered their names
        if (!Objects.equals(player1Name, "") & !Objects.equals(player2Name, "")){
            // Switch to the next scene
            application.switchScene(application.scene2);
        }
        else {
            System.out.println("Please Enter Your Names!");
        }

    }

    public void setApplication(boardgame.BoardGameApplication application){
        this.application=application;
    }

    // Getter methods for the player name instance variables
    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }
}
