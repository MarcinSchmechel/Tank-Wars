package com.tankwars.integration;

import com.tankwars.logic.Game;
import com.tankwars.logic.GameElement;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class Integration {
    private Game game;
    private GridPane gridPane;

    public Integration(Game game, GridPane gridPane) {
        this.game = game;
        this.gridPane = gridPane;
    }

    public Game getGame() {
        return game;
    }

    public GridPane getGridPane() {
        return gridPane;
    }
    public void displayGame(){
        gridPane.getChildren().clear();
        for (int x = 0; x < 50; x++) {
            for (int y = 0; y < 50; y++) {
                ImageView imageView = game.getElement(x,y).getImage();
                gridPane.add(imageView,x,y);
            }
        }
    }
}
