package com.badlogic.androidgames.framework.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AndroidFastRenderView extends SurfaceView implements Runnable
{	
	AndroidGame game;
	Bitmap frameBuffer;
	Thread renderThread = null;
	SurfaceHolder surfaceholder;
	volatile boolean running = false;

	public AndroidFastRenderView(AndroidGame game, Bitmap frameBuffer)
	{
		super(game);
		this.surfaceholder = super.getHolder();
		
		this.game = game;
		this.frameBuffer = frameBuffer;
	}
		
	public void pause()
	{
		running = false;
		while(true)
		{
			try
			{
				renderThread.join();
				break;
			}
			catch (InterruptedException e) 
			{
				// Ignore exception. Retry join().
			}
		}
	}
	
	public void resume()
	{
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}

	@Override
	public void run()
	{
		Rect dstRect = new Rect();
		long startTime = System.nanoTime();
		
		while(running)
		{
			if(surfaceholder.getSurface().isValid() == false)
			{
				continue;
			}
			
			float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f; // in seconds.
			startTime = System.nanoTime();
			
			game.getCurrentScreen().update(deltaTime);
			game.getCurrentScreen().present(deltaTime);
			
			Canvas canvas = surfaceholder.lockCanvas();
			canvas.getClipBounds(dstRect);
			canvas.drawBitmap(frameBuffer, null, dstRect, null);
			surfaceholder.unlockCanvasAndPost(canvas);
		}
	}
	
	

}
