package org.example.graphics6;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BezierCurve bezierCurve = new BezierCurve();

        StackPane root = new StackPane();
        root.getChildren().add(bezierCurve);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Krzywa Béziera");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
