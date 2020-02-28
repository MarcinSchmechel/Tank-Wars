package com.tankwars.logic;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class UserTankElement implements GameElement {
    private TankDirection direction = TankDirection.NORTH;

    @Override
    public ImageView getImage() {
        String directionName = "graphicElements/userTankElementNorth.png";
        switch (direction){
            case EAST:
                directionName = "graphicElements/userTankElementWest.png";
                break;
            case WEST:
                directionName = "graphicElements/userTankElementEast.png";
                break;
            case NORTH:
                directionName = "graphicElements/userTankElementNorth.png";
                break;
            case SOUTH:
                directionName = "graphicElements/userTankElementSouth.png";
                break;
        }
        return new ImageView(directionName);
    }

    @Override
    public TankDirection getDirection() {
        return this.direction;
    }

    public void setDirection(KeyCode code){
        switch (code){
            case RIGHT:
                direction = TankDirection.WEST;
                break;
            case LEFT:
                direction = TankDirection.EAST;
                break;
            case UP:
                direction = TankDirection.NORTH;
                break;
            case DOWN:
                direction = TankDirection.SOUTH;
                break;
        }
    }
}