package com.badlogic.androidgames.mrnom;

import java.util.List;

import com.badlogic.androidgames.framework.Interface.Game;
import com.badlogic.androidgames.framework.Interface.Graphics;
import com.badlogic.androidgames.framework.Interface.Screen;
import com.badlogic.androidgames.framework.Interface.Input.TouchEvent;

public class HighScoreScreen extends Screen
{
	String lines[] = new String[5]; 
	
	public HighScoreScreen(Game game)
	{
		// "send" game to Screen class.
		super(game);
		
		for(int ix = 0; ix < 5; ix++)
		{
			lines[ix] = "" + (ix + 1) + ". " + Settings.highscores[ix];
		}
	}

	@Override
	public void update(float deltaTime)
	{
		// Get all touch events since last update. 
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		// Clear all key events if any. (we do not need them here so we just empty key events buffer).
		game.getInput().getKeyEvents();

		int len = touchEvents.size();
		for (int ix = 0; ix < len; ix++)
		{
			TouchEvent event = touchEvents.get(ix);
			if(event.type == TouchEvent.TOUCH_UP)
			{ 
				if(event.x < 64 && event.y > 416)
				{
					Common.playClick();
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime)
	{
		Graphics graphics = game.getGraphics();
		
		graphics.drawPixmap(Assets.background, 0, 0);
		graphics.drawPixmap(Assets.mainMenu, 64, 20, 0, 42, 196, 42);

		int y = 100;		
		for (int ix = 0; ix < 5; ix++)
		{
			Common.drawText(graphics, lines[ix], 20, y);
			y += 50;
		}
		
		graphics.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);

		// Testing - draw first pointer coords to screen.
		int x1 = game.getInput().getTouchX(0);
		int y1 = game.getInput().getTouchY(0);

		String txt = String.format("x:%s - y:%s", x1, y1);		
		//graphics.drawText(txt, x1, y1);
		graphics.drawText(txt, 0, 0);
	}
	


	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void dispose()
	{
	}
}
