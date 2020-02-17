package com.tankwars.logic;

import javafx.scene.image.ImageView;

public class BootElement implements GameElement {
    @Override
    public ImageView getImage() {
        ImageView imageView = new ImageView("bootElement.png");
        return imageView;
    }
}