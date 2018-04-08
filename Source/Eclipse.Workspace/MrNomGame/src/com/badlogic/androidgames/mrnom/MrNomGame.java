package com.badlogic.androidgames.mrnom;

import com.badlogic.androidgames.framework.Interface.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;

public class MrNomGame extends AndroidGame 
{
	@Override
	public Screen getStartScreen()
	{
		return new LoadingScreen(this);
	}
}