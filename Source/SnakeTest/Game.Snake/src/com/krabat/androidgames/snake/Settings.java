package com.krabat.androidgames.snake;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.krabat.androidgames.framework.Interface.FileIO;

public class Settings {
	public static boolean soundEnabled = true;
	public static int[] highscores = new int[] { 100, 80, 50, 30, 10 };

	public static void load(FileIO files) {
		BufferedReader fileReader = null;
		try {
			fileReader = new BufferedReader(new InputStreamReader(
					files.readFile(".mrnom")));
			soundEnabled = Boolean.parseBoolean(fileReader.readLine());
			for (int ix = 0; ix < 5; ix++) {
				highscores[ix] = Integer.parseInt(fileReader.readLine());
			}
		} catch (IOException e) {
			// :( It's OK we have defaults
		} catch (NumberFormatException e) {
			// :/ It's OK, defaults save our day
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
			}
		}
	}

	public static void save(FileIO files) {
		BufferedWriter fileWriter = null;
		try {
			fileWriter = new BufferedWriter(new OutputStreamWriter(
					files.writeFile(".mrnom")));
			fileWriter.write(Boolean.toString(soundEnabled));
			fileWriter.write("\n");
			for (int ix = 0; ix < 5; ix++) {
				fileWriter.write(Integer.toString(highscores[ix]));
				fileWriter.write("\n");
			}
		} catch (IOException e) {
		} finally {
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
			}
		}
	}

	public static void addScore(int score) {
		for (int ix = 0; ix < 5; ix++) {
			if (highscores[ix] < score) {
				for (int jx = 4; jx > ix; jx--) {
					highscores[jx] = highscores[jx - 1];
				}
				highscores[ix] = score;
				break;
			}
		}
	}
}
