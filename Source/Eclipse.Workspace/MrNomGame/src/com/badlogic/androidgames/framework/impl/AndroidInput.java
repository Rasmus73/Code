package com.badlogic.androidgames.framework.impl;

import java.util.List;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

import com.badlogic.androidgames.framework.Interface.Input;
import com.badlogic.androidgames.framework.Interface.TouchHandler;

public class AndroidInput implements Input
{
	AccelerometerHandler accelerometerHandler;
	KeyboardHandler keyboardHandler;
	TouchHandler touchHandler;
	
	public AndroidInput (Context context, View view, float scaleX, float scaleY)
	{
		accelerometerHandler = new AccelerometerHandler(context);
		keyboardHandler = new KeyboardHandler(view);
		
		//if(VERSION.SDK_INT < 5)
		if(Integer.parseInt(VERSION.SDK) < 5)
		{
			touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
		}
		else
		{
			touchHandler = new MultiTouchHandler(view, scaleX, scaleY);
		}
	}
	

	@Override
	public boolean isKeyPressed(int keyCode)
	{
		return keyboardHandler.isKeyPressed(keyCode);
	}

	@Override
	public boolean isTouchDown(int pointer)
	{
		return touchHandler.isTouchDown(pointer);
	}

	@Override
	public int getTouchX(int pointer)
	{ 
		return touchHandler.getTouchX(pointer);
	}

	@Override
	public int getTouchY(int pointer)
	{
		return touchHandler.getTouchY(pointer);
	}

	@Override
	public float getAccelX()
	{
		return accelerometerHandler.getAccelX();
	}

	@Override
	public float getAccelY()
	{
		return accelerometerHandler.getAccelY();
	}

	@Override
	public float getAccelZ()
	{
		return accelerometerHandler.getAccelZ();	
	}

	@Override
	public List<KeyEvent> getKeyEvents()
	{
		return keyboardHandler.getKeyEvents();
	}

	@Override
	public List<TouchEvent> getTouchEvents()
	{
		return touchHandler.getTouchEvents();
	}
}
