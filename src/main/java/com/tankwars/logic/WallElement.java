package com.tankwars.logic;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WallElement implements GameElement {
    @Override
    public ImageView getImage() {
        ImageView imageView = new ImageView("graphicElements/wallElement.png");
        return imageView;
    }

//    @Override
    public void setDirection(KeyCode code) {

    }

    @Override
    public TankDirection getDirection() {
        return null;
    }
}
