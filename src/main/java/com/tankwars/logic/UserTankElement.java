package com.tankwars.logic;

import javafx.scene.image.ImageView;

public class UserTankElement implements GameElement {
    private TankDirection direction = TankDirection.NORTH;
    @Override
    public ImageView getImage() {
//        w zależności od kierunkui zwwracać różne obrazki
        ImageView imageView = new ImageView("userTankElementNorth.png");
        return imageView;
    }
}