package com.badlogic.androidgames.mrnom.model;

import java.util.Random;

public class World
{
	
	static final int WORLD_WIDTH = 10;
	static final int WORLD_HEIGHT = 13;
	static final int SCORE_INCREMENT = 10;
	static final float TICK_INITIAL = 0.5f;
	static final float TICK_DECREMENT = 0.05f;

	public Snake snake;
	public Stain stain;
	public boolean gameOver = false;
	public int score = 0;
	
	boolean worldFields[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
	Random rnd = new Random();
	float tickTime = 0f;
	static float tick = TICK_INITIAL;

	
	public World()
	{
		snake = new Snake();
		placeStain();
	}
	
	
	private void placeStain()
	{
		// Clear array
		for (int x = 0; x < WORLD_WIDTH; x++)		
		{
			for (int y = 0; y < WORLD_HEIGHT; y++)		
			{
				worldFields[x][y] = false;
			}
		}

		// Set occupied fields in array
		int len = snake.snakeParts.size();
		for (int ix = 0; ix < len; ix++)
		{
			SnakePart snakePart = snake.snakeParts.get(ix);
			worldFields[snakePart.x][snakePart.y] = true;
		}
		
		int stainX = rnd.nextInt(WORLD_WIDTH);
		int stainY = rnd.nextInt(WORLD_HEIGHT);
		
		while(worldFields[stainX][stainY])
		{
			stainX++;
			if(stainX >= WORLD_WIDTH)
			{
				// search from start of next line
				stainY++;  
				stainX = 0;
				if(stainY >= WORLD_HEIGHT)
				{
					// Search from top line
					stainY = 0;
				}
			}
		}
		
		// Add one of the 3 stains at a random location not occupied by snake. 
		stain = new Stain(stainX, stainY, rnd.nextInt(3));
	}
	
	public void update(float deltaTime)
	{
		if(gameOver)
		{
			return;
		}
		
		tickTime += deltaTime;
		
		while (tickTime > tick)
		{
			tickTime -= tick;
			snake.advance();
			
			if(snake.checkBitten())
			{
				// snake has eaten it self
				gameOver = true;
				return;
			}
		}
		
		// Check if stain is eaten
		SnakePart head = snake.snakeParts.get(0);
        if (head.x == stain.x && head.y == stain.y)
        {
            score += SCORE_INCREMENT;
            
            snake.eat();
            if (snake.snakeParts.size() == WORLD_WIDTH * WORLD_HEIGHT)
            {
            	// snake fills the world
                gameOver = true;
                return;
            }
            else
            {
                placeStain();
            }
            
            // decrease time interval every time score incremented by 10 (until max speed is reached). 
            if (score % 100 == 0 && tick - TICK_DECREMENT > 0)
            {
                tick -= TICK_DECREMENT;
            }
        }
	}
}
