package com.tankwars.logic;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<GameRow> rows = new ArrayList<>();

    public Game() {
        for (int i = 0; i < 29; i++) {
            rows.add(new GameRow());
        }
        initBoard();
    }
    public GameElement getElement(int x, int y){
        return rows.get(y).getCols().get(x);
    }

    public void setElement(int x, int y, GameElement gameElement){
        rows.get(y).getCols().set(x,gameElement);
    }
    public void findElementAndChangeItsDirection(KeyCode code){
        for (int x = 1; x < 29; x++) {
            for (int y = 1; y < 29; y++) {
                GameElement element = getElement(x, y);
                if (element instanceof UserTankElement) {
                    ((UserTankElement)element).setDirection(code);
                }
            }
        }
    }
        public void fireBullet(){
            for (int x = 1; x < 29; x++) {
                for (int y = 1; y < 29; y++) {
                    GameElement element = getElement(x, y);
                    if (element instanceof UserTankElement) {
                        int xDirection = 0;
                        int yDirection = 0;
                        switch (element.getDirection()) {
                            case EAST:
                                xDirection = (-1);
                                break;
                            case WEST:
                                xDirection = (1);
                                break;
                            case NORTH:
                                yDirection = (-1);
                                break;
                            case SOUTH:
                                yDirection = (1);
                                break;
                        }
                        checkAndPlaceBullet(x, y, element.getDirection(), xDirection, yDirection);
                    }
                }
            }
        }
    public void fireBulletBoot(){
        for (int x = 1; x < 29; x++) {
            for (int y = 1; y < 29; y++) {
                GameElement element = getElement(x, y);
                if (element instanceof BootElement) {
                    int xDirection = 0;
                    int yDirection = 0;
                    switch (element.getDirection()) {
                        case EAST:
                            xDirection = (-1);
                            break;
                        case WEST:
                            xDirection = (1);
                            break;
                        case NORTH:
                            yDirection = (-1);
                            break;
                        case SOUTH:
                            yDirection = (1);
                            break;
                    }
                    checkAndPlaceBullet(x, y, element.getDirection(), xDirection, yDirection);
                }
            }
        }
    }
    private void checkAndPlaceBullet(int x, int y, TankDirection tankDirection, int xDirection, int yDirection) {
        if(getElement((x + xDirection),(y + yDirection)) instanceof NoneElement){
            setElement((x + xDirection), (y + yDirection), new BulletElement(tankDirection));
        } else if (getElement((x + xDirection),(y + yDirection)) instanceof BootElement || getElement((x + xDirection),(y + yDirection)) instanceof UserTankElement){
            setElement((x + xDirection), (y + yDirection), new NoneElement());
        }
    }

    public void findBootAndChangeItsDirection(){
        for (int x = 1; x < 29; x++) {
            for (int y = 1; y < 29; y++) {
                GameElement element = getElement(x, y);
                if (element instanceof BootElement) {
                    ((BootElement)element).setRandomDirection();
                }
            }
        }
    }

    public void initBoard(){
        createBoardFrame();
        placeTanks();

        for (int i = 0; i < 15; i++) {
            placeWallHorizontal();
        }
        for (int i = 0; i < 15; i++) {
            placeWallVertical();
        }
        for (int i = 0; i < 50; i++) {
            placeWallElementInRandomEmptyPosition();
        }
    }

    private void createBoardFrame() {
        for (int x = 0; x < 29; x++)
        {
            setElement(x,0,new WallElement());
            setElement(x,28,new WallElement());
        }
        for (int y = 0; y < 29; y++)
        {
            setElement(0,y,new WallElement());
            setElement(28,y,new WallElement());
        }
    }

    private void placeTanks() {
        setElement(12,27,new UserTankElement());
        setElement(12,1,new BootElement());
        setElement(1,12,new BootElement());
        setElement(27,12,new BootElement());
    }

    public void placeWallHorizontal(){
        int x = getRandomNumberInRange();
        int y = getRandomNumberInRange();

        while(!(getElement(x,y) instanceof NoneElement)){
            x = randomNumberInRange(2, 26);
            y = randomNumberInRange(2, 26);
        }
        setElement(x,y,new WallElement());

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < randomNumber(9); j++) {
                if (x + j < 27) {
                    if (getElement(x + j, y) instanceof NoneElement) {
                        setElement(x + j, y, new WallElement());
                    }
                }
            }
        }
    }

    public void placeWallVertical(){
        int x = getRandomNumberInRange();
        int y = getRandomNumberInRange();

        while(!(getElement(x,y) instanceof NoneElement)){
            x = randomNumberInRange(2, 26);
            y = randomNumberInRange(2, 26);
        }
        setElement(x,y,new WallElement());

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < randomNumber(9); j++) {
                if (y + j < 27) {
                    if (getElement(x,y+j) instanceof NoneElement) {
                        setElement(x, y+j, new WallElement());
                    }
                }
            }
        }
    }

    public static int randomNumber(int i) {
        return (int)(Math.random()*(i+1));
    }

    public void placeWallElementInRandomEmptyPosition(){
        int x = getRandomNumberInRange();
        int y = getRandomNumberInRange();

        while(!(getElement(x,y) instanceof NoneElement)){
            x = getRandomNumberInRange();
            y = getRandomNumberInRange();
        }
        setElement(x,y,new WallElement());
    }

    public int getRandomNumberInRange(){
        return randomNumberInRange(2,  26);
    }

    public static int randomNumberInRange(int x, int y) {
        int min = x;
        int max = y;
        if(x > y) {
            min = y;
            max = x;
        }
        return (int)(Math.random() * (max - min + 1) + min);
    }
}
