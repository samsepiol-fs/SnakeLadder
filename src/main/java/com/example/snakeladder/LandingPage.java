package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LandingPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Snake and Ladder");

        Button startButton = new Button("Start Game");
        Button instructionsButton = new Button("Instructions");
        Button exitButton = new Button("Exit");

        // Styling buttons
        startButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        instructionsButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        exitButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PlayerSetup playerSetup = new PlayerSetup();
                try {
                    playerSetup.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                primaryStage.close();
            }
        });

        instructionsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Instructions instructions = new Instructions();
                try {
                    instructions.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        exitButton.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(20);
        layout.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, null)));
        layout.getChildren().addAll(startButton, instructionsButton, exitButton);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
