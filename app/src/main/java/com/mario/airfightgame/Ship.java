package com.mario.airfightgame;

/**
 * Created by zinow on 08.03.2018.
 */

public class Ship extends Enemy {

    private int movementSpeed = 0;

    public Ship(int centerX, int centerY) {

        setCenterX(centerX);
        setCenterY(centerY);

        setMovementSpeed(movementSpeed);
    }

    @Override
    public void update(){

        speedX = bg.getSpeedX() * 5;
        speedY = bg.getSpeedY() * 5;
        centerX += speedX;
        centerY += speedY;

        r.set(centerX,  centerY, centerX + 60, centerY + 60);
    }

}
