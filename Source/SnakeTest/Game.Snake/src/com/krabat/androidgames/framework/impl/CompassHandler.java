package com.krabat.androidgames.framework.impl;

import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class CompassHandler implements SensorEventListener
{
	float yaw;
	float pitch;
	float roll;
	
	public CompassHandler(Context context)
	{
		SensorManager sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
		
		@SuppressWarnings("deprecation")
		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);		
		if(sensors.size() > 0)
		{
			Sensor compass = sensors.get(0);
			sensorManager.registerListener(this, compass, SensorManager.SENSOR_DELAY_GAME);
		}		
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		yaw = event.values[0];
		pitch = event.values[1];
		roll = event.values[2];
	}
	
	public float getYaw()
	{
		return yaw;
	}
	
	public float getPitch()
	{
		return pitch;
	}
	
	public float getRoll()
	{
		return roll;
	}	
}
