package com.example.com.krabat.simpleapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void onMyButtonClick(View view)
	{
		
		
	    Intent intent = new Intent(this, HeroListActivity.class);
//	    EditText editText = (EditText) findViewById(R.id.edit_message);
//	    String message = editText.getText().toString();
//	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	    
	    /*
		ServiceProxy serviceProxy = new ServiceProxy("http://eu.battle.net");
		
		
//		String address = "http://eu.battle.net/api/d3/profile/krabat-2146/";		
		String text;
//		try
//		{
			//serviceProxy.AsyncServiceCall(address);
			serviceProxy.GetCareerProfile("krabat", 2146, this);
			text = "Call successfull";
//		}
//		catch (IOException e)
//		{
//			// TODO: handle exception
//			text = e.getMessage();
//		}		
				
		setTextMyLabel(text);

	    // Do something in response to button
	     */
	}
	
	private void setTextMyLabel(String text)
	{		
		TextView tv1 = (TextView)findViewById(R.id.myLabel);
		tv1.setText(text);
	}
/*
	@Override
	public void onDownloadCompleted(Diablo3APIRequestEnum requestEnum, IDiablo3APIType diablo3APIType)
	{
		//Log.i(this.getClass().getName(), "json downloaded:" + NEWLINE + json);
		// TODO Auto-generated method stub
		
		if(diablo3APIType instanceof Diablo3APIError)
		{
			Diablo3APIError error = (Diablo3APIError)diablo3APIType;
			setTextMyLabel(error.errorMessage);
		}
		else if(diablo3APIType instanceof CareerProfile)
		{
			CareerProfile careerProfile = (CareerProfile)diablo3APIType;
			//setTextMyLabel(careerProfile.name);
		}
		
	    Intent intent = new Intent(this, HeroListActivity.class);
//	    EditText editText = (EditText) findViewById(R.id.edit_message);
//	    String message = editText.getText().toString();
//	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
/*		
		switch(requestEnum)
		{
			case CareerProfile:
				// Call CareerProfile activity
				CareerProfile careerProfile = (CareerProfile)diablo3APIType;
				setTextMyLabel(careerProfile.name);
				break;

			case Hero:
				// Call Hero activity				
				break;
				
			case Item:
				break;
				
			default:
				break;
		}
*/		
//		setTextMyLabel(json);
//	}

}
