package com.badlogic.androidgames.mrnom;

import java.util.List;

import com.badlogic.androidgames.framework.Interface.Game;
import com.badlogic.androidgames.framework.Interface.Graphics;
import com.badlogic.androidgames.framework.Interface.Input.TouchEvent;
import com.badlogic.androidgames.framework.Interface.Screen;

public class MainMenuScreen extends Screen
{
	public MainMenuScreen(Game game)
	{
		super(game);
	}

	@Override
	public void update(float deltaTime)
	{
		Graphics graphics = game.getGraphics();
		// Get all touch events since last update. 
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		// Clear all key events if any. (we do not need them here).
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();		
		for (int ix = 0; ix < len; ix++)
		{
			TouchEvent event = touchEvents.get(ix);
			if(event.type == TouchEvent.TOUCH_UP)
			{
				if (isTouchEventInBounds(event, 0, graphics.getHeight() - 64, 64, 64))
				{
	            	Settings.soundEnabled = !Settings.soundEnabled;
	            	Common.playClick();
				}
				
				if (isTouchEventInBounds(event, 64, 220, 192, 42))
				{
					game.setScreen(new GameScreen(game));
	            	Common.playClick();
	            	return;
				}
				
				if (isTouchEventInBounds(event, 64, 220 + 42, 192, 42))
				{
					game.setScreen(new HighScoreScreen(game));
	            	Common.playClick();
	                return;
				}
	                
				if (isTouchEventInBounds(event, 64, 220 + 84, 192, 42))
				{
					game.setScreen(new HelpScreen1(game));
					Common.playClick();
	                return;
				}
			}
		}
	}
	
    private boolean isTouchEventInBounds(TouchEvent event, int x, int y, int width, int height)
    {
        if(event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1)
        {
        	return true;        	
        }
        else
        {
            return false;
        }            
    }	
    
	@Override
	public void present(float deltaTime)
	{
        Graphics graphics = game.getGraphics();
        
        graphics.drawPixmap(Assets.background, 0, 0);
        graphics.drawPixmap(Assets.logo, 32, 20);
        graphics.drawPixmap(Assets.mainMenu, 64, 220);
        
        if(Settings.soundEnabled)
        {
            graphics.drawPixmap(Assets.buttons, 0, 416, 0, 0, 64, 64);
        }
        else
        {
            graphics.drawPixmap(Assets.buttons, 0, 416, 64, 0, 64, 64);
        }
	}

	@Override
	public void pause()
	{
		Settings.save(game.getFileIO());
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
