package com.tankwars.logic;

import javafx.scene.image.ImageView;

public class UserTankElement implements GameElement {
    @Override
    public ImageView getImage() {
        ImageView imageView = new ImageView("userTankElement.png");
        return imageView;
    }
}