package com.krabat.androidgames.framework.impl;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.krabat.androidgames.framework.Interface.Music;

public class AndroidMusic implements Music, OnCompletionListener
{
	MediaPlayer mediaPlayer;
	boolean isPrepared = false;
	
	public AndroidMusic(AssetFileDescriptor assetFileDescriptor)
	{
		mediaPlayer = new MediaPlayer();
		
		try
		{
			mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
									  assetFileDescriptor.getStartOffset(),
									  assetFileDescriptor.getLength());
			
			mediaPlayer.prepare();
			isPrepared = true;
			mediaPlayer.setOnCompletionListener(this);
		}
		catch (Exception e) 
		{
			throw new RuntimeException("Could not load music.");
		}
	}
 
	@Override
	public void dispose()
	{
		if(mediaPlayer.isPlaying())
		{
			mediaPlayer.stop();
		}
		mediaPlayer.release();
	}
	

	@Override
	public void play()
	{
		// If the media player is already playing, do nothing.
		if(mediaPlayer.isPlaying())			
		{
			return;
		}
		
		try
		{
			synchronized (this)
			{
				if(isPrepared == false)
				{
					mediaPlayer.prepare();					
				}								
			}
		} 
		catch (IllegalStateException e)
		{
			e.printStackTrace();
			//throw new RuntimeException("Could not load ."); ?? book location 4402
		}
		catch (IOException e)
		{
			e.printStackTrace();
			//throw new RuntimeException("Could not load ."); ?? book location 4402
		}
	}

	@Override
	public void stop()
	{
		mediaPlayer.stop();
		
		synchronized (this)
		{
			isPrepared = false;
		}
	}

	@Override
	public void pause()
	{
		if(mediaPlayer.isPlaying())
		{
			mediaPlayer.pause();
		}
	}

	@Override
	public void setLooping(boolean looping)
	{
		mediaPlayer.setLooping(looping);
	}

	@Override
	public void setVolume(float volume)
	{
		mediaPlayer.setVolume(volume, volume);
	}

	@Override
	public boolean isPlaying()
	{
		return mediaPlayer.isPlaying();
	}

	@Override
	public boolean isLooping()
	{
		return mediaPlayer.isLooping();
	}
	
	@Override
	public boolean isStopped()
	{
		return isPrepared == false;
	}

	@Override
	public void onCompletion(MediaPlayer mediaPlayer)
	{
		synchronized (this)
		{
			isPrepared = false;
		}
	}	
}
