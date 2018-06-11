package com.mario.airfightgame;

import android.graphics.Rect;

import com.mario.framework.Image;

public class Tile {

	private int tileX, tileY, speedX, speedY;
	public int type;
	public Image tileImage;

	private Robot robot = GameScreen.getRobot();
	private Background bg = GameScreen.getBg1();

	private Rect r;

	public Tile(int x, int y, int typeInt) {
		tileX = x * 40;
		tileY = y * 40;

		type = typeInt;

		r = new Rect();

		if  (type == 4){
			tileImage = Assets.tilesea;

		} else {
			type = 0;
		}
	}
		public void update() {
			speedX = bg.getSpeedX() * 5;
			speedY = bg.getSpeedY() * 5;
			tileX += speedX;
			tileY += speedY;

			r.set(tileX, tileY, tileX + 40, tileY + 40);

			if (Rect.intersects(r, Robot.rect) && type != 0) {
				checkVerticalCollision(Robot.rect);
				checkSideCollision(Robot.rect);
			}
		}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	public void checkVerticalCollision(Rect rtop) {
		if (Rect.intersects(rtop, r)) {
			
		}
	}

	public void checkSideCollision(Rect rleft) {
		if (type != 5 && type != 2 && type != 0){
			if (Rect.intersects(rleft, r)) {
				robot.setCenterX(tileX + 102);

				robot.setSpeedX(0);
	
			}else if (Rect.intersects(rleft, r)) {
				
				robot.setCenterX(tileX + 85);
				robot.setSpeedX(0);
			}
			
			if (Rect.intersects(rleft, r)) {
				robot.setCenterX(tileX - 62);
	
				robot.setSpeedX(0);
			}
			
			else if (Rect.intersects(rleft, r)) {
				robot.setCenterX(tileX - 45);
				robot.setSpeedX(0);
			}
		}
	}

}