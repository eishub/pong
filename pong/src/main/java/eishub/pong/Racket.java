package eishub.pong;

/**
 * Pong in JAVA
 * Base source: http://codereview.stackexchange.com/questions/27197/pong-game-in-java
 * Modified for example eis project.
 */

import java.awt.Graphics;
import java.awt.Rectangle;

public class Racket {
	private static final int WIDTH = 10;
	private static final int HEIGHT = 60;
	private final Pong game;
	private final int xlocation;
	private int ylocation;
	private int ya;

	/**
	 * Racket constructor.
	 *
	 * @param game      the game instance
	 * @param xlocation the required x coordinate
	 */
	public Racket(final Pong game, final int xlocation) {
		this.game = game;
		this.xlocation = xlocation;
		this.ylocation = game.getHeight() / 2 - HEIGHT / 2;
	}

	/**
	 * Update Tick.
	 */
	public void update() {
		if (this.ylocation > 0 && this.ylocation < this.game.getHeight() - HEIGHT - 29) {
			this.ylocation += this.ya;
		} else if (this.ylocation <= 0) {
			this.ylocation++;
		} else if (this.ylocation >= this.game.getHeight() - HEIGHT - 29) {
			this.ylocation--;
		}
	}

	/**
	 * Move player 1 unit up.
	 */
	public void moveUp() {
		this.ya = -2;
	}

	/**
	 * Move player 1 unit down.
	 */
	public void moveDown() {
		this.ya = 2;
	}

	/**
	 * Stop player movement.
	 */
	public void moveStop() {
		this.ya = 0;
	}

	/**
	 * This returns the vertical position of the player.
	 *
	 * @return the y location of the player.
	 */
	public int getPosition() {
		return this.ylocation;
	}

	/**
	 * Get Racket bounds.
	 *
	 * @return the bounds of the racket
	 */
	public Rectangle getBounds() {
		return new Rectangle(this.xlocation, this.ylocation, WIDTH, HEIGHT);
	}

	/**
	 * Paint Racket.
	 *
	 * @param graphics the Graphics class
	 */
	public void paint(final Graphics graphics) {
		graphics.fillRect(this.xlocation, this.ylocation, WIDTH, HEIGHT);
	}
}