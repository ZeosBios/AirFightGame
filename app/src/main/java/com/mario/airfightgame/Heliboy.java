package com.mario.airfightgame;

import android.graphics.Rect;


public class Heliboy extends Enemy {


	private int movementSpeed = 2;
	private float angle = 0;

	public Heliboy(int centerX, int centerY) {

		setCenterX(centerX);
		setCenterY(centerY);

		setMovementSpeed(movementSpeed);
	}

	@Override
	public void update(){

		int position_x = (int) (robot.getCenterX() - centerX);
		int position_y = (int) (robot.getCenterY() - centerY);
		distance = (float) Math.sqrt(Math.pow(position_x, 2) + Math.pow(position_y, 2));

		centerX += speedX * (robot.getCenterX() - centerX) / distance;
		centerY += speedX * (robot.getCenterY() - centerY) / distance;

		angle = (float) cal_angle(position_x, position_y);

        speedX =  movementSpeed;
		speedY = movementSpeed;

		r.set(centerX,  centerY, centerX + 60, centerY + 60);
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

	private double cal_angle(float x, float y) {
		if(x >= 0 && y >= 0)
			return Math.toDegrees(Math.atan(y / x));
		else if(x < 0 && y >= 0)
			return Math.toDegrees(Math.atan(y / x)) + 180;
		else if(x < 0 && y < 0)
			return Math.toDegrees(Math.atan(y / x)) + 180;
		else if(x >= 0 && y < 0)
			return Math.toDegrees(Math.atan(y / x)) + 360;
		return 0;
	}

	public float getAngle() { return angle; }
}
