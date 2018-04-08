package com.badlogic.androidgames.framework.impl;

import java.util.ArrayList;
import java.util.List;

import android.view.MotionEvent;
import android.view.View;

import com.badlogic.androidgames.framework.Interface.Input.TouchEvent;
import com.badlogic.androidgames.framework.Interface.TouchHandler;
import com.badlogic.androidgames.framework.impl.ObjectPool.PoolObjectFactory;


public class SingleTouchHandler implements TouchHandler
{
	boolean isTouched;
	
	int touchX;
	int touchY;
	
	float scaleX;
	float scaleY;
	
	ObjectPool<TouchEvent> touchEventPool;
	List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
	List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
	

	public SingleTouchHandler(View view, float scaleX, float scaleY)
	{
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		
		PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>()
		{
			@Override
			public TouchEvent createObject()
			{ 
				return new TouchEvent();
			}
		};
		
		touchEventPool = new ObjectPool<TouchEvent>(factory, 100);
		
		view.setOnTouchListener(this);
	}

	
	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		synchronized (this)
		{
			TouchEvent touchEvent = touchEventPool.newObject();
			
			int touchAction = event.getAction();
			switch(touchAction)
			{
				case MotionEvent.ACTION_DOWN:
					touchEvent.type = TouchEvent.TOUCH_DOWN;
					isTouched = true;
					break;
					
				case MotionEvent.ACTION_MOVE:
					touchEvent.type = TouchEvent.TOUCH_DRAGGED;
					isTouched = true;					
					break;
					
				case MotionEvent.ACTION_CANCEL:
				case MotionEvent.ACTION_UP:
					touchEvent.type = TouchEvent.TOUCH_UP;
					isTouched = false;
					break;
			}
			
			touchEvent.x = touchX = (int)(event.getX() * scaleX);
			touchEvent.y = touchY = (int)(event.getY() * scaleY);
			touchEventsBuffer.add(touchEvent);
			
			return true;
		}
	}

	@Override
	public boolean isTouchDown(int pointer)
	{
		synchronized (this)
		{
			if(pointer == 0)
			{
				return isTouched;
			}
			else
			{
				return false;
			}
		}
	}

	@Override
	public int getTouchX(int pointer)
	{
		synchronized (this)
		{
			return touchX;
		}
	}

	@Override
	public int getTouchY(int pointer)
	{
		synchronized (this)
		{
			return touchY;
		}
	}

	@Override
	public List<TouchEvent> getTouchEvents()
	{
		synchronized (this)
		{
			int len = touchEvents.size();
			for(int ix = 0; ix < len; ix++)
			{
				touchEventPool.freeObject(touchEvents.get(ix));
			}
			
			touchEvents.clear();
			touchEvents.addAll(touchEventsBuffer);
			touchEventsBuffer.clear();
			return touchEvents;
		}		
	}
}


