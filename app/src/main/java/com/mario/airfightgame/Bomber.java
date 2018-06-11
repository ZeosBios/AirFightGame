package com.mario.airfightgame;

import android.graphics.Rect;

/**
 * Created by zinow on 08.03.2018.
 */

public class Bomber extends Enemy {
    private int movementSpeed = 1;

    public Bomber(int centerX, int centerY) {

        setCenterX(centerX);
        setCenterY(centerY);

        setMovementSpeed(movementSpeed);
    }

    @Override
    public void update(){

        speedX = movementSpeed;
        centerX -= speedX;
        centerY += speedY;

        //speedX = bg.getSpeedX() * 1 + movementSpeed;
        //speedY = bg.getSpeedX() * -2;

        r.set(centerX,  centerY, centerX + 120, centerY + 120);

        if (centerX < -20) {
            centerX = 1300;
        }
        else if (centerX > 1300){
            centerX = -20;
        }
        else if (centerY > 820){
            centerY = -20;
        }
        else if (centerY < -20){
            centerY = 820;
        }

        if (Rect.intersects(r, Robot.rect)) {
            checkCollision();
        }
    }
}
