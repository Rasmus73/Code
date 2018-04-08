package com.krabat.androidgames.snake;

import com.krabat.androidgames.framework.Interface.Graphics;

public class Common {
	public static void playClick() {
		if (Settings.soundEnabled) {
			Assets.click.play(1);
		}
	}

	public static void playBitten() {
		if (Settings.soundEnabled) {
			Assets.bitten.play(1);
		}
	}

	public static void playEatStain() {
		if (Settings.soundEnabled) {
			Assets.eat.play(1);
		}
	}

	public static void drawText(Graphics graphics, String line, int x, int y) {
		int len = line.length();
		for (int ix = 0; ix < len; ix++) {
			char character = line.charAt(ix);
			if (character == ' ') {
				x += 20;
				continue;
			}

			int srcX;
			int srcWidth;
			if (character == '.') {
				srcX = 200;
				srcWidth = 10;
			} else {
				srcX = (character - '0') * 20;
				srcWidth = 20;
			}

			graphics.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
			x += srcWidth;
		}
	}
}
