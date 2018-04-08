package com.badlogic.androidgames.framework.Interface;

import com.badlogic.androidgames.framework.Interface.Graphics.PixmapFormat;

public interface Pixmap 
{
	public int getWidth();
	public int getHeight();
	public PixmapFormat getFormat();
	public void dispose();

}