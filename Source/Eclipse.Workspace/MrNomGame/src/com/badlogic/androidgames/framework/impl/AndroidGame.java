package com.badlogic.androidgames.framework.impl;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.badlogic.androidgames.framework.Interface.Audio;
import com.badlogic.androidgames.framework.Interface.FileIO;
import com.badlogic.androidgames.framework.Interface.Game;
import com.badlogic.androidgames.framework.Interface.Graphics;
import com.badlogic.androidgames.framework.Interface.Input;
import com.badlogic.androidgames.framework.Interface.Screen;

public class AndroidGame extends Activity implements Game
{
	AndroidFastRenderView renderView;
	Graphics graphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;
	WakeLock wakeLock;

	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
						     WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		
		int frameBufferWidth = isLandscape ? 480 : 320;
		int frameBufferHeight = isLandscape ? 320 : 480;
		
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Config.RGB_565);
		
		Display defaultDisplay = getWindowManager().getDefaultDisplay();	
		float scaleX = (float) frameBufferWidth / defaultDisplay.getWidth();
		float scaleY = (float) frameBufferHeight / defaultDisplay.getHeight();		
		// Use instaed of deprecated getWidth/getHeight
		/*int sdk = android.os.Build.VERSION.SDK_INT;
          if (sdk < android.os.Build.VERSION.RELEASE) {
              Display display = getWindowManager().getDefaultDisplay();
              int width = display.getWidth();

          } else {
              Point size = new Point();
              display.getSize(size);
          }*/
		
		renderView = new AndroidFastRenderView(this, frameBuffer);
		graphics = new AndroidGraphics(getAssets(), frameBuffer);
		fileIO = new AndroidFileIO(this);
		audio = new AndroidAudio(this);
		input = new AndroidInput(this, renderView, scaleX, scaleY);
		screen = getStartScreen();
		setContentView(renderView);
		
		PowerManager powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
	}
		
	@Override
	public void onPause()
	{
		super.onPause();
		wakeLock.release();		
		renderView.pause();
		screen.pause();
		
		if(isFinishing())
		{
			screen.dispose();
		}
	}

	@Override
	public void onResume()
	{
		super.onResume();
		wakeLock.acquire();
		screen.resume();
		renderView.resume();
	}

	
	@Override
	public Input getInput()
	{
		return input;
	}

	@Override
	public FileIO getFileIO()
	{
		return fileIO;
	}

	@Override
	public Graphics getGraphics()
	{
		return graphics;
	}

	@Override
	public Audio getAudio()
	{
		return audio;
	}

	@Override
	public void setScreen(Screen screen)
	{
		if(screen == null)
		{
			throw new IllegalArgumentException("Screen must not be null");
		}
		
		this.screen.pause();
		this.screen.dispose();		
		screen.resume();
		screen.update(0);		
		this.screen = screen;
	}

	@Override
	public Screen getCurrentScreen()
	{ 
		return screen;
	}

	@Override
	public Screen getStartScreen()
	{
		return null;
	}
}
