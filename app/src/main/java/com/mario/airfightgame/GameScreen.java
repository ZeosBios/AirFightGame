package com.mario.airfightgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


import com.mario.framework.Game;
import com.mario.framework.Graphics;
import com.mario.framework.Image;
import com.mario.framework.Input.TouchEvent;
import com.mario.framework.Screen;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;

	// Variable Setup

	private static Background bg1;
	private static Robot robot;
	private static JoyStickClass js;
	private static Heliboy hel;
	private static Bomber bomb;
	private static ArrayList<Heliboy> hb = new ArrayList<Heliboy>();
	private static ArrayList<Bomber> bm = new ArrayList<Bomber>();
	private static Bullit b;
	private ArrayList<Bullit> bull = new ArrayList<Bullit>();

	private static Ship sh;
	private ArrayList<Ship> ships = new ArrayList<Ship>();

	private final Timer time = new Timer();
	private Image currentSprite, character, character2, character3, character4, heliboy, button, stick, shootbutton, bullit, bomber, light;
	private Animation anim, hanim, animsh;

	private Image ship1, ship2, ship3, ship4, ship5;
	private ArrayList<Tile> tilearray = new ArrayList<Tile>();


	int livesLeft = 1;
	Paint paint, paint2;

	public GameScreen(Game game) {
		super(game);

		// Initialize game objects here

		bg1 = new Background(0, 0);
		robot = new Robot();

		hb.add(new Heliboy(1290, robot.getCenterY()));
		bm.add(new Bomber(1290, robot.getCenterY()));

		ship1 = Assets.ship;
		ship2 = Assets.ship;
		ship3 = Assets.ship;
		ship4 = Assets.ship;
		ship5 = Assets.ship;

		for (int i = 0; i < 5; i++) {
			ships.add(new Ship((1 + (int) (Math.random() * 1270)), 1 + (int) (Math.random() * 800)));
		}

		time.schedule(new TimerTask(){
			@Override
			public void run() {
				bull.add(new Bullit(hel.getCenterX() + 45, hel.getCenterY() + 45));
			}
		}, 1500, 1500);

		character = Assets.character;
		character2 = Assets.character2;
     	character3 = Assets.character3;
     	character4 = Assets.character4;

		heliboy = Assets.heliboy;
		bomber = Assets.bomber;
		light = Assets.light;

		anim = new Animation();
		anim.addFrame(character, 50);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character4, 50);

		animsh = new Animation();
		animsh.addFrame(light, 50);

		hanim = new Animation();
		hanim.addFrame(heliboy, 100);
		//hanim.addFrame(heliboy2, 100);

		button = Assets.button;
		shootbutton = Assets.shootButton;
		stick = Assets.stick;
		bullit = Assets.bullit;

		js = new JoyStickClass(0, 325, stick.getWidth(), stick.getHeight());
		js.setMinimumDistance(5);

		currentSprite = anim.getImage();

		loadMap();

		// Defining a paint object
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);

		paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);

	}

	private void loadMap() {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;

		Scanner scanner = new Scanner(SampleGame.map);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			// no more lines to read
			if (line == null) {
				break;
			}
			if (!line.startsWith("!")) {
                lines.add(line);
			    width = Math.max(width, line.length());
			}
        }
		height = lines.size();

		for (int j = 0; j < lines.size(); j++) {
            String line = (String) lines.get(j);
            for (int i = 0; i < width; i++) {

                if (i < line.length()) {
                    char ch = line.charAt(i);
                    Tile t = new Tile(i, j, Character.getNumericValue(ch));
                    tilearray.add(t);
                }

            }
        }
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.
		// Refer to Unit 3's code. We did a similar thing without separating the
		// update methods.

		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateReady(List<TouchEvent> touchEvents) {

		// This example starts with a "Ready" screen.
		// When the user touches the screen, the game begins.
		// state now becomes GameState.Running.
		// Now the updateRunning() method will be called!

		if (touchEvents.size() > 0)
			state = GameState.Running;
	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

		// This is identical to the update() method from our Unit 2/3 game.

		// 1. All touch input is handled here:
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DRAGGED) {
				if (inBounds(event, 0, 325, 190, 185)) {
					js.drawPosition(event, 0, 325, 190, 185);
				}

				int direction = js.get8Direction();
				if (direction == JoyStickClass.STICK_UP) {
					robot.moveUp();

				} else if (direction == JoyStickClass.STICK_UPRIGHT) {
					robot.moveUpRight();

				} else if (direction == JoyStickClass.STICK_RIGHT) {
					robot.moveRight();

				} else if (direction == JoyStickClass.STICK_DOWNRIGHT) {
					robot.moveDownRight();

				} else if (direction == JoyStickClass.STICK_DOWN) {
					robot.moveDown();

				} else if (direction == JoyStickClass.STICK_DOWNLEFT) {
					robot.moveDownLeft();

				} else if (direction == JoyStickClass.STICK_LEFT) {
					robot.moveLeft();

				} else if (direction == JoyStickClass.STICK_UPLEFT) {
					robot.moveUpLeft();

				} else if (direction == JoyStickClass.STICK_NONE) {

				}
			}
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 1000, 650, 65, 65)) {
					robot.shoot();
				}
			}
				if (event.type == TouchEvent.TOUCH_UP) {
				/*	if (inBounds(event, 80, 325, 80, 80)) {
						robot.stopUp();
					} else if (inBounds(event, 0, 405, 80, 80)) {
						robot.stopLeft();
					} else if (inBounds(event, 160, 405, 80, 80)) {
						robot.stopRight();
					} else if (inBounds(event, 80, 485, 80, 80)) {
						robot.stopDown();
					}*/
					if (inBounds(event, 0, 0, 35, 35)) {
						pause();
					}

				}
			}

		// 2. Check miscellaneous events like death:



		if (livesLeft == 0) {
			state = GameState.GameOver;
		}

		// 3. Call individual update() methods here.
		// This is where all the game updates happen.
		// For example, robot.update();
		robot.update(js.getAngle());

		ArrayList projectiles = robot.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			if (p.isVisible() == true) {
				p.update(js.getAngle());
			} else {
				projectiles.remove(i);
			}
		}

		for (int i = 0; i < bull.size(); i++) {
			b = (Bullit) bull.get(i);
			if (b.isVisible() == true && hel.getVisible()== true) {
				b.update(hel.getAngle());
			} else {
				bull.remove(i);
			}
		}

		updateTiles();

		for (int i = 0; i < hb.size(); i++) {
               hel = (Heliboy) hb.get(i);
			if(hel.getVisible() == true) {
				hel.update();
			}else
			{
				hb.remove(i);
				if (hb.size() == 0){
					hb.add(new Heliboy((0 + (int) (Math.random() * 1290)), -10 + (int) (Math.random() * 0)));
				}
			}
        }

		for (int i = 0; i < bm.size(); i++) {
			bomb = (Bomber) bm.get(i);
			if(bomb.getVisible() == true) {
				bomb.update();
			}else
			{
				bm.remove(i);
				if (bm.size() == 0){
					bm.add(new Bomber(1290, getRobot().getCenterY()));
				}
			}
		}

		for (int i = 0; i < ships.size(); i++) {
			sh = (Ship) ships.get(i);
			if (sh.getVisible()== true) {
				sh.update();
			} else {
				ships.remove(i);
			}
		}

		if (Rect.intersects(ships.get(0).getR(), bomb.getR())) {
			ship1 = animsh.getImage();
	    }
		if (Rect.intersects(ships.get(1).getR(), bomb.getR())) {
			ship2 = animsh.getImage();
		}
		if (Rect.intersects(ships.get(2).getR(), bomb.getR())) {
			ship3 = animsh.getImage();
		}
		if (Rect.intersects(ships.get(3).getR(), bomb.getR())) {
			ship4 = animsh.getImage();
		}
		if (Rect.intersects(ships.get(4).getR(), bomb.getR())) {
			ship5 = animsh.getImage();
		}

		bg1.update();
		animate();

		//if (robot.getCenterY() > 800) {
		//	state = GameState.GameOver;
		//}

		if(robot.life == false) {
			state = GameState.GameOver;
		}
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 800, 240)) {

					if (!inBounds(event, 0, 0, 35, 35)) {
						resume();
					}
				}

				if (inBounds(event, 0, 240, 800, 240)) {
					nullify();
					goToMenu();
				}
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 0, 0, 800, 480)) {
					nullify();
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}

	}

	private void updateTiles() {

		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
		}

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();

		g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
		paintTiles(g);

		ArrayList projectiles = robot.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.drawImage(bullit, p.getX(), p.getY(), 0, 0, 7, 9);
		}

		for (int i = 0; i < bull.size(); i++) {
			b = (Bullit) bull.get(i);
			g.drawImage(bullit, b.getX(), b.getY(), 0, 0, 7, 9);
		}

			// First draw the game elements.
		g.drawImage(currentSprite, js.getAngle(), robot.getCenterX(),
				robot.getCenterY(), currentSprite.getWidth(), currentSprite.getWidth());

		//g.drawImage(currentSprite,  robot.getCenterX() - 61,
		//		robot.getCenterY() - 63);

        for (int i = 0; i < hb.size(); i++) {
             hel = (Heliboy) hb.get(i);
            g.drawImage(heliboy, hel.getAngle(), hel.getCenterX(),
					hel.getCenterY(), heliboy.getWidth(), heliboy.getHeight());
        }

		for (int i = 0; i < bm.size(); i++) {
			bomb = (Bomber) bm.get(i);
			g.drawImage(bomber, bomb.getCenterX(),
					bomb.getCenterY(), 0, 0,  bomber.getWidth(), bomber.getHeight());
		}

		g.drawImage(ship1, ships.get(0).getCenterX(), ships.get(0).getCenterY(),0 , 0, ship1.getWidth(), ship1.getHeight());
		g.drawImage(ship2, ships.get(1).getCenterX(), ships.get(1).getCenterY(),0 , 0, ship2.getWidth(), ship2.getHeight());
		g.drawImage(ship3, ships.get(2).getCenterX(), ships.get(2).getCenterY(),0 , 0, ship3.getWidth(), ship3.getHeight());
		g.drawImage(ship4, ships.get(3).getCenterX(), ships.get(3).getCenterY(),0 , 0, ship4.getWidth(), ship4.getHeight());
		g.drawImage(ship5, ships.get(4).getCenterX(), ships.get(4).getCenterY(),0 , 0, ship5.getWidth(), ship5.getHeight());


		// Example:
		// g.drawImage(Assets.background, 0, 0);
		// g.drawImage(Assets.character, characterX, characterY);

		// Secondly, draw the UI above the game elements.
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();
	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			if (t.type != 0) {
				g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
			}
		}
	}

	public void animate() {
		anim.update(10);
		hanim.update(50);
	}

	private void nullify() {

		// Set all variables to null. You will be recreating them in the
		// constructor.
		paint = null;
		bg1 = null;
		robot = null;
		js = null;
		hel = null;
		currentSprite = null;
		character = null;
		character2 = null;
		character3 = null;
		character4 = null;
		heliboy = null;
		bomber = null;
		button = null;
		shootbutton = null;
		stick = null;
		anim = null;
		hanim = null;
		animsh = null;
		b = null;
		sh = null;
		bullit = null;
		ship1 = null;
		ship2 = null;
		ship3 = null;
		ship4 = null;
		ship5 = null;
		light = null;

		// Call garbage collector to clean up memory.
		System.gc();

	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap to Start.", 400, 240, paint);

	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();
		g.drawImage(button, 0, 325, 0, 0, 190, 185);
		g.drawImage(button, 0, 0, 0, 195, 35, 35);
		g.drawImage(shootbutton, 1000, 650, 0, 0, 65, 65);
		g.drawImage(stick, (int)js.getDrawPosX(), (int)js.getDrawPosY());
		g.drawString("Your health: " + Integer.toString(robot.getHealth()), 400, 150, paint);

	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Resume", 400, 165, paint2);
		g.drawString("Menu", 400, 360, paint2);

	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 1281, 801, Color.BLACK);
		g.drawString("GAME OVER.", 400, 240, paint2);
		g.drawString("Tap to return.", 400, 290, paint);

	}
	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;

	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

	private void goToMenu() {
		// TODO Auto-generated method stub
		game.setScreen(new MainMenuScreen(game));

	}

	public static Background getBg1() {
		// TODO Auto-generated method stub
		return bg1;
	}

	public static Robot getRobot() {
		// TODO Auto-generated method stub
		return robot;
	}
	public static Heliboy getHel() {
		// TODO Auto-generated method stub
		return hel;
	}

	public static ArrayList<Heliboy> getHb() {

		return hb;
	}

	public static ArrayList<Bomber> getBm() {

		return bm;
	}

	public static Bomber getBomb() {
		// TODO Auto-generated method stub
		return bomb;
	}

}