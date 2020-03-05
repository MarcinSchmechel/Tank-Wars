package com.tankwars.logic;

import javafx.scene.image.ImageView;

public class BulletElement implements GameElement{
    private TankDirection bulletDirection;

    public BulletElement(TankDirection direction){
        bulletDirection = direction;
    }

    public ImageView getImage() {
        return new ImageView("graphicElements/bulletElement.png");
    }

    @Override
    public TankDirection getDirection(){
        return this.bulletDirection;
    }

    public void setDirection(TankDirection direction) {
        bulletDirection = direction;
    }
}
