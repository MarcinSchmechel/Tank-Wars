package com.tankwars.logic;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WallElement implements GameElement {
    @Override
    public ImageView getImage() {
//        Rectangle rectangle = new Rectangle(20,20);
//        rectangle.setFill(Color.TRANSPARENT);
        ImageView imageView = new ImageView("wallElement.png");
        return imageView;
//        skonwertowac rectangle na imageview lub załadować z obrazka
    }
}
