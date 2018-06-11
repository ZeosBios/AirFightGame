package com.mario.airfightgame;

import com.mario.framework.Game;
import com.mario.framework.Graphics;
import com.mario.framework.Graphics.ImageFormat;
import com.mario.framework.Screen;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {
		
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
		Assets.background = g.newImage("background.png", ImageFormat.RGB565);
		Assets.character = g.newImage("character.png", ImageFormat.ARGB4444);
		Assets.character2 = g.newImage("characterLeft.png", ImageFormat.ARGB4444);
		Assets.character3  = g.newImage("characterDown.png", ImageFormat.ARGB4444);
		Assets.character4 = g.newImage("characterUp.png", ImageFormat.ARGB4444);
		//Assets.characterDown = g.newImage("down.png", ImageFormat.ARGB4444);


		
		Assets.heliboy = g.newImage("heliboy.png", ImageFormat.ARGB4444);
		Assets.bomber = g.newImage("bomber.png", ImageFormat.RGB565);
		Assets.ship = g.newImage("ship.png", ImageFormat.RGB565);
		Assets.light = g.newImage("light.png", ImageFormat.RGB565);


		Assets.tilesea = g.newImage("tilesea.png", ImageFormat.RGB565);

		Assets.button = g.newImage("buttons.png", ImageFormat.RGB565);
		Assets.stick = g.newImage("image_button.png", ImageFormat.RGB565);
		Assets.shootButton = g.newImage("shootButton.png", ImageFormat.RGB565);
		Assets.bullit = g.newImage("bullit.png", ImageFormat.RGB565);
		//This is how you would load a sound if you had one.
		//Assets.click = game.getAudio().createSound("explode.ogg");

		
		game.setScreen(new MainMenuScreen(game));

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, 0, 0);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {

	}
}