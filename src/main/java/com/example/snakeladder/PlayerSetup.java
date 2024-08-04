package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PlayerSetup extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Player Setup");

        TextField player1NameField = new TextField();
        player1NameField.setPromptText("Enter Player 1 Name");
        TextField player2NameField = new TextField();
        player2NameField.setPromptText("Enter Player 2 Name");

        Button startGameButton = new Button("Start Game");
        startGameButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String player1Name = player1NameField.getText();
                String player2Name = player2NameField.getText();

                SnakeLadder game = new SnakeLadder(player1Name, player2Name);
                try {
                    game.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                stage.close();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(player1NameField, player2NameField, startGameButton);

        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}
