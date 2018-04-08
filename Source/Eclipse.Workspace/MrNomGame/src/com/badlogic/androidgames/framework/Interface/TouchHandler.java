package com.badlogic.androidgames.framework.Interface;

import java.util.List;

import android.view.View.OnTouchListener;

import com.badlogic.androidgames.framework.Interface.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener
{
	public boolean isTouchDown(int pointer);
	
	public int getTouchX(int pointer);
	public int getTouchY(int pointer);
	
	public List<TouchEvent> getTouchEvents();
}
