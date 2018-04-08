package com.krabat.androidgames.snake;

import java.util.List;

import com.krabat.androidgames.framework.Interface.Game;
import com.krabat.androidgames.framework.Interface.Graphics;
import com.krabat.androidgames.framework.Interface.Input.TouchEvent;
import com.krabat.androidgames.framework.Interface.Screen;

public class HelpScreen1 extends Screen {
	public HelpScreen1(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		// Get all touch events since last update.
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		// Clear all key events if any. (we do not need them here so we just
		// empty key events buffer).
		game.getInput().getKeyEvents();

		int len = touchEvents.size();
		for (int ix = 0; ix < len; ix++) {
			TouchEvent event = touchEvents.get(ix);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.x > 256 && event.y > 416) {
					game.setScreen(new HelpScreen2(game));
					Common.playClick();
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics graphics = game.getGraphics();
		graphics.drawPixmap(Assets.background, 0, 0);
		graphics.drawPixmap(Assets.help1, 64, 100);
		graphics.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
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
}
