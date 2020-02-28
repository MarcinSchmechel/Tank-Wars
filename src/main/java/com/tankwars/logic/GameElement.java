package com.tankwars.logic;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public interface GameElement {
    ImageView getImage();
    TankDirection getDirection();
}
