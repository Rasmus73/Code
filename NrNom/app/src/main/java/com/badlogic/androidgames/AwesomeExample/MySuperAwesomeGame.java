package com.badlogic.androidgames.AwesomeExample;

import com.badlogic.androidgames.framework.Screen;

//public class MySuperAwesomeGame extends AndroidGame
public class MySuperAwesomeGame extends AndroidGame
{
	public Screen getStartScreen()
	{
		return new MySuperAwesomeStartScreen(this);
	}	
}