package com.example.snakeladder;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Instructions extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Instructions");

        Label instructionsLabel = new Label("Game Rules:\n1. Roll the dice to move.\n2. Ladders help you climb up.\n3. Snakes bring you down.\n4. First to reach 100 wins!");

        VBox layout = new VBox(10);
        layout.getChildren().add(instructionsLabel);

        Scene scene = new Scene(layout, 400, 200);
        stage.setScene(scene);
        stage.show();
    }
}
