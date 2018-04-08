package com.badlogic.androidgames.framework.impl;

import java.util.ArrayList;
import java.util.List;

import android.view.MotionEvent;
import android.view.View;

import com.badlogic.androidgames.framework.Interface.Input.TouchEvent;
import com.badlogic.androidgames.framework.Interface.TouchHandler;
import com.badlogic.androidgames.framework.impl.ObjectPool.PoolObjectFactory;

public class MultiTouchHandler implements TouchHandler
{
	private static final int MAX_TOUCHPOINTS = 10;
	
	boolean[] isTouched = new boolean[MAX_TOUCHPOINTS];	
	int[] pointerId = new int[MAX_TOUCHPOINTS];
	
	int[] touchX = new int[MAX_TOUCHPOINTS];
	int[] touchY = new int[MAX_TOUCHPOINTS];
	
	float scaleX;
	float scaleY;

	ObjectPool<TouchEvent> touchEventPool;
	List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
	List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
	

	public MultiTouchHandler(View view, float scaleX, float scaleY)
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
			int action = event.getAction();
			
			int touchAction = action & MotionEvent.ACTION_MASK;
			int pointerIndex = (action & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT; 
			
			int pointerCount = event.getPointerCount();
			
			for(int ix = 0; ix < MAX_TOUCHPOINTS; ix++)
			{
				// Clear pointers not used. (need to reset if they have been used before)
				if(ix >= pointerCount)
				{
					isTouched[ix] = false;
					pointerId[ix] = -1;
					continue;
				}
				
				int pointerId = event.getPointerId(ix);
				if(action != MotionEvent.ACTION_MOVE && ix != pointerIndex)
				{
					// If it is and up/down/cancel/out event, 
					// mask the id to see if we should process it for this touch point.
					continue;
				}				
						
				switch(touchAction)
				{
					case MotionEvent.ACTION_DOWN:
					case MotionEvent.ACTION_POINTER_DOWN:
						storeTouchEvent(event, ix, pointerId, TouchEvent.TOUCH_DOWN, true);
						break;
						
					case MotionEvent.ACTION_MOVE:
						storeTouchEvent(event, ix, pointerId, TouchEvent.TOUCH_DRAGGED, true);
						break;						
					
					case MotionEvent.ACTION_UP:
					case MotionEvent.ACTION_POINTER_UP:
					case MotionEvent.ACTION_CANCEL:
						storeTouchEvent(event, ix, -1, TouchEvent.TOUCH_UP, false);
						break;
				}
			}
						
			return true;
		}
	}

	private void storeTouchEvent(MotionEvent event, int ix, int pointerId, int touchEventType, boolean isTouched)
	{
		TouchEvent touchEvent = touchEventPool.newObject();
		
		touchEvent.x = touchX[ix] = (int)(event.getX(ix) * scaleX);
		touchEvent.y = touchY[ix] = (int)(event.getY(ix) * scaleY);
		
		touchEvent.type = touchEventType;
		touchEvent.pointer = pointerId;
		this.isTouched[ix] = isTouched;
		this.pointerId[ix] = pointerId;
		
		touchEventsBuffer.add(touchEvent);
	}
	

	@Override
	public boolean isTouchDown(int pointer)
	{
		synchronized (this)
		{
			int ix = getIndex(pointer);
			if(ix < 0 || ix >= MAX_TOUCHPOINTS)
			{
				return false;
			}
			else
			{
				return isTouched[ix];
			}
		}
	}

	@Override
	public int getTouchX(int pointer)
	{
		synchronized (this)
		{
			int ix = getIndex(pointer);
			if(ix < 0 || ix >= MAX_TOUCHPOINTS)
			{
				return 0;
			}
			else
			{
				return touchX[ix];
			}
		}
	}

	@Override
	public int getTouchY(int pointer)
	{
		synchronized (this)
		{
			int ix = getIndex(pointer);
			if(ix < 0 || ix >= MAX_TOUCHPOINTS)
			{
				return 0;
			}
			else
			{
				return touchY[ix];
			}
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
	
	// returns the index for a given pointerId or -1 if no index.
	private int getIndex(int pointerId)
	{
		for (int ix = 0; ix < MAX_TOUCHPOINTS; ix++)
		{
			if(this.pointerId[ix] == pointerId)
			{
				return ix;
			}			
		}
		
		return -1;
	}
}
