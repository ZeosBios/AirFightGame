package com.mario.airfightgame;


public class Background {
	
	private int bgX, bgY, speedX, speedY;
	
	public Background(int x, int y){
		bgX = x;
		bgY = y;
		speedX = 0;
		speedY = 0;
	}
	
	public void update() {
		bgX += speedX;
		bgY +=speedY;

		if (bgX <= -4000){
			bgX += 8000;
		}
		else if (bgX >= 4000){
			bgX -= 8000;
		}
		else if (bgY <= -4000){
			bgY += 8000;
		}
		else if (bgY >= 4000){
			bgY -= 8000;
		}

	}

	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
}
