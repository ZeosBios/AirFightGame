package com.mario.airfightgame;

import android.graphics.Rect;


import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.sin;


public class Projectile {

	private int x, y, speedX;
	private boolean visible;
	private float DEGTORAD = 0.017453f;
	private Rect r;

	private ArrayList <Heliboy> hb = GameScreen.getHb();

	private ArrayList <Bomber> bm = GameScreen.getBm();

	public Projectile(int startX, int startY){
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
			y += dy;
			r.set(x, y, x, y );
			if (x > 1500 || x < 0) {
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
        for(int i=0; i<hb.size(); i++) {
			if (Rect.intersects(r, hb.get(i).getR())) {
				visible = false;
				if (hb.get(i).health > 0) {
					hb.get(i).health -= 1;
				}
				if (hb.get(i).health == 0) {
					hb.get(i).setVisible(false);
				}
			}
		}
		for(int i=0; i<bm.size(); i++) {
			if (Rect.intersects(r, bm.get(i).getR())) {
				visible = false;
				if (bm.get(i).health > 0) {
					bm.get(i).health -= 1;
				}
				if (bm.get(i).health == 0) {
					bm.get(i).setVisible(false);
				}
			}
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
