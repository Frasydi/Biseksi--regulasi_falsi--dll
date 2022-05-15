import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class NonLinier extends Application {
    Stage stage = new Stage();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent prn = FXMLLoader.load(getClass().getResource("Scene.fxml"));
        Scene sken = new Scene(prn, 800, 600);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setMaxHeight(600);
        stage.setMaxWidth(800);
        stage.setTitle("Non Linear ");
        stage.setScene(sken);
        stage.show();
    }
}
