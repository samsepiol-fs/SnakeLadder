package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize = 60, width = 10, height = 10; // Increased tileSize
    public static final int buttonLine = height * tileSize + 80, infoLine = buttonLine - 40;
    private static Dice dice = new Dice();
    private Player playerOne, playerTwo;
    private boolean gameStarted = false, playerOneTurn = false, playerTwoTurn = false;
    private String playerOneName, playerTwoName;

    public SnakeLadder() {
        this("Player 1", "Player 2");
    }

    public SnakeLadder(String playerOneName, String playerTwoName) {
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
    }

    private Pane createContent() {
        Pane root = new Pane();
        root.setPrefSize(width * tileSize, height * tileSize + 150); // Increased pane size

        // Create tiles
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j * tileSize);
                tile.setTranslateY(i * tileSize);
                root.getChildren().add(tile);
            }
        }

        Image img = new Image(getClass().getResourceAsStream("/istockphoto-531466314-1024x1024.jpg"));
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height * tileSize);
        board.setFitWidth(width * tileSize);

        // Create buttons
        Button player1Button = new Button(playerOneName);
        Button player2Button = new Button(playerTwoName);
        Button startButton = new Button("Start");

        // Style buttons
        player1Button.getStyleClass().add("button");
        player2Button.getStyleClass().add("button");
        startButton.getStyleClass().add("button");

        // Align buttons
        player1Button.setTranslateY(buttonLine);
        player1Button.setTranslateX(20);
        player1Button.setPrefSize(140, 50);

        player2Button.setTranslateY(buttonLine);
        player2Button.setTranslateX(200);
        player2Button.setPrefSize(140, 50);

        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(420);
        startButton.setPrefSize(140, 50);

        // Create labels
        Label player1Label = new Label("");
        Label player2Label = new Label("");
        Label diceLabel = new Label("Roll!");

        // Style labels
        player1Label.getStyleClass().add("label");
        player2Label.getStyleClass().add("label");
        diceLabel.getStyleClass().add("label");

        // Align labels
        player1Label.setTranslateY(infoLine);
        player1Label.setTranslateX(20);

        player2Label.setTranslateY(infoLine);
        player2Label.setTranslateX(180);

        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(420);

        playerOne = new Player(tileSize, Color.BLACK, playerOneName);
        playerTwo = new Player(tileSize, Color.WHITE, playerTwoName);

        player1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStarted && playerOneTurn) {
                    int diceValue = dice.getRolledDiceValue();
                    diceLabel.setText("Dice Value: " + diceValue);
                    playerOne.movePlayer(diceValue);

                    if (playerOne.isWinner()) {
                        diceLabel.setText("Winner is " + playerOne.getName() + "!");
                        endGame(player1Button, player2Button, startButton, player1Label, player2Label);
                    } else {
                        switchTurn(player1Button, player2Button, player1Label, player2Label);
                    }
                }
            }
        });

        player2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStarted && playerTwoTurn) {
                    int diceValue = dice.getRolledDiceValue();
                    diceLabel.setText("Dice Value: " + diceValue);
                    playerTwo.movePlayer(diceValue);

                    if (playerTwo.isWinner()) {
                        diceLabel.setText("Winner is " + playerTwo.getName() + "!");
                        endGame(player1Button, player2Button, startButton, player1Label, player2Label);
                    } else {
                        switchTurn(player1Button, player2Button, player1Label, player2Label);
                    }
                }
            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);

                playerOneTurn = true;
                player1Label.setText("Your Turn " + playerOne.getName());
                player1Button.setDisable(false);
                playerOne.startingPosition();

                playerTwoTurn = false;
                player2Label.setText("");
                player2Button.setDisable(true);
                playerTwo.startingPosition();
            }
        });

        root.getChildren().addAll(board, player2Button, player1Button, startButton, player1Label, player2Label, diceLabel, playerOne.getCoin(), playerTwo.getCoin());
        return root;
    }

    private void endGame(Button player1Button, Button player2Button, Button startButton, Label player1Label, Label player2Label) {
        playerOneTurn = false;
        playerTwoTurn = false;
        player1Button.setDisable(true);
        player1Label.setText("");

        player2Button.setDisable(true);
        player2Label.setText("");

        startButton.setDisable(false);
        startButton.setText("Restart Game");
        gameStarted = false;
    }

    private void switchTurn(Button player1Button, Button player2Button, Label player1Label, Label player2Label) {
        playerOneTurn = !playerOneTurn;
        playerTwoTurn = !playerTwoTurn;

        player1Button.setDisable(!playerOneTurn);
        player2Button.setDisable(!playerTwoTurn);

        player1Label.setText(playerOneTurn ? "Your Turn " + playerOne.getName() : "");
        player2Label.setText(playerTwoTurn ? "Your Turn " + playerTwo.getName() : "");
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setTitle("Snakes & Ladders!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
