package com.tankwars.logic;

import javafx.scene.image.ImageView;

public class BootElement implements GameElement {
    private TankDirection bootDirection;

    public ImageView getImage() {
        String directionName = "graphicElements/bootElementNorth.png";

        if(bootDirection == TankDirection.NORTH){
            directionName = "graphicElements/bootElementNorth.png";
        } else if(bootDirection == TankDirection.SOUTH){
            directionName = "graphicElements/bootElementSouth.png";
        }else if(bootDirection == TankDirection.WEST){
            directionName = "graphicElements/bootElementEast.png";
        }else if(bootDirection == TankDirection.EAST){
            directionName = "graphicElements/bootElementWest.png";
        }
        return new ImageView(directionName);
    }

    @Override
    public TankDirection getDirection(){
        return this.bootDirection;
    }

    public void setRandomDirection() {
        int localRandom = (int)(Math.random() *4);
        switch (localRandom){
            case 0:
                bootDirection = TankDirection.EAST;
                break;
            case 1:
                bootDirection = TankDirection.WEST;
                break;
            case 2:
                bootDirection = TankDirection.NORTH;
                break;
            case 3:
                bootDirection = TankDirection.SOUTH;
                break;
        }
    }
}