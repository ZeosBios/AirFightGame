package com.mario.airfightgame;

import android.graphics.Rect;


public class Enemy {

	protected int power, centerX, speedX, speedY, centerY;
	protected Background bg = GameScreen.getBg1();
	protected Robot robot = GameScreen.getRobot();

	protected float distance = 0;

	protected Rect r = new Rect(0, 0, 0, 0);
	protected int health = 1;
	protected boolean visible = true;

	protected int movementSpeed = 0;

	// Behavioral Methods
	public void update() {
		//follow();
	}
	public void checkCollision() {
		if (Rect.intersects(r, Robot.rect)) {

		}
	}

	public void follow() {
		
		if (centerX < -95 || centerX > 810){
			movementSpeed = 0;
		}

		else if (Math.abs(robot.getCenterX() - centerX) < 5) {
			movementSpeed = 0;
		}

		else {

			if (robot.getCenterX() >= centerX) {
				movementSpeed = 1;
			} else {
				movementSpeed = -1;
			}
		}

	}

	public void die() {

	}

	public void attack() {


	}

	public int getMovementSpeed() {return movementSpeed;}

	public void setMovementSpeed(int movementSpeed) { this.movementSpeed = movementSpeed;}

	public boolean getVisible() {return visible;}

	public void setVisible(boolean visible) {this.visible = visible;}

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}

	public Rect getR() {
		return r;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}

	public float getDistznce()  { return distance; }

}