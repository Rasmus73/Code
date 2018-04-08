/**
 * 
 */
package com.badlogic.androidgames.framework.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.preference.PreferenceManager;

import com.badlogic.androidgames.framework.Interface.FileIO;

/**
 * @author Rasmus Bangsgaard
 * Krabat
 *
 */

public class AndroidFileIO implements FileIO
{
	Context context;
	AssetManager assetManager;
	String externalStoragePath;
	
	public AndroidFileIO(Context context)
	{
		this.context = context;
		this.assetManager = context.getAssets(); 
		this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	}
	
	public SharedPreferences getPreferences()
	{
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		return sharedPreferences;
	}
	
	@Override
	public InputStream readAsset(String filename) throws IOException
	{
		InputStream assetStream = assetManager.open(filename);
		return assetStream;
	}

	@Override
	public InputStream readFile(String filename) throws IOException
	{
		FileInputStream fileInputStream = new FileInputStream(externalStoragePath + filename);
		return fileInputStream;
	}

	@Override
	public OutputStream writeFile(String filename) throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream(externalStoragePath + filename);
		return fileOutputStream;
	}
}
