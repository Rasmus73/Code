package com.badlogic.androidgames;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class AccelerometerTest extends Activity implements SensorEventListener
{
	TextView textView;
	StringBuilder builder = new StringBuilder();
	StringBuilder sensorNames = new StringBuilder();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        setContentView(textView);

        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() == 0)
        {
            textView.setText("No accelerometer installed");
        }
        else
        {
            Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            if (!manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME))
            {
                textView.setText("Couldn't register sensor listener");
            }
        }

        // Write out all types of sensors in device:
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
       	for (Sensor sensor : sensors)
		{
       		Log.d("SensorTest", sensor.getName());
       		sensorNames.append(sensor.getName());
       		sensorNames.append("\n");
		}
    }

	
	
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        builder.setLength(0);
    	
    	builder.append(sensorNames);

        builder.append("x: ");
        builder.append(event.values[0]);
        builder.append(", y: ");
        builder.append(event.values[1]);
        builder.append(", z: ");
        builder.append(event.values[2]);

        textView.setText(builder.toString());
    }

    
    
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
        // nothing to do here
    }
}