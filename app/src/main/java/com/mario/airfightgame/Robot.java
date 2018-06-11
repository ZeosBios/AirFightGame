package com.mario.airfightgame;


import java.util.ArrayList;

import android.graphics.Rect;

import com.mario.framework.Input;

public class Robot {

	// Constants are Here
	final int MOVESPEED = 2;
	private int centerX = 600;
	private int centerY = 700;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean movingUp = false;
	private boolean movingDown = false;
	private boolean readyToFire = true;
	private int speedX = 0;
	private int speedY = 0;
	public int health = 10;
	public boolean life = true;
	public static Rect rect = new Rect(0, 0, 0, 0);
	
	private Background bg1 = GameScreen.getBg1();

	public Input.TouchEvent event;

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public void update(float angle) {

		centerX += speedX; //* sin(angle);
		centerY += speedY; //* cos(angle);

		//if (speedX == 0 || speedX < 0) {
		//	bg1.setSpeedX(0);
		//}
		if (centerX < 0) {
			centerX = 1280;
		}
		else if (centerX > 1280){
			centerX = 0;
		}
		else if (centerY > 800){
			centerY = 0;
		}
		else if (centerY < 0){
			centerY = 800;
		}


		//if (speedX > 0 && centerX > 800) {
	//		bg1.setSpeedX(-MOVESPEED / 2);
	//	}
	//	else if (speedX < 0 && centerX < 200) {
	//		bg1.setSpeedX(MOVESPEED / 2);
	//	}
	//	else if (speedY < 0 && centerY < 200) {
	//		bg1.setSpeedY(MOVESPEED / 2);
	//	}
	//	else if (speedY > 0 && centerY > 500) {
	//		bg1.setSpeedY(-MOVESPEED / 2);
	//	}
	//	else{
	//		bg1.setSpeedX(0);
	//		bg1.setSpeedY(0);
	//	}
		// Updates Y Position
		if (centerY + speedY <= 45) {
			centerY = 46;
		}
		// Prevents going beyond Y coordinate of 0
		if (centerX + speedX <= 45) {
			centerX = 46;
		}

		rect.set(centerX, centerY , centerX + 60, centerY + 60);
}

	public void moveRight() {
		speedX = MOVESPEED;
		speedY = 0;
	}

	public void moveLeft() {
		speedX = -MOVESPEED;
		speedY = 0;
	}

	public void moveUp() {
		speedY = -MOVESPEED;
		speedX = 0;
	}
	public void moveDown() {
		speedY = MOVESPEED;
		speedX = 0;
	}
	public void moveUpRight() {
		speedX = MOVESPEED;
		speedY = -MOVESPEED;
	}

	public void moveUpLeft() {
		speedX = -MOVESPEED;
		speedY = -MOVESPEED;

	}

	public void moveDownRight() {
		speedX = MOVESPEED;
		speedY = MOVESPEED;
	}
	public void moveDownLeft() {
		speedX = -MOVESPEED;
		speedY = MOVESPEED;
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}
	public void stopUp() {
		setMovingUp(false);
		stop();
	}
	public void stopDown() {
		setMovingDown(false);
		stop();
	}

	private void stop() {
		if (isMovingRight() == false && isMovingLeft() == false && isMovingUp() == false && isMovingDown() == false) {
			speedX  = 0;
			speedY = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true && isMovingUp() == false && isMovingDown() == false) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false && isMovingUp() == false && isMovingDown() == false) {
			moveRight();
		}

		if (isMovingRight() == false && isMovingLeft() == false && isMovingUp() == true && isMovingDown() == false) {
			moveUp();
		}

		if (isMovingRight() == false && isMovingLeft() == false && isMovingUp() == false && isMovingDown() == true) {
			moveDown();
		}
	}

	public void shoot() {
		if (readyToFire) {
			Projectile p = new Projectile(centerX + 45, centerY + 45);
			projectiles.add(p);
		}
	}
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean getLife() {
		return life;
	}

	public void setLife(boolean life) {
		this.life = life;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}


	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public ArrayList getProjectiles() {
		return projectiles;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

	public boolean isMovingUp() {
		return movingUp;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public boolean isMovingDown() {
		return movingDown;
	}

	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	}
}
