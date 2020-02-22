package com.tankwars.integration;

import com.tankwars.logic.Game;
import com.tankwars.logic.GameElement;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import static javafx.scene.input.KeyCode.*;


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
        for (int x = 0; x < 49; x++) {
            for (int y = 0; y < 49; y++) {
                ImageView imageView = game.getElement(x,y).getImage();
                gridPane.add(imageView,x,y);
            }
        }
    }

    public void handleClick(KeyCode code) {
        switch (code){
            case DOWN:
//                odnależć na planszy czołg i ustaswić mu keriunek SOUTH
                break;
            case UP:
//               odnależć na planszy czołg i ustaswić mu keriunek NORTH
                break;
//                east i west

        }
    }
    public void doMove(){
//        tą metodę wykonywać timerem co 0,5 sek.
        setRandomEnemyDirection();
        moveTanks();
        displayGame();
    }

    private void moveTanks() {
//        zmienić położenie czołgu zgodnie z kierunkiem ruchu jeżeli jest to możliwe
//        to samo zrobić z czołgiem komputera
    }

    private void setRandomEnemyDirection() {
//        wylosować i ustawić czołgowi sterowanemu przez komputer kierunek
    }
}
