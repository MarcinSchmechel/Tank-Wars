package com.tankwars.gfx;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardCreator extends Parent {

    public GridPane BoardCreate(int size, int width,int height){
        GridPane grid = new GridPane();
        if(size>=1){
            for(int i=0; i<size; i++){
                for(int j=0; j<size; j++){
                    Rectangle rectangle = new Rectangle(width,height);
                    grid.add(rectangle,i,j);
                }
            }
        }
        grid.setAlignment(Pos.CENTER);
        return grid;
    }
}
