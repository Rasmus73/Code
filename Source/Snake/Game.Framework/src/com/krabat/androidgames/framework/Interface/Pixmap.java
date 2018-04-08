package com.krabat.androidgames.framework.Interface;

import com.krabat.androidgames.framework.Interface.Graphics.PixmapFormat;

public interface Pixmap 
{
	public int getWidth();
	public int getHeight();
	public PixmapFormat getFormat();
	public void dispose();

}