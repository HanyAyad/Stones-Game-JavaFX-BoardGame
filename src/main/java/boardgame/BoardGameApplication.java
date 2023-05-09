package boardgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BoardGameApplication extends Application {
    private Stage stage;
    Scene scene1;
    public Scene scene2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;

        // Load the first FXML file and create its controller
        FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("playerNames.fxml"));
        Parent root1 = loader1.load();
        BoardGameNamesController controller1 = loader1.getController();
        controller1.setApplication(this);

        // Load the second FXML file and create its controller
        FXMLLoader loader2 = new FXMLLoader(getClass().getClassLoader().getResource("ui.fxml"));
        Parent root2 = loader2.load();
        BoardGameController controller2 = loader2.getController();
        controller2.setApplication(this);

        // Create scenes
        scene1 = new Scene(root1 );
        scene2 = new Scene(root2);


        // Show the first scene
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    // Method to switch scenes
    public void switchScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}