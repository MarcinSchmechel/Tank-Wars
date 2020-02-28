package com.tankwars.logic;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class NoneElement implements GameElement {
    @Override
    public ImageView getImage() {
        ImageView imageView = new ImageView("graphicElements/noneElement.png");
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
