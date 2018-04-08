package com.badlogic.androidgames.framework.impl;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import com.badlogic.androidgames.framework.Interface.Graphics;
import com.badlogic.androidgames.framework.Interface.Pixmap;

public class AndroidGraphics implements Graphics 
{
	AssetManager assets;
	Bitmap frameBuffer;
	Canvas canvas;
	Paint paint;
	Rect srcRect = new Rect(); 
	Rect dstRect = new Rect();

	public AndroidGraphics(AssetManager assets, Bitmap frameBuffer)
	{
		this.assets = assets;
		this.frameBuffer = frameBuffer;
		this.canvas = new Canvas(frameBuffer);
		this.paint = new Paint();
	}
	
	@Override
	public Pixmap newPixmap(String filename, PixmapFormat format)
	{
		// Set decode options.
		Config config = GetConfig(format);
		Options options = new Options();
		options.inPreferredConfig = config;

		// load asset to bitmap
		InputStream inputStream = null;
		Bitmap bitmap = null;
		try
		{
			inputStream = assets.open(filename);
			bitmap = BitmapFactory.decodeStream(inputStream);
			if(bitmap == null)
			{
				throw new RuntimeException("Could not load bitmap from asset '" + filename + " (bitmap is null)'");
			}
		}
		catch (IOException e) 
		{
			throw new RuntimeException("Could not load bitmap from asset '" + filename + "' (error while reading file)");
		}
		finally
		{
			if(inputStream != null)
			{
				try
				{
					inputStream.close();
				}
				catch (IOException e)
				{
					// Do nothing.
				}
			}
		}
		
		// Set format from bitmap. (to get actual read format) 
		format = GetPixmapFormat(bitmap.getConfig());
		
		return new AndroidPixmap(bitmap, format);
	}
	
	private Config GetConfig(PixmapFormat format)
	{
		if(format == PixmapFormat.RGB565)
		{
			return Config.RGB_565;
		}
		
		if(format == PixmapFormat.ARGB4444)
		{
			return Config.ARGB_4444;
		}
		
		return Config.ARGB_8888;
	}
	
	private PixmapFormat GetPixmapFormat(Config config)
	{
		if(config == Config.RGB_565)
		{
			return PixmapFormat.RGB565;
		}
		
		if(config == Config.ARGB_4444)
		{
			return PixmapFormat.ARGB4444;
		}
		
		return PixmapFormat.ARGB8888;
	}	

	@Override
	public void clear(int color)
	{
		canvas.drawRGB(Red(color), Green(color), Blue(color));
	}
	
	private int Red(int color)
	{
		return (color & 0xff0000) >> 16;
	}
	private int Green(int color)
	{
		return (color & 0x00ff00) >> 8;
	}
	private int Blue(int color)
	{
		return (color & 0x0000ff);
	}
	
	@Override
	public void drawPixel(int x, int y, int color)
	{
		paint.setColor(color);
		canvas.drawPoint(x, y, paint);
	}

	@Override
	public void drawLine(int x, int y, int x2, int y2, int color)
	{
		paint.setColor(color);
		canvas.drawLine(x, y, x2, y2, paint);
	}

	@Override
	public void drawRect(int x, int y, int width, int height, int color)
	{
		paint.setColor(color);
		paint.setStyle(Style.FILL);
		
		int x2 = x + width - 1;
		int y2 = y + height - 1;
		
		canvas.drawRect(x, y, x2, y2, paint);
	}

	@Override
	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight)
	{
		int srcX2 = srcX + srcWidth - 1; 
		int srcY2 = srcY + srcHeight - 1;		
		srcRect.left = srcX;
		srcRect.top = srcY;
		srcRect.right = srcX2;
		srcRect.bottom = srcY2;
		
		int x2 = x + srcWidth - 1; 
		int y2 = y + srcHeight - 1;		
		dstRect.left = x;
		dstRect.top = y;
		dstRect.right = x2;
		dstRect.bottom = y2;
		
		Bitmap bitmap = ((AndroidPixmap)pixmap).bitmap;
		
		canvas.drawBitmap(bitmap, srcRect, dstRect, null);
	}

	@Override
	public void drawPixmap(Pixmap pixmap, int x, int y)
	{
		Bitmap bitmap = ((AndroidPixmap)pixmap).bitmap;
		canvas.drawBitmap(bitmap, x, y, null);		
	}

	@Override
	public int getWidth()
	{
		return frameBuffer.getWidth();
	}

	@Override
	public int getHeight()
	{
		return frameBuffer.getHeight();
	}
}
