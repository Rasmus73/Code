package com.badlogic.androidgames.AwesomeExample;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public class MySuperAwesomeStartScreen extends Screen 
{
	Pixmap awesomePic;
	float x;
	Graphics gameGraphics;

	public MySuperAwesomeStartScreen(Game game)
	{
		super(game);
		gameGraphics = game.getGraphics(); 
		awesomePic = gameGraphics.newPixmap("data/pic.png", PixmapFormat.RGB565);
	}

	@Override
	public void update(float deltaTime)
	{
		/*
		// Scroll picture from left to right.
		x++;
		if(x > 100)
		{
			x = 0;
		}
		*/
		
		//Frame Rate-Independent movement.		
		x += 50 * deltaTime;
		if(x > 100)
			x = 0;
	}

	@Override
	public void present(float deltaTime)
	{
		gameGraphics.clear(0);
		gameGraphics.drawPixmap(awesomePic, (int)x, 0, 0, 0, awesomePic.getWidth(), awesomePic.getHeight());
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose()
	{
		awesomePic.dispose();		
	}
}