package com.tankwars.integration;

import com.tankwars.logic.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
        for (int x = 0; x < 29; x++) {
            for (int y = 0; y < 29; y++) {
                ImageView imageView = game.getElement(x,y).getImage();
                gridPane.add(imageView,x,y);
            }
        }
    }

    public void handleClick(KeyCode code) {
        if(code == KeyCode.SPACE){
            game.fireBullet();
        } else {
            game.findElementAndChangeItsDirection(code);
        }
    }

    public void doMove(){
        game.findBootAndChangeItsDirection();
        moveTanks();
        displayGame();
    }
    public void doFireBoot(){
        game.fireBulletBoot();
    }


    public void moveBullet() {
        for (int x = 1; x < 28; x++) {
            for (int y = 1; y < 28; y++) {
                GameElement element = game.getElement(x,y);
                if (element instanceof BulletElement) {
                    int xDirection = 0;
                    int yDirection = 0;
                    switch (element.getDirection()) {
                        case EAST:
                            xDirection = (-1);
                            checkAndMoveElementOneStep(x, y, element, xDirection, yDirection);
                            break;
                        case WEST:
                            xDirection = (1);
                            checkAndMoveElementOneStep(x, y, element, xDirection, yDirection);
                            x++;
                            break;
                        case NORTH:
                            yDirection = (-1);
                            checkAndMoveElementOneStep(x, y, element, xDirection, yDirection);
                            break;
                        case SOUTH:
                            yDirection = (1);
                            checkAndMoveElementOneStep(x, y, element, xDirection, yDirection);
                            y++;
                            break;
                    }
                }
            }
        }
    }
    private void moveTanks() {
        for (int x = 1; x < 28; x++) {
            for (int y = 1; y < 28; y++) {
                GameElement element = game.getElement(x,y);
                if (element instanceof BootElement ||element instanceof UserTankElement) {
                    int xDirection = 0;
                    int yDirection = 0;
                    switch (element.getDirection()) {
                        case EAST:
                            xDirection = (-1);
                            checkAndMoveElementOneStep(x, y, element, xDirection, yDirection);
                            break;
                        case WEST:
                            xDirection = (1);
                            checkAndMoveElementOneStep(x, y, element, xDirection, yDirection);
                            x++;
                            break;
                        case NORTH:
                            yDirection = (-1);
                            checkAndMoveElementOneStep(x, y, element, xDirection, yDirection);
                            break;
                        case SOUTH:
                            yDirection = (1);
                            checkAndMoveElementOneStep(x, y, element, xDirection, yDirection);
                            y++;
                            break;
                    }
                }
            }
        }
    }

    private void checkAndMoveElementOneStep(int x, int y, GameElement element, int xDirection, int yDirection) {
        if(game.getElement((x + xDirection),(y + yDirection)) instanceof NoneElement){
            game.setElement((x + xDirection), (y + yDirection), element);
            game.setElement(x, y, new NoneElement());
        } else if(element instanceof BulletElement) {
            if (game.getElement((x + xDirection), (y + yDirection)) instanceof BootElement || game.getElement((x + xDirection), (y + yDirection)) instanceof UserTankElement) {
                game.setElement((x + xDirection), (y + yDirection), new NoneElement());
            }
            game.setElement(x, y, new NoneElement());
        }
    }
}
