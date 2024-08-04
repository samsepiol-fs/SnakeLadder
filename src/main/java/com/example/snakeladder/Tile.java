package com.example.snakeladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle{
    public Tile (int tileSize){
        setHeight(tileSize);
        setWidth(tileSize);
        setFill(Color.RED);
        setStroke(Color.BLACK);
    }
}