package com.mario.airfightgame;

import com.mario.framework.Image;
import com.mario.framework.Music;
import com.mario.framework.Sound;

public class Assets {
	
	public static Image menu, splash, background, character, character2, character3, character4, heliboy, bullit, bomber, ship;
	public static Image tilesea, light;
	public static Image button, stick, shootButton;
	public static Sound click;
	public static Music theme;
	
	public static void load(SampleGame sampleGame) {
		// TODO Auto-generated method stub
		theme = sampleGame.getAudio().createMusic("menutheme.mp3");
		theme.setLooping(true);
		theme.setVolume(0.85f);
		theme.play();
	}
}
