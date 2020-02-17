package com.tankwars.logic;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<GameRow> rows = new ArrayList<>();

    public Game() {
        for (int i = 0; i < 50; i++) {
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

    public void initBoard(){
        createBoardFrame();
        placeTanks();

        for (int i = 0; i < 15; i++) {
            placeWallHorizontal();
        }
        for (int i = 0; i < 15; i++) {
            placeWallVertical();
        }
        for (int i = 0; i < 100; i++) {
            placeWallElementInRandomEmptyPosition();
        }

    }
    public void placeWallVertical(){
        int x = getRandomNumberInRange();
        int y = getRandomNumberInRange();

        while(getElement(x,y).getClass() != NoneElement.class){
            x = randomNumberInRange(1, 48);
            y = randomNumberInRange(1, 48);
        }
        setElement(x,y,new WallElement());

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < randomNumber(9); j++) {
                if (y + j < 49) {
                    if (getElement(x,y+j).getClass() == NoneElement.class) {
                        setElement(x, y+j, new WallElement());
                    } else {
                        continue;
                    }
                }
            }
        }
    }
    public void placeWallHorizontal(){
        int x = getRandomNumberInRange();
        int y = getRandomNumberInRange();

        while(getElement(x,y).getClass() != NoneElement.class){
            x = randomNumberInRange(1, 48);
            y = randomNumberInRange(1, 48);
        }
        setElement(x,y,new WallElement());

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < randomNumber(9); j++) {
                if (x + j < 49) {
                    if (getElement(x + j, y).getClass() == NoneElement.class) {
                        setElement(x + j, y, new WallElement());
                    } else {
                        continue;
                    }
                }
            }
        }
    }
    public void placeWallElementInRandomEmptyPosition(){
        int x = getRandomNumberInRange();
        int y = getRandomNumberInRange();

        while(getElement(x,y).getClass() != NoneElement.class){
            x = getRandomNumberInRange();
            y = getRandomNumberInRange();
        }
        setElement(x,y,new WallElement());
    }
    public int getRandomNumberInRange(){
        return randomNumberInRange(1,  48);
    }
    public static int randomNumber(int i) {
        return (int)(Math.random()*(i+1));
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
    private void placeTanks() {
        setElement(25,48,new UserTankElement());
        setElement(25,1,new BootElement());
        setElement(1,25,new BootElement());
        setElement(48,25,new BootElement());
    }

    private void createBoardFrame() {
        for (int x = 0; x < 50; x++)
        {
            setElement(x,0,new WallElement());
            setElement(x,49,new WallElement());
        }
        for (int y = 0; y < 50; y++)
        {
            setElement(0,y,new WallElement());
            setElement(49,y,new WallElement());
        }
    }
}
