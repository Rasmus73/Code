package com.krabat.androidgames.snake;

import com.krabat.androidgames.framework.Interface.Screen;
import com.krabat.androidgames.framework.impl.AndroidGame;

public class KrabatSnake extends AndroidGame {
	@Override
	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}
}