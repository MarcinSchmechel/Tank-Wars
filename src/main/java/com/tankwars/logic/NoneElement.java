package com.tankwars.logic;

import javafx.scene.image.ImageView;

public class NoneElement implements GameElement {
    @Override
    public ImageView getImage() {
        ImageView imageView = new ImageView("noneElement.png");
        return imageView;
    }

}
