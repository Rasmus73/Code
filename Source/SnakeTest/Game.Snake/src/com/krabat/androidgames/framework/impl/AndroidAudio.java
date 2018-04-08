package com.krabat.androidgames.framework.impl;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.krabat.androidgames.framework.Interface.Audio;
import com.krabat.androidgames.framework.Interface.Music;
import com.krabat.androidgames.framework.Interface.Sound;

public class AndroidAudio implements Audio {

	AssetManager assetManager;
	SoundPool soundPool;
	
	public AndroidAudio(Activity activity)
	{
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assetManager = activity.getAssets();
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	}
	
	@Override
	public Music newMusic(String filename)
	{
		try
		{
			AssetFileDescriptor assetFileDescriptor = assetManager.openFd(filename);
			Music androidMusic = new AndroidMusic(assetFileDescriptor);
			return androidMusic;
		}
		catch (IOException e)
		{
			String errorString = String.format("Could not load music '%0'", filename);
			throw new RuntimeException(errorString);
		}
	}

	@Override
	public Sound newSound(String filename)
	{
		try
		{
			AssetFileDescriptor assetFileDescriptor = assetManager.openFd(filename);
			int soundId = soundPool.load(assetFileDescriptor, 0);
			Sound androidSound = new AndroidSound(soundPool, soundId);
			return androidSound;
		}
		catch (IOException e)
		{
			String errorString = String.format("Could not load sound '%0'", filename);
			throw new RuntimeException(errorString);
		}
	}
}
