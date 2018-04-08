package com.krabat.androidgames.snake.model;

import java.util.ArrayList;
import java.util.List;

public class Snake {
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;

	public List<SnakePart> snakeParts = new ArrayList<SnakePart>();
	public int currentDirection;

	public Snake() {
		currentDirection = UP;

		snakeParts.add(new SnakePart(5, 6)); // head
		snakeParts.add(new SnakePart(5, 7)); // 1. initial part
		snakeParts.add(new SnakePart(5, 8)); // 2. initial part
	}

	public void turnCounterClockwise() {
		currentDirection++;
		if (currentDirection > RIGHT) {
			// We have completed a full revolution counterclockwise: set
			// direction to UP
			currentDirection = UP;
		}
	}

	public void turnClockwise() {
		currentDirection--;
		if (currentDirection < UP) {
			// We have completed a full revolution clockwise: set direction to
			// RIGHT
			currentDirection = RIGHT;
		}
	}

	// Add part to snake
	public void eat() {
		SnakePart end = snakeParts.get(snakeParts.size() - 1);
		snakeParts.add(new SnakePart(end.x, end.y));
	}

	public void advance() {
		// the part stored in index 0 is always the head.
		SnakePart head = snakeParts.get(0);

		int len = snakeParts.size() - 1;
		for (int ix = len; ix > 0; ix--) {
			SnakePart before = snakeParts.get(ix - 1);
			SnakePart snakePart = snakeParts.get(ix);

			snakePart.x = before.x;
			snakePart.y = before.y;
		}

		// Change direction
		switch (currentDirection) {
		case UP:
			head.y--;
			break;
		case LEFT:
			head.x--;
			break;
		case DOWN:
			head.y++;
			break;
		case RIGHT:
			head.x++;
			break;
		}

		// Change screen "side".
		if (head.x < 0) {
			head.x = 9;
		}
		if (head.x > 9) {
			head.x = 0;
		}
		if (head.y < 0) {
			head.y = 12;
		}
		if (head.y > 12) {
			head.y = 0;
		}
	}

	// Collision check
	public boolean checkBitten() {
		SnakePart head = snakeParts.get(0);

		int len = snakeParts.size() - 1;
		for (int ix = 1; ix < len; ix++) {
			SnakePart snakePart = snakeParts.get(ix);

			if (snakePart.x == head.x && snakePart.y == head.y) {
				return true;
			}
		}

		return false;
	}
}