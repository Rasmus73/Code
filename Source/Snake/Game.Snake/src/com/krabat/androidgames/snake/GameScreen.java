package com.krabat.androidgames.snake;

import java.util.List;

import android.graphics.Color;

import com.krabat.androidgames.framework.Interface.Game;
import com.krabat.androidgames.framework.Interface.Graphics;
import com.krabat.androidgames.framework.Interface.Pixmap;
import com.krabat.androidgames.framework.Interface.Screen;
import com.krabat.androidgames.framework.Interface.Input.TouchEvent;
import com.krabat.androidgames.snake.model.Snake;
import com.krabat.androidgames.snake.model.SnakePart;
import com.krabat.androidgames.snake.model.Stain;
import com.krabat.androidgames.snake.model.World;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;

	World world;
	int oldScore = 0;
	String score = "0";

	public GameScreen(Game game) {
		super(game);
		world = new World();
	}

	@Override
	public void update(float deltaTime) {
		// Get all touch events since last update.
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		// Clear all key events if any. (we do not need them here so we just
		// empty key events buffer).
		game.getInput().getKeyEvents();

		if (state == GameState.Ready) {
			updateReady(touchEvents);
		}
		if (state == GameState.Running) {
			updateRunning(touchEvents, deltaTime);
		}
		if (state == GameState.Paused) {
			updatePaused(touchEvents);
		}
		if (state == GameState.GameOver) {
			updateGameOver(touchEvents);
		}
	}

	private void updateReady(List<TouchEvent> touchEvents) {
		if (touchEvents.size() > 0) {
			state = GameState.Running;
		}
	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		int len = touchEvents.size();
		for (int ix = 0; ix < len; ix++) {
			TouchEvent event = touchEvents.get(ix);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.x < 64 && event.y < 64) // check pause button
													// location.
				{
					Common.playClick();
					state = GameState.Paused;
					return;
				}
			}

			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (event.x < 64 && event.y > 416) {
					world.snake.turnCounterClockwise();
				}
				if (event.x > 256 && event.y > 416) {
					world.snake.turnClockwise();
				}
			}
		}

		world.update(deltaTime);

		if (world.gameOver) {
			Common.playBitten();
			state = GameState.GameOver;
		}

		if (oldScore != world.score) {
			oldScore = world.score;
			score = "" + oldScore;
			Common.playEatStain();
		}
	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();

		for (int ix = 0; ix < len; ix++) {
			TouchEvent event = touchEvents.get(ix);

			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.x > 80 && event.x <= 240) {
					if (event.y > 100 && event.y <= 148) // check continue
															// button location
					{
						Common.playClick();
						state = GameState.Running;
						return;
					}

					if (event.y > 148 && event.y < 196) // check end game button
					{
						Common.playClick();
						game.setScreen(new MainMenuScreen(game));
						return;
					}
				}
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();

		for (int ix = 0; ix < len; ix++) {
			TouchEvent event = touchEvents.get(ix);

			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.x >= 128 && event.x <= 192 && event.y >= 200
						&& event.y <= 264) // check continue after game over
											// button location
				{
					Common.playClick();
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics graphics = game.getGraphics();

		graphics.drawPixmap(Assets.background, 0, 0);

		drawWorld(world);

		switch (state) {
		case Ready:
			drawReadyUI();
			break;
		case Running:
			drawRunningUI();
			break;
		case Paused:
			drawPausedUI();
			break;
		case GameOver:
			drawGameOverUI();
			break;
		}

		int x = graphics.getWidth() / 2 - score.length() * 20 / 2;
		int y = graphics.getHeight() - 42;
		Common.drawText(graphics, score, x, y);
	}

	private void drawWorld(World world) {
		Graphics graphics = game.getGraphics();
		Snake snake = world.snake;
		SnakePart head = snake.snakeParts.get(0);
		Stain stain = world.stain;

		// draw stain
		Pixmap stainPixmap = null;
		switch (stain.type) {
		case Stain.TYPE_1:
			stainPixmap = Assets.stain1;
			break;
		case Stain.TYPE_2:
			stainPixmap = Assets.stain2;
			break;
		case Stain.TYPE_3:
			stainPixmap = Assets.stain3;
			break;
		}

		int x = stain.x * 32;
		int y = stain.y * 32;
		graphics.drawPixmap(stainPixmap, x, y);

		// draw all snake parts (except head)
		int len = snake.snakeParts.size();
		for (int ix = 1; ix < len; ix++) {
			SnakePart part = snake.snakeParts.get(ix);
			x = part.x * 32;
			y = part.y * 32;
			graphics.drawPixmap(Assets.tail, x, y);
		}

		// draw head part
		Pixmap headPixmap = null;
		switch (snake.currentDirection) {
		case Snake.UP:
			headPixmap = Assets.headUp;
			break;
		case Snake.LEFT:
			headPixmap = Assets.headLeft;
			break;
		case Snake.DOWN:
			headPixmap = Assets.headDown;
			break;
		case Snake.RIGHT:
			headPixmap = Assets.headRight;
			break;
		}

		// set center of head part
		x = head.x * 32 + 16;
		y = head.y * 32 + 16;

		// align center of head with world coordinates center
		int headX = headPixmap.getWidth() / 2;
		int headY = headPixmap.getHeight() / 2;
		graphics.drawPixmap(headPixmap, x - headX, y - headY);
	}

	private void drawReadyUI() {
		Graphics graphics = game.getGraphics();

		graphics.drawPixmap(Assets.ready, 47, 100);
		graphics.drawLine(0, 416, 480, 416, Color.BLACK);
	}

	// draw control and pause buttons
	private void drawRunningUI() {
		Graphics graphics = game.getGraphics();

		graphics.drawPixmap(Assets.buttons, 0, 0, 64, 128, 64, 64);
		graphics.drawLine(0, 416, 480, 416, Color.BLACK);
		graphics.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
		graphics.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
	}

	// draw continue and quit buttons
	private void drawPausedUI() {
		Graphics graphics = game.getGraphics();

		graphics.drawPixmap(Assets.pause, 80, 100);
		graphics.drawLine(0, 416, 480, 416, Color.BLACK);
	}

	private void drawGameOverUI() {
		Graphics graphics = game.getGraphics();

		graphics.drawPixmap(Assets.gameOver, 62, 100);
		graphics.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);
		graphics.drawLine(0, 416, 480, 416, Color.BLACK);
	}

	@Override
	public void pause() {
		if (state == GameState.Running) {
			state = GameState.Paused;
		}

		if (world.gameOver) {
			Settings.addScore(world.score);
			Settings.save(game.getFileIO());
		}
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}