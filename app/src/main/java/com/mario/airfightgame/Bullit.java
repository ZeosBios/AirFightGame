package com.mario.airfightgame;

import android.graphics.Rect;


import static java.lang.Math.cos;
import static java.lang.Math.sin;


public class Bullit {


    private int x, y, speedX;
    private boolean visible;
    private Rect r;
    private Robot robot = GameScreen.getRobot();
    private float DEGTORAD = 0.017453f;



    public Bullit(int startX, int startY){
        x = startX;
        y = startY;
        speedX = 25;
        visible = true;

        r = new Rect(0, 0, 0, 0);
    }

    public void update(float angle) {
        float dx = (float) cos(angle * DEGTORAD)*speedX;
        float dy = (float) sin(angle * DEGTORAD)*speedX;
        x += dx;         //движение по Х со скоростью mSpeed и углу заданном координатой angle
        y += dy;;     //движение по Х со скоростью mSpeed и углу заданном координатой angle
        r.set(x, y, x + 10, y + 5);
        if (x > 800 || x < 0) {
            visible = false;
            r = null;
        }
        if (y > 800 || y < 0) {
            visible = false;
            r = null;
        }

        if (visible) {
            checkCollision();
        }
    }

    private void checkCollision() {

        if (Rect.intersects(r, Robot.rect))
        {
            visible = false;
            robot.health -= 1;
        }
        if (robot.health == 0) {
                robot.setLife(false);
        }
    }




    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
